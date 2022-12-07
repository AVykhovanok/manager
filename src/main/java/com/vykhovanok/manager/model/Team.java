package com.vykhovanok.manager.model;

import com.vykhovanok.manager.converter.mark.Convertible;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Team implements Convertible {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @NotNull
    @NotEmpty(message = "TeamName should not be empty")
    @Column(name = "team_name",unique = true)
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[ ]?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$")
    private String teamName;

    @Range(min = 0, max = 10)
    @Column(name = "commission")
    private Byte commission;

    @Min(0)
    @Column(name = "money_account")
    private Double moneyAccount;

}
