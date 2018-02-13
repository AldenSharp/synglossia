package model.condition;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import static util.FieldType.LIST;

@Data
public class AndCondition extends Condition {
	private List<Condition> conditions;
	
	@Builder
	public AndCondition(List<Condition> conditions) {
        super(ConditionType.AND);
        this.conditions = conditions;
    }

    public static class AndConditionBuilder extends ConditionBuilder {
    	AndConditionBuilder() {
                super();
            }
    }

    public static AndCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("conditions"), Collections.singletonList(LIST), location, item);
        return AndCondition.builder()
                .conditions(Condition.getListFromItemList(item.get("conditions").getL(), location + ": conjunct condition"))
                .build();
    }
}