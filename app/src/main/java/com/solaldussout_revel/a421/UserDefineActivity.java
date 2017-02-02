package com.solaldussout_revel.a421;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.solaldussout_revel.a421.object.Game;
import com.solaldussout_revel.a421.object.Player;

public class UserDefineActivity extends MenuParentActivity {
    Game game;
    Button saveUserButton;
    EditText[] users = new EditText[5];
    TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = MainActivity.getGame();

        setContentView(R.layout.activity_user_define);
        this.initToolbar();

        users[0] = (EditText) findViewById(R.id.userDefineField1);
        users[1] = (EditText) findViewById(R.id.userDefineField2);
        users[2] = (EditText) findViewById(R.id.userDefineField3);
        users[3] = (EditText) findViewById(R.id.userDefineField4);
        users[4] = (EditText) findViewById(R.id.userDefineField5);

        debug = (TextView)findViewById(R.id.debug1);
        debug.setText(users[0].getText().toString());


        saveUserButton = (Button)findViewById(R.id.buttonSaveUsers);
        saveUserButton.setOnClickListener(saveUserListener);


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

            debug.setText(p.toString()+ testplayer.getName());

            if(p>1) {
                Intent scoreActivity = new Intent(UserDefineActivity.this, ScoreActivity.class);
                startActivity(scoreActivity);
            }
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
