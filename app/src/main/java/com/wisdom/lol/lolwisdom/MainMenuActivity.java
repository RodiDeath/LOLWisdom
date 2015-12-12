package com.wisdom.lol.lolwisdom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.wisdom.lol.lolwisdom.model.BD_LOLUniversity;
import com.wisdom.lol.lolwisdom.model.Champion;
import com.wisdom.lol.lolwisdom.model.Skill;
import com.wisdom.lol.lolwisdom.model.Skin;


public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    ProgressDialog progressDialog;
    ArrayList<Champion> freeRotationChamps = new ArrayList<>();


    int progress=0;

    // Comentario de Prueba

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        new getFreeRotation().execute("euw", "es");

        //new getAllChampionsData().execute();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mainmenu)
        {

        } else if (id == R.id.nav_champions)
        {
            Intent intent = new Intent(getApplicationContext(), ChampionListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings)
        {

        }else if (id == R.id.nav_refresh)
        {
            new getAllChampionsData().execute();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void championListClicked(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ChampionListActivity.class);
        startActivity(intent);
    }

    private class getAllChampionsData extends AsyncTask<Void, String, String>
    {
        String content;

        @Override
        protected void onProgressUpdate(String... msg)
        {
            super.onProgressUpdate(msg);
            progressDialog.setMessage("Invocando a " + msg[0]);
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress = 0;
            progressDialog = new ProgressDialog(MainMenuActivity.this);
            progressDialog.setIndeterminate(false);
            progressDialog.setTitle("Descargando Datos...");
            progressDialog.setMessage("");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(progress);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params)
        {
            String title = "";

            Elements championList = getChampionList();

            content = String.valueOf(championList.size());

            progressDialog.setMax(championList.size());



            for (Element e:championList)
            {
                publishProgress(e.text().toString());
                getChampionData(e.text(), "euw" , "es");
                progress++;
                progressDialog.setProgress(progress);
            }



            return title;
        }

        @Override
        protected void onPostExecute(String result)
        {
            progressDialog.dismiss();
        }

        private Elements getChampionList()
        {
            Elements chamopionList = new Elements();
            Document doc;

            try
            {
                doc = Jsoup.connect("http://leagueoflegends.wikia.com/wiki/List_of_champions").get();
                Elements campeones = doc.select("span[class^=character]");
                campeones = campeones.select("a[href]");

                for (Element e:campeones)
                {
                    if (e.text() != "")
                    {
                        chamopionList.add(e);
                    }
                }
                // Reset Database
                BD_LOLUniversity bd_lolUniversity = new BD_LOLUniversity(getApplicationContext());
                bd_lolUniversity.resetDatabase();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return chamopionList;

        }

        private void getChampionData(String championName, String region, String idioma)
        {
            Document doc;
            BD_LOLUniversity bd_lolUniversity = new BD_LOLUniversity(getApplicationContext());

            String champName = championName.replaceAll("\\W", "");
            champName = champName.toLowerCase();

            if (champName.equals("wukong"))
            {
                champName = "monkeyking"; // La mayor tocada de pelotas de la historia
            }

            //String url = "http://gameinfo.euw.leagueoflegends.com/es/game-info/champions/" + champName + "/";
            String url = "http://gameinfo."+region+".leagueoflegends.com/"+idioma+"/game-info/champions/" + champName + "/";

            try
            {
                Champion champion = new Champion();
                Elements elements;
                Element element;
                Bitmap imgChamp;
                doc = Jsoup.connect(url).get();


                element = doc.getElementById("champion-lore");
                champion.setLore(element.text());

                champion.setName(championName);

                elements = doc.select("em");
                champion.setTitle(elements.first().text());

                elements = doc.select("div[class^=faction]");
                champion.setRegion(elements.first().text());

                champion.setImg(null);

                elements = doc.select("span[class*=stat]");

                if (elements.size() > 16)
                {
                    champion.setHp(elements.get(1).text());
                    champion.setMp(elements.get(3).text());
                    champion.setAd(elements.get(5).text());
                    champion.setAspd(elements.get(7).text());
                    champion.setMovspeed(elements.get(9).text());
                    champion.setHpregen(elements.get(11).text());
                    champion.setMpregen(elements.get(13).text());
                    champion.setArmor(elements.get(15).text());
                    champion.setMr(elements.get(17).text());
                }
                else
                {
                    champion.setHp(elements.get(1).text());
                    champion.setAd(elements.get(3).text());
                    champion.setAspd(elements.get(5).text());
                    champion.setMovspeed(elements.get(7).text());
                    champion.setHpregen(elements.get(9).text());
                    champion.setArmor(elements.get(11).text());
                    champion.setMr(elements.get(13).text());
                }

                // Imagen Campeon
                Element img = doc.select("img").first();
                String src = img.attr("src");
                InputStream input = new URL(src).openStream();
                imgChamp = BitmapFactory.decodeStream(input);

                champion.setImg(bitmapToByteArray(imgChamp));


                bd_lolUniversity.addChampion(champion);
                Log.w("ChampionInstert", "Campeon Insertado: " + championName);

                if (champName.equals("monkeyking"))
                {
                    champName = "wukong"; // La mayor tocada de pelotas de la historia
                }

                getChampionSkills(doc, champName);
                getChampionsSkins(doc, champName);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void getChampionSkills(Document doc, String champName)
        {
            BD_LOLUniversity bd_lolUniversity = new BD_LOLUniversity(getApplicationContext());

            Elements elements;
            Element element_aux;
            Skill[] skills = new Skill[9];

            for (int i = 0; i < 9; i++)
            {
                skills[i] = new Skill();
            }

            elements=doc.select("div[id^=spell]");

            int skillCount=0;
            for (Element e :elements)
            {
                element_aux=e.select("p").first();

                String cadena=element_aux.html();
                cadena = cadena.replaceAll("<b>", "");
                cadena = cadena.replaceAll("</b>", "");

                String []costeRango = cadena.split("<br>");

                if (skillCount > 0) // Skills
                {
                    skills[skillCount].setSkill_cost(costeRango[0]); // COSTE
                    skills[skillCount].setSkill_range(costeRango[1]); // RANGO

                    skills[skillCount].setDescription(e.getElementsByClass("spell-description").first().text()); // DESCRIPCION SKILL
                    skills[skillCount].setDetail(e.getElementsByClass("spell-tooltip").first().text()); // TOOLTIP SKILL
                }
                else // Pasiva
                {
                    skills[skillCount].setDescription(e.getElementsByClass("spell-description").first().text()); // DESCRIPCION PASIVA
                }

                skills[skillCount].setSkill_name(e.select("h3").first().text()); // NOMBRE SKILL

                skills[skillCount].setChamp_name(champName);

                switch (skillCount)
                {
                    case 0:
                        skills[skillCount].setCast_char("P");
                        break;
                    case 1:
                        skills[skillCount].setCast_char("Q");
                        break;
                    case 2:
                        skills[skillCount].setCast_char("W");
                        break;
                    case 3:
                        skills[skillCount].setCast_char("E");
                        break;
                    case 4:
                        skills[skillCount].setCast_char("R");
                        break;
                    case 5:
                        skills[skillCount].setCast_char("Q");
                        break;
                    case 6:
                        skills[skillCount].setCast_char("W");
                        break;
                    case 7:
                        skills[skillCount].setCast_char("E");
                        break;
                    case 8:
                        skills[skillCount].setCast_char("R");
                        break;

                }

                // Imagen Skill
                try
                {
                    Bitmap imgSkill;
                    Element img = e.select("img").first();
                    String src = img.attr("src");
                    src = src.replaceAll(" ","%20");
                    InputStream input = null;
                    input = new URL(src).openStream();
                    imgSkill = BitmapFactory.decodeStream(input);
                    skills[skillCount].setSkill_img(bitmapToByteArray(imgSkill));
                    Log.e("SKILLIMG", "IMG: "+src);

                } catch (IOException e1)
                {
                    e1.printStackTrace();
                }


                skillCount++;
            }

            for (Skill s:skills)
            {
                if (s.getSkill_name() != null)
                {
                    bd_lolUniversity.addSkill(s);
                    Log.w("SkillInstert", "SkillInsertada: " + s.getSkill_name());
                }
            }
        }

        private void getChampionsSkins(Document doc, String champName)
        {
            BD_LOLUniversity bd_lolUniversity = new BD_LOLUniversity(getApplicationContext());

            Elements elementsSkins = doc.select("a[class^=skins]");

            for (Element e: elementsSkins)
            {
                Skin skin = new Skin();
                skin.setChamp_name(champName);

                skin.setSkin_name(e.attr("title"));

                // IMG SKIN URL
                String src = "";
                src = e.attr("href");
                src = src.replaceAll(" ", "%20");
                skin.setSkin_url(src);

                Log.e("SKINIMG", "IMGSKIN: " + src);
                bd_lolUniversity.addSkin(skin);
            }
        }
    }

    private class getFreeRotation extends AsyncTask<String, Void, Boolean>
    {
        String labelRotacion = "";
        BD_LOLUniversity bdrotation = new BD_LOLUniversity(getApplicationContext());

        @Override
        protected Boolean doInBackground(String... params)
        {
            Document doc;
            String region = params[0];
            String idioma = params[1];
            String urlRotationsList = "http://"+region+".leagueoflegends.com/"+idioma+"/news/champions-skins/free-rotation/";

            try
            {
                doc = Jsoup.connect(urlRotationsList).get();

                labelRotacion = doc.select("h1").first().text();
                Element lastFreeRotation = doc.select("h4").first().select("a").first();

                String relativeUrl = lastFreeRotation.attr("href");

                String urlLastRotation = "http://"+region+".leagueoflegends.com"+relativeUrl;
                doc = Jsoup.connect(urlLastRotation).get();

                Elements championsInRotation = doc.select("span[class=champion-name]");

                for (Element e:  championsInRotation)
                {
                    freeRotationChamps.add(bdrotation.getChampionByName(e.text()));
                }
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
            ImageView[]array_iv = new ImageView[10];

            array_iv[0]= (ImageView)findViewById(R.id.imageView1);
            array_iv[1]= (ImageView)findViewById(R.id.imageView2);
            array_iv[2]= (ImageView)findViewById(R.id.imageView3);
            array_iv[3]= (ImageView)findViewById(R.id.imageView4);
            array_iv[4]= (ImageView)findViewById(R.id.imageView5);
            array_iv[5]= (ImageView)findViewById(R.id.imageView6);
            array_iv[6]= (ImageView)findViewById(R.id.imageView7);
            array_iv[7]= (ImageView)findViewById(R.id.imageView8);
            array_iv[8]= (ImageView)findViewById(R.id.imageView9);
            array_iv[9]= (ImageView)findViewById(R.id.imageView10);

            super.onPostExecute(done);

            int i=0;
            for(Champion champ: freeRotationChamps){

                array_iv[i].setImageBitmap(byteArrayToBitmap(champ.getImg()));
                i++;

            }

            if (done)
            {
                TextView tvLabelRotacion = (TextView) findViewById(R.id.tvRotacion);
                tvLabelRotacion.setText(labelRotacion);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No se ha podido conectar con el servidor", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private Bitmap byteArrayToBitmap(byte[] byteArray)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bitmap;
    }

    private byte[] bitmapToByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, blob);
        byte[] byteArray = blob.toByteArray();

        return byteArray;
    }

}
