/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function loadBalance() {
    axios.get('/players/' + localStorage.getItem('Actual'))
            .then(function (response) {
                localStorage.setItem('currentBalance', response.data['money']);
                document.getElementById("moneyL").innerHTML = response.data['money'];

            })
            .catch(function (error) {
                alert("Error, balance not loaded");
            })

}

function loadPanel() {
    saveLobbies();
    loadTables();
    loadBalance();
    
}


function loadTables() {
    axios.get('/lobbies/tables')
            .then(function (response) {
                var tableBlack = $("#tablesBlackJack");
                var tableHoldEm = $("#tablesHoldEm");
                var tableRoulette = $("#tablesRoulette");
                var cont = 1;
                var contBlack = 1;
                var contHoldEm = 1;
                var contRoulette = 1;
                var tables = response.data
                for (var i = 0; i < tables.length; i++) {
                    var selectTable = tables[i]
                    var nameGame = 'lobby' + selectTable['lobbyId'].toString();
                    var lobby = localStorage.getItem(nameGame);
                    if (lobby === "blackJack") {
                        cont = contBlack;
                        var onclick = 'onclick="joinGame(' + selectTable['id'] + ','+ selectTable['lobbyId']+ ')"';
                        tableBlack.append('<tr><td scope="row">' + cont + "</td><td>" + selectTable['name'] + "</td><td>" + lobby + "</td><td>/6" + "</td><td>" + "$" + selectTable['stakes'] + " USD" + '</td><td><button class="btn btn-info" name="button" ' + onclick + ' >Join</button></td>')
                        contBlack++;
                    }
                    if (lobby === "rulette") {
                        cont = contRoulette;
                        var onclick = 'onclick="joinGame(' + selectTable['id'] + ','+ selectTable['lobbyId']+ ')"';
                        tableRoulette.append('<tr><td scope="row">' + cont + "</td><td>" + selectTable['name'] + "</td><td>" + lobby + "</td><td>/6" + "</td><td>" + "$" + selectTable['stakes'] + " USD" + '</td><td><button class="btn btn-info" name="button" ' + onclick + ' >Join</button></td>')
                        contRoulette++;
                    }
                    if (lobby === "holdEm") {
                        cont = contHoldEm;
                        var onclick = 'onclick="joinGame(' + selectTable['id'] + ','+ selectTable['lobbyId']+ ')"';
                        tableHoldEm.append('<tr><td scope="row">' + cont + "</td><td>" + selectTable['name'] + "</td><td>" + lobby + "</td><td>/6" + "</td><td>" + "$" + selectTable['stakes'] + " USD" + '</td><td><button class="btn btn-info" name="button" ' + onclick + '>Join</button></td>')
                        contHoldEm++;
                    }
                }
            })
            .catch(function (error) {
                alert("Error, lobbies not loaded");
            })

}

function saveLobbies() {
    axios.get('/lobbies/')
            .then(function (response) {
                var lobbies = response.data;
                for (var i = 0; i < lobbies.length; i++) {
                    var key = 'lobby' + lobbies[i]['id'];

                    localStorage.setItem(key, lobbies[i]['nameGame']);
                }

            })
            .catch(function (error) {
                alert("Error, lobbies not save");
            })

}

function joinGame(id,lobbyId){
    axios.get('/lobbies/'+lobbyId+'/tables/'+ id)
            .then(function (response) {
                var table = response.data;
                var nameGame = 'lobby' + table['lobbyId'].toString();
                var lobby = localStorage.getItem(nameGame);
                alert("Esta apunto de ir a la mesa: "+table['name']+" de "+lobby);
                localStorage.setItem('currentTable',table['id']);
                localStorage.setItem('currentLobby',lobby);
                localStorage.setItem('currentNameTable',table['name']);
                
                location.href = "game.html";
                

            })
            .catch(function (error) {
                alert("Error, could not join");
            })
    
}
