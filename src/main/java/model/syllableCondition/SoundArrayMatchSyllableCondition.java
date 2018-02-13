package model.syllableCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;
import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
public class SoundArrayMatchSyllableCondition extends SyllableCondition {
	private SoundPosition initialPosition;
	private List<String> array;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public SoundArrayMatchSyllableCondition(SoundPosition initialPosition, List<String> array, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.SOUND_ARRAY_MATCH);
        this.initialPosition = initialPosition;
        this.array = array;
        this.syllablePositionType = syllablePositionType;
    }

    public static class SoundArrayMatchSyllableConditionBuilder extends SyllableConditionBuilder {
    	SoundArrayMatchSyllableConditionBuilder() {
                super();
            }
    }

    public static SoundArrayMatchSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("initialPosition", "array", "syllablePositionType"),
                Arrays.asList(OBJECT, LIST, STRING),
                location, item);
        return SoundArrayMatchSyllableCondition.builder()
                .initialPosition(SoundPosition.getFromItem(item.get("initialPosition").getM(), location + ": position object"))
                .array(TypeUtils.getStringListFromItemList(item.get("array").getL(), location + ": array item"))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}