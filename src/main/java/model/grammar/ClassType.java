package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum ClassType {
    DEFAULT("DEFAULT"), VARIANT("VARIANT");

    private final String value;
    ClassType(String value) { this.value = value; }

    public static ClassType getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }
}
