package com.solaldussout_revel.a421.object;

/**
 * Created by Solal on 30/01/2017.
 */

public class Score {
    private String lib;
    private Float valueBase;
    private Float total;
    private Integer selfSquall;
    private Integer coSquall;
    private Boolean activeSquall;
    private Boolean isFirstTry;

    public Score(String lib, float valueBase, int selfSquall, int coSquall, boolean activeSquall, boolean firstTry){
        this.setSelfSquall(selfSquall);
        this.setCoSquall(coSquall);
        this.setValueBase(valueBase);
        this.setActiveSquall(activeSquall);
        this.setLib(lib);
        this.setFirstTry(firstTry);
        this.calcTotal();
    }

    public Boolean getFirstTry() {
        return isFirstTry;
    }

    public void setFirstTry(Boolean firstTry) {
        isFirstTry = firstTry;
    }

    public Boolean isFirstTry(){
        Boolean f = this.getFirstTry();
        if(f){
            return true;
        } else {
            return false;
        }
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public Float getValueBase() {
        return valueBase;
    }

    public void setValueBase(Float valueBase) {
        this.valueBase = valueBase;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getSelfSquall() {
        return selfSquall;
    }

    public void setSelfSquall(Integer selfSquall) {
        this.selfSquall = selfSquall;
    }

    public Integer getCoSquall() {
        return coSquall;
    }

    public void setCoSquall(Integer coSquall) {
        this.coSquall = coSquall;
    }

    public Boolean getActiveSquall() {
        return activeSquall;
    }

    public void setActiveSquall(Boolean activeSquall) {
        this.activeSquall = activeSquall;
    }


    public void calcTotal(){
        Float totalValue;
        Float b = this.getValueBase();
        Float s = Float.valueOf(this.getSelfSquall());
        Float c = Float.valueOf(this.getCoSquall());

        Float sResult = s-3 > 0 ? s-3 : 0;
        Float cResult = c-3 > 0 ? c-3 : 0;

        if(this.getActiveSquall()){
            totalValue = b + sResult + cResult;
        } else {
            totalValue = b;
        }
        if(this.getActiveSquall() && this.isFirstTry()){
            totalValue+= 1;
        }

        this.setTotal(totalValue);
    }

}
