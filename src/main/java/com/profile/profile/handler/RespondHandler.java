package com.profile.profile.handler;

import com.profile.profile.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespondHandler {

    public static ResponseEntity<Response> handleResponse(Object data, HttpStatus status){

        Response res = new Response();
        res.setTimeStamp(System.currentTimeMillis());
        res.setResponse(data);

        return new ResponseEntity<>(res, status);
    }
}
