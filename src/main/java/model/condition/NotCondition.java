package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.OBJECT;

@Data
public class NotCondition extends Condition {
    private Condition condition;

    @Builder
    public NotCondition(Condition condition) {
        super(ConditionType.NOT);
        this.condition = condition;
    }

    public static class NotConditionBuilder extends ConditionBuilder {
        NotConditionBuilder() { super(); }
    }

    public static NotCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("condition"), Collections.singletonList(OBJECT), location, item);
        return NotCondition.builder()
                .condition(Condition.getFromItem(item.get("condition").getM(), location + ": negated condition"))
                .build();
    }
}
