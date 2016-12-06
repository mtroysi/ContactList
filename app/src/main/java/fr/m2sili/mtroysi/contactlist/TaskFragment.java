package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Morgane TROYSI on 12/5/16.
 */

public class TaskFragment extends Fragment {
    static interface TaskCallBacks {
        public void onContactUpdate(Contact contact);
        public void onContactDone(Contact contact);
    }
    private TaskCallBacks mMainActivityListener = null;
    private ContactAdapter adapter;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mMainActivityListener = (TaskCallBacks) activity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.mMainActivityListener = null;
    }
    protected void onProgressUpdate(Contact contact) {
        if (mMainActivityListener != null) mMainActivityListener.onContactUpdate(contact);
    }
    protected void onPostExecute(Contact contact) {
        if (mMainActivityListener != null) mMainActivityListener.onContactDone(contact);
    }

    public void launchUpload(Contact contact) {
        new UploadTask(this).execute(contact);
    }

    public ContactAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ContactAdapter adapter) {
        this.adapter = adapter;
    }
}
