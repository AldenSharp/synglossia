package handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.NounRoot;
import model.Syngloss;
import model.Word;
import model.evolution.Evolution;
import service.LanguageService;
import service.WordService;

public class LanguageHandler {

    private LanguageService languageService = new LanguageService();
    private WordService wordService = new WordService();

    public List<String> getLanguageNames() { return languageService.getLanguageNames(); }

    public Syngloss getSyngloss(Map<String, String> languageNameMap) throws IOException {
        return languageService.getSyngloss(languageNameMap.get("a"));
    }

    public Word getRandomWord(Map<String, String> languageNameMap) {
        return wordService.getRandomWord(languageNameMap.get("a"));
    }

    public NounRoot getRandomNounRoot(Map<String, String> languageNameMap) {
        return wordService.getRandomNounRoot(
                languageNameMap.get("languageName"),
                languageNameMap.get("gender"),
                languageNameMap.get("class")
                );
    }
}