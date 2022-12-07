package com.vykhovanok.manager.dto.transfer;

import com.vykhovanok.manager.converter.mark.Convertible;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerTeamTransferWithStringsDto implements Convertible {

    @NotBlank
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*\\s?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$",
            message = "PLayername має відповідати шаблону")
    String playerName;

    @NotBlank
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*\\s?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$",
            message = "Teamname має відповідати шаблону")
    String teamName;

}
