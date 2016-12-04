package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.new_contact:
                LayoutInflater inflater = LayoutInflater.from(mainActivity);
                final View alertDialogView = inflater.inflate(R.layout.alert_dialog,
                        null);
                AlertDialog.Builder adb = new AlertDialog.Builder(mainActivity);
                adb.setView(alertDialogView);
                adb.setTitle("Nouveau contact");
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nom = (EditText) alertDialogView.findViewById(R.id.saisie_nom);
                        EditText prenom = (EditText) alertDialogView.findViewById(R.id.saisie_prenom);
                        EditText mail = (EditText) alertDialogView.findViewById(R.id.saisie_mail);
                        DatePicker birthday = (DatePicker) alertDialogView.findViewById(R.id.saisie_ddn);
                        Contact contact = new Contact(nom.getText().toString(), prenom.getText().toString(), birthday.getDayOfMonth(), birthday.getMonth() + 1, birthday.getYear(), mail.getText().toString());
                        adapter.add(contact);
                        adapter.notifyDataSetChanged();
                    }
                });

                adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.show();
                break;
        }
        return true;
    }
}
