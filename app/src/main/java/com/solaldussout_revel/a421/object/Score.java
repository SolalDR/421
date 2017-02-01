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

    public Score(String lib, float valueBase, int selfSquall, int coSquall){
        this.setSelfSquall(selfSquall);
        this.setCoSquall(coSquall);
        this.setValueBase(valueBase);
        this.setLib(lib);
        this.calcTotal();
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


    public void calcTotal(){
        Float b = this.getValueBase();
        Float s = Float.valueOf(this.getSelfSquall());
        Float c = Float.valueOf(this.getCoSquall());

        Float sResult = s-3 > 0 ? s-3 : 0;
        Float cResult = c-3 > 0 ? c-3 : 0;

        Float total = b + sResult + cResult;

        this.setTotal(total);
    }

}
