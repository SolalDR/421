package com.solaldussout_revel.a421;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.solaldussout_revel.a421.object.Game;
import com.solaldussout_revel.a421.object.Player;

public class UserDefineActivity extends AppCompatActivity {
    Game game;
    Button saveUserButton;
    EditText[] users = new EditText[5];
    TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = MainActivity.getGame();
        setContentView(R.layout.activity_user_define);

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsButton);

        users[0] = (EditText) findViewById(R.id.userDefineField1);
        users[1] = (EditText) findViewById(R.id.userDefineField2);
        users[2] = (EditText) findViewById(R.id.userDefineField3);
        users[3] = (EditText) findViewById(R.id.userDefineField4);
        users[4] = (EditText) findViewById(R.id.userDefineField5);

        saveUserButton = (Button)findViewById(R.id.buttonSaveUsers);
        saveUserButton.setOnClickListener(saveUserListener);
        settingsButton.setOnClickListener(settingsListener);
    }

    View.OnClickListener saveUserListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer p = 0;
            Player testplayer=null;

            for(int i=0; i<users.length; i++){
                if(!users[i].getText().toString().isEmpty()){
                    testplayer = game.addPlayer(users[i].getText().toString());
                    p++;
                }
            }

            if(p>1) {
                Intent scoreActivity = new Intent(UserDefineActivity.this, ScoreActivity.class);
                startActivity(scoreActivity);
            }
        }

    };

    View.OnClickListener settingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent prefActivity = new Intent(UserDefineActivity.this, PreferenceActivity.class);
            startActivity(prefActivity);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



}
