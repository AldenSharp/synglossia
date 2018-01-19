package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

@Data
public class EmptyAtAllSyllableCondition extends SyllableCondition {
	private List<SoundPosition> positions;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public EmptyAtAllSyllableCondition(List<SoundPosition> positions, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.EMPTY_AT_ALL);
        this.positions = positions;
        this.syllablePositionType = syllablePositionType;
    }

    public static class EmptyAtAllSyllableConditionBuilder extends SyllableConditionBuilder {
    	EmptyAtAllSyllableConditionBuilder() {
                super();
            }
    }

    public static EmptyAtAllSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return EmptyAtAllSyllableCondition.builder()
                .positions(SoundPosition.getListFromItemList(item.get("positions").getL()))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}