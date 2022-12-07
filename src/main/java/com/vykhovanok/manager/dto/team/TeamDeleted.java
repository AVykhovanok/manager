package com.vykhovanok.manager.dto.team;
import com.vykhovanok.manager.converter.mark.Convertible;
import com.vykhovanok.manager.model.Player;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamDeleted implements Convertible{

    Long teamId;

    String teamName;

    Byte commission;

    Double moneyAccount;

    Set<Player> playerSet;

}
