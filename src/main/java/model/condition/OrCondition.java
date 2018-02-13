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
public class OrCondition extends Condition {
	private List<Condition> conditions;
	
	@Builder
	public OrCondition(List<Condition> conditions) {
        super(ConditionType.OR);
        this.conditions = conditions;
    }

    public static class OrConditionBuilder extends ConditionBuilder {
    	OrConditionBuilder() {
                super();
            }
    }

    public static OrCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("conditions"), Collections.singletonList(LIST), location, item);
        return OrCondition.builder()
                .conditions(Condition.getListFromItemList(item.get("conditions").getL(), location + ": disjunct condition"))
                .build();
    }
}