package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.LIST;
import static util.FieldType.STRING;

@Data
@Builder
public class NounRoot {
    private List<List<String>> phonology;
    private String nounClass;

    public static NounRoot getFromItem(Map<String, AttributeValue> item) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("phonology", "class"),
                Arrays.asList(LIST, STRING),
                "Noun root", item
        );
        return NounRoot.builder()
                .phonology(TypeUtils.getStringListListFromItemList(item.get("phonology").getL(), "Noun root phonology"))
                .nounClass(TypeUtils.getStringFromItem(item.get("class")))
                .build();
    }
}
