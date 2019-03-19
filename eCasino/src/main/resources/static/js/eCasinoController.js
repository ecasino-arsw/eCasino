/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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


tables = (function () {
    var search = function (param) {
        var tableBlack = $("#tablesBlackJack");
        var tableHoldEm = $("#tablesHoldEm");
        var tableRoulette = $("#tablesRoulette");
        var cont = 1;
        var contBlack = 1;
        var contHoldEm = 1;
        var contRoul = 1;

        var lobby = param[1];
        var nameTable, rowCount, tableSelect;
        for (var j = 0; j < param[0].length; j++) {
            nameTable = param[0][j].name;
            rowCount = lobby[nameTable][0].tables.length;
            tableSelect = lobby[nameTable][0];
            for (var i = 0; i < rowCount; i++) {
                if (tableSelect.tables[i].game.name === "BlackJack") {
                    cont = contBlack;
                    tableBlack.append('<tr><td scope="row">' + cont + "</td><td>" + tableSelect.name + "</td><td>" + tableSelect.tables[i].game.name + "</td><td>" + tableSelect.tables[i].game.persons + "/6" + "</td><td>" + "$" + tableSelect.tables[i].game.stakes + " USD" + '</td><td><button class="btn btn-info" name="button" onclick="details.detailsByTable($('+tableSelect.tables[i].game.name.toString() +').val())">Details</button></td>')
                    contBlack++;
                }
                if (tableSelect.tables[i].game.name === "HoldEm") {
                    cont = contHoldEm;
                    tableHoldEm.append('<tr><td scope="row">' + cont + "</td><td>" + tableSelect.name + "</td><td>" + tableSelect.tables[i].game.name + "</td><td>" + tableSelect.tables[i].game.persons + "/9" + "</td><td>" + "$" + tableSelect.tables[i].game.stakes + " USD" + "</td>")
                    contHoldEm++;
                }
                if (tableSelect.tables[i].game.name === "Roulette") {
                    cont = contRoul;
                    tableRoulette.append('<tr><td scope="row">' + cont + "</td><td>" + tableSelect.name + "</td><td>" + tableSelect.tables[i].game.name + "</td><td>" + tableSelect.tables[i].game.persons + "/15" + "</td><td>" + "$" + tableSelect.tables[i].game.stakes + " USD" + "</td>")
                    contRoul++;
                }

            }
        }
    }

    return{
        getTables: function () {
            return apiecasino.getAllTables(search);
        }
    }

})();


details = (function () {
    var search = function (param) {
        document.getElementById("detailsTable").innerHTML = "Hello <b>world</b>!";
        
    }

    return{
        detailsByTable: function (game) {
            search(game);
            console.log("hol"+game);
            return game;
        }
    }

})();