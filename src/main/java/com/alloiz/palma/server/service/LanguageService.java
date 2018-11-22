package com.alloiz.palma.server.service;

import java.util.List;

public interface LanguageService {

    String getTranslateFile(String language);

    List<String> getAllTranslate();

}
