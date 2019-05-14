/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var optCountry;
var optGame;

function country(country) {
    optCountry = country;
}

function game(game) {
    optGame = game;
}


function createLobby() {

    axios.post('/lobbies/', {

        nameGame: optGame



    })
            .then(function (response) {
                var text = ["Success", "Lobby is create"];
                callAlert(text);
            })
            .catch(function (error) {
                var text = ["Error", "Could not create the lobby"]
                callAlert(text);
            })

}

function createTable() {


    axios.post('/lobbies/' + document.getElementById('lobbyId').value + '/tables/', {

        lobbyId: document.getElementById('lobbyId').value,
        name: document.getElementById('nameTable').value,
        stakes: document.getElementById('stakes').value



    })
            .then(function (response) {
                var text = ["Success", "Table is create"];
                callAlert(text);
            })
            .catch(function (error) {
                var text = ["Error", "Could not create the table"]
                callAlert(text);
            })

}


function loadPanelAdmin() {
    loadUsers();
    loadLobbies();
    loadTables();

}


function loadLobbies() {
    axios.get('/lobbies/')
            .then(function (response) {
                var table = $("#showLobbies");
                var lobbies = response.data;
                for (var i = 0; i < lobbies.length; i++) {
                    var selectLobby = lobbies[i];
                    table.append('<tr><td scope="row">' + selectLobby['id'] + "</td><td>" + selectLobby['nameGame'] + "</td>")

                }

            })
            .catch(function (error) {
                var text = ["Error", "Could not load the lobbies"]
                callAlert(text);
            })
}

function loadTables() {
    axios.get('/lobbies/tables/')
            .then(function (response) {
                var table = $("#showTables");
                var tables = response.data;
                console.log(tables.length);
                for (var i = 0; i < tables.length; i++) {
                    var selectTable = tables[i];
                    console.log(selectTable);
                    var onclick = 'onclick="deleteTable(' + selectTable['id'] + ',' + selectTable['lobbyId'] + ')"';
                    table.append('<tr><td scope="row">' + selectTable['id'] + "</td><td>" + selectTable['lobbyId'] + "</td><td>" + selectTable['name'] + "</td>"+ selectTable['currentPlayers'] +"<td>" + "</td><td>" + "$" + selectTable['stakes'] + '</td><td><button class="btn btn-info" name="button" ' + onclick + '>Delete</button></td>')

                }

            })
            .catch(function (error) {
                var text = ["Error", "Could not load the tables"]
                callAlert(text);
            })

}

function loadUsers() {
    axios.get('/players/')
            .then(function (response) {
                var table = $("#showUsers");
                var users = response.data;
                for (var i = 0; i < users.length; i++) {
                    var selectUser = users[i];
                    var onclick = 'onclick="deleteUser(' + selectUser['id'] + ')"';
                    table.append('<tr><td scope="row">' + selectUser['id'] + "</td><td>" + selectUser['username'] + "</td><td>" + selectUser['fullName'] + "</td><td>" + selectUser['email'] + "</td><td>" + "$" + selectUser['money'] + '</td><td><button class="btn btn-info" name="button" ' + onclick + '>Delete</button></td>')

                }

            })
            .catch(function (error) {
                var text = ["Error", "users could not be loaded"]
                callAlert(text);
            })

}


function deleteUser(id) {
    alert(id);
    axios.delete('/players/' + id)
            .then(function (response) {
                var text = ["Success", "User Delete"];
                callAlert(text);
            })

}

function deleteTable(id, lobbyId) {
    alert(id+"lobby"+lobbyId);
    axios.delete('/lobbies/' + lobbyId + '/tables/' + id)
            .then(function (response) {
                console(response);
                var text = ["Success", "Table Delete"];
                callAlert(text);
            })
            .catch(function (error) {
                var text = ["Error", "users could not be loaded by: "+error]
                callAlert(text);
            })

}

function callAlert(text) {
    alertify.alert(text[0], text[1]).set('label', 'OK');
}