package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum SyllableConditionType {
    DEFAULT("DEFAULT"),
    NOT("NOT"),
    AND("AND"),
    OR("OR"),
    WORD_MEDIAL_CLUSTERS("WORD_MEDIAL_CLUSTERS"),
    WORD_INITIAL_CLUSTERS("WORD_INITIAL_CLUSTERS"),
    WORD_FINAL_CLUSTERS("WORD_FINAL_CLUSTERS"),
    SYLLABLE_INITIAL_CLUSTERS("SYLLABLE_INITIAL_CLUSTERS"),
    SYLLABLE_FINAL_CLUSTERS("SYLLABLE_FINAL_CLUSTERS"),
    BEFORE("BEFORE"),
    AFTER("AFTER"),
    SOUND_VALUES("SOUND_VALUES"),
    EMPTY("EMPTY"),
    EMPTY_AT_ALL("EMPTY_AT_ALL"),
    EMPTY_AT_SOME("EMPTY_AT_SOME"),
    OPEN("OPEN"),
    BEFORE_HIATUS("BEFORE_HIATUS"),
    SYLLABLE_POSITION("SYLLABLE_POSITION"),
    SHORT_VOWEL("SHORT_VOWEL"),
    LONG_VOWEL("LONG_VOWEL"),
    MATCH("MATCH"),
    SOUND_ARRAY_MATCH("SOUND_ARRAY_MATCH"),
    LENGTH("LENGTH"),
    STRESSED("STRESSED"),
    BEFORE_STRESS("BEFORE_STRESS"),
    AFTER_STRESS("AFTER_STRESS"),
    STRESS_EXISTENCE("STRESS_EXISTENCE"),
    STRESS_UNIQUENESS("STRESS_UNIQUENESS"),
    STRESS_PARADIGM("STRESS_PARADIGM"),
    SYLLABLE_COUNT("SYLLABLE_COUNT"),
    FOLLOWS_FROM_LAST_STEP("FOLLOWS_FROM_LAST_STEP");

    private final String value;
    SyllableConditionType(String value) { this.value = value; }

    public static SyllableConditionType getFromItem(AttributeValue item) {
        return valueOf(TypeUtils.getStringFromItem(item));
    }
}