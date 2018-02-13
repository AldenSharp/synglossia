package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.Comparison;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.NUMBER;
import static util.FieldType.STRING;

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

    public static LengthSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("length", "comparison"),
                Arrays.asList(NUMBER, STRING),
                location, item);
        return LengthSyllableCondition.builder()
                .length(TypeUtils.getIntegerFromItem(item.get("length")))
                .comparison(Comparison.getFromItem(item.get("comparison")))
                .build();
    }
}
