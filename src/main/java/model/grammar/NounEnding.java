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

import static util.FieldType.*;

@Data
@Builder
public class NounEnding {
    private List<List<String>> phonology;
    private List<NounCategories> categories;

    public static NounEnding getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("phonology", "categories"),
                Arrays.asList(LIST, LIST),
                location, item
        );
        return NounEnding.builder()
                .phonology(TypeUtils.getStringListListFromItemList(item.get("phonology").getL(), location))
                .categories(NounCategories.getListFromItemList(item.get("categories").getL(), location))
                .build();
    }

    public static List<NounEnding> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(),location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
