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
        this.birthday.setText("Né le " + day + " " + getMonthName(month) + " " + year);
    }

    public String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case 1 :
                monthName = "Janvier";
                break;
            case 2 :
                monthName = "Février";
                break;
            case 3 :
                monthName = "Mars";
                break;
            case 4 :
                monthName = "Avril";
                break;
            case 5 :
                monthName = "Mai";
                break;
            case 6 :
                monthName = "Juin";
                break;
            case 7 :
                monthName = "Juillet";
                break;
            case 8 :
                monthName = "Aout";
                break;
            case 9 :
                monthName = "Septembre";
                break;
            case 10 :
                monthName = "Octobre";
                break;
            case 11 :
                monthName = "Novembre";
                break;
            case 12 :
                monthName = "Décembre";
                break;
        }
        return monthName;
    }
}
