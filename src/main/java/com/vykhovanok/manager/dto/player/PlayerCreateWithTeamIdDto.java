package com.vykhovanok.manager.dto.player;

import com.vykhovanok.manager.converter.mark.Convertible;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerCreateWithTeamIdDto implements Convertible {


    @NotBlank
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*\\s?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$",
            message = "PLayername має відповідати шаблону")

    String playerName;

    @NotNull
    @Range(min = 1,max = 127)
    Byte age;

    @NotNull
    @Range(min = 0,max = 1524)
    Short experience;

    @NotNull
    Long teamId;



}
