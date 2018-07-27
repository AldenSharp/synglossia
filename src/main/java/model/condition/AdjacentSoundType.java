package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Data;
import util.TypeUtils;

public enum AdjacentSoundType {
    CONSONANT("CONSONANT"), VOWEL("VOWEL"), ANY("ANY");

    private final String value;
    AdjacentSoundType(String value) { this.value = value; }

    public static AdjacentSoundType getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }
}