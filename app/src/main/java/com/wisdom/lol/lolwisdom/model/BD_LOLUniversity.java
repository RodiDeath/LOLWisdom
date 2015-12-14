package com.wisdom.lol.lolwisdom.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rodi on 30/11/2015.
 */
public class BD_LOLUniversity extends SQLiteOpenHelper
{
    static final String DATABASE_NAME = "BDLOLUniversity.db";
    static final int DATABASE_VERSION = 1;

    // TABLA CAMPEONES
        // DESCRIPTION
        static final String TABLE_CHAMPIONS = "champions";
        static final String COLUMN_ID = "id";
        static final String COLUMN_LORE = "lore";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_REGION = "region";
        static final String COLUMN_TIPS = "tips";
        static final String COLUMN_IMG = "img";

        // STATS
        static final String COLUMN_HP = "hp"; // health
        static final String COLUMN_MP ="mp"; // mana
        static final String COLUMN_AD = "ad"; // attack damage
        static final String COLUMN_AP = "ap"; // ability power
        static final String COLUMN_ASPD = "aspd"; // attack speed
        static final String COLUMN_MOVSPEED = "movspeed"; // movement speed
        static final String COLUMN_HPREGEN = "hpregen"; // health regeneration
        static final String COLUMN_MPREGEN = "mpregen"; // mana regenration
        static final String COLUMN_ARMOR = "armor"; // armor
        static final String COLUMN_MR = "mr"; // magic resist
        static final String COLUMN_CRITSTRIKE = "critstrike"; // critical strike
        static final String COLUMN_LS = "ls"; // life steal
        static final String COLUMN_CDR = "cdr";//cooldown reduction


    // TABLA HABILIDADES
    static final String TABLE_SKILLS = "skills";
    static final String COLUMN_CHAMP_NAME = "champ_name";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_DETAIL = "detail";
    static final String COLUMN_SKILL_NAME = "skill_name";
    static final String COLUMN_SKILL_VIDEO = "skill_video";
    static final String COLUMN_CHAR = "cast_char";
    static final String COLUMN_COST = "cost";
    static final String COLUMN_RANGE = "range";
    static final String COLUMN_SKILL_IMG = "skill_img";

    //TABLA SKIN
    static final String TABLE_SKINS = "skins";
    static final String COLUMN_SKIN_NAME = "skin_name";
    static final String COLUMN_SKIN_VIDEO = "skin_video";
    static final String COLUMN_SKIN_IMG = "skin_img";
    static final String COLUMN_SKIN_URL = "skin_url";

    static final String CREATE_CHAMPION_TABLE =
            "CREATE TABLE " + TABLE_CHAMPIONS + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_LORE + " TEXT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_REGION + " TEXT,"
                    + COLUMN_TIPS + " TEXT,"
                    + COLUMN_IMG + " BLOB,"
                    + COLUMN_HP + " TEXT,"
                    + COLUMN_MP + " TEXT,"
                    + COLUMN_AD + " TEXT,"
                    + COLUMN_AP + " TEXT,"
                    + COLUMN_ASPD + " TEXT,"
                    + COLUMN_MOVSPEED + " TEXT,"
                    + COLUMN_HPREGEN + " TEXT,"
                    + COLUMN_MPREGEN + " TEXT,"
                    + COLUMN_ARMOR + " TEXT,"
                    + COLUMN_MR + " TEXT,"
                    + COLUMN_CRITSTRIKE + " TEXT,"
                    + COLUMN_LS + " TEXT,"
                    + COLUMN_CDR + " TEXT"
                    + ");";

    static final String CREATE_SKILLS_TABLE =
            "CREATE TABLE " + TABLE_SKILLS + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CHAMP_NAME + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_DETAIL + " TEXT,"
                    + COLUMN_CHAR + " TEXT,"
                    + COLUMN_SKILL_NAME + " TEXT,"
                    + COLUMN_SKILL_VIDEO + " TEXT,"
                    + COLUMN_COST + " TEXT,"
                    + COLUMN_RANGE + " TEXT,"
                    + COLUMN_SKILL_IMG + " BLOB"
                    + ");";

