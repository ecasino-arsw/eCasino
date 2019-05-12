/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var username = null;
var channel = null;
var stompClient = null;
var height = 200;
var isConnect = false;




function setConnectedGame(connected) {
    $("#connectGame").prop("disabled", connected);
    $("#disconnectGame").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        document.getElementById("connectGame").innerHTML = 'IS PLAYING...';
    } else {
        $("#conversation").hide();
    }
    $("#userinfo").html("");
}

function connectGame(event) {
    username = localStorage.getItem('Actual');
    channel = localStorage.getItem('currentTable');
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnectedGame(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/newgame' + channel, function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
    isConnect = true;
    event.preventDefault();
}



function disconnectGame() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnectedGame(false);
    console.log("Disconnected");
}

function play() {
    if (isConnect) {
        var lisNumbers = localStorage.getItem('listNumbers');
        var lisTimes = localStorage.getItem('listTimes');
        console.log(lisNumbers);
        console.log(lisNumbers);
        var content = [JSON.stringify({'username': username}), JSON.stringify({'tableId': channel}), JSON.stringify({'numbers': listNumbers}), JSON.stringify({'times': times})];
        stompClient.send("/app/newgame" + channel, {}, content);
        //stompClient.send("/app/newgame" + channel, {}, JSON.stringify({'name': username}));
        var height = document.getElementById('listaPastGames').scrollHeight;
        $("#listaPastGames").scrollTop(height);
    } else {
        alert("first connect.");
    }

}

function showGreeting(message) {
    var numberWinner = message[0];
    var color = message[2];
    var dozen = message[3];
    var opt = message[3];
    var money = message[3];
    var d = new Date();
    var h = addZero(d.getHours());
    var m = addZero(d.getMinutes());
    var s = addZero(d.getSeconds());
    if (numberWinner === 37) {
        numberWinner = "00";
    }
    ;
    document.getElementById('number' + message[0]).innerHTML = "<button style='border:1px solid black;' class='btn btn-info'><b>" + numberWinner + "</b></button>";
    var content = "<tr><td>Winner Number: <b>" + numberWinner + "</b></td>" +
            "<td>Winner Color: <b><font color='" + color + "'>" + color + "</font></b></td>" +
            "<td>Winner Dozen: <b>" + dozen + "</b></td>" +
            "<td>You <b>" + opt + "</b></td>" +
            "<td>Money: <b>" + money + "</b></td>" +
            "<td><b>" + h + ":" + m + ":" + s;
    +"</b></td>" +
            "</tr>";
    $("#userinfo").append(content);


}
function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connectGame").click(function () {
        connectGame();
    });
    $("#disconnectGame").click(function () {
        disconnectGame();
    });
    $("#play").click(function () {
        play();
    });
});



