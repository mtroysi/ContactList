package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class Display extends Activity {
    private DisplayFragment displayFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary);
        displayFragment=(DisplayFragment)getFragmentManager().findFragmentById(R.id.display);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String mail = intent.getStringExtra("mail");
            int day = intent.getIntExtra("day", 0);
            int month = intent.getIntExtra("month", 0);
            int year = intent.getIntExtra("year", 0);
            displayFragment.displayContact(name, mail, day, month, year);
        }

    }
}