    static final String CREATE_SKINS_TABLE =
            "CREATE TABLE " + TABLE_SKINS + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CHAMP_NAME + " TEXT,"
                    + COLUMN_SKIN_NAME + " TEXT,"
                    + COLUMN_SKIN_VIDEO + " TEXT,"
                    + COLUMN_SKIN_URL + " TEXT,"
                    + COLUMN_SKIN_IMG + " BLOB"
                    + ");";

    static final String DROP_TABLE_CHAMPIONS = "DROP TABLE IF EXISTS " + TABLE_CHAMPIONS;
    static final String DROP_TABLE_SKINS = "DROP TABLE IF EXISTS " + TABLE_SKINS;
    static final String DROP_TABLE_SKILLS = "DROP TABLE IF EXISTS " + TABLE_SKILLS;

    public BD_LOLUniversity(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**************************************************************************/
    /**************** METODOS SOBREESCRITOS DE SQLiteOpenHelper ***************/
    /**************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_CHAMPION_TABLE);
        db.execSQL(CREATE_SKILLS_TABLE);
        db.execSQL(CREATE_SKINS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE_CHAMPIONS);
        db.execSQL(DROP_TABLE_SKILLS);
        db.execSQL(DROP_TABLE_SKINS);

        db.execSQL(CREATE_CHAMPION_TABLE);
        db.execSQL(CREATE_SKILLS_TABLE);
        db.execSQL(CREATE_SKINS_TABLE);
    }


    /**************************************************************************/
    /******************* METODOS PROPIOS DE GESTION DE DATOS ******************/
    /**************************************************************************/

    /********* DROP DATABASE **********/

    public void resetDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(DROP_TABLE_CHAMPIONS);
        Log.e("DATABASE", "Borrada Tabla Campeones");
        db.execSQL(DROP_TABLE_SKILLS);
        Log.e("DATABASE", "Borrada Tabla Skills");
        db.execSQL(DROP_TABLE_SKINS);
        Log.e("DATABASE", "Borrada Tabla Skins");

        db.execSQL(CREATE_CHAMPION_TABLE);
        Log.e("DATABASE", "Creada Tabla Campeones");
        db.execSQL(CREATE_SKILLS_TABLE);
        Log.e("DATABASE", "Creada Tabla Skills");
        db.execSQL(CREATE_SKINS_TABLE);
        Log.e("DATABASE", "Creada Tabla Skins");

        db.close();
    }

    /*********** CHAMPIONS ***********/

