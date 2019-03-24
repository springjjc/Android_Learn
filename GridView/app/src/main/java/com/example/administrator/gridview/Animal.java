package com.example.administrator.gridview;

/**
 * Created by Administrator on 2019/3/21.
 */
public class Animal {
    private String animal;
    private int imgId;

    public Animal(String animal,int imgId) {
        this.animal = animal;
        this.imgId=imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgId() {

        return imgId;
    }

    public void setAnimal(String animal) {

        this.animal = animal;
    }

    public String getAnimal() {

        return animal;
    }
}
