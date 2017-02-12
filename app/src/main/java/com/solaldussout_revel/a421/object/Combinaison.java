package com.solaldussout_revel.a421.object;

import java.util.ArrayList;

/**
 * Created by Solal on 01/02/2017.
 */

public class Combinaison {

    String lib=null;
    Float value=null;
    Boolean activeSquall=null;
    Boolean breakSquall=null;

    public Combinaison(String libTmp, Float valueTmp, Boolean activeSquallTmp, Boolean breakSquall){
        this.setActiveSquall(activeSquallTmp);
        this.setValue(valueTmp);
        this.setLib(libTmp);
        this.setBreakSquall(breakSquall);
    }

    public Boolean getBreakSquall() {
        return breakSquall;
    }

    public void setBreakSquall(Boolean breakSquall) {
        this.breakSquall = breakSquall;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getActiveSquall() {
        return activeSquall;
    }

    public void setActiveSquall(Boolean activeSquall) {
        this.activeSquall = activeSquall;
    }

}
