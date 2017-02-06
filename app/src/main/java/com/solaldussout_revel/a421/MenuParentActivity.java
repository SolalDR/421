package com.solaldussout_revel.a421;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.solaldussout_revel.a421.object.Game;

public abstract class MenuParentActivity extends AppCompatActivity {

    Toolbar toolbar;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.game = MainActivity.getGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem overviewButton = menu.findItem(R.id.overviewButton);
        MenuItem scoreActivityButton = menu.findItem(R.id.scoreActivityButton);
        MenuItem stopGameButton = menu.findItem(R.id.stopGame);


        overviewButton.setOnMenuItemClickListener(setOverviewListenerClick);
        scoreActivityButton.setOnMenuItemClickListener(setScoreActivityListenerClick);
        stopGameButton.setOnMenuItemClickListener(stopGameButtonListenerClick);


        ImageButton previousButton = (ImageButton) findViewById(R.id.previousButtonNav);
        previousButton.setOnClickListener(previousButtonListener);


        ImageButton warningButton= (ImageButton) findViewById(R.id.warningButton);
        warningButton.setOnClickListener(setWarningClickListener);
        return true;
    }

    View.OnClickListener setWarningClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("Bonjour");
            Intent secondeActivite = new Intent(getBaseContext(), WarningActivity.class);
            startActivity(secondeActivite);
        }
    };

    View.OnClickListener previousButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            game.previousShot();
            Intent secondeActivite = new Intent(getBaseContext(), ScoreActivity.class);
            startActivity(secondeActivite);
        }
    };

    MenuItem.OnMenuItemClickListener setOverviewListenerClick = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent secondeActivite = new Intent(getBaseContext(), OverviewActivity.class);
            startActivity(secondeActivite);
            return false;
        }
    };

    MenuItem.OnMenuItemClickListener setScoreActivityListenerClick = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent secondeActivite = new Intent(getBaseContext(), ScoreActivity.class);
            startActivity(secondeActivite);
            return false;
        }
    };

    MenuItem.OnMenuItemClickListener stopGameButtonListenerClick = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent secondeActivite = new Intent(getBaseContext(), MainActivity.class);
            startActivity(secondeActivite);
            return false;
        }
    };


    protected void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        System.out.println(toolbar);
        setSupportActionBar(toolbar);
    }
}
