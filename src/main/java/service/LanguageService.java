package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.Syngloss;
import model.SynglossType;
import model.evolution.*;
import model.writingSystem.WritingSystem;
import util.TypeUtils;

public class LanguageService {

    private DynamoDBService dynamoDBService = new DynamoDBService();

    public List<String> getLanguageNames() {
        return dynamoDBService.getAllLanguages()
                .stream()
                .map(item -> item.get("name").getS())
                .sorted()
                .collect(Collectors.toList());
    }

    public Syngloss getSyngloss(String languageName) throws IOException {
        Map<String, AttributeValue> languageItem = dynamoDBService.getLanguage(languageName);
        if (languageItem.get("type").getS().equals("PARENT")) {
            Syngloss syngloss = Syngloss.getFromItem(languageItem);
            syngloss.setWritingSystems(getWritingSystemsFromData(languageName));
            syngloss.setDescendantLanguages(getDescendantLanguagesFromData(languageName));
            List<Map<String, AttributeValue>> morphemeItems = dynamoDBService.getGrammaticalMorphemes(languageName);
            syngloss.getMorphology().setMorphemes(morphemeItems);
            return syngloss;
        }
        return Syngloss.builder()
                .name(TypeUtils.getStringFromItem(languageItem.get("name")))
                .type(SynglossType.DESCENDANT)
                .date(TypeUtils.getIntegerFromItem(languageItem.get("date")))
                .build();
    }

    private List<WritingSystem> getWritingSystemsFromData(String language) {
        List<Map<String, AttributeValue>> writingSystemItems = dynamoDBService.getWritingSystems(language);
        return writingSystemItems.stream()
                .map(WritingSystem::getFromItem)
                .collect(Collectors.toList());
    }

    public List<DescendantLanguage> getDescendantLanguagesFromData(String parentLanguage) {
        List<Map<String, AttributeValue>> evolutionItems = dynamoDBService.getEvolutions(parentLanguage);
        List<Evolution> evolutions = Evolution.getListFromItemList(evolutionItems, parentLanguage + " data");
        return evolutions.stream()
                .map(this::getDescendantLanguageFromEvolution)
                .collect(Collectors.toList());
    }

    private DescendantLanguage getDescendantLanguageFromEvolution(Evolution evolution) {
        Map<String, AttributeValue> descendantLanguageItem;
        try {
            descendantLanguageItem = dynamoDBService.getDescendantLanguage(
                    evolution.getDescendantLanguage()
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return DescendantLanguage.getFromItemAndEvolution(descendantLanguageItem, evolution);
    }

    public List<Syngloss> getAncestorSynglosses(String descendantLanguage) {
        List<Map<String, AttributeValue>> evolutionItems = dynamoDBService.getAncestorEvolutions(descendantLanguage);
        /*
        TODO: For each evolutionItem, get its parentLanguage's syngloss object.
        TODO: If this syngloss has type PARENT, then build the full syngloss.
         */
        return new ArrayList<>();
    }
}