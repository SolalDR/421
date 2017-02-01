package com.solaldussout_revel.a421.object;

import java.util.ArrayList;

/**
 * Created by Solal on 01/02/2017.
 */

public class Combinaison {

    String lib=null;
    Float value=null;
    Boolean activeSquall=null;

    public Combinaison(String libTmp, Float valueTmp, Boolean activeSquallTmp){
        this.setActiveSquall(activeSquallTmp);
        this.setValue(valueTmp);
        this.setLib(libTmp);
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
