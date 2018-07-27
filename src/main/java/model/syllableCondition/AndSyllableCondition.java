package model.syllableCondition;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;

import static util.FieldType.LIST;

@Data
@EqualsAndHashCode(callSuper = true)
public class AndSyllableCondition extends SyllableCondition {
	private List<SyllableCondition> conditions;
	
	@Builder
	public AndSyllableCondition(List<SyllableCondition> conditions) {
        super(SyllableConditionType.AND);
        this.conditions = conditions;
    }

    public static class AndSyllableConditionBuilder extends SyllableConditionBuilder {
    	AndSyllableConditionBuilder() { super(); }
    }

    public static AndSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("conditions"), Collections.singletonList(LIST), location, item);
        return AndSyllableCondition.builder()
                .conditions(SyllableCondition.getListFromItemList(item.get("conditions").getL(), location + ": conjunct condition"))
                .build();
    }
}