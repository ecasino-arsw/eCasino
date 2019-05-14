/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var listNumbers = [];
var times = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
var coin = 1;
var playGame = false;

var reload = function () {
    listNumbers = [];
    times = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

    playGame = false;
    loadGame();
}
function loadGame() {
    document.getElementById("moneyG").innerHTML = localStorage.getItem('currentBalance');
    document.getElementById("tableIdG").innerHTML = localStorage.getItem('currentTable');
    document.getElementById("usernameG").innerHTML = localStorage.getItem('Actual');
    document.getElementById("nameGameG").innerHTML = localStorage.getItem('currentLobby');
    document.getElementById("nameTableG").innerHTML = localStorage.getItem('currentNameTable');
    loadTablero();
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
    document.getElementById("showGame").innerHTML = "<table></table>";
    var tablero = $("#showGame");
    var cont = 1;
    var fila1 = "<tr></tr>";
    var fila2 = "<tr></tr>";
    var fila3 = "<tr></tr>";

    fila2 += ("<td ><button id='number0' style='border:1px solid black;' class='btn btn-success' onclick='numberSelect(0)'>0 <span class='badge'>" + times[0] + "</span> </button></td>");
    fila3 += ("<td  ><button id='number37' style='border:1px solid black;' class='btn btn-success' onclick='numberSelect(37)'>00 <span class='badge'>" + times[37] + "</span></button></td>");
    fila1 += ("<td></td>");

    var listRed = [1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36];
    var color = '';

    for (var i = 1; i < 37; i++) {
        var onclick = "onclick='numberSelect(" + i + ")'";
        if (listRed.includes(i) === true) {
            color = 'btn btn-danger';
        } else {
            color = 'btn btn';
        }
        if (cont === 1) {
            fila1 += "<td  ><button id='number" + i + "' style='border:1px solid black;' class='" + color + "' onclick='numberSelect(" + i + ")'>" + i + " <span class='badge'>" + times[i] + "</span> </button></td>";
            cont += 1;
        } else if (cont === 2) {
            fila2 += "<td ><button id='number" + i + "' style='border:1px solid black;' class='" + color + "' onclick='numberSelect(" + i + ")'>" + i + " <span class='badge'>" + times[i] + "</span> </button></td>";
            cont += 1;
        } else {
            fila3 += "<td ><button id='number" + i + "' style='border:1px solid black;' class='" + color + "' onclick='numberSelect(" + i + ")'>" + i + " <span class='badge'>" + times[i] + "</span> </button></td>";
            cont = 1;
        }
    }
    tablero.append(fila3);
    tablero.append(fila2);
    tablero.append(fila1);

    selectValueCoin(coin);

}

function numberSelect(number) {

    var pos = listNumbers.indexOf(number);
    if (coin === 0 && times[number] !== 0) {
        times[number] = 0;

        listNumbers.splice(pos, 1);
    }
    if (coin !== 0) {
        if (listNumbers.includes(number) === true) {
            times[number] += coin;
        } else {
            listNumbers.push(number);
            times[number] += coin;
        }
    }
    localStorage.setItem('listNumbers', listNumbers)
    localStorage.setItem('listTimes', times)

    loadTablero();
}

function selectValueCoin(value) {
    if (coin !== value || !playGame) {
        playGame = true;
        document.getElementById("coins").innerHTML = "<div></div>";
        var divCoins = $("#coins");
        var select = "class='btn btn-info'";
        var noSelect = 'class="btn btn-outline-warning"';
        var width = 'style="width: 58px"';
        var width2 = 'style="width: 45px"';

        if (value === 1) {
            coin = 1;
            divCoins.append('<a ' + select + '><img ' + width + ' src="img/coin100.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(2)"  ' + noSelect + '><img ' + width2 + ' src="img/coin200.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(5)"  ' + noSelect + '><img ' + width2 + ' src="img/coin500.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(10)"  ' + noSelect + '><img ' + width2 + ' src="img/coin1000.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(0)"  ' + noSelect + '><img ' + width2 + ' src="img/coinDelete.png"/></a>');
        } else if (value === 2) {
            coin = 2;
            divCoins.append('<a onclick="selectValueCoin(1)"  ' + noSelect + '><img ' + width2 + ' src="img/coin100.png"/></a>');
            divCoins.append('<a ' + select + '><img ' + width + ' src="img/coin200.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(5)"  ' + noSelect + '><img ' + width2 + ' src="img/coin500.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(10)"  ' + noSelect + '><img ' + width2 + ' src="img/coin1000.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(0)"  ' + noSelect + '><img ' + width2 + ' src="img/coinDelete.png"/></a>');
        } else if (value === 5) {
            coin = 5;
            divCoins.append('<a onclick="selectValueCoin(1)"  ' + noSelect + '><img ' + width2 + ' src="img/coin100.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(2)"  ' + noSelect + '><img ' + width2 + ' src="img/coin200.png"/></a>');
            divCoins.append('<a  ' + select + '><img ' + width + ' src="img/coin500.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(10)"  ' + noSelect + '><img ' + width2 + ' src="img/coin1000.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(0)"  ' + noSelect + '><img ' + width2 + ' src="img/coinDelete.png"/></a>');
        } else if (value === 10) {
            coin = 10;
            divCoins.append('<a onclick="selectValueCoin(1)"  ' + noSelect + '><img ' + width2 + ' src="img/coin100.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(2)"  ' + noSelect + '><img ' + width2 + ' src="img/coin200.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(5)"  ' + noSelect + '><img ' + width2 + ' src="img/coin500.png"/></a>');
            divCoins.append('<a  ' + select + '><img ' + width + ' src="img/coin1000.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(0)"  ' + noSelect + '><img ' + width2 + ' src="img/coinDelete.png"/></a>');
        } else {
            coin = 0;
            divCoins.append('<a onclick="selectValueCoin(1)"  ' + noSelect + '><img ' + width2 + ' src="img/coin100.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(2)"  ' + noSelect + '><img ' + width2 + ' src="img/coin200.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(5)"  ' + noSelect + '><img ' + width2 + ' src="img/coin500.png"/></a>');
            divCoins.append('<a onclick="selectValueCoin(10)"  ' + noSelect + '><img ' + width2 + ' src="img/coin1000.png"/></a>');
            divCoins.append('<a  ' + select + '><img ' + width + ' src="img/coinDelete.png"/></a>');
        }

    }

}

