package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.grammar.VerbMorpheme;
import model.grammar.*;
import model.grammar.Number;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.LIST;

@Data
@Builder
public class VerbMorphology {
    List<Person> persons;
    List<Number> numbers;
    List<Voice> voices;
    List<Tense> tenses;
    List<Aspect> aspects;
    List<Mood> moods;
    List<NonFiniteForm> nonFiniteForms;
    List<VerbClass> classes;
    List<VerbMorpheme> morphemes;

    public static VerbMorphology getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("persons", "numbers", "voices", "tenses", "aspects", "moods", "nonFiniteForms", "classes"),
                Arrays.asList(LIST, LIST, LIST, LIST, LIST, LIST, LIST, LIST),
                location, item
        );
        return VerbMorphology.builder()
                .persons(Person.getListFromItemList(item.get("persons").getL(), location + ": person"))
                .numbers(Number.getListFromItemList(item.get("numbers").getL(), location + ": number"))
                .voices(Voice.getListFromItemList(item.get("voices").getL(), location + ": voices"))
                .tenses(Tense.getListFromItemList(item.get("tenses").getL(), location + ": tenses"))
                .aspects(Aspect.getListFromItemList(item.get("aspects").getL(), location + ": aspects"))
                .moods(Mood.getListFromItemList(item.get("moods").getL(), location + ": moods"))
                .nonFiniteForms(NonFiniteForm.getListFromItemList(item.get("nonFiniteForms").getL(), location + ": non-finite forms"))
                .classes(VerbClass.getListFromItemList(item.get("classes").getL(), location + ": class"))
                .build();
    }

    void setMorphemes(List<Map<String, AttributeValue>> morphemeItems) {
        this.morphemes = VerbMorpheme.getListFromItemList(morphemeItems, "Verb morphology");
    }
}
