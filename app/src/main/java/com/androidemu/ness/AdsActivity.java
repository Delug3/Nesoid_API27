package com.androidemu.ness;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Adnan on 5/13/18.
 */

public class AdsActivity extends Activity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_view);
        MobileAds.initialize(this, "ca-app-pub-1789408044804203~5562631378");

        imageView = (ImageView) findViewById(R.id.loadingImage);
        Glide.with(this).load(R.drawable.loading).asGif().dontAnimate().into(imageView);

        imageView.setVisibility(View.GONE);

        loadBannerAds();
        initInterstitialAds () ;

    }

    private void initInterstitialAds(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1789408044804203/4719273240");
    }

    private void loadBannerAds () {
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        mAdView.loadAd(adRequest);


    }

    public void loadIntersititalAds (View view) {
        imageView.setVisibility(View.VISIBLE);
        findViewById(R.id.loadButton).setVisibility(View.GONE);

        Bundle extras = new Bundle();
        extras.putBoolean("is_designed_for_families", true);

        AdRequest request = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();

        mInterstitialAd.loadAd(request);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                finish();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                finish();
            }
        });
    }

}
