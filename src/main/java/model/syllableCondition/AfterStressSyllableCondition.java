package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class AfterStressSyllableCondition extends SyllableCondition {
    private Integer order;

    @Builder
    public AfterStressSyllableCondition(Integer order) {
        super(SyllableConditionType.AFTER_STRESS);
        this.order = order;
    }

    public static class AfterStressSyllableConditionBuilder extends SyllableConditionBuilder {
        AfterStressSyllableConditionBuilder() { super(); }
    }

    public static AfterStressSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("order", key -> new AttributeValue().withN("1"));
        ExceptionUtils.checkObjectElements(Collections.singletonList("order"), Collections.singletonList(NUMBER), location, item);
        return AfterStressSyllableCondition.builder()
                .order(TypeUtils.getIntegerFromItem(item.get("order")))
                .build();
    }
}
