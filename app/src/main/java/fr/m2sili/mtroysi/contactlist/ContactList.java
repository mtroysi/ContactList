package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class ContactList extends Activity {

    private ContactFragment contactFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        contactFragment=(ContactFragment) getFragmentManager().findFragmentById(R.id.contact_fragment);
    }
}
