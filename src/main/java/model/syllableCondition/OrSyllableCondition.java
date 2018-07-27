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
public class OrSyllableCondition extends SyllableCondition {
	private List<SyllableCondition> conditions;
	
	@Builder
	public OrSyllableCondition(List<SyllableCondition> conditions) {
        super(SyllableConditionType.OR);
        this.conditions = conditions;
    }

    public static class OrSyllableConditionBuilder extends SyllableConditionBuilder {
    	OrSyllableConditionBuilder() { super(); }
    }

    public static OrSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("conditions"), Collections.singletonList(LIST), location, item);
        return OrSyllableCondition.builder()
                .conditions(SyllableCondition.getListFromItemList(item.get("conditions").getL(), location + ": disjunct condition"))
                .build();
    }
}