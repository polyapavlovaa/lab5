package models;

import annotations.NonNull;

public class Location {
    @NonNull
    private Float x;

    @NonNull
    private Integer y;

    @NonNull
    private Integer z;

    @NonNull
    private String name;

    public Location (Float x, Integer y, Integer z, String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }


}