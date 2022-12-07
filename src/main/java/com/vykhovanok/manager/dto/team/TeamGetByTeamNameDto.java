package com.vykhovanok.manager.dto.team;

import com.vykhovanok.manager.converter.mark.Convertible;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamGetByTeamNameDto implements Convertible {

    @NotBlank()
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*\\s?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$",
            message = "Teamname має відповідати шаблону")
    String teamName;


}
