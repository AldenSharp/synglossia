package handler;

import java.io.IOException;
import java.util.Map;

import model.Syngloss;
import service.LanguageService;

public class LanguageHandler {

    private LanguageService languageService = new LanguageService();

    public Syngloss getSyngloss(Map<String, String> languageNameMap) throws IOException {
        return languageService.getSyngloss(languageNameMap.get("a"));
    }
}