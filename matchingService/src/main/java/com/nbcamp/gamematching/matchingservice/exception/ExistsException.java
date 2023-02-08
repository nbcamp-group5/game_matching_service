package com.nbcamp.gamematching.matchingservice.exception;

public class ExistsException extends RuntimeException {

    public static class DuplicatedEmail extends ExistsException{}
    public static class AlreadyMemberException extends ExistsException {}
    public static class AlreadyFriendException extends ExistsException {}
    public static class AlreadydApplyFriendException extends ExistsException {}
}
