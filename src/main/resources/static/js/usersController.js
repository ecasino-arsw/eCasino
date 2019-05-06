/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @param {cedulaActual} cedula del usuraio actual 
 * @returns {undefined}
 */
function iniciarLocalStorageUser(username) {

    localStorage.setItem('Actual', username);
    //localStorage.removeItem('key');
    //localStorage.clear();
}


function cerrarLocalStorageUsuario() {
    //localStorage.removeItem('key');
    localStorage.clear();
}

/**
 * 
 * @returns {undefined}
 */
function iniciarSesion() {
    var nullAlert = false;
    document.getElementById("alertDiv").innerHTML = "";
    var alerta;
    if (document.getElementById("usernameIn").value === '') {
        nullAlert = true;
        alerta = ' Enter your username.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);
    }
    if (document.getElementById("passIn").value === '') {
        nullAlert = true;
        alerta = ' Enter your password.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);
    } 
    if (!nullAlert) {
        axios.get('/players/' + document.getElementById("usernameIn").value)
                .then(function (response) {
                    iniciarLocalStorageUser(document.getElementById("usernameIn").value);
                    location.href = "lobby.html";
                    /*if (response.data["contraseñaUsuario"] === document.getElementById("inContraseña").value) {
                     iniciarLocalStorageUsuario(response.data["cedulaUsuario"])
                     location.href = "panelUsuario.html";
                     } else {
                     alert("Contraseña incorrecta");
                     }*/
                })
                .catch(function (error) {
                    alerta = ' This Users not found.';
                    document.getElementById("alertDiv").innerHTML += divAlerta(alerta);

                })
    }

}
function divAlerta(alerta) {
    return '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
            '<strong>Error!</strong>' + alerta +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
            '<span aria-hidden="true">&times;</span>' +
            '</button>' +
            '</div>';
}

function registarse() {
    var nullAlert = false;
    document.getElementById("alertDiv").innerHTML = "";
    var alerta;
    if (document.getElementById("usernameUp").value === '') {
        nullAlert = true;
        alerta = ' Enter your username.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);
    }
    if (document.getElementById("fullNameUp").value === '') {
        nullAlert = true;
        alerta = ' Enter your Full Name.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);

    }
    if (document.getElementById("emailUp").value === '') {
        nullAlert = true;
        alerta = ' Enter your Email.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);

    }
    if (document.getElementById("passUp").value === '') {
        nullAlert = true;
        alerta = ' Enter your Password.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);
    }

    if (document.getElementById("passUp2").value === '') {
        nullAlert = true;
        alerta = ' Please, Repeat de Password.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);

    }
    if (document.getElementById("passUp2").value !== document.getElementById("passUp").value) {
        nullAlert = true;
        alerta = ' The Passwords not similars.';
        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);

    }

    if (!nullAlert) {
        axios.post('/players/', {
                username: document.getElementById("usernameUp").value,
                password: document.getElementById("passUp").value,
                fullName: document.getElementById("fullNameUp").value,
                email: document.getElementById("emailUp").value
            
        })
                .then(function (response) {
                    console.log(response.data);
                    alert('Registered User')
                    location.href = "login.html";
                })
    }

}


function cerrarSesion() {
    cerrarLocalStorageUsuario()
    location.href = "index.html";
}



function loadProfile() {
    axios.get('/players/' + localStorage.getItem('Actual'))
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