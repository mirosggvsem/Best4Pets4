package com.hfad.best4pets;

public class Pizza {
    private String name;
    private int imageResourceId;
    public static Pizza[] pizzas = {
            new Pizza("Для кошек", R.drawable.cat),
            new Pizza("Для грызунов", R.drawable.homo),
            new Pizza("Для рыбок", R.drawable.fish),
            new Pizza("Для птиц", R.drawable.birds),
            new Pizza("Для собак", R.drawable.fordogs),
    };
    Pizza(String name, int imageResourceId){
        this.name = name;
        this.imageResourceId =imageResourceId;
    }
    public String getName(){
        return name;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
}
