package com.example.android.scoresheet.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Kari Stromsland on 9/9/2016.
 */
public class EventNewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_event);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setElevation(0f);

//        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putParcelable(EventNewFragment.EVENTNEW_URI, getIntent().getData());

//            EventNewFragment fragment = new EventNewFragment();
////            fragment.setArguments(arguments);
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.event_new_container, fragment)
//                    .commit();
//        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.event_new, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, OptionsActivity.class));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public Intent getParentActivityIntent() {
//        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//    }

}
