package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.LIST;
import static util.FieldType.STRING;

@Data
@Builder
public class NounMorpheme {
    private List<List<String>> phonology;
    private MorphemeType type;
    private List<NounCategories> categories;

    private static NounMorpheme getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("phonology", "type", "categories"),
                Arrays.asList(LIST, STRING, LIST),
                location, item
        );
        return NounMorpheme.builder()
                .phonology(TypeUtils.getStringListListFromItemList(item.get("phonology").getL(), location + ": phonology"))
                .type(MorphemeType.valueOf(item.get("type").getS()))
                .categories(NounCategories.getListFromItemList(item.get("categories").getL(), location + ": categories"))
                .build();
    }

    public static List<NounMorpheme> getListFromItemList(List<Map<String, AttributeValue>> itemList, String location) {
        return itemList.stream()
                .map(item -> getFromItem(item, location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
