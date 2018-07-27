package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
public class VariantVerbClass extends VerbClass {
    private String defaultClass;
    private SyllableCondition condition;

    @Builder
    public VariantVerbClass(String name, Integer endingStartPosition, String defaultClass, SyllableCondition condition) {
        super(name, endingStartPosition, ClassType.VARIANT);
        this.defaultClass = defaultClass;
        this.condition = condition;
    }

    public static class VariantVerbClassBuilder extends VerbClass.VerbClassBuilder {
        VariantVerbClassBuilder() { super(); }
    }

    public static VariantVerbClass getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("condition", key -> SyllableCondition.getDefaultItem());
        ExceptionUtils.checkObjectElements(
                Arrays.asList("defaultClass", "condition"),
                Arrays.asList(STRING, OBJECT),
                location, item
        );
        return VariantVerbClass.builder()
                .defaultClass(TypeUtils.getStringFromItem(item.get("defaultClass")))
                .condition(SyllableCondition.getFromItem(item.get("conditions").getM(), location + ": condition"))
                .build();
    }
}
