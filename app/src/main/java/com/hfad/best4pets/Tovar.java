package com.hfad.best4pets;

public class Tovar {
    private String name;
    private String zola;
    private String zhir;
    private String sostav;
    private String protein;
    private String letchatka;
    private String image;
    private int cost;
    private int how;
    public Tovar() {
    }
    public Tovar(String name, String zola, String zhir, String sostav, String protein, String letchatka, String image, int cost, int how) {
        this.name = name;
        this.zola = zola;
        this.zhir = zhir;
        this.sostav = sostav;
        this.protein = protein;
        this.letchatka = letchatka;
        this.image = image;
        this.cost = cost;
        this.how = how;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getZola() {
        return zola;
    }
    public void setZola(String zola) {
        this.zola = zola;
    }

    public String getZhir() {
        return zhir;
    }
    public void setZhir(String zhir) {
        this.zhir = zhir;
    }
    public String getSostav() {
        return sostav;
    }
    public void setSostav(String sostav) {
        this.sostav = sostav;
    }
    public String getProtein() {
        return protein;
    }
    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getLetchatka() {
        return letchatka;
    }
    public void setLetchatka(String letchatka) {
        this.letchatka = letchatka;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getCost() {
        return cost; }
    public void setCost(int cost) {
        this.cost = cost; }
    public int getHow() {
        return how;
    }
    public void setHow(int how) {
        this.how = how;
    }
}