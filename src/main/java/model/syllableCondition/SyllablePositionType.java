package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum SyllablePositionType {
	RELATIVE("RELATIVE"), ABSOLUTE("ABSOLUTE");
	
	private final String value;
	SyllablePositionType(String value) {
		this.value = value;
	}

	public static SyllablePositionType getFromItem(AttributeValue item) {
	    return valueOf(TypeUtils.getStringFromItem(item));
    }
}