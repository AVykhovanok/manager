package com.vykhovanok.manager.dto.team;

import com.vykhovanok.manager.converter.mark.Convertible;


import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamCreateDto implements Convertible {

     @NotBlank
     @Size(min = 1,max = 50)
     @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*\\s?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$",
             message = "Teamname має відповідати шаблону")
     String teamName;

     @NotNull
     @Range(min = 0, max = 10)
     Byte commission;

     @NotNull
     @Min(0)
     Double moneyAccount;

}
