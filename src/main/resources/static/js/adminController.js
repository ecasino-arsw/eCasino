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
                console.log(response.data);
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
                console.log(response.data);
                alert('Lobby is create');
            })
            .catch(function (error) {
                alert("Error, No se pudo crear lobby");
            })

}


function loadPanelAdmin() {
    loadUsers();
    loadLobbies()

}

function loadTables() {
    axios.get('/tables/')
            .then(function (response) {
                document.getElementById("usernameP").innerHTML = response.data['username'];
                document.getElementById("fullNameP").innerHTML = response.data['fullName'];
                document.getElementById("emailP").innerHTML = response.data['email'];
                document.getElementById("idP").innerHTML = response.data['id'];
                document.getElementById("moneyP").innerHTML = response.data['money'];

            })
            .catch(function (error) {
                alert("Error, No se pudo cargar usuario");
            })
}
function loadLobbies() {
    axios.get('/lobbies/')
            .then(function (response) {
                var table = $("#showLobbies");
                var lobbies = response.data;
                console.log(lobbies.length);
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
    axios.get('/lobbies/')
            .then(function (response) {
                var table = $("#showUsers");
                var users = response.data;
                console.log(users.length);
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

function loadUsers() {
    axios.get('/players/')
            .then(function (response) {
                var table = $("#showUsers");
                var users = response.data;
                console.log(users.length);
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
                alert("User delete");

            })

}