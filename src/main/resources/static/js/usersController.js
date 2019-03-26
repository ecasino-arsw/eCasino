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
    if (document.getElementById("usernameIn").value==='') {
        alert("Enter your username");
    }
    if(document.getElementById("passIn").value==='') {
        alert("Enter your password");
    } else {
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
                    alert("Este usuario no existe");
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
                document.getElementById("usernameP").innerHTML = localStorage.getItem('Actual');
                
            })
            .catch(function (error) {
                alert("Error, No se pudo cargar usuario");
            })

}