package model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.descendantLanguage.DescendantLanguage;
import model.prosody.Prosody;
import model.syllableCondition.SyllableCondition;
import model.writingSystem.WritingSystem;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.*;

@Data
@Builder
public class Syngloss {
    private String name;
    private Integer date;
    private Phonology phonology;
    private SyllableCondition validity;
    private List<WritingSystem> writingSystems;
    private List<DescendantLanguage> descendantLanguages;

    public static Syngloss getFromItem(Map<String, AttributeValue> item) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "date", "phonology", "validity"),
                Arrays.asList(STRING, NUMBER, OBJECT, OBJECT),
                "Syngloss", item
        );
        return Syngloss.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .phonology(Phonology.getFromItem(item.get("phonology").getM(), "Syngloss phonology"))
                .validity(SyllableCondition.getFromItem(item.get("validity").getM(), "Syngloss validity"))
                .build();
    }
}