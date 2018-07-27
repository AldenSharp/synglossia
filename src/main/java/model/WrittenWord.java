package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
class WrittenWord {
    private String form;
    private String writingSystem;

    private static WrittenWord getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("form", "writingSystem"),
                Arrays.asList(STRING, STRING),
                location + ": syllable", item
        );
        return WrittenWord.builder()
                .form(TypeUtils.getStringFromItem(item.get("form")))
                .writingSystem(TypeUtils.getStringFromItem(item.get("writingSystem")))
                .build();
    }

    static List<WrittenWord> getListFromItemList(List<AttributeValue> itemList) {
        ExceptionUtils.checkListElements(itemList, "Word", OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(), "Word at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
