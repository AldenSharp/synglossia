package model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.evolution.DescendantLanguage;
import model.syllableCondition.SyllableCondition;
import model.writingSystem.WritingSystem;
import service.LanguageService;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.*;

@Data
@Builder
public class Syngloss {
    private String name;
    private SynglossType type;
    private Integer date;
    private Phonology phonology;
    private Morphology morphology;
    private SyllableCondition validity;
    private List<WritingSystem> writingSystems;
    private List<DescendantLanguage> descendantLanguages;

    public static Syngloss getFromItem(Map<String, AttributeValue> item) throws IOException {
        item.computeIfAbsent("validity", key -> SyllableCondition.getDefaultItem());
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "date", "phonology", "morphology", "validity"),
                Arrays.asList(STRING, NUMBER, OBJECT, OBJECT, OBJECT),
                "Syngloss", item
        );
        return Syngloss.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .type(SynglossType.PARENT)
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .phonology(Phonology.getFromItem(item.get("phonology").getM(), "Syngloss phonology"))
                .morphology(Morphology.getFromItem(item.get("morphology").getM(), "Syngloss morphology"))
                .validity(SyllableCondition.getFromItem(item.get("validity").getM(), "Syngloss validity"))
                .descendantLanguages(new LanguageService().getDescendantLanguagesFromData(item.get("name").getS()))
                .build();
    }
}