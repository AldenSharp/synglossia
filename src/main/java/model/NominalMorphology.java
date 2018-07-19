package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.grammar.*;
import model.grammar.Number;
import util.ExceptionUtils;

import java.util.*;

import static util.FieldType.LIST;

@Data
@Builder
public class NominalMorphology {
    private List<Gender> genders;
    private List<Number> numbers;
    private List<Case> cases;
    private List<NounClass> classes;
    private List<NounMorpheme> morphemes;

    public static NominalMorphology getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("genders", "numbers", "cases"),
                Arrays.asList(LIST, LIST, LIST),
                location, item
        );
        item.computeIfAbsent("classes", key -> NominalMorphology.setDefaultClasses());
        return NominalMorphology.builder()
                .genders(Gender.getListFromItemList(item.get("genders").getL(), location + ": gender"))
                .numbers(Number.getListFromItemList(item.get("numbers").getL(), location + ": number"))
                .cases(Case.getListFromItemList(item.get("cases").getL(), location + ": case"))
                .classes(NounClass.getListFromItemList(item.get("classes").getL(), location + ": class"))
                .build();
    }

    void setMorphemes(List<Map<String, AttributeValue>> morphemeItems) {
        this.morphemes = NounMorpheme.getListFromItemList(morphemeItems, "Nominal morphology: morphemes");
    }

    private static AttributeValue setDefaultClasses() {
        Map<String, AttributeValue> classItem = new HashMap<>();
        classItem.put("name", new AttributeValue("COMMON"));
        classItem.put("genders", new AttributeValue().withL(Collections.singleton(new AttributeValue("COMMON"))));
        classItem.put("endingStartPosition", new AttributeValue().withN("0"));
        classItem.put("type", new AttributeValue("DEFAULT"));
        return new AttributeValue().withL(Collections.singletonList(new AttributeValue().withM(classItem)));
    }
}
