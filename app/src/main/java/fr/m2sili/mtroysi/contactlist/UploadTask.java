package fr.m2sili.mtroysi.contactlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.FileOutputStream;
import java.net.URL;

/**
 * Created by Morgane TROYSI on 12/5/16.
 */

public class UploadTask extends AsyncTask<Contact, Contact, Contact> {

    String urlBase = "http://www.info.univ-angers.fr/pub/barichar/page_perso/enseignements/android/";

    protected TaskFragment taskFragment = null;

    public UploadTask(TaskFragment taskFragment) {
        this.taskFragment = taskFragment;
    }

    @Override
    protected Contact doInBackground(Contact... contacts) {
        Bitmap bitmapRes = null;
        try {
            // Création d'une nouvelle image
            bitmapRes = Bitmap.createBitmap(100, 200, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bitmapRes);

            // Choix des images composant l'avatar
            String[] files = {"yeux" + getValueForAvatar(contacts[0].getPrenom()) + ".png", "cheveux" + getValueForAvatar(contacts[0].getNom()) + ".png",
                    "habits" + getValueForClothes(contacts[0].getNom(), contacts[0].getPrenom()) + ".png"};
            String storageDirectory = Environment.getExternalStorageDirectory().toString();

            int i;
            for (i = 0; i < files.length; ++i) {
                // Chargement d'une image existante
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inPreferredConfig = Bitmap.Config.RGB_565;
                Bitmap bmp = BitmapFactory.decodeStream(new URL(urlBase + files[i]).openStream(), null, o);
                // Fusion de l'image dans la nouvelle créée précédemment
                c.drawBitmap(bmp, 0, 0, null);
                //publishProgress
                double num = (double) (i + 1) / (double)files.length;
                contacts[0].setProgression((int) (num * 100));
                publishProgress(contacts[0]);
                Thread.sleep(1000);
            }

            // Sauvegarde de l'avatar créé
            FileOutputStream out = null;
            out = new FileOutputStream(storageDirectory + "/" + contacts[0].getNom());
            bitmapRes.compress(Bitmap.CompressFormat.PNG, 100, out);
            contacts[0].setAvatar(storageDirectory + "/" + contacts[0].getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts[0];
    }

    public int getValueForAvatar(String str) {
        int i, sum = 0;
        int rang = (int) 'A';
        for (i = 0; i < str.length(); ++i) {
            sum += str.charAt(i) - rang;
        }
        return (sum % 3) + 1;
    }

    public int getValueForClothes(String str1, String str2) {
        int i, sum = 0;
        int rang = (int) 'A';
        for (i = 0; i < str1.length(); ++i) {
            sum += str1.charAt(i) - rang;
        }
        for (i = 0; i < str2.length(); ++i) {
            sum += str2.charAt(i) - rang;
        }
        return (sum % 3) + 1;
    }

    @Override
    protected void onProgressUpdate(Contact... values) {
        taskFragment.onProgressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(Contact contact) {
        taskFragment.onPostExecute(contact);
    }
}
