package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum Comparison {
    LESS_THAN("less than"), GREATER_THAN("greater than"), EQUALS("equals");

    private final String value;
    Comparison(String value) {
        this.value = value;
    }

    public static Comparison getFromItem(AttributeValue item) {
        return valueOf(TypeUtils.getStringFromItem(item));
    }
}