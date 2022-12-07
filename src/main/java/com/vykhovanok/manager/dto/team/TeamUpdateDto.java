package com.vykhovanok.manager.dto.team;

import com.vykhovanok.manager.converter.mark.Convertible;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamUpdateDto implements Convertible {

    @NotNull
    private Long teamId;

    @NotBlank
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*\\s?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$",
            message = "Teamname має відповідати шаблону")
    String teamName;

    @NotNull
    @Range(min = 0, max = 10)
    private Byte commission;

    @NotNull
    @Min(0)
    Double moneyAccount;

}
