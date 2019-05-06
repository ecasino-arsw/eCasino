# eCasino
The project was developed by Daniel Cifuentes, Juan Villate and Daniel Vela for the ARSW class in 2019-1.

This project consists of the implementation of an online Casino, in which you can play Roulette, BlackJack and Texas Hold'Em Poker (the implementation of more games is a WIP). The service requires the player to have an account registered.

The system works in lobbies designated for each game, this lobbies host a number of tables which can vary in the initial payout and the minimum bet.

To play, the player has the ability to join any table that hosts the game that he/she wants to play at any moment, given that he has enough money to pay the initial payout required to play. After the player leaves the table, the money earned (or lost) is registered in the respective account.

The application works based on a RESTful API, this API is connected to a PostgreSQL database. Both the database and the application are hosted by Heroku. Games are played thorugh WebSockets to minimize API calls and deter possible malfunctions or slowness.

## Future features

- Deposit or withdrawal of money through different payment methods like PayPal, a credit/debit card, etc.
- Exchange of currency for non-cash prizes.
- Implementation of more games.
- Implementation of chat in the table.
- A social system in which players can make friends and have private chats and give gifts to each other.
- Players can host private tables to play with friends.

## Installation instructions
For the project to work you'll need Java 8 JDK and Maven.

After cloning this repository in your preferred installation folder, and from the root folder of the project, you have to execute the commands:

    mvn clean compile
    mvm spring-boot:run
