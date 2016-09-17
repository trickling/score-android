package com.example.android.scoresheet.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Kari Stromsland on 8/25/2016.
 */
public class ScoreSheetSyncAdapterService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static ScoreSheetSyncAdapter sScoreSheetSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("ScoreSheetSyncService", "onCreate - ScoreSheetSyncService");
        synchronized (sSyncAdapterLock) {
            if (sScoreSheetSyncAdapter == null) {
                sScoreSheetSyncAdapter = new ScoreSheetSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sScoreSheetSyncAdapter.getSyncAdapterBinder();
    }


}
