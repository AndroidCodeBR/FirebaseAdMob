package br.com.androidcode.firebaseadmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    //Criamos os objetos para a apresentação da publicidade
    //Create the objects to present the Advertisement
    private AdView adView;
    private Button btnInterstitialAd;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para mostrar o banner, inicializamos o objeto e fazemos o request da publicidade
        //To show up the banner, initialize the object and pull the Advertisement request
        adView = (AdView)findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();

        //O loadAd irá exibir o banner
        //The loadAd will show the banner
        adView.loadAd(adRequest);

        //Inicializamos o objeto interstitialAd
        //Initialize the interstitialAd object
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admob_instrstitial_id));

        //Chamamos a função que faz a carga das publicidades
        //Call the function that load the AD
        LoadInterstitial();

        //Implementamos o setAdListener para quando o AD for fechado, fazer uma nova recarga
        //Implement the setAdListener to reload the AD when closed
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                LoadInterstitial();
            }
        });

        btnInterstitialAd = (Button) findViewById(R.id.btnInterstitial);
        btnInterstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialAd.show();
            }
        });
    }

    //Implementamos onPause, onResume e onDestroy para gerenciar o estado do BANNER
    //Implement onPause, onResume and onDestroy to manage the Banner state
    @Override
    public void onPause(){
        if(adView !=null){
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(adView !=null){
            adView.resume();
        }
    }

    @Override
    public void onDestroy(){
        if(adView !=null){
            adView.destroy();
        }
        super.onDestroy();
    }

    private void LoadInterstitial(){
        AdRequest interAdRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(interAdRequest);
    }
}
