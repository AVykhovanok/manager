package com.vykhovanok.manager.service;

import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferDto;
import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferWithIdsDto;
import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferWithStringsDto;

public interface TransferService {

    PlayerTeamTransferDto transferPlayerByPlayerIdAndBuyerTeamId(PlayerTeamTransferWithIdsDto playerTeamTransferWithIdsDto);

    PlayerTeamTransferDto transferPlayerByPlayerNameAndBuyerTeamName(PlayerTeamTransferWithStringsDto playerTeamTransferWithStringsDto);

    PlayerTeamTransferDto transferPlayerByPlayerAndTeam(PlayerTeamTransferDto playerTeamTransferDto);

}
