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
public class BeforeStressSyllableCondition extends SyllableCondition {
    private Integer order;

    @Builder
    public BeforeStressSyllableCondition(Integer order) {
        super(SyllableConditionType.BEFORE_STRESS);
        this.order = order;
    }

    public static class BeforeStressSyllableConditionBuilder extends SyllableConditionBuilder {
        BeforeStressSyllableConditionBuilder() { super(); }
    }

    public static BeforeStressSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("order"), Collections.singletonList(NUMBER), location, item);
        return BeforeStressSyllableCondition.builder()
                .order(TypeUtils.getIntegerFromItem(item.get("order")))
                .build();
    }
}
