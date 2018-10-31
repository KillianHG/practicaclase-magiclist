package com.example.a41011561p.cartaslareunion;

import java.io.Serializable;

public class Cartas implements Serializable {
    String name;
    String colors;
    String rarity;
    String imageUrl;
    int multiverseid;

    public String getName() {
        return name;
    }

    public String getColors() {
        return colors;
    }

    public String getRarity() {
        return rarity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getMultiverseid() {
        return multiverseid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMultiverseid(int multiverseid) {
        this.multiverseid = multiverseid;
    }

    @Override
    public String toString() {
        return "Cartas{" +
                "name='" + name + '\'' +
                ", colors='" + colors + '\'' +
                ", rarity='" + rarity + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", multiverseid=" + multiverseid +
                '}';
    }
}