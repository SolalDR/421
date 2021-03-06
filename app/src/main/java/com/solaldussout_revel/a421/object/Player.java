package com.solaldussout_revel.a421.object;



public class Player {
    private String name;
    private Score scores[];
    private Integer selfSquall;
    private Boolean warned;
    private Integer nbWarn;


    public Player(String tempName){
        this.setName(tempName);
        this.setNbWarn(0);
        this.setWarned(false);
        this.setSelfSquall(0);
    }

    //Getteurs and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Score[] getScores() {
        return scores;
    }

    private void setScores(Score[] scores) {
        this.scores = scores;
    }

    private Integer getSelfSquall() {
        return selfSquall;
    }

    private void setSelfSquall(Integer selfSquall) {
        this.selfSquall = selfSquall;
    }

    public Boolean getWarned() {
        return warned;
    }

    public void setWarned(Boolean warned) {
        this.warned = warned;
    }

    public Integer getNbWarn() {
        return nbWarn;
    }

    public void setNbWarn(Integer nbWarn) {
        this.nbWarn = nbWarn;
    }




    //Avertis un joueur, si renvoie vrai, ce dernier à un carton rouge.
    public boolean warn(){
        Boolean warned = this.getWarned();
        Integer nbWarned = this.getNbWarn();
        if(warned){
            this.setNbWarn(nbWarned+1);
            return true;
        } else {
            this.setWarned(true);
            return false;
        }
    }


    //Rajoute un object score
    public void addScore(String lib, float valueBase, boolean activeSquall, int coSquall, boolean firsttry){
        Score[] preScores = this.getScores();
        Score[] newScores;
        if(preScores == null){
            newScores = new Score[1];
        } else {
            newScores = new Score[preScores.length+1];
        }

        Integer selfSquall = this.manageSelfSquall(lib);
        Score newScore = new Score(lib, valueBase, selfSquall, coSquall, activeSquall, firsttry);

        if(preScores != null){
            for(int i = 0; i < preScores.length ; i++){
                newScores[i]=preScores[i];
            }
            newScores[preScores.length] = newScore;
        } else {
            newScores[0] = newScore;
        }

        this.setScores(newScores);
    }


    //Gère la réinitialisation ou l'incrémentation.
    private Integer manageSelfSquall(String lib){
        Integer cur = this.getSelfSquall();
        if(lib.equals("Schlass")){
            this.setSelfSquall(0);
        } else {
            this.setSelfSquall(cur+1);
        }
        return this.getSelfSquall();
    }



    //Supprime le dernier score d'un joueur
    public void removeLastScore(){
        Score[] preScores = this.getScores();
        Score[] newScores = new Score[preScores.length - 2];
        for(int i = 0; i < newScores.length ; i++){
            newScores[i]=preScores[i];
        }
        this.setScores(newScores);
    }


    //Calcule la somme de tous scores en prenant compte les bourasque
    public Float calcTotal(){
        Score[] scores = this.getScores();
        Float total = Float.valueOf(0);
        if(scores!=null){
            for(int i=0; i<scores.length ; i++){
                total+=scores[i].getTotal();
            }
        } else {
            total = Float.valueOf(0);
        }

        return total;
    }

    public Integer getTrueSquall(){
        Integer selfSquallValue = this.getSelfSquall();
        Integer result = selfSquallValue-3>0 ? selfSquallValue-3 : 0;
        return result;
    }

    public Score getLastScore(){
        Score[] scoresT = getScores();
        Score score;
        if(scoresT!=null){
            score = scoresT[scoresT.length-1];
        }  else {
            score =null;
        }
        return score;
    }


    public void deleteLastScore(){
        Score last = getLastScore();
        //Baisser l'AB si besoin
        if(last.getActiveSquall()){
            this.setSelfSquall(this.getSelfSquall()-1);
        }


        Score[] actuals = getScores();
        Score[] newScores;
        if(last != null){
            //Supprimer les derniers scores
            if(actuals.length>1){
                newScores = new Score[actuals.length-1];
            } else {
                newScores = null;
            }

            if(newScores != null){
                for(int i=0; i<newScores.length; i++){
                    newScores[i] = actuals[i];
                }
            }

            this.setScores(newScores);


        }
    }


}
