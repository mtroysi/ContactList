package fr.m2sili.mtroysi.contactlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
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

import java.io.File;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class ContactFragment extends ListFragment implements TaskFragment.TaskCallBacks {

    private ContactList mainActivity = null;
    private ContactAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (ContactList) activity;
    }

    public void setup() {
        Contact c1 = new Contact("Dupont", "Jean", 26, 5, 1994, "test@test.fr");
        Contact c2 = new Contact("Smith", "John", 26, 5, 1994, "test@test.fr");

        onContactAdded(c1);
        onContactAdded(c2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        setHasOptionsMenu(true);
        adapter = new ContactAdapter(inflater.getContext(), R.layout.ligne);
        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mainActivity.getAdapter() != null) {
            // Si l'adapter est null et qu'il y a des éléments dans le FSI (-> rotation)
            this.populate();
        } else {
            // Si l'adapter n'est pas null, on peuple celui du FSI (-> 1er lancement)
            mainActivity.initiateAdapter();
            setup();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Contact contact = adapter.getItem(position);

        DisplayFragment displayFragment=(DisplayFragment)getFragmentManager().findFragmentById(R.id.display);
        if(getResources().getBoolean(R.bool.land_value) && displayFragment != null){
            displayFragment.displayContact(contact.getNom().toUpperCase() + " " + contact.getPrenom(), contact.getMail(), contact.getDay(), contact.getMonth(), contact.getYear(), contact.getAvatar());
        } else {
            Intent intent = new Intent(mainActivity, Display.class);
            intent.putExtra("name", contact.getNom().toUpperCase() + " " + contact.getPrenom().toUpperCase());
            intent.putExtra("mail", contact.getMail());
            intent.putExtra("day", contact.getDay());
            intent.putExtra("month", contact.getMonth());
            intent.putExtra("year", contact.getYear());
            intent.putExtra("avatar", contact.getAvatar());
            startActivity(intent);
        }
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
                        onContactAdded(contact);
                    }
                });

                adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                });
                adb.show();
                break;
        }
        return true;
    }

    @Override
    public void onContactUpdate(Contact contact) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onContactDone(Contact contact) {
        adapter.getItem(contact.getPosition()).setInProgress(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onContactAdded(Contact contact) {
        adapter.add(contact);
        mainActivity.onContactAdded(contact);
        adapter.notifyDataSetChanged();
        if (!new File(contact.getAvatar()).exists()) {
            contact.setInProgress(true);
            mainActivity.launchUpload(contact);
        }
    }

    public void populate() {
        int i;
        for(i = 0; i < mainActivity.getAdapter().getCount(); ++i) {
            adapter.add(mainActivity.getAdapter().getItem(i));
        }
    }
}
