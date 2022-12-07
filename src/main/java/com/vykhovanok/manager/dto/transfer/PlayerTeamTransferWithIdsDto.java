package com.vykhovanok.manager.dto.transfer;

import com.vykhovanok.manager.converter.mark.Convertible;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerTeamTransferWithIdsDto implements Convertible {

    @NotNull
    Long playerId;

    @NotNull
    Long teamId;

}
