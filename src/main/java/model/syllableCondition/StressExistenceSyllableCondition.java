package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

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

    public static StressExistenceSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return StressExistenceSyllableCondition.builder()
                .orders(TypeUtils.getIntegerListFromItemList(item.get("orders").getL()))
                .build();
    }
}