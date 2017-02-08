package com.solaldussout_revel.a421;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.solaldussout_revel.a421.object.Game;

public abstract class MenuParentActivity extends AppCompatActivity {
    public static final int CD_PREVIOUS = 1;
    public static final int CD_STOP = 2;


    Toolbar toolbar;
    Game game;
    ConfirmDialog confirmDialog;
    Integer codeReady;

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
        MenuItem settingButton = menu.findItem(R.id.settingActivityButton);

        overviewButton.setOnMenuItemClickListener(setOverviewListenerClick);
        scoreActivityButton.setOnMenuItemClickListener(setScoreActivityListenerClick);
        stopGameButton.setOnMenuItemClickListener(stopGameButtonListenerClick);
        settingButton.setOnMenuItemClickListener(settingListenerClick);

        ImageButton previousButton = (ImageButton) findViewById(R.id.previousButtonNav);
        ImageButton warningButton= (ImageButton) findViewById(R.id.warningButton);
        ImageButton overviewButtonNav= (ImageButton) findViewById(R.id.overviewButtonNav);
        ImageButton scoreActivityNav = (ImageButton) findViewById(R.id.playGameButton);

        overviewButtonNav.setOnClickListener(setOverviewClickListener);
        warningButton.setOnClickListener(setWarningClickListener);
        previousButton.setOnClickListener(previousButtonListener);
        scoreActivityNav.setOnClickListener(scoreActivityNavListener);

        return true;
    }


    protected void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        System.out.println(toolbar);
        setSupportActionBar(toolbar);
    }



    //////////////////////////////////////////////
    //
    //          Boutons du menu linéaire
    //
    ///////////////////////////////////////////////


    View.OnClickListener setWarningClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent secondeActivite = new Intent(getBaseContext(), WarningActivity.class);
            startActivity(secondeActivite);
        }
    };

    View.OnClickListener setOverviewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent secondeActivite = new Intent(getBaseContext(), OverviewActivity.class);
            startActivity(secondeActivite);
        }
    };

    View.OnClickListener previousButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = game.getActualPlayer().getName();
            confirm("Voulez vous vraiment revenir au tour de  "+name+" ?");
            codeReady = MenuParentActivity.CD_PREVIOUS;

        }
    };

    View.OnClickListener scoreActivityNavListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent secondeActivite = new Intent(getBaseContext(), ScoreActivity.class);
            startActivity(secondeActivite);
        }
    };


    //////////////////////////////////////////////
    //
    //          Boutons du menu interne
    //
    ///////////////////////////////////////////////

    MenuItem.OnMenuItemClickListener settingListenerClick = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent secondeActivite = new Intent(getBaseContext(), PreferenceActivity.class);
            startActivity(secondeActivite);
            return false;
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
            codeReady = MenuParentActivity.CD_STOP;
            confirm("Voulez vous vraiment quittez la partie ? Vous serez redirigé vers la page d'accueil.");
            return false;
        }
    };

    public void confirmReady(){
        int code = codeReady;

        switch (code){
            case MenuParentActivity.CD_PREVIOUS :
                game.previousShot();
                startActivity(new Intent(getBaseContext(), ScoreActivity.class));
                break;

            case MenuParentActivity.CD_STOP : startActivity(new Intent(getBaseContext(), MainActivity.class));
                break;

            default:
                System.out.println("codeReady is Null");
                break;
        }

        codeReady = null;

    }

    public void confirm(String message){
        confirmDialog = new ConfirmDialog(this, message, new ConfirmDialog.DialogListener() {
            public void cancelled() {

            }
            public void ready() {
                confirmReady();
            }
        });
        confirmDialog.show();
    }

}
