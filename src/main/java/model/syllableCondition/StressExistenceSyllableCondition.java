package model.syllableCondition;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;

@Data
public class StressExistenceSyllableCondition extends SyllableCondition {
	private List<Integer> orders;
	
	@Builder
	public StressExistenceSyllableCondition(List<Integer> orders) {
        super(SyllableConditionType.STRESS_EXISTENCE);
        this.orders = orders;
    }

    public static class StressExistenceSyllableConditionBuilder extends SyllableConditionBuilder {
    	StressExistenceSyllableConditionBuilder() {
                super();
            }
    }

    public static StressExistenceSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("orders"), Collections.singletonList(LIST), location, item);
        return StressExistenceSyllableCondition.builder()
                .orders(TypeUtils.getIntegerListFromItemList(item.get("orders").getL(), ": order item"))
                .build();
    }
}