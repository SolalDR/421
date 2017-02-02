package com.solaldussout_revel.a421;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;

import com.solaldussout_revel.a421.object.Game;


public class MainActivity extends AppCompatActivity {

    Button buttonStartGame = null;

    private static Game game = new Game();
    public static Game getGame() { return game; }
    public static void setGame(Game g) { game = g; }


    private View.OnClickListener buttonStartListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
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
