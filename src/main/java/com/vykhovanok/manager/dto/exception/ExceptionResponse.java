package com.vykhovanok.manager.dto.exception;

import com.vykhovanok.manager.converter.mark.Convertible;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionResponse implements Convertible {

    String message;

    HttpStatusCode status;

    LocalDateTime timeStamp;

}
