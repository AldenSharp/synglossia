package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
public class SyllableInitialClustersSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
	private List<List<String>> values;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public SyllableInitialClustersSyllableCondition(Integer syllablePosition, List<List<String>> values, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.SYLLABLE_INITIAL_CLUSTERS);
        this.syllablePosition = syllablePosition;
    }

    public static class SyllableInitialClustersSyllableConditionBuilder extends SyllableConditionBuilder {
    	SyllableInitialClustersSyllableConditionBuilder() {
                super();
            }
    }

    public static SyllableInitialClustersSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return SyllableInitialClustersSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .values(TypeUtils.getStringListListFromItemList(item.get("values").getL()))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}