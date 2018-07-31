package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum TransformationType {
    SOUND_CHANGE("SOUND_CHANGE"),
    SOUND_DELETION("SOUND_DELETION"),
    SOUND_INSERTION("SOUND_INSERTION"),
    SOUND_MIGRATION("SOUND_MIGRATION"),
    SOUND_COPY("SOUND_COPY"),
    SOUND_SWAP("SOUND_SWAP"),
    CONSONANT_DEGEMINATION("CONSONANT_DEGEMINATION"),
    SYLLABLE_COLLAPSE("SYLLABLE_COLLAPSE"),
    SYLLABLE_INSERTION("SYLLABLE_INSERTION"),
    STRESS_SHIFT("STRESS_SHIFT"),
    SYLLABLE_POSITION_INSERTION("SYLLABLE_POSITION_INSERTION"),
    SYLLABLE_POSITION_DELETION("SYLLABLE_POSITION_DELETION");

    private final String value;
    TransformationType(String value) { this.value = value; }

    public static TransformationType getFromItem(AttributeValue item) {
        return valueOf(TypeUtils.getStringFromItem(item));
    }
}