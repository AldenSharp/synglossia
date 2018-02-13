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
public class OpenSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public OpenSyllableCondition(Integer syllablePosition, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.OPEN);
        this.syllablePosition = syllablePosition;
        this.syllablePositionType = syllablePositionType;
    }

    public static class OpenSyllableConditionBuilder extends SyllableConditionBuilder {
    	OpenSyllableConditionBuilder() {
                super();
            }
    }

    public static OpenSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllablePosition", "syllablePositionType"),
                Arrays.asList(NUMBER, STRING),
                location, item);
	    return OpenSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}