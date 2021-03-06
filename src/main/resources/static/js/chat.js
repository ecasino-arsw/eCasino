/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var actualizado = false;

function loadConnect() {

    connect();


}
function disconnectAll(){
    if (actualizado) {
        disconnected()

        location.href = "lobby.html";
    }
}


function exitTable() {


    if (username === localStorage.getItem('Actual')) {
        exitPlayerInTable();

    }
    
}

function exitPlayerInTable() {
    
    axios.get('/lobbies/' + localStorage.getItem('currentLobbyId') + '/tables/' + localStorage.getItem('currentTable'))
            .then(function (response) {
                var table = response.data;
               
                var currentPlayers = table['currentPlayers'];
                localStorage.setItem('playersInTable', currentPlayers);
                var capacity = parseInt(localStorage.getItem('capacityTable'));
                var players = parseInt(localStorage.getItem('playersInTable'));
                if (0 < players) {
                    var players = players - 1;

                    axios.put('/lobbies/' + localStorage.getItem('currentLobbyId') + '/tables', {
                        id: localStorage.getItem('currentTable'),
                        lobbyId: localStorage.getItem('currentLobbyId'),
                        name: localStorage.getItem('currentNameTable'),
                        stakes: localStorage.getItem('currentStakes'),
                        capacity: capacity,
                        currentPlayers: players
                    })
                            .then(function (response) {
                                localStorage.setItem('playersInTable', players);
                                
                                actualizado = true;
                                disconnectAll();

                            })
                            .catch(function (error) {
                                alert("Error, not exit player in table");
                            })
                }


            })
            .catch(function (error) {
                alert("Error, not update players in table");
            })

}

'use strict';
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var stompClient = null;
var username = null;
var channel = null;
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];
function connect(event) {
    if (localStorage.getItem('connect') === "false" || localStorage.getItem('connect') === null) {
        username = localStorage.getItem('Actual');
        channel = localStorage.getItem('currentTable');
        if (username) {
            usernamePage.classList.add('hidden');
            chatPage.classList.remove('hidden');
            var socket = new SockJS('/stompendpoint');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
        }
        event.preventDefault();
    } else {
        console.log("ya esta conectado");
    }

}
function onConnected() {
// Subscribe to the Public Topic
    stompClient.subscribe('/topic/public' + channel, onMessageReceived);
    // Tell your username to the server
    stompClient.send("/app/chat.addUser" + channel,
            {},
            JSON.stringify({sender: username, type: 'JOIN'})
            )
    connectingElement.classList.add('hidden');
}
function disconnected() {
// Tell your username to the server
    stompClient.send("/app/chat.addUser" + channel,
            {},
            JSON.stringify({sender: username, type: 'LEAVE'})
            )
    connectingElement.classList.add('hidden');
}
function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}
function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/topic/public" + channel, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');
    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
        alertify.error("<b>" + message.sender + '</b> has disconnected');
        
        localStorage.setItem('connect', 'false');
    } else {
        messageElement.classList.add('chat-message');
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);
        messageElement.appendChild(avatarElement);
        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
        
    }
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}
usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
