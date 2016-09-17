package com.example.android.scoresheet.app.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract;
//import com.example.android.scoresheet.app.EventData;

/**
 * Created by Kari Stromsland on 8/25/2016.
 */
public class ScoreSheetSyncAdapter extends AbstractThreadedSyncAdapter{
    public static final String LOG_TAG = ScoreSheetSyncAdapter.class.getSimpleName();
    public static final int SYNC_INTERVAL = 60 * 180;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL/3;
    private static final int EVENT_NOTIFICATION_ID = 3004;

    private static final String[] NOTIFY_EVENT_PROJECTION = new String[] {
            ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC
    };

    // these indices must match the projection
    private static final int INDEX_SHORT_DESC = 1;

    public ScoreSheetSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.d(LOG_TAG, "Starting sync");

        return;
    }

    private static void dropDB(Context context) {

        context.getContentResolver().delete(ScoreSheetContract.EventEntry.CONTENT_URI, null, null);

        Log.d(LOG_TAG, "Delete Complete.");
    }


    private static void seedEventDB(String[] event_seed, Context context) {

        ContentValues eventValue = new ContentValues();
        for (String item : event_seed){
            eventValue.put(ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC, item);
            context.getContentResolver().insert(ScoreSheetContract.EventEntry.CONTENT_URI, eventValue);
        }

        Log.d(LOG_TAG, "Insert Complete.");
    }

    private static void seedEntrantDB(String[] entrant_seed, Context context) {

        ContentValues entrantValue = new ContentValues();
        for (String item : entrant_seed){
            entrantValue.put(ScoreSheetContract.EntrantEntry.COLUMN_TEAM_DESC, item);
            context.getContentResolver().insert(ScoreSheetContract.EntrantEntry.CONTENT_URI, entrantValue);
        }

        Log.d(LOG_TAG, "Insert Complete.");
    }

    private void syncDB(String[] event_sync) {

    }

    /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
//        dropDB(context);
//        seedEventDB(EventData.EVENTS, context);
//        seedEntrantDB(EntrantData.ENTRANTS, context);
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if ( null == accountManager.getPassword(newAccount) ) {

    /*
     * Add the account and account type, no password or user data
     * If successful, return the Account object, otherwise report an error.
     */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
        /*
         * If you don't set android:syncable="true" in
         * in your <provider> element in the manifest,
         * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
         * here.
         */
            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
    /*
     * Since we've created an account
     */
        ScoreSheetSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

    /*
     * Without calling setSyncAutomatically, our periodic sync will not be enabled.
     */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

    /*
     * Finally, let's do a sync to get things started
     */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
        syncImmediately(context);
    }
}

