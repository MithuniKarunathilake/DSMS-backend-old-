package edu.icet.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DrivingSchoolUtil {
    private DrivingSchoolUtil() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
