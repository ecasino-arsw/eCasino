/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = (function () {

    class Point {
        constructor(x, y) {
            this.x = x;
            this.y = y;
        }
    }

    var stompClient = null;
    var connected = null;
    var channel;

    var addPointToCanvas = function (point) {
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.beginPath();
        ctx.arc(point.x, point.y, 3, 0, 2 * Math.PI);
        ctx.stroke();
    };
    
    var addPolygonToCanvas = function polygon(polyPoints) {
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.beginPath();
        ctx.moveTo(polyPoints[0].x, polyPoints[0].y);
        for (var i = 1; i < polyPoints.length; i++) {
            ctx.lineTo(polyPoints[i].x, polyPoints[i].y);
        }
        var colors = ["Black", "Orange", "Red", "Blue", "Yellow", "Green", "Gray"]
        ctx.closePath();
        ctx.stroke();
        ctx.fillStyle = colors[ Math.floor((Math.random() * 6) + 1)];
        ctx.fill();
}


    var getMousePosition = function (evt) {
        canvas = document.getElementById("canvas");
        var rect = canvas.getBoundingClientRect();
        return {
            x: evt.clientX - rect.left,
            y: evt.clientY - rect.top
        };
    };


    var connectAndSubscribe = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newpoint.' + channel, function (eventbody) {
                //alert("Punto x: " + JSON.parse(eventbody.body).x + " Punto y: " + JSON.parse(eventbody.body).y);
                console.log("Connection: " + channel);

                var pointX = JSON.parse(eventbody.body).x;
                var pointY = JSON.parse(eventbody.body).y;

                var pointToCanvas = new Point(pointX, pointY);

                addPointToCanvas(pointToCanvas);

            });
            stompClient.subscribe('/topic/newpolygon.' + channel, function (eventbody) {
                console.log("Draw polygon");

                addPolygonToCanvas(JSON.parse(eventbody.body));
            });

        });


    };



    return {

        init: function () {

            //var can = document.getElementById("canvas");
            connected = false;
            //websocket connection parte1
            //connectAndSubscribe();

            //parte2

            //can.addEventListener("click", canvasClick = function canvasClick(evt) {
            //var pointX = getMousePosition(evt).x;
            //var pointY = getMousePosition(evt).y;
            //alert("Punto x: " + pointX + " Punto y: " + pointY);
            //stompClient.send("/topic/newpoint.", {}, JSON.stringify({x: pointX, y: pointY}));
            //});

            //connectAndSubscribe();

            //can.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
        },

        connect: function () {
            if (!(localStorage.toString() === "")) {
                if (!connected) {
                    channel = idConnect;
                    alert("Se conecto al canal" + channel);
                    var can = document.getElementById("canvas");

                    can.addEventListener("click", canvasClick = function canvasClick(evt) {
                        var pointX = getMousePosition(evt).x;
                        var pointY = getMousePosition(evt).y;

                        stompClient.send("/app/newpoint." + channel, {}, JSON.stringify({x: pointX, y: pointY}));
                    });

                    connectAndSubscribe();

                    connected = true;

                    can.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);

                    document.getElementById("id").disabled = true;
                } else {
                    alert("Actualmente esta en en canal " + channel);
                }
            } else {
                alert("Debe digitar un canal para conectarse");
            }

        },

        publishPoint: function (px, py) {
            //parte1
            var pt = new Point(px, py);
            console.info("publishing point at " + pt);
            addPointToCanvas(pt);

            //publicar el evento
            stompClient.send("/topic/newpoint", {}, JSON.stringify(pt));
        },

        disconnect: function () {
            if (connected) {
                stompClient.disconnect();
                stompClient.unsubscribe(channel);

                stompClient = null;
                alert("Desconexion Exitosa");
                connected = false;
                console.log("Disconnected");

                document.getElementById("id").disabled = false;
                document.getElementById("canvas").removeEventListener("click", canvasClick);
            } else {
                alert("No esta conectado a ningun canal");
            }
        }
    };

})();