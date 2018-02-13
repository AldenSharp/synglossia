package model.syllableCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import static util.FieldType.LIST;
import static util.FieldType.STRING;

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

    public static EmptyAtAllSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("positions", "syllablePositionType"),
                Arrays.asList(LIST, STRING),
                location, item);
        return EmptyAtAllSyllableCondition.builder()
                .positions(SoundPosition.getListFromItemList(item.get("positions").getL(), location + ": position object"))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}