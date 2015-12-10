package com.wisdom.lol.lolwisdom.model;


/**
 * Created by Rodi on 30/11/2015.
 */
public class Skill
{
    private int skill_id;
    private String champ_name;
    private String description;
    private String detail;
    private String skill_name;
    private String skill_video;
    private String cast_char;
    private byte[] skill_img;
    private String skill_cost;
    private String skill_range;


    public Skill() { }


    // GETTERS AND SETTERS

    public int getId() { return skill_id; }

    public String getSkill_cost() {
        return skill_cost;
    }

    public void setSkill_cost(String skill_cost) {
        this.skill_cost = skill_cost;
    }

    public String getSkill_range() {
        return skill_range;
    }

    public void setSkill_range(String skill_range) {
        this.skill_range = skill_range;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    public String getChamp_name() {
        return champ_name;
    }

    public void setChamp_name(String champ_name) {
        this.champ_name = champ_name;
    }

    public void setId(int skill_id) {
        this.skill_id = skill_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkill_video() {
        return skill_video;
    }

    public void setSkill_video(String skill_video) {
        this.skill_video = skill_video;
    }

    public String getCast_char() {
        return cast_char;
    }

    public void setCast_char(String cast_char) {
        this.cast_char = cast_char;
    }

    public byte[] getSkill_img() {
        return skill_img;
    }

    public void setSkill_img(byte[] skill_img) {
        this.skill_img = skill_img;
    }
}
