package model.evolution;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import service.LanguageService;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;
import static util.FieldType.NUMBER;
import static util.FieldType.STRING;

@Data
@Builder
public class DescendantLanguage {
    private String name;
    private Integer date;
    private List<EvolutionStep> evolution;
    private List<DescendantLanguage> descendantLanguages;

    public static DescendantLanguage getFromItem(Map<String, AttributeValue> item) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "date", "evolution"),
                Arrays.asList(STRING, NUMBER, LIST),
                "Descendant language", item);
        String locationWithName = "Descendant language '" + item.get("name").getS() + "'";
        return DescendantLanguage.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .evolution(EvolutionStep.getListFromItemList(item.get("evolution").getL(), locationWithName + " evolution step"))
                .descendantLanguages(getRecursiveDescendantLanguages(TypeUtils.getStringFromItem(item.get("name"))))
                .build();
    }

    private static List<DescendantLanguage> getRecursiveDescendantLanguages(String languageName) {
        return new LanguageService().getDescendantLanguagesFromData(languageName);
    }
}