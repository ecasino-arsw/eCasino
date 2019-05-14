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
    window.setInterval("loadTables()", 3000);

}


function loadTables() {
    axios.get('/lobbies/tables')
            .then(function (response) {
                document.getElementById("tablesBlackJack").innerHTML = '<table><tr><th scope="col">#</th><th scope="col">Name</th><th scope="col">Game</th>' +
                        '<th scope="col">Persons</th><th scope="col">Stakes</th> <th ></th></tr></table>';
                document.getElementById("tablesHoldEm").innerHTML = '<table><tr><th scope="col">#</th><th scope="col">Name</th><th scope="col">Game</th>' +
                        '<th scope="col">Persons</th><th scope="col">Stakes</th> <th ></th></tr></table>';
                document.getElementById("tablesRoulette").innerHTML = '<table><tr><th scope="col">#</th><th scope="col">Name</th><th scope="col">Game</th>' +
                        '<th scope="col">Persons</th><th scope="col">Stakes</th> <th ></th></tr></table>';
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
                        var onclick = 'onclick="joinGame(' + selectTable['id'] + ',' + selectTable['lobbyId'] + ')"';
                        tableBlack.append('<tr><td scope="row">' + cont + "</td><td>" + selectTable['name'] + "</td><td>" + lobby + "</td><td>" + selectTable['currentPlayers'] + "/" + selectTable['capacity'] + "</td><td>" + "$" + selectTable['stakes'] + " USD" + '</td><td><button class="btn btn-info" name="button" ' + onclick + ' >Join</button></td>')
                        contBlack++;
                    }
                    if (lobby === "rulette") {
                        cont = contRoulette;
                        var onclick = 'onclick="joinGame(' + selectTable['id'] + ',' + selectTable['lobbyId'] + ')"';
                        tableRoulette.append('<tr><td scope="row">' + cont + "</td><td>" + selectTable['name'] + "</td><td>" + lobby + "</td><td>" + selectTable['currentPlayers'] + "/" + selectTable['capacity'] + "</td><td>" + "$" + selectTable['stakes'] + " USD" + '</td><td><button class="btn btn-info" name="button" ' + onclick + ' >Join</button></td>')
                        contRoulette++;
                    }
                    if (lobby === "holdEm") {
                        cont = contHoldEm;
                        var onclick = 'onclick="joinGame(' + selectTable['id'] + ',' + selectTable['lobbyId'] + ')"';
                        tableHoldEm.append('<tr><td scope="row">' + cont + "</td><td>" + selectTable['name'] + "</td><td>" + lobby + "</td><td>" + selectTable['currentPlayers'] + "/" + selectTable['capacity'] + "</td><td>" + "$" + selectTable['stakes'] + " USD" + '</td><td><button class="btn btn-info" name="button" ' + onclick + '>Join</button></td>')
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

function joinGame(id, lobbyId) {
    axios.get('/lobbies/' + lobbyId + '/tables/' + id)
            .then(function (response) {
                var table = response.data;
                var currentPlayers = table['currentPlayers'];
                localStorage.setItem('playersInTable', currentPlayers);
                localStorage.setItem('capacityTable', table['capacity']);
                localStorage.setItem('currentLobbyId', lobbyId)
                localStorage.setItem('currentStakes', table['stakes'])
                var nameGame = 'lobby' + table['lobbyId'].toString();
                var lobby = localStorage.getItem(nameGame);
                var text = "You are going to enter the table: <b>" + table['name'] + "</b> of the game <b>" + lobby + "</b>";
                confirmar(text, table['id'], lobby, table['name']);
            })
            .catch(function (error) {
                alert("Error, could not join");
            })

}

function joinPlayerInTable(web) {
    var capacity = parseInt(localStorage.getItem('capacityTable'));
    var players = parseInt(localStorage.getItem('playersInTable'));

    console.log(players + "/" + capacity);
    if (players < capacity) {
        var players = parseInt(localStorage.getItem('playersInTable')) + 1;
        
        console.log(players + "/" + capacity);
        axios.put('/lobbies/' + localStorage.getItem('currentLobbyId') + '/tables', {
            id: localStorage.getItem('currentTable') ,
            lobbyId: localStorage.getItem('currentLobbyId') ,
            name: localStorage.getItem('currentNameTable') ,
            stakes: localStorage.getItem('currentStakes') ,
            capacity: capacity,
            currentPlayers: players
        })
                .then(function (response) {
                    localStorage.setItem('playersInTable', players);
                    location.href = "gameholdem.html";

                })
                .catch(function (error) {
                    alert("Error, not join in table");
                })
    }
}

function confirmar(text, currentTable, currentLobby, currentNameTable) {
    var confirm = alertify.confirm('ENTERING A ROOM', text, null, null).set('labels', {ok: 'Join', cancel: 'Cancel'});

    //callbak al pulsar botón positivo
    confirm.set('onok', function () {
        alertify.success('Has confirmado');
        localStorage.setItem('currentTable', currentTable);
        localStorage.setItem('currentLobby', currentLobby);
        localStorage.setItem('currentNameTable', currentNameTable);
        if (currentLobby === 'rulette') {
            joinPlayerInTable(location.href = "gameroulette.html");
        } else if (currentLobby === 'holdEm') {
            joinPlayerInTable(location.href = "gameholdem.html");
            //location.href = "gameholdem.html";
        } else {
            joinPlayerInTable(location.href = "gameblackjack.html");
        }

    });
    //callbak al pulsar botón negativo
    confirm.set('oncancel', function () {
        alertify.error('You cancel');
    })

}