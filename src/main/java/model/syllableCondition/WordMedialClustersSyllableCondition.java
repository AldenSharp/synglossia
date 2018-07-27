package model.syllableCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.*;

@Data
public class WordMedialClustersSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
	private List<List<String>> values;
	private Boolean syllablePositionAbsolute;
	
	@Builder
	public WordMedialClustersSyllableCondition(Integer syllablePosition, List<List<String>> values, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.WORD_MEDIAL_CLUSTERS);
        this.syllablePosition = syllablePosition;
        this.values = values;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class WordMedialClustersSyllableConditionBuilder extends SyllableConditionBuilder {
    	WordMedialClustersSyllableConditionBuilder() {
                super();
            }
    }

    public static WordMedialClustersSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
	    item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllablePosition", "values", "syllablePositionAbsolute"),
                Arrays.asList(NUMBER, LIST, BOOLEAN),
                location, item);
        return WordMedialClustersSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .values(TypeUtils.getStringListListFromItemList(item.get("values").getL(), location + ": value item"))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}