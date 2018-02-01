package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum Comparison {
    LESS_THAN("LESS_THAN"), GREATER_THAN("GREATER_THAN"), EQUALS("EQUALS");

    private final String value;
    Comparison(String value) {
        this.value = value;
    }

    public static Comparison getFromItem(AttributeValue item) {
        return valueOf(TypeUtils.getStringFromItem(item));
    }
}