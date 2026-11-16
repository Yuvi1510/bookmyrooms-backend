package com.bookmyrooms.main.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PropertyType {
    APARTMENT,
    HOUSE,
    VILLA,
    GUEST_HOUSE,
    HOTEL;

    @JsonCreator
    public static PropertyType fromString(String value){
        if( value == null) return null;

        String cleanedValue = value.replaceAll("\\s", "_").toUpperCase();

        return PropertyType.valueOf(cleanedValue);
    }
}
