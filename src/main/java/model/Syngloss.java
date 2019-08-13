package model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.evolution.AncestorLanguage;
import model.evolution.DescendantLanguage;
import model.syllableCondition.SyllableCondition;
import model.writingSystem.WritingSystem;
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
    private List<AncestorLanguage> ancestorLanguages;

    public static Syngloss getFromItem(Map<String, AttributeValue> item) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "date"),
                Arrays.asList(STRING, NUMBER),
                "Syngloss", item
        );
        Syngloss syngloss = Syngloss.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .type(SynglossType.valueOf(TypeUtils.getStringFromItem(item.get("type"))))
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .build();
        if (syngloss.getType().equals(SynglossType.PARENT)) {
            item.computeIfAbsent("validity", key -> SyllableCondition.getDefaultItem());
            ExceptionUtils.checkObjectElements(
                    Arrays.asList("phonology", "morphology", "validity"),
                    Arrays.asList(OBJECT, OBJECT, OBJECT),
                    "Parent Syngloss", item
            );
            syngloss.setPhonology(Phonology.getFromItem(item.get("phonology").getM(), "Syngloss phonology"));
            syngloss.setMorphology(Morphology.getFromItem(item.get("morphology").getM(), "Syngloss morphology"));
            syngloss.setValidity(SyllableCondition.getFromItem(item.get("validity").getM(), "Syngloss validity"));
        }
        return syngloss;
    }
}