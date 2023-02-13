package com.nbcamp.gamematching.matchingservice.exception;

import java.util.concurrent.ExecutionException;

public class NotFoundException extends RuntimeException{

    public static class NotFoundMemberException extends NotFoundException {}

    public static class NotFoundRoomException extends NotFoundException {}

    public static class APPLYNOTAPPLIED extends NotFoundException {}


}