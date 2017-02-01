package com.solaldussout_revel.a421;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.app.Dialog;
import android.widget.TextView;


import com.solaldussout_revel.a421.object.Game;
import com.solaldussout_revel.a421.object.Player;

import java.util.ArrayList;


public class ScoreActivity extends AppCompatActivity {

    private final static int SPINNER_IDENTIFIANT  = 0;

    Game game;
    Player player;
    SpinnerDialog mSpinnerDialog;
    ArrayList mqrOptions;
    Button mqrButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.setGame();
        this.setPlayer();
        this.setMqrOptions();

        setContentView(R.layout.activity_score);

        TextView selfSquallLabel = (TextView)findViewById(R.id.selfSquallLabel);
        TextView coSquallLabel = (TextView)findViewById(R.id.coSquallLabel);
        TextView actualScoreLabel = (TextView)findViewById(R.id.actualScoreLabel);
        TextView playerNameLabel = (TextView)findViewById(R.id.playerNameLabel);


        selfSquallLabel.setText("AB : "+this.player.getTrueSquall().toString());
        coSquallLabel.setText("BC : "+this.game.getTrueSquall().toString());
        playerNameLabel.setText(this.player.getName());


        mqrButton = (Button)findViewById(R.id.mqrButton);
        mqrButton.setOnClickListener(mqrButtonListener);
    }


    View.OnClickListener mqrButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showDialog(0);
        }
    };


    @Override
    public Dialog onCreateDialog(int identifiant) {
        switch(identifiant) {
            case SPINNER_IDENTIFIANT :
                mSpinnerDialog = new SpinnerDialog(this, this.mqrOptions, new SpinnerDialog.DialogListener() {
                    public void cancelled() {
                        // do your code here
                    }
                    public void ready(int n) {
                        //
                    }
                });
                break;
        }
        return mSpinnerDialog;
    }


    private void setMqrOptions(){
        mqrOptions = new ArrayList<String>();
        mqrOptions.add("1");
        mqrOptions.add("2");
        mqrOptions.add("3");
        mqrOptions.add("4");
        mqrOptions.add("5");
        mqrOptions.add("6");
    }


    private void setGame(){
        this.game = MainActivity.getGame();
    }


    public void setPlayer(){
        this.player = game.getActualPlayer();
    }

    public Player getPlayer(){
        return this.player;
    }

}
