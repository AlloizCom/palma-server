package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.service.LanguageService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Override
    public String getTranslateFile(String language) {
        String languagePath = getPath(language);

        File file = null;
        try {
            file = ResourceUtils.getFile(this.getClass().getResource(languagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();  // return JSON
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return language;
    }

    @Override
    public List<String> getAllTranslate() {
        List<String> translates = new ArrayList<>();
        translates.add(getTranslateFile(Language.UK.toString()));
        translates.add(getTranslateFile(Language.RU.toString()));
        translates.add(getTranslateFile(Language.EN.toString()));
        translates.add(getTranslateFile(Language.PL.toString()));
        return translates;
    }

    private String getPath(String language) {
        String languagePath = "";
        ClassPathResource path = null;
        if (language.equalsIgnoreCase(Language.EN.toString())) {
            languagePath = "/i18n/en.json";
        }
        if (language.equalsIgnoreCase(Language.RU.toString())) {
            languagePath = "/i18n/ru.json";
        }
        if (language.equalsIgnoreCase(Language.UK.toString())) {
            languagePath = "/i18n/uk.json";
        }
        if (language.equalsIgnoreCase(Language.PL.toString())) {
            languagePath = "/i18n/pl.json";
        }
        return languagePath;
    }


}

