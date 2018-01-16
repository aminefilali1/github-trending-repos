package com.amineprojs.mccgithub.models;

public class Repository {
    private int id;
    private String name;
    private String description;
    private int stars;
    private String ownerName;
    private String ownerPhoto;

    public Repository(int id, String name, String description, int stars, String ownerName, String ownerPhoto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.ownerName = ownerName;
        this.ownerPhoto = ownerPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoto() {
        return ownerPhoto;
    }

    public void setOwnerPhoto(String ownerPhoto) {
        this.ownerPhoto = ownerPhoto;
    }

}
