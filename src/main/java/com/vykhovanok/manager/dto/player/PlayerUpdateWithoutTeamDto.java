package com.vykhovanok.manager.dto.player;

import com.vykhovanok.manager.converter.mark.Convertible;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerUpdateWithoutTeamDto implements Convertible {

    @NotNull
    Long playerId;

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
}
