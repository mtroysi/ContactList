package fr.m2sili.mtroysi.contactlist;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context context;

    public ContactAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.ligne, parent, false);

        Contact contact = getItem(position);

        TextView nom = (TextView)v.findViewById(R.id.nom);
        nom.setText(contact.getNom().toUpperCase() + " " + contact.getPrenom().toUpperCase());

        ImageView avatar = (ImageView)v.findViewById(R.id.avatar);
        avatar.setVisibility(View.GONE);

        TextView no_image = (TextView)v.findViewById(R.id.no_image);
        no_image.setTypeface(null, Typeface.ITALIC);

        return v;
    }
}
