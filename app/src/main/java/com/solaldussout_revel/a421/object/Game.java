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
        this.setCurrentTour(1);
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
                new Combinaison("Schlass", (float) 0, false),
                new Combinaison("111", (float) 6, true),
                new Combinaison("Iniakin", (float) 6, true),
                new Combinaison("Sphinx", (float) 5, true),
                new Combinaison("Eddy Malou", (float) 1.5, true),
                new Combinaison("Nenette", (float) 0, false),
                new Combinaison("KT1", (float) 1, false),
                new Combinaison("Koala", (float) -1, false),
                new Combinaison("MQR", (float) 6, true),
                new Combinaison("Guinguette", (float) 6, true),
                new Combinaison("Colombette", (float) 4, true),
                new Combinaison("Punk", (float) 3, true),
                new Combinaison("Molotov", (float) 6, true)
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
                if(last.getActiveSquall()){
                    setCoSquall(getCoSquall()-1);
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
