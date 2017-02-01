package com.solaldussout_revel.a421.object;

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


    public Game(){
        this.setCoSquall(0);
        this.setPaused(true);
        this.setPlayers(new Player[1]);
        this.setCurrentTour(0);
        this.setCurrentPlayer(0);
        this.setCombinaisons();
    }

    public void start(){
       isPaused = false;
        this.setCurrentPlayer(0);
    }



    //Getteurs and Setters
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
                new Combinaison("421", (float) 9, true),
                new Combinaison("Slash", (float) 0, false),
                new Combinaison("111", (float) 6, true),
                new Combinaison("Iniakin", (float) 6, true),
                new Combinaison("Sphinx", (float) 5, true),
                new Combinaison("Eddy Malou", (float) 1.5, true),
                new Combinaison("Nenette", (float) 0, false),
                new Combinaison("KT1", (float) 1, false),
                new Combinaison("Koala", (float) -1, false),
                new Combinaison("MQR", (float) 9, true),
                new Combinaison("Guinguette", (float) 9, true),
                new Combinaison("Colombette", (float) 9, true),
                new Combinaison("Punk", (float) 9, true),
                new Combinaison("Molotov", (float) 9, true)
        };


        this.combinaisonsLib = new String[this.combinaisons.length];
        System.out.println(this.combinaisonsLib.length);
        for(int i=0; i<this.combinaisons.length; i++){
            System.out.println(this.combinaisons[i].getLib());
            this.combinaisonsLib[i] = this.combinaisons[i].getLib();
        }
    }

    public Combinaison[] getCombinaisons() {
        return combinaisons;
    }

    public Combinaison getCombinaison(Integer id){
/*        for(int i=0; i<this.combinaisons.length; i++){
            if(this.combinaisons[i].getLib()==lib){
                return this.combinaisons[i];
            }
        }*/
        return this.combinaisons[id];
    }

    public String[] getCombinaisonsLib(){
        return this.combinaisonsLib;
    }

    //Functions

    public Player addPlayer(String name){
        Player[] players = this.getPlayers();
        Player[] newPlayers = new Player[players.length];
        Player newPlayer = new Player(name);


        if(players.length == 1 && players[0]==null){
            players[0] = newPlayer;
        } else {
            for(int i=0; i<players.length; i++){
                newPlayers[i] = players[i];
            }
            newPlayers[players.length-1] = newPlayer;
            this.setPlayers(newPlayers);
        }
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
        Integer cur = this.getCurrentPlayer();
        cur+=1;

        Player curPlayer = this.getPlayers()[cur];
        if(curPlayer == null){
            this.setCurrentPlayer(0);
            this.setCurrentTour(this.getCurrentTour()+1);
            return this.getPlayers()[0];
        } else {
            this.setCurrentPlayer(cur);
            return curPlayer;
        }
    }
    public Integer getTrueSquall(){
        Integer coSquallValue = this.getCoSquall();
        Integer result = coSquallValue-3>0 ? coSquallValue-3 : 0;
        return result;
    }
}
