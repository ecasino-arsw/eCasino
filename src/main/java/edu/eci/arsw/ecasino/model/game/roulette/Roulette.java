package edu.eci.arsw.ecasino.model.game.roulette;

import edu.eci.arsw.ecasino.model.game.interf.IGame;
import java.util.Random;
import java.util.Random;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
/*public class Roulette {

    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}*/



public class Roulette {
    static final int CUADRADOS = 38;
    static final int SALDO_INICIAL = 10000;
    static final int SALDO_MINIMO = -10000;
    public long tableID ;
    
    int [] numerosRuleta;
    boolean [] coloresRuleta;
    
    public Roulette(){
        
        int numero = 0;
        int posicion = 0;
        boolean color = false;
        numerosRuleta[0] = 0;
        
        //Rellena el vector con los numeros del tapete
        for(posicion = 1; posicion < CUADRADOS; posicion++){
            numerosRuleta[posicion] = numero;
            numero++;
        }
       
        //Distribuir los colores de la ruleta altenandolos segun el tapete
        color = false;
        for(posicion = 0; posicion <= 12; posicion++){
            coloresRuleta[posicion] = color;
            color = !color;
        }
        for(posicion = 12; posicion <= 20; posicion++){
            coloresRuleta[posicion] = color;
            color = !color;
        }
        for(posicion = 20; posicion <= 29; posicion++){
            coloresRuleta[posicion] = color;
            color = !color;
        }
        for(posicion = 29; posicion < CUADRADOS; posicion++){
            coloresRuleta[posicion] = color;
        }
    }

    public int giraRuleta (){
        
        int posicion;
        Random random = new Random();
        //Calcular una posicion aleatoria de la ruleta
        posicion = random.nextInt(CUADRADOS);
        return posicion;
    }

    public long getTableID() {
        return tableID;
    }

    public void setTableID(long tableID) {
        this.tableID = tableID;
    }
    
    
    
}
