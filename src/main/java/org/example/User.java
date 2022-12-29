package org.example;

public class User {

    private final int id;
    private final String name;
    private final String photoLink;

    public User(int id, String name, String photoLink) {
        this.id = id;
        this.name = name;
        this.photoLink = photoLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoLink='" + photoLink + '\'' +
                '}';
    }
}
