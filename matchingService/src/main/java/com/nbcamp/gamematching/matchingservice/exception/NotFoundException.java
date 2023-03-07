package com.nbcamp.gamematching.matchingservice.exception;

import java.io.IOException;

public class NotFoundException extends RuntimeException{

    public static class NotFoundRefreshTokenException extends NotFoundException {}
    public static class NotFoundDiscordNameException extends NotFoundException {}
    public static class NotFoundMemberException extends NotFoundException {}
    public static class NotFoundRoomException extends NotFoundException {}
    public static class ApplyNotAppliedException extends NotFoundException {}
    public static class NotFoundMatchingException extends NotFoundException {}
    public static class NotFoundConnectException extends NotFoundException {}

}