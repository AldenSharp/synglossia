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
    private List<List<PhonotacticsPosition>> phonotactics;
    private Integer vowelCore;
    private Prosody prosody;
    private SyllableCondition validity;
    private List<WritingSystem> writingSystems;
    private List<DescendantLanguage> descendantLanguages;

    public static Syngloss getFromItem(Map<String, AttributeValue> item) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "date", "phonotactics", "vowelCore", "prosody", "validity"),
                Arrays.asList(STRING, NUMBER, LIST, NUMBER, OBJECT, OBJECT),
                "Syngloss", item
        );
        return Syngloss.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .phonotactics(PhonotacticsPosition.getListListFromItemList(item.get("phonotactics").getL(), "Syngloss phonotactics"))
                .vowelCore(Integer.parseInt(item.get("vowelCore").getN()))
                .prosody(Prosody.getFromItem(item.get("prosody").getM(), "Syngloss prosody"))
                .validity(SyllableCondition.getFromItem(item.get("validity").getM(), "Syngloss validity"))
                .build();
    }
}