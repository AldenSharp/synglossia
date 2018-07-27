package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.grammar.PartOfSpeech;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.LIST;
import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
public class Word {
    private PartOfSpeech partOfSpeech;
    private SpokenWord spokenForm;
    private List<WrittenWord> writtenForms;

    public static Word getFromItem(Map<String, AttributeValue> item) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("partOfSpeech", "spokenForm", "writtenForms"),
                Arrays.asList(STRING, OBJECT, LIST),
                "Word", item
        );
        return Word.builder()
                .partOfSpeech(PartOfSpeech.valueOf(item.get("partOfSpeech").getS()))
                .spokenForm(SpokenWord.getFromItem(item.get("spokenForm").getM(), "Word"))
                .writtenForms(WrittenWord.getListFromItemList(item.get("writtenForms").getL()))
                .build();
    }
}
