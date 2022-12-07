package com.vykhovanok.manager.dto.player;

import com.vykhovanok.manager.converter.mark.Convertible;
import com.vykhovanok.manager.model.Team;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerUpdateDto implements Convertible{

    Long playerId;

    String playerName;

    Byte age;

    Short experience;

    Team team;

}
