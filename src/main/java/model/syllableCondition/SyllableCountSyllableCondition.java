package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.Comparison;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class SyllableCountSyllableCondition extends SyllableCondition {
	private Comparison comparison;
	private Integer count;
	
	@Builder
	public SyllableCountSyllableCondition(Comparison comparison, Integer count) {
        super(SyllableConditionType.SYLLABLE_COUNT);
        this.comparison = comparison;
        this.count = count;
    }

    public static class SyllableCountSyllableConditionBuilder extends SyllableConditionBuilder {
    	SyllableCountSyllableConditionBuilder() {
                super();
            }
    }

    public static SyllableCountSyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return SyllableCountSyllableCondition.builder()
                .comparison(Comparison.getFromItem(item.get("comparison")))
                .count(TypeUtils.getIntegerFromItem(item.get("count")))
                .build();
    }
}