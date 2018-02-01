package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.Comparison;
import util.TypeUtils;

import java.util.Map;

@Data
@Builder
public class LengthSyllableCondition extends SyllableCondition {
    private Integer length;
    private Comparison comparison;

    @Builder
    public LengthSyllableCondition(Integer length, Comparison comparison) {
        super(SyllableConditionType.LENGTH);
        this.length = length;
        this.comparison = comparison;
    }

    public static class LengthSyllableConditionBuilder extends SyllableConditionBuilder {
        LengthSyllableConditionBuilder() {
            super();
        }
    }

    public static LengthSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return LengthSyllableCondition.builder()
                .length(TypeUtils.getIntegerFromItem(item.get("length")))
                .comparison(Comparison.getFromItem(item.get("comparison")))
                .build();
    }
}
