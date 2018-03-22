package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.grammar.*;
import model.grammar.Number;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.LIST;

@Data
@Builder
public class NominalMorphology {
    private List<Gender> genders;
    private List<Number> numbers;
    private List<Case> cases;
    private List<NounClass> classes;
    private List<NounEnding> endings;

    public static NominalMorphology getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("genders", "numbers", "cases", "classes", "endings"),
                Arrays.asList(LIST, LIST, LIST, LIST, LIST),
                location, item
        );
        return NominalMorphology.builder()
                .genders(Gender.getListFromItemList(item.get("genders").getL(), location + ": gender"))
                .numbers(Number.getListFromItemList(item.get("numbers").getL(), location + ": number"))
                .cases(Case.getListFromItemList(item.get("cases").getL(), location + ": case"))
                .classes(NounClass.getListFromItemList(item.get("classes").getL(), location + ": class"))
                .endings(NounEnding.getListFromItemList(item.get("endings").getL(), location + ": endings"))
                .build();
    }
}
