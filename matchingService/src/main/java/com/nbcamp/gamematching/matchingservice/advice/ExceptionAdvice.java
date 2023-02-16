package com.nbcamp.gamematching.matchingservice.advice;

import com.nbcamp.gamematching.matchingservice.exception.*;
import com.nbcamp.gamematching.matchingservice.exception.api.RestApiException;
import com.nbcamp.gamematching.matchingservice.exception.api.Status;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Log4j2
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException NotFoundException(NotFoundException e) {
        log.info("e = {}", e.getMessage());

        if (e instanceof NotFoundException.APPLYNOTAPPLIED){
            return new RestApiException(Status.APPLY_NOT_APPLIED);}
        else if (e instanceof NotFoundException.NotFoundMemberException){
            return new RestApiException(Status.NOT_FOUND_MEMBER);}
        else if (e instanceof  NotFoundException.NotFoundRoomException) {
            return new RestApiException(Status.NOT_FOUND_ROOM);}
        else if (e instanceof  NotFoundException.NotFoundDiscordNameException) {
            return new RestApiException(Status.NOT_FOUND_DISCORDNAME);
        }
        return new RestApiException(Status.NOT_FOUND);
    }

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException CommonException(CommonException e) {
        log.info("e = {}", e.getMessage());

        if (e instanceof CommonException.MemberAuthorityException) {
            return new RestApiException(Status.MEMBER_AUTHORITY);
        } else if (e instanceof CommonException.RefreshTokenNotFoundException) {
            return new RestApiException(Status.REFRESHTOKEN_NOT_FOUND);
        } else if (e instanceof CommonException.IllegalAgumentException) {
            return new RestApiException(Status.ILLEGAL_ARGUMENT);}
        return new RestApiException(Status.AUTHORITY);
    }



    @ExceptionHandler(ExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException ExistsException(ExistsException e) {
        log.info("e = {}", e.getMessage());

        if (e instanceof ExistsException.DuplicatedEmail) {
            return new RestApiException(Status.DUPLICATED_EMAIL);
        } else if (e instanceof ExistsException.AlreadyFriendException) {
            return new RestApiException(Status.ALREADY_FRIEND);
        } else if (e instanceof ExistsException.AlreadyMemberException) {
            return new RestApiException(Status.ALREADY_MEMBER_EXISTS);
        } else if (e instanceof ExistsException.AlreadydApplyFriendException) {
            return new RestApiException(Status.ALREADY_APPLIED_FRIEND);
        }

        return new RestApiException(Status.DUPLICATED);
    }



    // 커스텀 외 익셉션
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException illegalArgumentException(IllegalArgumentException e) {
        log.info("e = {}", e.getMessage());

        return new RestApiException(Status.ILLEGAL_ARGUMENT);
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException RefreshTokenException(TokenException e) {
        log.info("e = {}", e.getMessage());

        return new RestApiException(Status.REFRESHTOKEN_NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException AccessDeniedException(AccessDeniedException e) {
        log.info("e = {}", e.getMessage());

        return new RestApiException(Status.MEMBER_AUTHORITY);
    }

    @ExceptionHandler(SignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException SignException(SignException e) {
        log.info("e = {}", e.getMessage());

        return new RestApiException(Status.NOT_FOUND_MEMBER_NOT_MATCHES);
    }

}
