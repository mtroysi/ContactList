package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Contact contact = adapter.getItem(position);
        Intent intent = new Intent(mainActivity, Display.class);
        intent.putExtra("name", contact.getNom().toUpperCase() + " " + contact.getPrenom().toUpperCase());
        intent.putExtra("mail", contact.getMail());
        //TODO: voir pour le mois en lettres
        intent.putExtra("day", contact.getDay());
        intent.putExtra("month", contact.getMonth());
        intent.putExtra("year", contact.getYear());
        startActivity(intent);
    }
}