    public void addChampion(Champion champ)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_LORE, champ.getLore());
        values.put(COLUMN_NAME, champ.getName());
        values.put(COLUMN_TITLE, champ.getTitle());
        values.put(COLUMN_REGION, champ.getRegion());
        values.put(COLUMN_TIPS, champ.getTips());
        values.put(COLUMN_IMG, champ.getImg());
        values.put(COLUMN_HP, champ.getHp());
        values.put(COLUMN_MP, champ.getMp());
        values.put(COLUMN_AD, champ.getAd());
        values.put(COLUMN_AP, champ.getAp());
        values.put(COLUMN_ASPD, champ.getAspd());
        values.put(COLUMN_MOVSPEED, champ.getMovspeed());
        values.put(COLUMN_HPREGEN, champ.getHpregen());
        values.put(COLUMN_MPREGEN, champ.getMpregen());
        values.put(COLUMN_ARMOR, champ.getArmor());
        values.put(COLUMN_MR, champ.getMr());
        values.put(COLUMN_CRITSTRIKE, champ.getCritstrike());
        values.put(COLUMN_LS, champ.getLs());
        values.put(COLUMN_CDR, champ.getCdr());

        db.insert(TABLE_CHAMPIONS, null, values);
        db.close();
    }

    public Champion getChampionByName(String champName){

        if(champName.contains("'")) {
            champName = champName.replaceAll("'","''");
        }
        String consulta = "SELECT * FROM " + TABLE_CHAMPIONS + " WHERE " + COLUMN_NAME + " = '" + champName + "';";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor.moveToFirst()) {

            Champion champ = new Champion();

            // DESRIPTION
            champ.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            champ.setLore(cursor.getString(cursor.getColumnIndex(COLUMN_LORE)));
            champ.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            champ.setRegion(cursor.getString(cursor.getColumnIndex(COLUMN_REGION)));
            champ.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            champ.setTips(cursor.getString(cursor.getColumnIndex(COLUMN_TIPS)));
            champ.setImg(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
            // STATS
            champ.setHp(cursor.getString(cursor.getColumnIndex(COLUMN_HP)));
            champ.setMp(cursor.getString(cursor.getColumnIndex(COLUMN_MP)));
            champ.setAd(cursor.getString(cursor.getColumnIndex(COLUMN_AD)));
            champ.setAp(cursor.getString(cursor.getColumnIndex(COLUMN_AP)));
            champ.setAspd(cursor.getString(cursor.getColumnIndex(COLUMN_ASPD)));
            champ.setMovspeed(cursor.getString(cursor.getColumnIndex(COLUMN_MOVSPEED)));
            champ.setHpregen(cursor.getString(cursor.getColumnIndex(COLUMN_HPREGEN)));
            champ.setMpregen(cursor.getString(cursor.getColumnIndex(COLUMN_MPREGEN)));
            champ.setArmor(cursor.getString(cursor.getColumnIndex(COLUMN_ARMOR)));
            champ.setMr(cursor.getString(cursor.getColumnIndex(COLUMN_MR)));
            champ.setCritstrike(cursor.getString(cursor.getColumnIndex(COLUMN_CRITSTRIKE)));
            champ.setLs(cursor.getString(cursor.getColumnIndex(COLUMN_LS)));
            champ.setCdr(cursor.getString(cursor.getColumnIndex(COLUMN_CDR)));

            return champ;
        }else{

            return null;
        }

    }
    public ArrayList<Champion> getChampions()
    {
        ArrayList<Champion> champions = new ArrayList<>();

        String consulta = "SELECT * FROM " + TABLE_CHAMPIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor.moveToFirst())
        {
            do {
                // Champion a rellenar
                Champion champ = new Champion();

                // DESRIPTION
                champ.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                champ.setLore(cursor.getString(cursor.getColumnIndex(COLUMN_LORE)));
                champ.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                champ.setRegion(cursor.getString(cursor.getColumnIndex(COLUMN_REGION)));
                champ.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                champ.setTips(cursor.getString(cursor.getColumnIndex(COLUMN_TIPS)));
                champ.setImg(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
                // STATS
                champ.setHp(cursor.getString(cursor.getColumnIndex(COLUMN_HP)));
                champ.setMp(cursor.getString(cursor.getColumnIndex(COLUMN_MP)));
                champ.setAd(cursor.getString(cursor.getColumnIndex(COLUMN_AD)));
                champ.setAp(cursor.getString(cursor.getColumnIndex(COLUMN_AP)));
                champ.setAspd(cursor.getString(cursor.getColumnIndex(COLUMN_ASPD)));
                champ.setMovspeed(cursor.getString(cursor.getColumnIndex(COLUMN_MOVSPEED)));
                champ.setHpregen(cursor.getString(cursor.getColumnIndex(COLUMN_HPREGEN)));
                champ.setMpregen(cursor.getString(cursor.getColumnIndex(COLUMN_MPREGEN)));
                champ.setArmor(cursor.getString(cursor.getColumnIndex(COLUMN_ARMOR)));
                champ.setMr(cursor.getString(cursor.getColumnIndex(COLUMN_MR)));
                champ.setCritstrike(cursor.getString(cursor.getColumnIndex(COLUMN_CRITSTRIKE)));
                champ.setLs(cursor.getString(cursor.getColumnIndex(COLUMN_LS)));
                champ.setCdr(cursor.getString(cursor.getColumnIndex(COLUMN_CDR)));

                // Añadir campeon al Array de campeones
                champions.add(champ);

            }while (cursor.moveToNext());
        }


        db.close();
        return champions;
    }

    /*********** SKILLS ***********/

    public void addSkill(Skill skill)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CHAMP_NAME, skill.getChamp_name());
        values.put(COLUMN_DESCRIPTION, skill.getDescription());
        values.put(COLUMN_DETAIL, skill.getDetail());
        values.put(COLUMN_SKILL_NAME, skill.getSkill_name());
        values.put(COLUMN_SKILL_VIDEO, skill.getSkill_video());
        values.put(COLUMN_CHAR, skill.getCast_char());
        values.put(COLUMN_COST, skill.getSkill_cost());
        values.put(COLUMN_RANGE, skill.getSkill_range());
        values.put(COLUMN_SKILL_IMG, skill.getSkill_img());

        db.insert(TABLE_SKILLS, null, values);
        db.close();
    }

    public ArrayList<Skill> getAllSkills()
    {
        ArrayList<Skill> skills = new ArrayList<>();

        String consulta = "SELECT * FROM " + TABLE_SKILLS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor.moveToFirst())
        {
            do {
                // Skill a rellenar
                Skill skill = new Skill();

                skill.setSkill_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                skill.setChamp_name(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_NAME)));
                skill.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                skill.setDetail(cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL)));
                skill.setSkill_name(cursor.getString(cursor.getColumnIndex(COLUMN_SKILL_NAME)));
                skill.setSkill_video(cursor.getString(cursor.getColumnIndex(COLUMN_SKILL_VIDEO)));
                skill.setCast_char(cursor.getString(cursor.getColumnIndex(COLUMN_CHAR)));
                skill.setSkill_cost(cursor.getString(cursor.getColumnIndex(COLUMN_COST)));
                skill.setSkill_range(cursor.getString(cursor.getColumnIndex(COLUMN_RANGE)));
                skill.setSkill_img(cursor.getBlob(cursor.getColumnIndex(COLUMN_SKILL_IMG)));

                // Añadir skill al Array de skills
                skills.add(skill);

            }while (cursor.moveToNext());
        }
        db.close();
        return skills;
    }

    public ArrayList<Skill> getSkillsByChampName(String champName)
    {
        ArrayList<Skill> skills = new ArrayList<>();

        String consulta = "SELECT * FROM " + TABLE_SKILLS + " WHERE " + COLUMN_CHAMP_NAME + " = '" + champName + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor.moveToFirst())
        {
            do {
                // Skill a rellenar
                Skill skill = new Skill();

                skill.setSkill_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                skill.setChamp_name(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_NAME)));
                skill.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                skill.setDetail(cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL)));
                skill.setSkill_name(cursor.getString(cursor.getColumnIndex(COLUMN_SKILL_NAME)));
                skill.setSkill_video(cursor.getString(cursor.getColumnIndex(COLUMN_SKILL_VIDEO)));
                skill.setCast_char(cursor.getString(cursor.getColumnIndex(COLUMN_CHAR)));
                skill.setSkill_cost(cursor.getString(cursor.getColumnIndex(COLUMN_COST)));
                skill.setSkill_range(cursor.getString(cursor.getColumnIndex(COLUMN_RANGE)));
                skill.setSkill_img(cursor.getBlob(cursor.getColumnIndex(COLUMN_SKILL_IMG)));

                // Añadir skill al Array de skills
                skills.add(skill);

            }while (cursor.moveToNext());
        }
        db.close();
        return skills;
    }


    /*********** SKINS ***********/

    public void addSkin(Skin skin)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CHAMP_NAME, skin.getChamp_name());
        values.put(COLUMN_SKIN_NAME, skin.getSkin_name());
        values.put(COLUMN_SKIN_VIDEO, skin.getSkin_video());
        values.put(COLUMN_SKIN_IMG, skin.getSkin_img());
        values.put(COLUMN_SKIN_URL, skin.getSkin_url());

        db.insert(TABLE_SKINS, null, values);
        db.close();
    }

    public ArrayList<Skin> getSkins(String champName)
    {
        ArrayList<Skin> skins = new ArrayList<>();

        String consulta = "SELECT * FROM " + TABLE_SKINS + " WHERE " + COLUMN_CHAMP_NAME + " = '" + champName + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor.moveToFirst())
        {
            do {
                // Skin a rellenar
                Skin skin = new Skin();

                skin.setSkin_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                skin.setChamp_name(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_NAME)));
                skin.setSkin_name(cursor.getString(cursor.getColumnIndex(COLUMN_SKIN_NAME)));
                skin.setSkin_video(cursor.getString(cursor.getColumnIndex(COLUMN_SKIN_VIDEO)));
                skin.setSkin_img(cursor.getBlob(cursor.getColumnIndex(COLUMN_SKIN_IMG)));
                skin.setSkin_url(cursor.getString(cursor.getColumnIndex(COLUMN_SKIN_URL)));

                // Añadir skin al Array de skins
                skins.add(skin);

            }while (cursor.moveToNext());
        }
        db.close();
        return skins;
    }
}
