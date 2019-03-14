/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.model.cards;

import java.util.ArrayList;

/**
 *
 * @author villate
 */
public class Hand {
    ArrayList<Card> cards = new ArrayList<Card>();
    int position=0;
    
    
    /**
     * 
     * @param c carta para agregarla a la baraja
     */
    public void addCard(Card c){
        cards.add(c);
    }
    
    public int getValue(){
        
        int total=0;
        boolean ace = false;
        
        /*for (int i=0; i < cards.size(); i++){
            Card sCard = cards.get(i);
            int cardValue = sCard.getValue();
            if (cardValue>10) cardValue=10;
            if (cardValue==1) ace=true;
            
            total+=cardValue;
                      
        }
        if(ace && total+10<=21) total +=10;*/
        
        return total;
    }
    
    /**
     * 
     * @return la mano en string
     */
    public String toString(){
        String hand = "";
        for(int i =0; i < cards.size(); i++){
            hand += cards.get(i).toString() + " ";
        }
        return hand;
    }
}
