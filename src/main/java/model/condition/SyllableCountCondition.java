package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.Comparison;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.NUMBER;
import static util.FieldType.STRING;

@Data
public class SyllableCountCondition extends Condition {
	private Comparison comparison;
	private Integer count;
	
	@Builder
	public SyllableCountCondition(Comparison comparison, Integer count) {
        super(ConditionType.SYLLABLE_COUNT);
        this.comparison = comparison;
        this.count = count;
    }

    public static class SyllableCountConditionBuilder extends ConditionBuilder {
    	SyllableCountConditionBuilder() {
                super();
            }
    }

    public static SyllableCountCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("comparison", "count"),
                Arrays.asList(STRING, NUMBER),
                location, item);
	    return SyllableCountCondition.builder()
                .comparison(Comparison.getFromItem(item.get("comparison")))
                .count(TypeUtils.getIntegerFromItem(item.get("count")))
                .build();
    }
}