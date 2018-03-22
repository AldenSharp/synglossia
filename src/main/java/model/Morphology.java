package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.OBJECT;

@Data
@Builder
public class Morphology {
    private NominalMorphology nominals;
    private VerbMorphology verbs;

    public static Morphology getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("nominals", "verbs"),
                Arrays.asList(OBJECT, OBJECT),
                "Syngloss morphology", item
        );
        return Morphology.builder()
                .nominals(NominalMorphology.getFromItem(item.get("nominals").getM(), location + ": nominals"))
                .verbs(VerbMorphology.getFromItem(item.get("verbs").getM(), location + ": verbs"))
                .build();
    }
}
