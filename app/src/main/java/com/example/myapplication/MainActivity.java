package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    final static String CYCLEVIEPREFS="cycle_vie_prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("oncreate","oncreate lll");
        if (savedInstanceState != null){
            String valeur = savedInstanceState.getString("clé");
            Log.i("oncreate",valeur);

        }


        setContentView(R.layout.activity_main);
        Button btnQuitter = (Button) findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(btnQuitterOnClickListener);
        popUp("onCreate()");

        Log.i("oncreate","oncreate jjjjj");

        Button btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(btnEnvoyerOnClickListener);

        Button btnActiver = (Button) findViewById(R.id.btnActiver);
        btnActiver.setOnClickListener(btnActiverOnClickListener);


    }

    /** =============================================================
     * Exécuté que l'activité arrêtée via un "stop" redémarre.
     *
     * La fonction onRestart() est suivie de la fonction onStart().
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        popUp("onRestart()");
    }
    /** ==============================================================
     * Exécuté lorsque l'activité devient visible à l'utilisateur.
     *
     * La fonction onStart() est suivie de la fonction onResume().
     */
    @Override
    protected void onStart() {
        super.onStart();
        popUp("onStart()");
        SharedPreferences settings = getSharedPreferences(CYCLEVIEPREFS, Context.MODE_PRIVATE);
        setTxTValeur(settings.getString("valeur", ""));
    }
    /** ==============================================================
     * Exécutée à chaque passage en premier plan de l'activité.
     * Ou bien, si l'activité passe à nouveau en premier
     * (si une autre activité était passée en premier plan entre temps).
     *
     * La fonction onResume() est suivie de l'exécution de l'activité.
     */
    @Override
    protected void onResume() {
        super.onResume();
        popUp("onResume() ");
        SharedPreferences settings = getSharedPreferences(CYCLEVIEPREFS, Context.MODE_PRIVATE);
        setTxTValeur(settings.getString("valeur", ""));
    }
    /** =============================================================
     * La fonction onPause() est suivie :
     * - d'un onResume() si l'activité passe à nouveau en premier plan
     * - d'un onStop() si elle devient invisible à l'utilisateur
     *
     * L'exécution de la fonction onPause() doit être rapide,
     * car la prochaine activité ne démarrera pas tant que l'exécution
     * de la fonction onPause() n'est pas terminée.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            popUp("onPause, l'utilisateur à demandé la fermeture via un finish()");
        } else {
            popUp("onPause, l'utilisateur n'a pas demandé la fermeture via un finish()");
        }

        SharedPreferences settings = getSharedPreferences("cycle_vie_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("valeur", getTxtValeur());
        editor.commit();
    }
    /** ==============================================================
     * La fonction onStop() est exécutée :
     * - lorsque l'activité n'est plus en premier plan
     * - ou bien lorsque l'activité va être détruite
     *
     * Cette fonction est suivie :
     * - de la fonction onRestart() si l'activité passe à nouveau en premier plan
     * - de la fonction onDestroy() lorsque l'activité se termine
     *    ou bien lorsque le système décide de l'arrêter
     */
    @Override
    protected void onStop() {
        super.onStop();
        popUp("onStop()");
    }
    /** =============================================================
     * Cette fonction est exécutée lorsque l'activité se termine ou bien lorsque
     * le système décide de l'arrêter.
     *
     * La fonction onCreate() devra à nouveau être exécutée pour obtenir à nouveau l'activité.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        popUp("onDestroy()");
    }
    //=================================================================
    View.OnClickListener btnQuitterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    public void popUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    View.OnClickListener btnEnvoyerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popUp("valeur saisie = " + getTxtValeur());
        }
    };

    View.OnClickListener btnActiverOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent(v.getContext(), Main2Activity.class);
            intent.putExtra("string",getTxtValeur());
            startActivity(intent);

        }
    };
    public String getTxtValeur() {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        return zoneValeur.getText().toString();
    }
    public void setTxTValeur(String valeur) {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        zoneValeur.setText(valeur);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("cle","zzzzzz");
        outPersistentState.putString("cle2", "yyyyyyyyy");

    }


    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        String valeur = savedInstanceState.getString("cle2");
       // Log.i("onRestoreInstanceState",valeur);
        Log.i("persistentState",valeur);
        Toast.makeText(this, valeur, Toast.LENGTH_SHORT).show();
    }


}