package com.nbcamp.gamematching.matchingservice.security;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
