/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function loadLobbie() {
    axios.get('/lobbies')
            .then(function (response) {
                var tableBlack = $("#tablesBlackJack");
                var tableHoldEm = $("#tablesHoldEm");
                var tableRoulette = $("#tablesRoulette");
                var cont = 1;
                var contBlack = 1;
                var contHoldEm = 1;
                var contRoul = 0;
                var lobbies = response.data

                for (var i = 0; i < lobbies.length; i++) {
                    contRoul += lobbies[i]['numOfTables'];
                    console.log(lobbies[i]['name']);
                }

                for (var i = 0; i < lobbies.length; i++) {
                    var table = lobbies[i]['tables'];
                    var x = 1;
                    for (var c = 0; c < lobbies[i]['numOfTables']; c++) {


                        var find = false;
                        while (contRoul >= x && !find) {
                            if (table[x] === undefined) {
                                x++;
                            } else {
                                
                                if (lobbies[i]['name'] === "BlackJack") {
                                    cont = contBlack;
                                    
                                    tableBlack.append('<tr><td scope="row">' + cont + "</td><td>" + table[x]['nameTable'] + "</td><td>" + lobbies[i]['name'] + "</td><td>" + table[x]['players'] + "/6" + "</td><td>" + "$" + 0 + " USD" + '</td><td><button class="btn btn-info" name="button" ' + onclick + '>Details</button></td>')
                                    contBlack++;
                                }
                                if (lobbies[i]['name'] === "HoldEm") {
                                    cont = contHoldEm;
                                    tableHoldEm.append('<tr><td scope="row">' + cont + "</td><td>" + table[x]['nameTable'] + "</td><td>" + lobbies[i]['name'] + "</td><td>" + table[x]['players'] + "/6" + "</td><td>" + "$" + 0 + " USD" + '</td><td><button class="btn btn-info" name="button">Details</button></td>')
                                    contHoldEm++;
                                }
                                find = true;
                                x += 1;
                            }

                        }

                    }


                }

            })
            .catch(function (error) {
                alert("Error, lobies no load");
            })

}

function loadBalance() {
    axios.get('/players/' + localStorage.getItem('Actual'))
            .then(function (response) {
                localStorage.setItem('balanceActual',response.data['money']);
                document.getElementById("moneyL").innerHTML = response.data['money'];

            })
            .catch(function (error) {
                alert("Error, No se pudo cargar usuario");
            })

}

function loadPanel(){
    loadBalance();
    loadLobbies()
}

apiecasino = (function () {

    var lobby = [];
    var tables = [];
    tables = [{"name": "Bogota"}, {"name": "Orlando"}, {"name": "Paris"}];

    lobby["Bogota"] = [{"name": "Bogota", "tables": [{"game": {"name": "BlackJack", "persons": "2", "stakes": 5.00}},
                {"game": {"name": "HoldEm", "persons": "5", "stakes": 50.00}},
                {"game": {"name": "Roulette", "persons": "8", "stakes": 10.00}}]}];

    lobby["Orlando"] = [{"name": "Orlando", "tables": [{"game": {"name": "BlackJack", "persons": "5", "stakes": 1.00}},
                {"game": {"name": "HoldEm", "persons": "2", "stakes": 10.00}}]}];

    lobby["Paris"] = [{"name": "Paris", "tables": [{"game": {"name": "BlackJack", "persons": "3", "stakes": 5.00}},
                {"game": {"name": "Roulette", "persons": "14", "stakes": 3.00}}]}];


    return {
        getAllTables: function (callback) {
            console.log([tables, lobby]);
            callback(
                    [tables, lobby]
                    );

        }
    }

})();


