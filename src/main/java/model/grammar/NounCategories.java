package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.NominalMorphology;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.LIST;
import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
public class NounCategories {
    List<String> classes;
    List<Gender> genders;
    List<Number> numbers;
    List<Case> cases;

    public static NounCategories getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("numbers", "cases"),
                Arrays.asList(LIST, LIST),
                location, item
        );
        item.computeIfAbsent("classes", key -> new AttributeValue().withL(Collections.singletonList(new AttributeValue("COMMON"))));
        item.computeIfAbsent("genders", key -> new AttributeValue().withL(Collections.singletonList(new AttributeValue("COMMON"))));
        return NounCategories.builder()
                .classes(TypeUtils.getStringListFromItemList(item.get("classes").getL(), location + ": class"))
                .genders(Gender.getListFromItemList(item.get("genders").getL(), location + ": gender"))
                .numbers(Number.getListFromItemList(item.get("numbers").getL(), location + ": number"))
                .cases(Case.getListFromItemList(item.get("cases").getL(), location + ": case"))
                .build();
    }

    public static List<NounCategories> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(),location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
