package com.vykhovanok.manager.dto.team;

import com.vykhovanok.manager.converter.mark.Convertible;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamGetDto implements Convertible {

    Long teamId;

    String teamName;

    Byte commission;

    Double moneyAccount;

}
