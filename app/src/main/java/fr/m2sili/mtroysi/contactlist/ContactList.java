package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class ContactList extends Activity implements TaskFragment.TaskCallBacks {

    private ContactFragment contactFragment;
    private static final String TAG_TASKS_FRAGMENT = "task_fragment";
    private TaskFragment mTaskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FragmentManager fm = getFragmentManager();

        contactFragment = (ContactFragment) fm.findFragmentById(R.id.contact_fragment);

        mTaskFragment = (TaskFragment) fm.findFragmentByTag(TAG_TASKS_FRAGMENT);
        if (mTaskFragment == null) {
            mTaskFragment = new TaskFragment();
            fm.beginTransaction().add(mTaskFragment, TAG_TASKS_FRAGMENT).commit();
        }
    }

    @Override
    public void onContactUpdate(Contact contact) {
        contactFragment.onContactUpdate(contact);
    }

    @Override
    public void onContactDone(Contact contact) {
        contactFragment.onContactDone(contact);
    }

    public void launchUpload(Contact contact) {
        mTaskFragment.launchUpload(contact);
    }

    public void onContactAdded(Contact contact) {
        mTaskFragment.onContactAdded(contact);
    }

    public ContactAdapter getAdapter() {
        return mTaskFragment.getAdapter();
    }

    public void initiateAdapter() {
        mTaskFragment.setAdapter(new ContactAdapter(this));
    }

//    public void populate(ContactAdapter adapter) {
//        int i;
//        for(i = 0; i < adapter.getCount(); ++i) {
//            mTaskFragment.getAdapter().add(adapter.getItem(i));
//        }
//    }
}
