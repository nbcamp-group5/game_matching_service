package com.nbcamp.gamematching.matchingservice.exception;

public class NotFoundException extends RuntimeException{

    public static class NotFoundMemberException extends NotFoundException {}

    public static class NotFoundRoomException extends NotFoundException {}

    public static class APPLYNOTAPPLIED extends NotFoundException {}


}