function loadTableroHoldEm() {
    axios.get('/lobbies/' + localStorage.getItem('currentLobbyId') + '/tables/' + localStorage.getItem('currentTable'))
            .then(function (response) {
                var table = response.data;
                document.getElementById("showGame").innerHTML = "<table><tr></tr></table>";
                var tablero = $("#showGame");
                var limitPlayers = table['capacity'];
                var currentPlayers = table['currentPlayers'];
                console.log(currentPlayers + "/" + limitPlayers);
                for (var i = 0; i < parseInt(limitPlayers); i++) {
                    
                    if (parseInt(currentPlayers) > i) {
                        //var ancho = (($("#bodyPrincipal").width()/parseInt(currentPlayers))/parseInt(currentPlayers)/parseInt(currentPlayers));
                        tablero.append( "<tr id='player" + i + "'><td style='padding: 30px;' >"+
                            "<img src='/img/cartas/card_back.png'/>"+"<img src='/img/cartas/card_01.png'/>"+
                            "<div id='cards" + i + "'>"+
                            "<br><br>"+
                            "<button id='fold" + i + "' style='border:1px solid black;'>Fold</button>"+
                            "<button id='call" + i + "' style='border:1px solid black;'>Call</button>"+
                            "<button id='raise" + i + "' style='border:1px solid black;'>Raise To</button>"+
                            "<button id='allin" + i + "' style='border:1px solid black;'>AllIn</button>"+
                            "<br><b>Jugador<span id='player" + i + "'></span></b>"+
                            "</td></tr>");
                    }
                    
                    
                }


            })
            .catch(function (error) {
                alert("Error, could not join");
            })
    /*document.getElementById("showGame").innerHTML = "<table></table>";
     var tablero = $("#showGame");
     var cont = 1;
     var fila1 = "<tr></tr>";
     var fila2 = "<tr></tr>";
     var fila3 = "<tr></tr>";
     
     fila2 += ("<td ><button id='number0' style='border:1px solid black;' class='btn btn-success' onclick='numberSelect(0)'>0 <span class='badge'>" + times[0] + "</span> </button></td>");
     fila3 += ("<td  ><button id='number37' style='border:1px solid black;' class='btn btn-success' onclick='numberSelect(37)'>00 <span class='badge'>" + times[37] + "</span></button></td>");
     fila1 += ("<td></td>");
     
     var listRed = [1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36];
     var color = '';
     
     for (var i = 1; i < 37; i++) {
     var onclick = "onclick='numberSelect(" + i + ")'";
     if (listRed.includes(i) === true) {
     color = 'btn btn-danger';
     } else {
     color = 'btn btn';
     }
     if (cont === 1) {
     fila1 += "<td  ><button id='number" + i + "' style='border:1px solid black;' class='" + color + "' onclick='numberSelect(" + i + ")'>" + i + " <span class='badge'>" + times[i] + "</span> </button></td>";
     cont += 1;
     } else if (cont === 2) {
     fila2 += "<td ><button id='number" + i + "' style='border:1px solid black;' class='" + color + "' onclick='numberSelect(" + i + ")'>" + i + " <span class='badge'>" + times[i] + "</span> </button></td>";
     cont += 1;
     } else {
     fila3 += "<td ><button id='number" + i + "' style='border:1px solid black;' class='" + color + "' onclick='numberSelect(" + i + ")'>" + i + " <span class='badge'>" + times[i] + "</span> </button></td>";
     cont = 1;
     }
     }
     tablero.append(fila3);
     tablero.append(fila2);
     tablero.append(fila1);
     
     selectValueCoin(coin);*/

}