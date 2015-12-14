package com.wisdom.lol.lolwisdom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisdom.lol.lolwisdom.model.BD_LOLUniversity;
import com.wisdom.lol.lolwisdom.model.Champion;
import com.wisdom.lol.lolwisdom.model.Skill;

import java.util.ArrayList;

public class SkillDataActivity extends AppCompatActivity {

    String champ_name="";
    BD_LOLUniversity bd= new BD_LOLUniversity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_data);

        champ_name= getIntent().getStringExtra("champ_name");
        ArrayList<Skill> skills;
        skills= bd.getSkillsByChampName(champ_name);

        //IMAGENES
        ImageView pasiveSkillimg= (ImageView)findViewById(R.id.iv_pasiveSkillimage);
        ImageView qSkillimg= (ImageView)findViewById(R.id.iv_qSkillimage);
        ImageView wSkillimg= (ImageView)findViewById(R.id.iv_wSkillimage);
        ImageView eSkillimg= (ImageView)findViewById(R.id.iv_eSkillimage);
        ImageView rSkillimg= (ImageView)findViewById(R.id.iv_rSkillimage);

        //PASIVA
        TextView pasiveSkillTitle= (TextView)findViewById(R.id.tv_pasiveSkillTitle);
        TextView pasiveSkillDescription= (TextView)findViewById(R.id.tv_pasiveSkillDescription);

        //Q
        TextView qSkillTitle= (TextView)findViewById(R.id.tv_qSkillTitle);
        TextView qSkillCost= (TextView)findViewById(R.id.tv_qSkillCost);
        TextView qSkillRange= (TextView)findViewById(R.id.tv_qSkillRange);
        TextView qSkillDescription= (TextView)findViewById(R.id.tv_qSkillDescription);

        //W
        TextView wSkillTitle= (TextView)findViewById(R.id.tv_wSkillTitle);
        TextView wSkillCost= (TextView)findViewById(R.id.tv_wSkillCost);
        TextView wSkillRange= (TextView)findViewById(R.id.tv_wSkillRange);
        TextView wSkillDescription= (TextView)findViewById(R.id.tv_wSkillDescription);

        //E
        TextView eSkillTitle= (TextView)findViewById(R.id.tv_eSkillTitle);
        TextView eSkillCost= (TextView)findViewById(R.id.tv_eSkillCost);
        TextView eSkillRange= (TextView)findViewById(R.id.tv_eSkillRange);
        TextView eSkillDescription= (TextView)findViewById(R.id.tv_eSkillDescription);

        //R
        TextView rSkillTitle= (TextView)findViewById(R.id.tv_rSkillTitle);
        TextView rSkillCost= (TextView)findViewById(R.id.tv_rSkillCost);
        TextView rSkillRange= (TextView)findViewById(R.id.tv_rSkillRange);
        TextView rSkillDescription= (TextView)findViewById(R.id.tv_rSkillDescription);

        //IMAGENES

        pasiveSkillimg.setImageBitmap(byteArrayToBitmap(skills.get(0).getSkill_img()));
        qSkillimg.setImageBitmap(byteArrayToBitmap(skills.get(1).getSkill_img()));
        wSkillimg.setImageBitmap(byteArrayToBitmap(skills.get(2).getSkill_img()));
        eSkillimg.setImageBitmap(byteArrayToBitmap(skills.get(3).getSkill_img()));
        rSkillimg.setImageBitmap(byteArrayToBitmap(skills.get(4).getSkill_img()));

        //PASIVA

        pasiveSkillTitle.setText(skills.get(0).getSkill_name());
        pasiveSkillDescription.setText(skills.get(0).getDescription());

        //Q

        qSkillTitle.setText(skills.get(1).getSkill_name());
        qSkillCost.setText(skills.get(1).getSkill_cost());
        qSkillRange.setText(skills.get(1).getSkill_range());
        qSkillDescription.setText(skills.get(1).getDescription()+"\n\n"+skills.get(1).getDetail());

        //W

        wSkillTitle.setText(skills.get(2).getSkill_name());
        wSkillCost.setText(skills.get(2).getSkill_cost());
        wSkillRange.setText(skills.get(2).getSkill_range());
        wSkillDescription.setText(skills.get(2).getDescription()+"\n\n"+skills.get(2).getDetail());

        //E

        eSkillTitle.setText(skills.get(3).getSkill_name());
        eSkillCost.setText(skills.get(3).getSkill_cost());
        eSkillRange.setText(skills.get(3).getSkill_range());
        eSkillDescription.setText(skills.get(3).getDescription()+"\n\n"+skills.get(3).getDetail());

        //R

        rSkillTitle.setText(skills.get(4).getSkill_name());
        rSkillCost.setText(skills.get(4).getSkill_cost());
        rSkillRange.setText(skills.get(4).getSkill_range());
        rSkillDescription.setText(skills.get(4).getDescription()+"\n\n"+skills.get(4).getDetail());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent champName = new Intent();
        champName.putExtra("champ_name", champ_name);
        setResult(17, champName);
        finish();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){

            Intent champName = new Intent();
            champName.putExtra("champ_name", champ_name);
            setResult(17, champName);
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_skill_data, menu);
        return true;
    }
//comentario prueba
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home)
        {
            Intent champName = new Intent();
            champName.putExtra("champ_name", champ_name);
            setResult(17, champName);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private Bitmap byteArrayToBitmap(byte[] byteArray)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bitmap;
    }
}
