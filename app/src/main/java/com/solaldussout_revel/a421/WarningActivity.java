package com.solaldussout_revel.a421;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.solaldussout_revel.a421.object.Game;
import com.solaldussout_revel.a421.object.Player;

import org.w3c.dom.Text;

public class WarningActivity extends MenuParentActivity {

    TableLayout warningTab;
    Game game;
    Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = MainActivity.getGame();
        players = game.getPlayers();
        setContentView(R.layout.activity_warning);

        warningTab = (TableLayout) findViewById(R.id.warningGrid);

        genRows();
        initToolbar();
    }

    public void genRows(){
        for(int i=0; i<players.length; i++){
            genRow(players[i]);
        }
    }

    public void genRow(Player player){
        TableRow row = (TableRow)getLayoutInflater().inflate(R.layout.warning_row_grid, null);

        Boolean isWarn = player.getWarned();

        ImageView warnView = (ImageView) row.findViewById(R.id.isWarnedIcon);
        TextView name = (TextView) row.findViewById(R.id.playerNameText);
        TextView nbWarned = (TextView) row.findViewById(R.id.nbWarnText);

        updateContent(player, name, nbWarned, warnView);

        row.setOnClickListener(setRowClickListener);
        warningTab.addView(row);
    }

    public void updateContent(Player p, TextView name, TextView nbWarned, ImageView i){
        String namePlayer = p.getName();
        Integer nbWarn = p.getNbWarn();
        Boolean isWarn = p.getWarned();
        if(isWarn==true){
            i.setVisibility(View.VISIBLE);
        } else {
            i.setVisibility(View.INVISIBLE);
        }
        name.setText(namePlayer);
        nbWarned.setText(nbWarn.toString());
    }

    View.OnClickListener setRowClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView nameView = (TextView) v.findViewById(R.id.playerNameText);
            ImageView warnView = (ImageView) v.findViewById(R.id.isWarnedIcon);
            TextView nbWarned  = (TextView) v.findViewById(R.id.nbWarnText);
            Player p = game.getPlayerFromName(nameView.getText().toString());
            if(p!=null){
                if(p.getWarned() == true){
                    p.setWarned(false);
                    p.setNbWarn(p.getNbWarn()+1);
                } else {
                    p.setWarned(true);
                }
                updateContent(p, nameView, nbWarned, warnView);
            }
        }
    };
}
