package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.NUMBER;

@Data
public class SyllablePositionSyllableCondition extends SyllableCondition {
	private Integer position;
	
	@Builder
	public SyllablePositionSyllableCondition(Integer position) {
        super(SyllableConditionType.SYLLABLE_POSITION);
        this.position = position;
    }

    public static class SyllablePositionSyllableConditionBuilder extends SyllableConditionBuilder {
    	SyllablePositionSyllableConditionBuilder() {
                super();
            }
    }

    public static SyllablePositionSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("position"), Collections.singletonList(NUMBER), location, item);
	    return SyllablePositionSyllableCondition.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .build();
    }
}