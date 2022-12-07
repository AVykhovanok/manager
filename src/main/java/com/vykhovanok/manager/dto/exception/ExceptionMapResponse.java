package com.vykhovanok.manager.dto.exception;

import com.vykhovanok.manager.converter.mark.Convertible;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionMapResponse implements Convertible {

    Map<String, String> messageMap;

    HttpStatusCode status;

    LocalDateTime timeStamp;

}
