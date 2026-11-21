package com.bookmyrooms.main.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoomType {
    BASIC, STANDARD, PREMIUM;

    @JsonCreator
    public static RoomType fromString(String value){
        if( value == null) return null;

        String cleanedValue = value.replaceAll("\\s", "_").toUpperCase();

        return RoomType.valueOf(cleanedValue);
    }
}
