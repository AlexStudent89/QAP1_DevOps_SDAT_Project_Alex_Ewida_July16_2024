
//SuggestionEngineTest
package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine;
    private SuggestionsDatabase suggestionsDatabase;

    @BeforeEach
    public void setUp() {
        suggestionEngine = new SuggestionEngine();
        suggestionsDatabase = new SuggestionsDatabase();
    }

    @Test
    public void testGenerateSuggestions() throws Exception {
        Path dictionaryPath = getPathFromResource("words.txt");
        suggestionEngine.loadDictionaryData(dictionaryPath);
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellw").contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        Path dictionaryPath = getPathFromResource("words.txt");
        suggestionEngine.loadDictionaryData(dictionaryPath);
        Assertions.assertFalse(suggestionEngine.generateSuggestions("hello").contains("hello"));
    }

    @Test
    public void testSuggestionsAsMock() {
        Map<String, Integer> wordMapForTest = new HashMap<>();
        wordMapForTest.put("test", 1);

        ReflectionUtils.setField(suggestionsDatabase, "wordMap", wordMapForTest);

        suggestionEngine.setWordSuggestionDB(suggestionsDatabase);

        Assertions.assertFalse(suggestionEngine.generateSuggestions("test").contains("test"));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("tes").contains("test"));
    }

    private Path getPathFromResource(String fileName) {
        try {
            InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (resourceStream == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {
                Path tempFile = Files.createTempFile("temp", fileName);
                Files.copy(resourceStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                return tempFile;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource file: " + fileName, e);
        }
    }
}