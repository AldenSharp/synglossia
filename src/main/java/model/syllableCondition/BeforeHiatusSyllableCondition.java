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
public class BeforeHiatusSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
    private Boolean syllablePositionAbsolute;
	
	@Builder
	public BeforeHiatusSyllableCondition(Integer syllablePosition, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.BEFORE_HIATUS);
        this.syllablePosition = syllablePosition;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class BeforeHiatusSyllableConditionBuilder extends SyllableConditionBuilder {
    	BeforeHiatusSyllableConditionBuilder() { super(); }
    }

    public static BeforeHiatusSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllablePosition", "syllablePositionAbsolute"),
                Arrays.asList(NUMBER, BOOLEAN),
                location, item);
	    return BeforeHiatusSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}