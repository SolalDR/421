package com.solaldussout_revel.a421;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.solaldussout_revel.a421.object.*;
import android.widget.GridView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class OverviewActivity extends MenuParentActivity {


    Player[] players;
    Score[] scores;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        initToolbar();

        game = MainActivity.getGame();
        players = game.getPlayers();





        Integer countScore = 0;
        Integer nbPlayers = players.length;
        String displayedScore;
        String[] listString;
        Float total;

        for(int i=0; i<nbPlayers; i++){
            scores = players[i].getScores();
            if(scores != null){
                countScore = countScore + scores.length;
            }
        }

        listString = new String[countScore];
        for(int i=0; i<nbPlayers; i++) {
            scores = players[i].getScores();
            if(scores != null){
                for(int j=0; j<scores.length ; j++){
                    total = scores[j].getTotal();
                    if(total.intValue() == total){
                        displayedScore = scores[j].getLib() +" "+ String.valueOf(total.intValue());
                    } else {
                        displayedScore = scores[j].getLib() +" "+ total.toString();
                    }
                    listString[nbPlayers*j+i] = displayedScore;
                }
            }
        }

        GridView scoreViewGrid = (GridView) findViewById(R.id.scoreViewGrid);
        scoreViewGrid.setNumColumns(players.length);
        scoreViewGrid.setAdapter(new ArrayAdapter<String>(this,R.layout.text_grid_overview, listString));



        String[] listName = new String[nbPlayers];
        for(int i=0; i<nbPlayers; i++){
            listName[i] = players[i].getName()+ "\n" + "AB : "+players[i].getTrueSquall().toString();
        }


        GridView nameViewGrid = (GridView) findViewById(R.id.nameViewGrid);
        nameViewGrid.setNumColumns(players.length);
        nameViewGrid.setAdapter(new ArrayAdapter<String>(this,R.layout.text_grid_overview, listName));
    }

}
