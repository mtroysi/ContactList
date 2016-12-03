package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class ContactFragment extends ListFragment {

    private ContactList mainActivity = null;
    private ContactAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (ContactList) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        adapter = new ContactAdapter(inflater.getContext(), R.layout.ligne);
        setListAdapter(adapter);
        adapter.add(new Contact("Dupont", "Jean", 26, 5, 1994, "test@test.fr"));
        adapter.add(new Contact("Smith", "John", 26, 5, 1994, "test@test.fr"));
        return rootView;
    }
}
