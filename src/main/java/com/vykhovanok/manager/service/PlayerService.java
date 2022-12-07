package com.vykhovanok.manager.service;

import com.vykhovanok.manager.dto.player.*;
import com.vykhovanok.manager.model.Player;

import java.util.List;
import java.util.Set;

public interface PlayerService {

    PlayerGetDto getPlayerByPlayerId(Long playerId);

    PlayerGetDto getPlayerByPlayerName(PlayerGetByPlayerNameDto playerGetByPlayerNameDto);

    List<Player> getPlayersByTeamTeamId(Long teamId);

    List<Player> getPlayersByTeamTeamName(String teamName);

    Set<PlayerGetDto> getAllPlayers();

    PlayerCreateDto createPlayerWithoutTeamId(PlayerCreateWithoutPlayerIdDto playerCreateWithoutPlayerIdDto);

    PlayerCreateDto createPlayerWithTeamId(PlayerCreateWithTeamIdDto playerCreateWithTeamIdDto);

    PlayerUpdateDto updatePlayerWithoutTeamId(PlayerUpdateWithoutTeamDto playerUpdateWithoutTeamDto);

    PlayerUpdateDto updatePlayerWithTeamId(PlayerUpdateWithTeamIdDto playerUpdateWithTeamIdDto);

    PlayerDeleted deletePlayerByPlayerId(Long playerId);

    PlayerDeleted deletePlayerByPlayerName(PlayerDeleteByPlayerNameDto playerDeleteByPlayerNameDto);

}
