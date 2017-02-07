package com.solaldussout_revel.a421;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;

import com.solaldussout_revel.a421.object.Game;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button buttonStartGame = null;

    private static Game game;
    public static Game getGame() { return game; }
    public static void setGame(Game g) { game = g; }


    private View.OnClickListener buttonStartListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            Map pref = preferences.getAll();

            MainActivity.setGame(new Game(pref));
            Intent secondeActivite = new Intent(MainActivity.this, UserDefineActivity.class);
            startActivity(secondeActivite);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        buttonStartGame = (Button)findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(buttonStartListener);
    }

}
