package fr.m2sili.mtroysi.contactlist;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;

    public ContactAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, parent, false);

        Contact contact = getItem(position);

        TextView nom = (TextView) v.findViewById(R.id.nom);
        nom.setText(contact.getNom().toUpperCase() + " " + contact.getPrenom().toUpperCase());

        TextView no_image = (TextView) v.findViewById(R.id.no_image);
        ProgressBar bar = (ProgressBar)v.findViewById(R.id.progress);

        ImageView avatar = (ImageView) v.findViewById(R.id.avatar);

        // Affichage de la bar de progression
        if (!contact.isInProgress() ) {
            bar.setVisibility(View.GONE);
        }
        else {
            bar.setVisibility(View.VISIBLE);
            bar.setProgress(contact.getProgression());
        }

        // Affichage de l'avatar
        if(!contact.getAvatar().isEmpty()) {
            avatar.setImageBitmap(BitmapFactory.decodeFile(contact.getAvatar()));
            avatar.setVisibility(View.VISIBLE);
            no_image.setVisibility(View.GONE);
            bar.setVisibility(View.GONE);
        } else {
            avatar.setVisibility(View.GONE);
            bar.setVisibility(View.VISIBLE);
            no_image.setTypeface(null, Typeface.ITALIC);
        }

        return v;
    }
}
