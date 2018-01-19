package model.writingSystem;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum WritingSystemType {
	ALPHABET("alphabet");
	
	private final String value;
	WritingSystemType(String value) {
		this.value = value;
	}

	public static WritingSystemType getFromItem(AttributeValue item) {
	    return valueOf(TypeUtils.getStringFromItem(item));
    }
}