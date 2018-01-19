package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum TransformationType {
	SOUND_CHANGE("SOUND_CHANGE"),
	SOUND_DELETION("SOUND_DELETION"),
	SOUND_MIGRATION("SOUND_MIGRATION"),
	SOUND_COPY("SOUND_COPY"),
	CONSONANT_DEGEMINATION("CONSONANT_DEGEMINATION"),
	SYLLABLE_COLLAPSE("SYLLABLE_COLLAPSE"),
	STRESS_SHIFT("STRESS_SHIFT");
	
	private final String value;
	TransformationType(String value) {
		this.value = value;
	}

	public static TransformationType getFromItem(AttributeValue item) {
	    return valueOf(TypeUtils.getStringFromItem(item));
    }
}