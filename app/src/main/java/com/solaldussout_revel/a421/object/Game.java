package com.solaldussout_revel.a421.object;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

/**
 * Created by Solal on 30/01/2017.
 */

public class Game {
    private Integer coSquall;
    private Integer currentPlayer;
    private Integer currentTour;
    private Boolean isPaused;

    private Combinaison[] combinaisons;
    private String[] combinaisonsLib;
    private Player players[];

    private Float nenetteValue;
    private Boolean koalaExist;
    private Boolean bonusFirst421;
    private Boolean nenetteBrokeSquall;


    public Game(Map pref){
        this.setPref(pref);
        this.setCoSquall(0);
        this.setPaused(true);
        this.setCurrentTour(1);
        this.setCurrentPlayer(0);
        this.setCombinaisons();
    }

    public void start(){
       isPaused = false;
        this.setCurrentPlayer(0);
    }

    public void setPref(Map pref){

        if(pref!=null){

            if(pref.get("nenetteValue") != null && (Boolean) pref.get("nenetteValue")){
                nenetteValue =  ((Integer) 2).floatValue();
            } else {
                nenetteValue =  ((Integer) 0).floatValue();
            }

            if(pref.get("nenetteSquall") != null && (Boolean) pref.get("nenetteSquall")) {
                nenetteBrokeSquall = true;
            } else {
                nenetteBrokeSquall = false;
            }

            if(pref.get("first421") != null && (Boolean) pref.get("first421")) {
                bonusFirst421 = true;
            } else {
                bonusFirst421 = false;
            }

        } else {
            nenetteValue =  ((Integer) 0).floatValue();
            nenetteBrokeSquall = false;
            koalaExist = true;
            bonusFirst421 = true;
        }

    }

    //Getteurs and Setters

    public Boolean getKoalaExist() {
        return koalaExist;
    }

    public void setKoalaExist(Boolean koalaExist) {
        this.koalaExist = koalaExist;
    }

    public Boolean getBonusFirst421() {
        return bonusFirst421;
    }

    public void setBonusFirst421(Boolean bonusFirst421) {
        this.bonusFirst421 = bonusFirst421;
    }

    public Integer getCoSquall() {
        return coSquall;
    }

    public void setCoSquall(Integer coSquall) {
        this.coSquall = coSquall;
    }

    public Integer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Integer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Boolean getPaused() {
        return isPaused;
    }

    public void setPaused(Boolean paused) {
        isPaused = paused;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Integer getCurrentTour() {
        return currentTour;
    }

    public void setCurrentTour(Integer currentTour) {
        this.currentTour = currentTour;
    }

    public void setCombinaisons(){

        this.combinaisons = new Combinaison[]{
                // Nom combinaison, valeure par défault, incrémente les bourrasque, casse les bourrasque
                new Combinaison("421", (float) 9, true, false),
                new Combinaison("Schlass", (float) 0, false, true),
                new Combinaison("111", (float) 6, true, false),
                new Combinaison("Iniakin", (float) 6, true, false),
                new Combinaison("Sphinx", (float) 5, true, false),
                new Combinaison("Eddy Malou", (float) 1.5, true, false),
                new Combinaison("Koala", (float)-1, false, false),
                new Combinaison("Nenette", nenetteValue, false, nenetteBrokeSquall),
                new Combinaison("KT1", (float) 1, false, false),
                new Combinaison("MQR", (float) 6, true, false),
                new Combinaison("Guinguette", (float) 6, true, false),
                new Combinaison("Colombette", (float) 4, true, false),
                new Combinaison("Punk", (float) 3, true, false),
                new Combinaison("Molotov", (float) 6, true, false)
        };

        this.combinaisonsLib = new String[this.combinaisons.length];
        System.out.println(this.combinaisonsLib.length);
        for(int i=0; i<this.combinaisons.length; i++){
            System.out.println(this.combinaisons[i].getLib());
            this.combinaisonsLib[i] = this.combinaisons[i].getLib();
        }
    }

    public Boolean getNenetteBrokeSquall() {
        return nenetteBrokeSquall;
    }

    public void setNenetteBrokeSquall(Boolean nenetteBrokeSquall) {
        this.nenetteBrokeSquall = nenetteBrokeSquall;
    }

    public Combinaison[] getCombinaisons() {
        return combinaisons;
    }


    public Combinaison getCombinaison(Integer id){
        return this.combinaisons[id];
    }

    public String[] getCombinaisonsLib(){
        return this.combinaisonsLib;
    }

    //Functions


    public void addScore(Combinaison combin, Boolean isFirstTry){
        coSquall = this.getCoSquall();
        Player player = this.getActualPlayer();
        Float valueBase = combin.getValue();

        if(combin.getBreakSquall()){
            this.setCoSquall(0);
        } else {
            this.setCoSquall(this.getCoSquall()+1);
        }

        if(combin.getLib().equals("421") && this.getBonusFirst421()){
            this.setBonusFirst421(false);
            valueBase+=4;
        }

        player.addScore(combin.getLib(), valueBase, combin.getActiveSquall(), this.getCoSquall(), isFirstTry);
    }

    public Player addPlayer(String name){
        Player[] players = this.getPlayers();
        Player[] newPlayers;
        Player newPlayer = new Player(name);

        if(players != null){
            newPlayers = new Player[players.length+1];
        } else {
            newPlayers = new Player[1];
        }

        if(players == null){
            newPlayers[0] = newPlayer;
        } else {
            for(int i=0; i<players.length; i++){
                newPlayers[i] = players[i];
            }
            newPlayers[players.length] = newPlayer;
        }

        this.setPlayers(newPlayers);
        return newPlayer;
    }

    public Player getActualPlayer(){
        Integer cur = this.getCurrentPlayer();
        Player[] players = this.getPlayers();
        Player player = players[cur];
        return player;
    }

    //Passe au joueurs suivant et le renvoi
    public Player nextPlayer(){
        Integer next = this.getCurrentPlayer()+1;
        Player[] players = this.getPlayers();

        if(players.length == next){
            this.setCurrentPlayer(0);
            this.setCurrentTour(this.getCurrentTour()+1);
            return players[0];
        } else {
            this.setCurrentPlayer(next);
            return players[next];
        }
    }

    public void previousShot(){
        Integer next = getCurrentPlayer()-1;

        Player[] players = getPlayers();
        if(players != null){
            if(next == -1 ){
                next = players.length-1;
                if(getCurrentTour() > 1){
                    setCurrentTour(getCurrentTour()-1);
                }
            }
            Player p = players[next];
            Score last = p.getLastScore();
            if(last != null){
                if(last.getActiveSquall()) {
                    setCoSquall(getCoSquall() - 1);
                }
                p.deleteLastScore();
                setCurrentPlayer(next);
            }
        }
    }

    public Integer getTrueSquall(){
        Integer coSquallValue = this.getCoSquall();
        Integer trueSquall = coSquallValue-3>0 ? coSquallValue-3 : 0;
        return trueSquall;
    }

    public Player getPlayerFromName(String name){
        Player p = null;
        for(int i=0; i<this.players.length; i++){
            if(this.players[i].getName().equals(name)){
                p = this.players[i];
            }
        }
        return p;
    }
}
