package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import service.LanguageService;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.NUMBER;
import static util.FieldType.STRING;

@Data
@Builder
public class AncestorLanguage {
    private String name;
    private Integer date;
    private List<EvolutionStep> evolution;
    private List<AncestorLanguage> ancestorLanguages;

    public static AncestorLanguage getFromItemAndEvolution(Map<String, AttributeValue> item, Evolution evolution) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "date"),
                Arrays.asList(STRING, NUMBER),
                "Ancestor language", item);
        return AncestorLanguage.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .evolution(evolution.getEvolution())
                .ancestorLanguages(getRecursiveAncestorLanguages(TypeUtils.getStringFromItem(item.get("name"))))
                .build();
    }

    private static List<AncestorLanguage> getRecursiveAncestorLanguages(String languageName) {
        return new LanguageService().getAncestorLanguagesFromData(languageName);
    }
}
