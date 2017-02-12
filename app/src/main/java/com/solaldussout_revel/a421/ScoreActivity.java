package com.solaldussout_revel.a421;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.Dialog;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.Button;
import android.content.Intent;


import com.solaldussout_revel.a421.object.Combinaison;
import com.solaldussout_revel.a421.object.Game;
import com.solaldussout_revel.a421.object.Player;

import java.util.ArrayList;


public class ScoreActivity extends MenuParentActivity {

    private final static int SPINNER_IDENTIFIANT  = 0;

    Game game;
    Player player;

    Combinaison tmpCombin;
    String tmpLibScore;
    Float tmpValueScore;
    TextView actualScoreLabel;
    Button validScoreButton;
    Boolean isFirstTry;

    SpinnerDialog mSpinnerDialog;
    ArrayList mqrOptions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_score);
        this.initToolbar();

        this.game = MainActivity.getGame();
        this.player = game.getActualPlayer();
        this.isFirstTry = false;
        this.actualScoreLabel = (TextView)findViewById(R.id.actualScoreLabel);
        this.setMqrOptions();

        initTextViewValues();
        initGrid();

        //Evenement de validation et de firstTry
        Button firstTry = (Button) findViewById(R.id.firstTryButton);
        validScoreButton = (Button) findViewById(R.id.validScoreButton);

        firstTry.setOnClickListener(firstTryListener);
        validScoreButton.setOnClickListener(validScoreListener);
    }


    //Affiche les informations propres au coup actuelle
    public void initTextViewValues(){
        //Récupération des vues
        TextView selfSquallLabel = (TextView)findViewById(R.id.selfSquallLabel);
        TextView coSquallLabel = (TextView)findViewById(R.id.coSquallLabel);
        TextView playerNameLabel = (TextView)findViewById(R.id.playerNameLabel);

        //Composition des texte
        String selfSquallText = "AB : "+this.player.getTrueSquall().toString();
        String coSquallText = "BC : "+this.game.getTrueSquall().toString();

        //Attribution des valeurs
        selfSquallLabel.setText(selfSquallText);
        coSquallLabel.setText(coSquallText);
        playerNameLabel.setText(this.player.getName());
    }


    //Initialisation de la grille
    public void initGrid(){
        //Initialisation
        String[] combinaisons = this.game.getCombinaisonsLib();
        GridView grid = (GridView)findViewById(R.id.gridViewTest);

        //On créer un item par combinaison
        ArrayAdapter adapater =  new ArrayAdapter(this, R.layout.button_grid, combinaisons);
        grid.setAdapter(adapater);

        //Clique sur un item de grille
        grid.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapater, View view, int position, long id) {

                //On met jour la combinaison temporaire
                Integer pos = position;
                tmpCombin = game.getCombinaison(pos);


                if(tmpCombin.getLib().equals("MQR")){
                    showDialog(0);
                } else {
                    updateTmpScore();
                }
            }
        });
    }


    //Callback d'un clique sur une combinaison, met à jour la combinaison active en attente d'une validation
    public void updateTmpScore(){

        //Met à jour la combinaison active
        Float value = tmpCombin.getValue();
        String lib = tmpCombin.getLib();
        this.setTmpLibScore(lib);
        this.setTmpValueScore(value);


        //Met à jour la valeure du coup actuelle dans la vue
        Integer intValue = value.intValue();
        if(intValue.floatValue() != value){
            this.actualScoreLabel.setText(lib+" : "+value.toString());
        } else {
            this.actualScoreLabel.setText(lib+" : "+intValue.toString());
        }
    }


    //Quand on crée un dialogue
    public Dialog onCreateDialog(int identifiant) {
        switch(identifiant) {
            case SPINNER_IDENTIFIANT :
                mSpinnerDialog = new SpinnerDialog(this, this.mqrOptions, new SpinnerDialog.DialogListener() {
                    public void cancelled() {
                        // do your code here
                    }
                    public void ready(int n) {
                        Spinner spin = (Spinner) mSpinnerDialog.findViewById(R.id.dialog_spinner);
                        Float value = Float.valueOf(spin.getSelectedItem().toString());
                        tmpCombin.setValue(value);
                        updateTmpScore();
                    }
                });
                break;
        }
        return mSpinnerDialog;
    }


    //Bouton de validation d'un score
    View.OnClickListener validScoreListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Combinaison combin = tmpCombin;

            //Si la combinaison est valide on rajoute un score au jeu et on passe au score suivant
            if(combin != null && combin.getLib() != null && combin.getValue() != null && combin.getActiveSquall() != null){
                game.addScore(combin, isFirstTry);
                game.nextPlayer();
                Intent secondeActivite = new Intent(ScoreActivity.this, ScoreActivity.class);
                startActivity(secondeActivite);

            //Sinon on indique à l'utilisateur de sélectionner un score
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Il faut sélectionner un score !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };



    //Bouton FirstTry
    View.OnClickListener firstTryListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            System.out.println("Bonjour");
            Toast toast;
            if(isFirstTry){
                isFirstTry = false;
                toast = Toast.makeText(getApplicationContext(), "C'est plus un first try déso <3", Toast.LENGTH_SHORT);

            } else {
                isFirstTry = true;
                toast = Toast.makeText(getApplicationContext(), "C'est un First try mon frère", Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    };


    //Définis les options de MQR
    private void setMqrOptions(){
        mqrOptions = new ArrayList<>();
        mqrOptions.add("2");
        mqrOptions.add("3");
        mqrOptions.add("4");
        mqrOptions.add("5");
        mqrOptions.add("6");
    }


    //Bloque l'évenement de clique sur le bouton retour
    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "Fais pas ça frère...", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setTmpLibScore(String tmpLibScore) {
        this.tmpLibScore = tmpLibScore;
    }

    public void setTmpValueScore(Float tmpValueScore) {
        this.tmpValueScore = tmpValueScore;
    }


}
