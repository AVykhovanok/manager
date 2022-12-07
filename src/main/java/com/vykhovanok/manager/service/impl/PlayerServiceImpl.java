package com.vykhovanok.manager.service.impl;

import com.vykhovanok.manager.converter.DtoConverter;
import com.vykhovanok.manager.dto.player.*;
import com.vykhovanok.manager.exception.NotExistException;
import com.vykhovanok.manager.model.Player;
import com.vykhovanok.manager.model.Team;
import com.vykhovanok.manager.repository.PlayerRepository;
import com.vykhovanok.manager.repository.TeamRepository;
import com.vykhovanok.manager.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    private final DtoConverter dtoConverter;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository, DtoConverter dtoConverter) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public PlayerGetDto getPlayerByPlayerId(Long playerId) {

        return dtoConverter.convertToDto(playerRepository
                .getPlayerByPlayerId(playerId)
                .orElseThrow(() -> new NotExistException("NotFound player with id - " + playerId)), PlayerGetDto.class);

    }

    @Override
    public PlayerGetDto getPlayerByPlayerName(PlayerGetByPlayerNameDto playerGetByPlayerNameDto) {

        return dtoConverter.convertToDto(playerRepository
                .getPlayerByPlayerName(playerGetByPlayerNameDto.getPlayerName())
                .orElseThrow(() -> new NotExistException("NotFound player with name - " + playerGetByPlayerNameDto.getPlayerName())), PlayerGetDto.class);

    }

    @Override
    public List<Player> getPlayersByTeamTeamId(Long teamId) {

        return playerRepository.getPlayersByTeamTeamId(teamId)
                .orElseThrow(() -> new NotExistException("NotFound players with teamId - " + teamId));

    }

    @Override
    public List<Player> getPlayersByTeamTeamName(String teamName) {

        return playerRepository.getPlayersByTeamTeamName(teamName)
                .orElseThrow(() -> new NotExistException("NotFound players with teamName - " + teamName));

    }

    @Override
    public Set<PlayerGetDto> getAllPlayers() {

        Set<PlayerGetDto> playerGetDtoSet = playerRepository.getAllPlayers();

        if (playerGetDtoSet.isEmpty()) throw  new NotExistException("NotFound players");

        return playerGetDtoSet;

    }

    @Override
    public PlayerCreateDto createPlayerWithoutTeamId(PlayerCreateWithoutPlayerIdDto playerCreateWithoutPlayerIdDto) throws DataIntegrityViolationException {

        Player player = Player
                .builder()
                .playerId(null)
                .playerName(playerCreateWithoutPlayerIdDto.getPlayerName())
                .age(playerCreateWithoutPlayerIdDto.getAge())
                .experience(playerCreateWithoutPlayerIdDto.getExperience())
                .team(null)
                .build();

        return dtoConverter.convertToDto(playerRepository.save(player), PlayerCreateDto.class);

    }

    @Override
    public PlayerCreateDto createPlayerWithTeamId(PlayerCreateWithTeamIdDto playerCreateWithTeamIdDto) throws DataIntegrityViolationException {

        Team team = teamRepository.getTeamByTeamId(playerCreateWithTeamIdDto.getTeamId()).orElseThrow(() ->
                new NotExistException("NotFound team with id - " + playerCreateWithTeamIdDto.getTeamId()));

        Player player = Player
                .builder()
                .playerId(null)
                .playerName(playerCreateWithTeamIdDto.getPlayerName())
                .age(playerCreateWithTeamIdDto.getAge())
                .experience(playerCreateWithTeamIdDto.getExperience())
                .team(team)
                .build();

        return dtoConverter.convertToDto(playerRepository.save(player), PlayerCreateDto.class);

    }

    @Override
    public PlayerUpdateDto updatePlayerWithoutTeamId(PlayerUpdateWithoutTeamDto playerUpdateWithoutTeamDto) throws DataIntegrityViolationException {

        if(!playerRepository.existsByPlayerId(playerUpdateWithoutTeamDto.getPlayerId())) throw new NotExistException("NotExists player with id - " + playerUpdateWithoutTeamDto.getPlayerId());

        Player player = Player
                .builder()
                .playerId(playerUpdateWithoutTeamDto.getPlayerId())
                .playerName(playerUpdateWithoutTeamDto.getPlayerName())
                .age(playerUpdateWithoutTeamDto.getAge())
                .experience(playerUpdateWithoutTeamDto.getExperience())
                .team(null)
                .build();

        return dtoConverter.convertToDto(playerRepository.save(player), PlayerUpdateDto.class);

    }

    @Override
    public PlayerUpdateDto updatePlayerWithTeamId(PlayerUpdateWithTeamIdDto playerUpdateWithTeamIdDto) throws DataIntegrityViolationException {

        if(!playerRepository.existsByPlayerId(playerUpdateWithTeamIdDto.getPlayerId())) throw new NotExistException("NotExists player with id - " + playerUpdateWithTeamIdDto.getPlayerId());

        Team team = teamRepository.getTeamByTeamId(playerUpdateWithTeamIdDto.getTeamId()).orElseThrow(() ->
                new NotExistException("NotFound team with id - " + playerUpdateWithTeamIdDto.getTeamId()));

        Player player = Player
                .builder()
                .playerId(playerUpdateWithTeamIdDto.getPlayerId())
                .playerName(playerUpdateWithTeamIdDto.getPlayerName())
                .age(playerUpdateWithTeamIdDto.getAge())
                .experience(playerUpdateWithTeamIdDto.getExperience())
                .team(team)
                .build();

        return dtoConverter.convertToDto(playerRepository.save(player), PlayerUpdateDto.class);

    }

    @Override
    public PlayerDeleted deletePlayerByPlayerId(Long playerId) {

        Player player = dtoConverter.convertToEntity(getPlayerByPlayerId(playerId), new Player());

        playerRepository.delete(player);

        return dtoConverter.convertToDto(player, PlayerDeleted.class);

    }

    @Override
    public PlayerDeleted deletePlayerByPlayerName(PlayerDeleteByPlayerNameDto playerDeleteByPlayerNameDto) {

        Player player = dtoConverter.convertToEntity(getPlayerByPlayerName(
                new PlayerGetByPlayerNameDto(playerDeleteByPlayerNameDto.getPlayerName())), new Player());

        playerRepository.delete(player);

        return dtoConverter.convertToDto(player, PlayerDeleted.class);

    }

}
