package com.example.android.scoresheet.app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.android.scoresheet.app.Entrants.EntrantsActivity;
import com.example.android.scoresheet.app.Events.EventsActivity;
import com.example.android.scoresheet.app.Users.UsersActivity;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TabHost tabHost =  (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third Tab");

        tab1.setIndicator("Events");
        tab1.setContent(new Intent(this, EventsActivity.class));
        tab2.setIndicator("Teams");
        tab2.setContent(new Intent(this, EntrantsActivity.class));
        tab3.setIndicator("Users");
        tab3.setContent(new Intent(this, UsersActivity.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

    }
}