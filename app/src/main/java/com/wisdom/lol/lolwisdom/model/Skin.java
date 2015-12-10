package com.wisdom.lol.lolwisdom.model;


/**
 * Created by Rodi on 30/11/2015.
 */
public class Skin
{
    private int skin_id;
    private String champ_name;
    private String skin_name;
    private String skin_video;
    private byte[] skin_img;
    private String skin_url;

    public Skin() { }





    // GETTERS AND SETTERS


    public String getSkin_url() {
        return skin_url;
    }

    public void setSkin_url(String skin_url) {
        this.skin_url = skin_url;
    }

    public int getSkin_id() {
        return skin_id;
    }

    public String getChamp_name() {
        return champ_name;
    }

    public void setChamp_name(String champ_name) {
        this.champ_name = champ_name;
    }

    public void setSkin_id(int skin_id) {
        this.skin_id = skin_id;
    }

    public String getSkin_name() {
        return skin_name;
    }

    public void setSkin_name(String skin_name) {
        this.skin_name = skin_name;
    }

    public String getSkin_video() {
        return skin_video;
    }

    public void setSkin_video(String skin_video) {
        this.skin_video = skin_video;
    }

    public byte[] getSkin_img() {
        return skin_img;
    }

    public void setSkin_img(byte[] skin_img) {
        this.skin_img = skin_img;
    }
}
