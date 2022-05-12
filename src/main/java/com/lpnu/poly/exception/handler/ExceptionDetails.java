package com.lpnu.poly.exception.handler;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionDetails {

    private  final HttpStatus STATUS;
    private  final String MESSAGE;
    private  final int HTTP_CODE;


}
