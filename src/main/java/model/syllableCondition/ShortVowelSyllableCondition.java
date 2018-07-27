package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.BOOLEAN;
import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShortVowelSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
    private Boolean syllablePositionAbsolute;
	
	@Builder
	public ShortVowelSyllableCondition(Integer syllablePosition, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.SHORT_VOWEL);
        this.syllablePosition = syllablePosition;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class ShortVowelSyllableConditionBuilder extends SyllableConditionBuilder {
    	ShortVowelSyllableConditionBuilder() { super(); }
    }

    public static ShortVowelSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllablePosition", "syllablePositionAbsolute"),
                Arrays.asList(NUMBER, BOOLEAN),
                location, item);
	    return ShortVowelSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}