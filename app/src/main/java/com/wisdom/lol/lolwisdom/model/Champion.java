package com.wisdom.lol.lolwisdom.model;


/**
 * Created by Rodi on 30/11/2015.
 */
public class Champion
{
    // DESCRIPTION
    private int id;
    private String lore; // champion history
    private String name;
    private String tips;
    private String title;
    private String region;
    private byte[] img;

    // STATS
    private String hp; // health
    private String mp; // mana
    private String ad; // attack damage
    private String ap; // ability power
    private String aspd; // attack speed
    private String movspeed; // movement speed
    private String hpregen; // health regeneration
    private String mpregen; // mana regenration
    private String armor; // armor
    private String mr; // magic resist
    private String critstrike; // critical strike
    private String ls; // life steal
    private String cdr;//cooldown reduction





    public Champion (){}




    // GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAspd() {
        return aspd;
    }

    public void setAspd(String aspd) {
        this.aspd = aspd;
    }

    public String getMovspeed() {
        return movspeed;
    }

    public void setMovspeed(String movspeed) {
        this.movspeed = movspeed;
    }

    public String getHpregen() {
        return hpregen;
    }

    public void setHpregen(String hpregen) {
        this.hpregen = hpregen;
    }

    public String getMpregen() {
        return mpregen;
    }

    public void setMpregen(String mpregen) {
        this.mpregen = mpregen;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getMr() {
        return mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getCritstrike() {
        return critstrike;
    }

    public void setCritstrike(String critstrike) {
        this.critstrike = critstrike;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public String getCdr() {
        return cdr;
    }

    public void setCdr(String cdr) {
        this.cdr = cdr;
    }
}
