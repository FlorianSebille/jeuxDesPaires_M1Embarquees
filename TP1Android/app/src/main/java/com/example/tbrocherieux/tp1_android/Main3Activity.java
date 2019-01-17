package com.example.tbrocherieux.tp1_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/* Objectif : tester le cycle de vie d'une application */

public class Main3Activity extends Activity {

    /** Listener à l'écoute des evenements Bouton */
    OnClickListener btnEnvoyerOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            popUp("Valeur saisie = " + getTxtValeur());
        }
    };

    OnClickListener btnQuitterOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // la methode finish est appelee quand l'activite est finie et
            // doit être fermee
            finish();
        }
    };

    /** Gestion des zones de texte */
    public String getTxtValeur() {
        return((EditText) findViewById(R.id.editTxtValeur)).getText().toString();
    }

    public void setTxTValeur(String valeur) {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        zoneValeur.setText(valeur);
    }

    /** Creation d'un "Toast" pour afficher temporairement les informations
     * a l'ecran
     */
    public void popUp(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Appel au debut du cycle complet
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Initialiser l'activite
        Button btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(btnEnvoyerOnClickListener);

        Button btnQuitter = (Button) findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(btnQuitterOnClickListener);

        // Affichage du toast
        popUp("onCreate()");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu3, menu);
        return true;
    }

    /** Appelee apres onCreate, à utiliser pour restaurer l'etat de l'interface */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        //Restaurer l'interface utilisateur à partir de savedInstanceState.
        //Ne sera appelee que si l'activite a ete tuee par le systême
        //depuis qu'elle a ete visible pour la dernière fois.

        // Affichage du toast
        popUp("onRestoreInstanceState()");

    }


    /**
     * Appelee avant les cycles visibles d'une activite.
     * La fonction onRestart() est suivie de la fonction onStart().
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        popUp("onRestart()");
    }

    /**
     * Execute lorsque l'activité devient visible à l'utilisateur.
     * La fonction onStart() est suivie de la fonction onResume().
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Récupération des anciens paramètres
        {
            SharedPreferences settings = getSharedPreferences("cycle_vie_prefs", Context.MODE_PRIVATE);
            setTxTValeur(settings.getString("valeur", ""));
        }

        popUp("onStart()");
    }

    /**
     * Exécutée à chaque passage en premier plan de l'activité.
     * Ou bien, si l'activité passe à nouveau en premier
     *  (si une autre activité était passée en premier plan entre temps).
     *
     * La fonction onResume() est suivie de l'exécution de l'activité.
     */
    @Override
    protected void onResume() {
        super.onResume();

        popUp("onResume()");
    }

    /**
     * La fonction onPause() est suivie :
     * - d'un onResume() si l'activité passe à nouveau en premier plan;
     * - d'un onStop() si elle devient invisible à l'utilisateur;
     *
     * L'exécution de la fonction onPause() doit être rapide,
     * car la prochaine activité ne démarrera pas tant que l'execution
     * de la fonction onPause() n'est pas terminee.
     */
    @Override
    protected void onPause() {
        super.onPause();

        // Sauvegarde des parametres
        // pour pouvoir les restaurer au prochain demarrage
        {
            SharedPreferences settings = getSharedPreferences("cycle_vie_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("valeur", getTxtValeur());
            editor.commit();
        }

        if (isFinishing()) {
            popUp("onPause, l'utilisateur a demande la fermeture via un finish()");
        } else {
            popUp("onPause, l'utilisateur n'a pas demande la fermeture via un finish()");
        }
    }

    /**
     * La fonction onStop() est executee :
     * - lorsque l'activite n'est plus en premier plan
     * - ou bien lorsque l'activite va etre detruite
     *
     * Cette fonction est suivie :
     * - de la fonction onRestart() si l'activite passe à nouveau en premier plan;
     * - de la fonction onDestroy() lorsque l'activite se termine ou bien lorsque le systeme decide de l'arreter
     */
    @Override
    protected void onStop() {
        super.onStop();

        popUp("onStop()");

    }

    /**
     * Cette fonction est executee lorsque l'activite se termine ou bien lorsque
     * le systeme decide de l'arreter.
     *
     * La fonction onCreate() devra a nouveau etre executee pour obtenir à nouveau l'activite.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        popUp("onDestroy()");
    }



}