package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.LIST;
import static util.FieldType.OBJECT;

@Data
@Builder
public class VerbCategories {
    List<String> classes;
    List<Person> persons;
    List<Number> numbers;
    List<Voice> voices;
    List<Tense> tenses;
    List<Aspect> aspects;
    List<Mood> moods;

    public static VerbCategories getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("classes", "persons", "numbers", "voices", "tenses", "aspects", "moods"),
                Arrays.asList(LIST, LIST, LIST, LIST, LIST, LIST, LIST),
                location, item
        );
        return VerbCategories.builder()
                .classes(TypeUtils.getStringListFromItemList(item.get("classes").getL(), location + ": class"))
                .persons(Person.getListFromItemList(item.get("persons").getL(), location + ": person"))
                .numbers(Number.getListFromItemList(item.get("numbers").getL(), location + ": number"))
                .voices(Voice.getListFromItemList(item.get("voices").getL(), location + ": voice"))
                .tenses(Tense.getListFromItemList(item.get("tenses").getL(), location + ": tense"))
                .aspects(Aspect.getListFromItemList(item.get("aspects").getL(), location + ": aspect"))
                .moods(Mood.getListFromItemList(item.get("moods").getL(), location + ": mood"))
                .build();
    }

    public static List<VerbCategories> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(),location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
