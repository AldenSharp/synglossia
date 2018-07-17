package service;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.NounRoot;
import model.Word;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class WordService {

    private Random random = new Random();

    private DynamoDBService dynamoDBService = new DynamoDBService();

    public Word getRandomWord(String languageName) {
        List<Map<String, AttributeValue>> attestationItems = dynamoDBService.getAttestations(languageName);
        Integer index = random.nextInt(attestationItems.size());
        Map<String, AttributeValue> wordItem = attestationItems.get(index);
        return Word.getFromItem(wordItem);
    }

    public NounRoot getRandomNounRoot(String languageName) {
        List<Map<String, AttributeValue>> morphemeItems = dynamoDBService.getSemanticMorphemes(languageName);
        List<Map<String, AttributeValue>> nounRootItems = morphemeItems.stream()
                .filter(morphemeItem ->
                        morphemeItem.get("partOfSpeech").getS().equals("NOUN")
                                && morphemeItem.get("type").getS().equals("ROOT")
                )
                .collect(Collectors.toList());
        Integer index = random.nextInt(nounRootItems.size());
        Map<String, AttributeValue> nounRootItem = nounRootItems.get(index);
        return NounRoot.getFromItem(nounRootItem);
    }

}
