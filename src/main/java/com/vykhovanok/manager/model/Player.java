package com.vykhovanok.manager.model;

import com.vykhovanok.manager.converter.mark.Convertible;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player implements Convertible {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @NotNull
    @NotEmpty(message = "PlayerName should not be empty")
    @Column(name = "player_name",unique = true)
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*\\s?[А-ЩЬЮЯЇІЄҐA-Z]?[а-щьюяїієґa-z]*[а-щьюяїієґa-z]$")
    private String playerName;

    @NotNull
    @Column(name = "age")
    @Range(min = 1,max = 127)
    private Byte age;

    @NotNull
    @Column(name = "experience")
    @Range(min = 0,max = 1524)
    private Short experience;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Team team;

}
