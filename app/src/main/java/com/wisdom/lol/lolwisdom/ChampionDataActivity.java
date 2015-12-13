package com.wisdom.lol.lolwisdom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisdom.lol.lolwisdom.model.BD_LOLUniversity;
import com.wisdom.lol.lolwisdom.model.Champion;

public class ChampionDataActivity extends AppCompatActivity {

    String champ_name="";
    BD_LOLUniversity bd= new BD_LOLUniversity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_data);

        champ_name= getIntent().getStringExtra("champ_name");
        Champion champ= new Champion();
        champ= bd.getChampionByName(champ_name);


        ImageView champimg= (ImageView)findViewById(R.id.iv_champion);
        TextView tvName= (TextView)findViewById(R.id.tv_championName);
        TextView tvTitle= (TextView)findViewById(R.id.tv_championTitle);
        TextView tvHp= (TextView)findViewById(R.id.tv_hpChampionValue);
        TextView tvMp= (TextView)findViewById(R.id.tv_manaChampionValue);
        TextView tvAd= (TextView)findViewById(R.id.tv_adChampionValue);
        TextView tvArmor= (TextView)findViewById(R.id.tv_armorChampionValue);
        TextView tvHpregen= (TextView)findViewById(R.id.tv_hpregenChampionValue);
        TextView tvMpregen= (TextView)findViewById(R.id.tv_manaregenChampionValue);
        TextView tvAspd= (TextView)findViewById(R.id.tv_aspdChampionValue);
        TextView tvMr= (TextView)findViewById(R.id.tv_mrChampionValue);
        TextView tvlore= (TextView)findViewById(R.id.tv_championLore);


        champimg.setImageBitmap(byteArrayToBitmap(champ.getImg()));
        tvName.setText(champ.getName());
        tvTitle.setText(champ.getTitle());
        tvHp.setText(champ.getHp().replaceAll("[A-Za-z +]", ""));

        if(champ.getMp()!=null) {
            tvMp.setText(champ.getMp().replaceAll("[A-Za-z +]", ""));
            tvMpregen.setText(champ.getMpregen().replaceAll("[A-Za-z +]", ""));
        }else{
            tvMp.setText("N/A");
            tvMpregen.setText("N/A");
        }
        tvAd.setText(champ.getAd().replaceAll("[A-Za-z +]", ""));
        tvArmor.setText(champ.getArmor().replaceAll("[A-Za-z +]",""));
        tvHpregen.setText(champ.getHpregen().replaceAll("[A-Za-z +]", ""));

        tvAspd.setText(champ.getAspd().replaceAll("[A-Za-z +]", ""));
        tvMr.setText(champ.getMr().replaceAll("[A-Za-z +]", ""));
        tvlore.setText(champ.getLore());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_champion_data, menu);
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
    private Bitmap byteArrayToBitmap(byte[] byteArray)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bitmap;
    }
}
