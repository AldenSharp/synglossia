package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

@Data
public class AndSyllableCondition extends SyllableCondition {
	private List<SyllableCondition> conditions;
	
	@Builder
	public AndSyllableCondition(List<SyllableCondition> conditions) {
        super(SyllableConditionType.AND);
        this.conditions = conditions;
    }

    public static class AndSyllableConditionBuilder extends SyllableConditionBuilder {
    	AndSyllableConditionBuilder() {
                super();
            }
    }

    public static AndSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return AndSyllableCondition.builder()
                .conditions(SyllableCondition.getListFromItemList(item.get("conditions").getL()))
                .build();
    }
}