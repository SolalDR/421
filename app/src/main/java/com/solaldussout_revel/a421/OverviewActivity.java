package com.solaldussout_revel.a421;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.solaldussout_revel.a421.object.*;

import android.widget.Button;
import android.widget.GridView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OverviewActivity extends MenuParentActivity {


    Player[] players;
    Score[] scores;
    Game game;
    Button consultScore;

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

        TextView coSquallLabel = (TextView) findViewById(R.id.coSquallLabel);
        coSquallLabel.setText("BC : "+game.getTrueSquall());


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
        nameViewGrid.setAdapter(new ArrayAdapter<String>(this,R.layout.text_grid_header_overview, listName));



        String[] scoresTotal = new String[nbPlayers];
        for(int i=0; i<nbPlayers; i++){
            scoresTotal[i] = players[i].calcTotal().toString();
        }
        GridView totalViewGrid = (GridView) findViewById(R.id.totalViewGrid);
        totalViewGrid.setNumColumns(players.length);
        totalViewGrid.setAdapter(new ArrayAdapter<String>(this,R.layout.text_grid_overview, scoresTotal));


        consultScore = (Button) findViewById(R.id.consultScore);
        consultScore.setOnClickListener(consultScoreListener);
    }

    View.OnClickListener consultScoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.animate().translationY(dpToPx(60)).setDuration(100);
        }
    };

    //Convertis une dimension en dp en px pour les animations
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
