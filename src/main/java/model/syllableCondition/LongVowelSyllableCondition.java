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
public class LongVowelSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
    private Boolean syllablePositionAbsolute;
	
	@Builder
	public LongVowelSyllableCondition(Integer syllablePosition, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.LONG_VOWEL);
        this.syllablePosition = syllablePosition;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class LongVowelSyllableConditionBuilder extends SyllableConditionBuilder {
    	LongVowelSyllableConditionBuilder() { super(); }
    }

    public static LongVowelSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllablePosition", "syllablePositionAbsolute"),
                Arrays.asList(NUMBER, BOOLEAN),
                location, item);
	    return LongVowelSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}