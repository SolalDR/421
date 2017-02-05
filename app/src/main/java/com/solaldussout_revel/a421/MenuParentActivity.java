package com.solaldussout_revel.a421;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

public abstract class MenuParentActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        return true;
    }



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
            return false;
        }
    };


    protected void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        System.out.println(toolbar);
        setSupportActionBar(toolbar);
    }
}
