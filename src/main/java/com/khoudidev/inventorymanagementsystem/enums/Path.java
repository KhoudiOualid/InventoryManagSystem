package com.khoudidev.inventorymanagementsystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {
    PHOTOS_PATH(System.getProperty("user.dir") + "/src/main/resources/static/product-images");

    private final String photosURL;
}
