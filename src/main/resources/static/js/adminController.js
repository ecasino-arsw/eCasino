/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function createLobby() {
    axios.post('/lobbies/', {
        id: parseInt(document.getElementById("idL").value),
        nameGame: document.getElementById("nameL").value
        

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