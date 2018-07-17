package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static util.FieldType.LIST;

@Data
@Builder
public class SpokenWord {
    private List<Syllable> syllables;

    public static SpokenWord getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Collections.singletonList("syllables"),
                Collections.singletonList(LIST),
                location + ": spoken word", item
        );
        return SpokenWord.builder()
                .syllables(Syllable.getListFromItemList(item.get("syllables").getL(), location + ": spoken word"))
                .build();
    }
}
