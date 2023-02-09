package com.nbcamp.gamematching.matchingservice.exception;

public class CommonException extends RuntimeException {

    public static class MemberAuthorityException extends CommonException {}

    public static class RefreshTokenNotFoundException extends CommonException {}

    public static class IllegalAgumentException extends CommonException {}

}
