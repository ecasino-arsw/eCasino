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
                alert('Lobby is create');
            })
            .catch(function (error) {
                alert("Error, No se pudo crear lobby");
            })

}

function createTable() {
    

    axios.post('/lobbies/'+ document.getElementById('lobbyId').value + '/tables/', {

        lobbyId: document.getElementById('lobbyId').value,
        name: document.getElementById('nameTable').value,
        stakes: document.getElementById('stakes').value



    })
            .then(function (response) {
                alert('Lobby is create');
            })
            .catch(function (error) {
                alert("Error, No se pudo crear lobby");
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
                alert("Error, No se pudo cargar usuario");
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
                    var onclick = 'onclick="deleteTable(' + selectTable['id'] + ','+ selectTable['lobbyId']+ ')"';
                    table.append('<tr><td scope="row">' + selectTable['id'] + "</td><td>" + selectTable['lobbyId'] + "</td><td>" + selectTable['name'] + "</td><td>" + "</td><td>" + "$" + selectTable['stakes'] + '</td><td><button class="btn btn-info" name="button" ' + onclick + '>Delete</button></td>')

                }

            })
            .catch(function (error) {
                alert("Error, No se pudo cargar las mesas");
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
                alert("Error, No se pudo cargar usuario");
            })

}


function deleteUser(id) {
    alert(id);
    axios.delete('/players/' + id)
            .then(function (response) {
                alert("User Delete");

            })

}

function deleteTable(id, lobbyId) {
    alert(id);
    axios.delete('/lobbies/'+ lobbyId +'/tables/' + id)
            .then(function (response) {
                alert("Table Delete");

            })

}