package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

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

    public static OpenSyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return OpenSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}