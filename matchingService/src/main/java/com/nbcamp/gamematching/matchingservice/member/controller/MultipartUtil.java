package com.nbcamp.gamematching.matchingservice.member.controller;

import java.util.UUID;
import org.springframework.util.StringUtils;

public final class MultipartUtil {

    private static final String BASE_DIR = "images";

    public static String getLocalHomeDirectory() {
        return System.getProperty("user.home");
    }

    public static String createFileId() {
        return UUID.randomUUID().toString();
    }

    public static String getFormat(String contentType) {
        if (StringUtils.hasText(contentType)) {
            return contentType.substring(contentType.lastIndexOf('/') + 1);
        }
        return null;
    }

    public static String createPath(String fileId, String format) {
        return String.format("%s/%s.%s", BASE_DIR, fileId, format);
    }
}
