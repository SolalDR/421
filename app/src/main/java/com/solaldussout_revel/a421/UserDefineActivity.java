package com.solaldussout_revel.a421;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.solaldussout_revel.a421.object.Game;

public class UserDefineActivity extends AppCompatActivity {
    Game game;
    Button saveUserButton;
    EditText[] users = new EditText[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = MainActivity.getGame();
        setContentView(R.layout.activity_user_define);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsButton);

        users[0] = (EditText) findViewById(R.id.userDefineField1);
        users[1] = (EditText) findViewById(R.id.userDefineField2);
        users[2] = (EditText) findViewById(R.id.userDefineField3);
        users[3] = (EditText) findViewById(R.id.userDefineField4);
        users[4] = (EditText) findViewById(R.id.userDefineField5);
        users[5] = (EditText) findViewById(R.id.userDefineField6);

        saveUserButton = (Button)findViewById(R.id.buttonSaveUsers);
        saveUserButton.setOnClickListener(saveUserListener);
        settingsButton.setOnClickListener(settingsListener);
    }

    View.OnClickListener saveUserListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer p = 0;

            for (EditText textEdit: users) {
                if(!textEdit.getText().toString().isEmpty()){
                    game.addPlayer(textEdit.getText().toString());
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


}
