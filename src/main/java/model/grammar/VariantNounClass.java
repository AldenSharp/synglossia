package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
public class VariantNounClass extends NounClass {
    private String defaultClass;
    private SyllableCondition condition;

    @Builder
    public VariantNounClass(String name, List<Gender> genders, Integer endingStartPosition, String defaultClass, SyllableCondition condition) {
        super(name, genders, endingStartPosition, ClassType.VARIANT);
        this.defaultClass = defaultClass;
        this.condition = condition;
    }

    public static class VariantNounClassBuilder extends NounClassBuilder {
        VariantNounClassBuilder() { super(); }
    }

    public static VariantNounClass getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("condition", key -> SyllableCondition.getDefaultItem());
        ExceptionUtils.checkObjectElements(
                Arrays.asList("defaultClass", "condition"),
                Arrays.asList(STRING, OBJECT),
                location, item
        );
        return VariantNounClass.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .genders(Gender.getListFromItemList(item.get("genders").getL(), location + ": gender"))
                .endingStartPosition(TypeUtils.getIntegerFromItem(item.get("endingStartPosition")))
                .defaultClass(TypeUtils.getStringFromItem(item.get("defaultClass")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": condition"))
                .build();
    }
}
