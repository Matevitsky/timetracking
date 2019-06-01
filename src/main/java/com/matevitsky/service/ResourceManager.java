package com.matevitsky.service;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    INSTANCE;

    private final static String RESOURCE_NAME = "i18n/common";
    private ResourceBundle resourceBundle;

    ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
