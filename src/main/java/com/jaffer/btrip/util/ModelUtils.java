package com.jaffer.btrip.util;

import org.springframework.ui.Model;

public class ModelUtils {
    public static String getString(Model model, String obj) {
        return (String) model.getAttribute(obj);
    }
}
