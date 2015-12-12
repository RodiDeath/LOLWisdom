package com.wisdom.lol.lolwisdom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wisdom.lol.lolwisdom.model.BD_LOLUniversity;
import com.wisdom.lol.lolwisdom.model.Champion;

import java.util.ArrayList;

public class ChampionListActivity extends AppCompatActivity
{
    GridView gridViewChampionList;
    BD_LOLUniversity bd = new BD_LOLUniversity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_list);

        ArrayList<byte[]> championsImages = new ArrayList<>();
        ArrayList<String> championNames = new ArrayList<>();
        String[] championNamesArray;

        ArrayList<Champion> champions = new ArrayList<>();
        champions = bd.getChampions();

        for (Champion champ: champions)
        {
            championsImages.add(champ.getImg());
            championNames.add(champ.getName());
        }

        championNamesArray = new String[championNames.size()];
        championNamesArray = championNames.toArray(championNamesArray);

        gridViewChampionList = (GridView) findViewById(R.id.gridViewChampionList);
        gridViewChampionList.setAdapter(new ImageAdapter(this, championNamesArray, championsImages));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_champion_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private Bitmap byteArrayToBitmap(byte[] byteArray)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bitmap;
    }

    public class ImageAdapter extends BaseAdapter
    {
        String [] result;
        Context context;
        ArrayList<byte[]> imagesChamps;
        byte[][] imageChampsArray;

        private LayoutInflater inflater=null;

        public ImageAdapter(Context c, String[] champNames, ArrayList<byte[]> images)
        {
            // TODO Auto-generated constructor stub
            result=champNames;
            context=c;
            imagesChamps=images;
            imageChampsArray = new byte[images.size()][];
            imageChampsArray = images.toArray(imageChampsArray);


            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder
        {
            TextView tv;
            ImageView img;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            Holder holder=new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.image_champion_list_layout, null);
            holder.tv=(TextView) rowView.findViewById(R.id.tvNombreChampList);
            holder.img=(ImageView) rowView.findViewById(R.id.imageChampList);


            holder.tv.setText(result[position]);

            holder.img.setImageBitmap(byteArrayToBitmap(imageChampsArray[position]));


            rowView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, result[position], Toast.LENGTH_SHORT).show();
                }
            });
            return rowView;
        }

    }

}
