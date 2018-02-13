package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.NUMBER;
import static util.FieldType.STRING;

@Data
public class ShortVowelSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public ShortVowelSyllableCondition(Integer syllablePosition, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.SHORT_VOWEL);
        this.syllablePosition = syllablePosition;
        this.syllablePositionType = syllablePositionType;
    }

    public static class ShortVowelSyllableConditionBuilder extends SyllableConditionBuilder {
    	ShortVowelSyllableConditionBuilder() {
                super();
            }
    }

    public static ShortVowelSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllablePosition", "syllablePositionType"),
                Arrays.asList(NUMBER, STRING),
                location, item);
	    return ShortVowelSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}