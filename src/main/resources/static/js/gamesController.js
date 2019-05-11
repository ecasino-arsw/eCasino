/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadGame() {
    document.getElementById("moneyG").innerHTML = localStorage.getItem('currentBalance');
    document.getElementById("tableIdG").innerHTML = localStorage.getItem('currentTable');
    document.getElementById("usernameG").innerHTML = localStorage.getItem('Actual');
    document.getElementById("nameGameG").innerHTML = localStorage.getItem('currentLobby');
    document.getElementById("nameTableG").innerHTML = localStorage.getItem('currentNameTable');
}



function loadTablero() {
    if (localStorage.getItem('currentLobby') === 'rulette') {
        loadTableroRoulette();
    }
    if (localStorage.getItem('currentLobby') === 'blackJack') {
        loadTableroBlackJack();
    }
    if (localStorage.getItem('currentLobby') === 'holdEm') {
        loadTableroHoldEm();
    }
}





function loadTableroRoulette() {
    var tablero = $("#showGame");
    tablero.append("<tr><td><button class=btn btn-info>0</button></td><td><button class=btn btn-info>3</button></td><td><button class=btn btn-info>6</button></td></tr>");
    tablero.append("<tr><td><button class=btn btn-info>00</button></td><td><button class=btn btn-info>2</button></td><td><button class=btn btn-info>5</button></td></tr>");

}


