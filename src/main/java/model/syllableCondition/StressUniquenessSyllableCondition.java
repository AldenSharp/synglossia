package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
public class StressUniquenessSyllableCondition extends SyllableCondition {
	private List<Integer> orders;
	
	@Builder
	public StressUniquenessSyllableCondition(List<Integer> orders) {
        super(SyllableConditionType.STRESS_UNIQUENESS);
        this.orders = orders;
    }

    public static class StressUniquenessSyllableConditionBuilder extends SyllableConditionBuilder {
    	StressUniquenessSyllableConditionBuilder() {
                super();
            }
    }

    public static StressUniquenessSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return StressUniquenessSyllableCondition.builder()
                .orders(TypeUtils.getIntegerListFromItemList(item.get("orders").getL()))
                .build();
    }
}