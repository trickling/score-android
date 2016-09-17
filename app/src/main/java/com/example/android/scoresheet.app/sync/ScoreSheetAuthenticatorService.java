package com.example.android.scoresheet.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Kari Stromsland on 8/25/2016.
 */
public class ScoreSheetAuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private ScoreSheetAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new ScoreSheetAuthenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}