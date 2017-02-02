package com.solaldussout_revel.a421;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.Dialog;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.Button;
import android.content.Intent;


import com.solaldussout_revel.a421.object.Combinaison;
import com.solaldussout_revel.a421.object.Game;
import com.solaldussout_revel.a421.object.Player;

import java.util.ArrayList;


public class ScoreActivity extends MenuParentActivity {

    private final static int SPINNER_IDENTIFIANT  = 0;

    Game game;
    Player player;

    Combinaison tmpCombin;
    String tmpLibScore;
    Float tmpValueScore;
    TextView actualScoreLabel;
    TextView playerNameLabel;
    Button validScoreButton;

    SpinnerDialog mSpinnerDialog;
    ArrayList mqrOptions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        this.initToolbar();



        this.game = MainActivity.getGame();
        this.player = game.getActualPlayer();
        this.setMqrOptions();



        //Get textview
        TextView selfSquallLabel = (TextView)findViewById(R.id.selfSquallLabel);
        TextView coSquallLabel = (TextView)findViewById(R.id.coSquallLabel);
        this.playerNameLabel = (TextView)findViewById(R.id.playerNameLabel);
        this.actualScoreLabel = (TextView)findViewById(R.id.actualScoreLabel);


        //Set textview
        String selfSquallText = "AB : "+this.player.getTrueSquall().toString();
        String coSquallText = "BC : "+this.game.getTrueSquall().toString();

        selfSquallLabel.setText(selfSquallText);
        coSquallLabel.setText(coSquallText);
        playerNameLabel.setText(this.player.getName());



        //Gestison de la grille
        String[] combinaisons = this.game.getCombinaisonsLib();
        GridView grid = (GridView)findViewById(R.id.gridViewTest);
        ArrayAdapter adapater =  new ArrayAdapter(this, R.layout.button_grid, combinaisons);
        grid.setAdapter(adapater);
        grid.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapater, View view, int position, long id) {
                // Que faire quand on clique sur un élément de la liste ?

                Integer pos = position;
                tmpCombin = game.getCombinaison(pos);

                if(tmpCombin.getLib().equals("MQR")){
                    showDialog(0);
                } else {
                    updateTmpScore();
                }

                Toast toast = Toast.makeText(getApplicationContext(), "Combinaison : " + tmpCombin.getLib()+"\nValeure de base : "+tmpCombin.getValue().toString(), Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        validScoreButton = (Button) findViewById(R.id.validScoreButton);
        validScoreButton.setOnClickListener(validScoreListener);
    }

    public void updateTmpScore(){
        Float value = tmpCombin.getValue();
        String lib = tmpCombin.getLib();
        this.setTmpLibScore(lib);
        this.setTmpValueScore(value);

        Integer intValue = value.intValue();
        if(intValue.floatValue() != value){
            this.actualScoreLabel.setText(lib+" : "+value.toString());
        } else {
            this.actualScoreLabel.setText(lib+" : "+intValue.toString());
        }
    }


    public String getTmpLibScore() {
        return tmpLibScore;
    }

    public void setTmpLibScore(String tmpLibScore) {
        this.tmpLibScore = tmpLibScore;
    }

    public Float getTmpValueScore() {
        return tmpValueScore;
    }

    public void setTmpValueScore(Float tmpValueScore) {
        this.tmpValueScore = tmpValueScore;
    }

    public TextView getActualScoreLabel() {
        return actualScoreLabel;
    }

    public void setActualScoreLabel(TextView actualScoreLabel) {
        this.actualScoreLabel = actualScoreLabel;
    }


    public Dialog onCreateDialog(int identifiant) {
        switch(identifiant) {
            case SPINNER_IDENTIFIANT :
                mSpinnerDialog = new SpinnerDialog(this, this.mqrOptions, new SpinnerDialog.DialogListener() {
                    public void cancelled() {
                        // do your code here
                    }
                    public void ready(int n) {
                        Spinner spin = (Spinner) mSpinnerDialog.findViewById(R.id.dialog_spinner);
                        Float value = Float.valueOf(spin.getSelectedItem().toString());
                        tmpCombin.setValue(value);
                        updateTmpScore();
                    }
                });
                break;
        }
        return mSpinnerDialog;
    }

    View.OnClickListener validScoreListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Combinaison combin = tmpCombin;
            if(combin != null){
                if(combin.getLib() != null && combin.getValue() != null && combin.getActiveSquall() != null){
                    if(combin.getLib().equals("Schlass")){
                        game.setCoSquall(0);
                    } else {
                        game.setCoSquall(game.getCoSquall()+1);
                    }
                    player.addScore(combin.getLib(), combin.getValue(), combin.getActiveSquall(), game.getCoSquall());
                    game.nextPlayer();
                    Intent secondeActivite = new Intent(ScoreActivity.this, ScoreActivity.class);
                    startActivity(secondeActivite);
                }
            } else {
                //Alert l'utilisateur qu'il doit rentrer une combin
            }
        }
    };

    private void setMqrOptions(){
        mqrOptions = new ArrayList<>();
        mqrOptions.add("1");
        mqrOptions.add("2");
        mqrOptions.add("3");
        mqrOptions.add("4");
        mqrOptions.add("5");
        mqrOptions.add("6");
    }

}
