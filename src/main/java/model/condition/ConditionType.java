package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum ConditionType {
	DEFAULT(""),
	AND("and"),
	OR("or"),
	BEFORE("before"),
	AFTER("after"),
	CONSONANTAL("consonantal"),
	SYLLABIC("syllabic"),
	WORD_INITIAL("word-initial"),
	WORD_FINAL("word-final"),
	SYLLABLE_INITIAL("syllable-initial"),
	SYLLABLE_FINAL("syllable-final"),
	SOUND_VALUES("sound values"),
	EMPTY("empty"),
	SYLLABLE_COUNT("syllable count");
	
	private final String value;
	ConditionType(String value) {
		this.value = value;
	}

	public static ConditionType getFromItem(AttributeValue item) {
	    return valueOf(TypeUtils.getStringFromItem(item));
    }
}