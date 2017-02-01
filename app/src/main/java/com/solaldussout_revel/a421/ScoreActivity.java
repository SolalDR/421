package com.solaldussout_revel.a421;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.Dialog;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;


import com.solaldussout_revel.a421.object.Combinaison;
import com.solaldussout_revel.a421.object.Game;
import com.solaldussout_revel.a421.object.Player;

import java.util.ArrayList;


public class ScoreActivity extends AppCompatActivity {

    private final static int SPINNER_IDENTIFIANT  = 0;

    Game game;
    Player player;
    SpinnerDialog mSpinnerDialog;
    ArrayList mqrOptions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.game = MainActivity.getGame();
        this.player = game.getActualPlayer();
        this.setMqrOptions();

        setContentView(R.layout.activity_score);

        //Define tour info
        TextView selfSquallLabel = (TextView)findViewById(R.id.selfSquallLabel);
        TextView coSquallLabel = (TextView)findViewById(R.id.coSquallLabel);
        TextView actualScoreLabel = (TextView)findViewById(R.id.actualScoreLabel);
        TextView playerNameLabel = (TextView)findViewById(R.id.playerNameLabel);

        selfSquallLabel.setText("AB : "+this.player.getTrueSquall().toString());
        coSquallLabel.setText("BC : "+this.game.getTrueSquall().toString());
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
                Combinaison combin = game.getCombinaison(pos);
                if(combin.getLib()=="MQR"){
                    showDialog(0);
                }

                Toast toast = Toast.makeText(getApplicationContext(), "Combinaison : " + combin.getLib()+"\nValeure de base : "+combin.getValue().toString(), Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }



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

}
