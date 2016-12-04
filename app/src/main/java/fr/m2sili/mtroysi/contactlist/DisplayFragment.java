package fr.m2sili.mtroysi.contactlist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class DisplayFragment extends Fragment {
    private TextView name;
    private TextView mail;
    private TextView birthday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.display_layout, container, false);

        name = (TextView) rootView.findViewById(R.id.content_name);
        mail = (TextView) rootView.findViewById(R.id.content_mail);
        birthday = (TextView) rootView.findViewById(R.id.content_birthday);

        return rootView;
    }

    public void displayContact(String name, String mail, int day, int month, int year) {
        this.name.setText(name);
        this.mail.setText(mail);
        this.birthday.setText("Né le " + day + " " + month + " " + year);
    }
}
