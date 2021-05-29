package models;

import annotations.NonNull;

public class Address {
    private String street; //Поле может быть null

    @NonNull
    private String zipCode;

    @NonNull
    private Location town;

    public Address (String street, String zipCode, Location town){
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }


}
