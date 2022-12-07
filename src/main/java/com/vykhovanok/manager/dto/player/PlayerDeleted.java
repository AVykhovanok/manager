package com.vykhovanok.manager.dto.player;

import com.vykhovanok.manager.converter.mark.Convertible;
import com.vykhovanok.manager.model.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerDeleted implements Convertible{

    Long playerId;

    String playerName;

    Byte age;

    Short experience;

    Team team;

}
