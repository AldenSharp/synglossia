package model.prosody;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.TypeUtils;

public enum ProsodyType {
	STRESS("STRESS"), PITCH("PITCH"), NONE("NONE");
	
	private final String value;
	ProsodyType(String value) { this.value = value; }

	public static ProsodyType getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }
}