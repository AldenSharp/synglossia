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
import static util.FieldType.NUMBER;
import static util.FieldType.STRING;

@Data
public class WordMedialClustersSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
	private List<List<String>> values;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public WordMedialClustersSyllableCondition(Integer syllablePosition, List<List<String>> values, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.WORD_MEDIAL_CLUSTERS);
        this.syllablePosition = syllablePosition;
        this.values = values;
        this.syllablePositionType = syllablePositionType;
    }

    public static class WordMedialClustersSyllableConditionBuilder extends SyllableConditionBuilder {
    	WordMedialClustersSyllableConditionBuilder() {
                super();
            }
    }

    public static WordMedialClustersSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllablePosition", "values", "syllablePositionType"),
                Arrays.asList(NUMBER, LIST, STRING),
                location, item);
        return WordMedialClustersSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .values(TypeUtils.getStringListListFromItemList(item.get("values").getL(), location + ": value item"))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}