package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

@Data
public class EmptyAtSomeSyllableCondition extends SyllableCondition {
	private List<SoundPosition> positions;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public EmptyAtSomeSyllableCondition(List<SoundPosition> positions, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.EMPTY_AT_SOME);
        this.positions = positions;
        this.syllablePositionType = syllablePositionType;
    }

    public static class EmptyAtSomeSyllableConditionBuilder extends SyllableConditionBuilder {
    	EmptyAtSomeSyllableConditionBuilder() {
                super();
            }
    }

    public static EmptyAtSomeSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return EmptyAtSomeSyllableCondition.builder()
                .positions(SoundPosition.getListFromItemList(item.get("positions").getL()))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}