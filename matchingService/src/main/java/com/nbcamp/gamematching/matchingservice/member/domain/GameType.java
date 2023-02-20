package com.nbcamp.gamematching.matchingservice.member.domain;


import java.util.HashMap;
import java.util.Map;

public enum GameType {
    LOL, STAR;
    private static final Map<String, GameType> stringToEnum = new HashMap<>();


    static {
        for (GameType gameType : values()) {
            stringToEnum.put(gameType.name(), gameType);
        }
    }
    public static boolean isContains(GameType gameType) {
        return stringToEnum.containsValue(gameType);
    }

}

