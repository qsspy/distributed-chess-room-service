package com.qsspy.roomservice.controller;

import com.qsspy.roomservice.dto.response.BaseErrorResponse;
import com.qsspy.roomservice.dto.response.Response;
import com.qsspy.roomservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class RoomControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String INTERNAL_ERROR_MESSAGE = "Internal Error occured";

    @ExceptionHandler(RoomServiceException.class)
    public Response<BaseErrorResponse> roomServiceErrorResponse(final RoomServiceException exception) {
        return getErrorEntity(exception);
    }

    @ExceptionHandler(Exception.class)
    public Response<BaseErrorResponse> baseErrorResponse(final Exception exception) {
        final BaseErrorResponse body = new BaseErrorResponse(INTERNAL_ERROR_MESSAGE, exception.getMessage());
        log.error(INTERNAL_ERROR_MESSAGE, exception);
        return Response.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), body);
    }

    private Response<BaseErrorResponse> getErrorEntity(final RoomServiceException exception) {
        int status = 400;

        if(exception instanceof InvalidPasswordException) {
            status = 401;
        }
        else if(exception instanceof NoSuchRoomExistsException) {
            status = 401;
        }
        else if(exception instanceof RoomNotAvailableException) {
            status = 402;
        }
        else if(exception instanceof InvalidAccessTokenException) {
            status = 403;
        }

        final BaseErrorResponse body = new BaseErrorResponse(exception.getMessage(), null);
        return Response.of(status, body);
    }
}
