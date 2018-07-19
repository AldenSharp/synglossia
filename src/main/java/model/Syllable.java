package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.LIST;
import static util.FieldType.NUMBER;
import static util.FieldType.OBJECT;

@Data
@Builder
class Syllable {
    private Integer accent;
    private List<String> phonemes;

    private static Syllable getFromItem(Map<String, AttributeValue> item, String location) {
            item.computeIfAbsent("accent", key -> new AttributeValue().withN("0"));
            ExceptionUtils.checkObjectElements(
                    Arrays.asList("accent", "phonemes"),
                    Arrays.asList(NUMBER, LIST),
                    location + ": syllable", item
            );
            return Syllable.builder()
                    .accent(TypeUtils.getIntegerFromItem(item.get("accent")))
                    .phonemes(TypeUtils.getStringListFromItemList(item.get("phonemes").getL(), location + ": syllable phonemes"))
                    .build();
        }

        static List<Syllable> getListFromItemList(List<AttributeValue> itemList, String location) {
            ExceptionUtils.checkListElements(itemList, location, OBJECT);
            return itemList.stream()
                    .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                    .collect(Collectors.toList());
    }
}
