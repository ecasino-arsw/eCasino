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
        if ('admin' === document.getElementById("passIn").value && 'admin' === document.getElementById("usernameIn").value) {
            location.href = "paneladmin.html";
        } else {
            axios.get('/players/' + document.getElementById("usernameIn").value)
                    .then(function (response) {
                        if (response.data['password'] === document.getElementById("passIn").value) {
                            iniciarLocalStorageUser(document.getElementById("usernameIn").value);
                            location.href = "lobby.html";

                        } else {
                            alerta = ' Incorrect username or password.';
                            document.getElementById("alertDiv").innerHTML += divAlerta(alerta);
                        }

                    })
                    .catch(function (error) {
                        alerta = ' Incorrect username or password.';
                        document.getElementById("alertDiv").innerHTML += divAlerta(alerta);

                    })
        }

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
                    var text = ["Success","Registered User"];
                    var web = "login.html";
                    callAlert(text, web ); 
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


function callAlert(text, web){
    if(web!==null){
        alertify.alert(text[0],text[1]).set('label', 'OK');
        location.href = web;
    } else {
        alertify.alert(text[0],text[1]).set('label', 'OK');
    }
    
}