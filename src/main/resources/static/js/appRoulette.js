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
        var user = JSON.stringify({'username': username})
        stompClient.send("/app/newgame" + channel + '/user', {}, user);
    });
    isConnect = true;
    event.preventDefault();
}

function timeRoulette() {

    if (isConnect) {
        stompClient.send("/app/newgame" + channel + '/time', {}, null);

    }

}


function disconnectGame() {
     stompClient.send("/app/newgame/"+channel+"/disconnect",
            {},
            JSON.stringify({sender: username})
            )
}

function play() {
    console.log(timeTurn);
    if (timeTurn === 1) {
        if (localStorage.getItem('listNumbers') === null || localStorage.getItem('listNumbers') === undefined) {
            var lisNumbers = "null";
            var lisTimes = times;
        } else {
            var lisNumbers = localStorage.getItem('listNumbers');
            var lisTimes = localStorage.getItem('listTimes');
        }
        ;

        var moneyU = localStorage.getItem('currentBalance');

        console.log(lisNumbers);
        console.log(lisTimes);
        var data = JSON.stringify({'username': username, 'channel': channel, 'selectNumbers': lisNumbers, 'timesNumbers': lisTimes, 'money': moneyU});
        stompClient.send("/app/newgame" + channel + '/content', {}, data);
        var height = document.getElementById('listaPastGames').scrollHeight;
        $("#listaPastGames").scrollTop(height);
        
    } else {
        alert("first connect.");
    }

}

function showGreeting(message) {
    console.log(message);
    var trans = 20 - message[8];
    if (message[9] === 2) {
        if (trans === 0) {
            alertify.success("Your time is over, number already fell.");
            play();
        } else if (trans < 10) {
            alertify.success("you have <b>" + trans + "</b> seconds to place your bet");


        }
        timeTurn = trans;

    } else if (message[9] === 1) {
        alertify.success("<b>" + message[4] + '</b> has connected');
        alertify.success("You have <b>" + trans + "</b> seconds to bet");

        timeRoulette();
        //play();


    } else {
        var numberWinner = message[0];
        var color = message[2];
        var dozen = message[3];
        var opt = message[5];
        var money = message[6];
        var user = message[7];
        var d = new Date();
        var h = addZero(d.getHours());
        var m = addZero(d.getMinutes());
        var s = addZero(d.getSeconds());
        if (numberWinner === 37) {
            numberWinner = "00";
        }
        ;

        clearTablero();
        document.getElementById('number' + message[0]).innerHTML = "<button style='border:1px solid black;' class='btn btn-info'><b>" + numberWinner + "</b></button>";
        var content = "<tr><td>Username: <b>" + user + "</b></td>" +
                "<td><td>Winner Number: <b>" + numberWinner + "</b></td>" +
                "<td>Winner Color: <b><font color='" + color + "'>" + color + "</font></b></td>" +
                "<td>Winner Dozen: <b>" + dozen + "</b></td>" +
                "<td><b>" + opt + "</b></td>" +
                "<td>Money: <b>" + money + "</b></td>" +
                "<td><b>" + h + ":" + m + ":" + s;
        +"</b></td>" +
                "</tr>";
        $("#userinfo").append(content);
    }
    //alertify.success("you have <b>"+message[8]+"</b> seconds to place your bet");
    //timeTurn = message[8]

    //window.setTimeout("timeRoulette()", timeTurn);


}
function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function clearTablero() {
    //times = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    reload();
    localStorage.setItem('listNumbers', "null");
    localStorage.setItem('listTimes', times);
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

function callAlert(text) {
    alertify.alert(text[0], text[1]).set('label', 'OK');
}

