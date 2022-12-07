package com.vykhovanok.manager.dto.transfer;

import com.vykhovanok.manager.converter.mark.Convertible;
import com.vykhovanok.manager.model.Player;
import com.vykhovanok.manager.model.Team;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerTeamTransferDto implements Convertible {

    Player player;

    Team team;

}
