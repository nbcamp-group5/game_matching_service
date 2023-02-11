package com.nbcamp.gamematching.matchingservice.member.domain;

import java.util.HashMap;
import java.util.Map;

public enum Tier {
    BRONZE, SILVER, GOLD, PLATINUM, DIAMOND, MASTER, CHALLENGE;

    private static final Map<String, Tier> stringToEnum = new HashMap<>();

    static {
        for (Tier tier : values()) {
            stringToEnum.put(tier.name(), tier);
        }
    }

    public static boolean isContains(Tier tier) {
        return stringToEnum.containsValue(tier);
    }
}
