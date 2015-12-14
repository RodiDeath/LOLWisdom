package com.wisdom.lol.lolwisdom;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wisdom.lol.lolwisdom.model.BD_LOLUniversity;
import com.wisdom.lol.lolwisdom.model.Champion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PatchNotesActivity extends AppCompatActivity {

    private WebView wvPatchnotes;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        String region = "";
        String lang = "";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch_notes);

        wvPatchnotes  = (WebView)findViewById(R.id.webViewPatchNotes);

        wvPatchnotes.getSettings().setJavaScriptEnabled(true); // enable javascript
        wvPatchnotes.getSettings().setBuiltInZoomControls(true);

        region = getIntent().getStringExtra("region");
        lang = getIntent().getStringExtra("lang");

        new getPatchNotes().execute(region,lang);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patch_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings)
        {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private class getPatchNotes extends AsyncTask<String, Void, Boolean>
    {
        String urlLastPatchNotes = "";

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            Toast.makeText(getApplicationContext(), "Please wait a moment, the website will load soon...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Boolean doInBackground(String... params)
        {
            Document doc;
            String region = params[0];
            String idioma = params[1];
            String urlRotationsList = "http://"+region+".leagueoflegends.com/"+idioma+"/news/game-updates/patch/";

            try
            {
                doc = Jsoup.connect(urlRotationsList).get();

                Element lastPatchNotes = doc.select("a[href^=/"+idioma+"/news/game-updates/patch/]").first();
                //Element lastPatchNotes = doc.select("h4").first().select("a").first();

                String relativeUrl = lastPatchNotes.attr("href");

                urlLastPatchNotes = "http://"+region+".leagueoflegends.com"+relativeUrl;
                Log.e("URL", "URL Patch Notes: "+urlLastPatchNotes);

                return true;

            } catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean done)
        {
            super.onPostExecute(done);

            if (done)
            {
                wvPatchnotes.loadUrl(urlLastPatchNotes);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Cannot connect to server", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Please, test your internet connection", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
