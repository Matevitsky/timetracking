package com.matevitsky.service;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    INSTANCE;

    private final String resourceName = "i18n/common";
    private ResourceBundle resourceBundle;

    ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}