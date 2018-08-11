package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.prosody.Prosody;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.*;
import static util.FieldType.OBJECT;

@Data
@Builder
public class Phonology {
    private List<List<PhonotacticsPosition>> phonotactics;
    private Syllable incrementingSyllable;
    private Integer vowelCore;
    private Prosody prosody;

    public static Phonology getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("phonotactics", "vowelCore", "incrementingSyllable", "prosody"),
                Arrays.asList(LIST, NUMBER, OBJECT, OBJECT),
                location + " phonology", item
        );
        return Phonology.builder()
                .phonotactics(PhonotacticsPosition.getListListFromItemList(item.get("phonotactics").getL(), location + ": phonotactics"))
                .incrementingSyllable(Syllable.getFromItem(item.get("incrementingSyllable").getM(), location + ": incrementing syllable"))
                .vowelCore(Integer.parseInt(item.get("vowelCore").getN()))
                .prosody(Prosody.getFromItem(item.get("prosody").getM(), location + ": prosody"))
                .build();
    }
}
