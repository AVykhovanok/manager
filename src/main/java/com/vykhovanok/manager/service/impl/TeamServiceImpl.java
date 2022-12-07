package com.vykhovanok.manager.service.impl;

import com.vykhovanok.manager.converter.DtoConverter;
import com.vykhovanok.manager.dto.player.*;
import com.vykhovanok.manager.dto.team.*;
import com.vykhovanok.manager.exception.NotExistException;
import com.vykhovanok.manager.model.Team;
import com.vykhovanok.manager.repository.TeamRepository;
import com.vykhovanok.manager.service.PlayerService;
import com.vykhovanok.manager.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@Transactional
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final PlayerService playerService;

    private final DtoConverter dtoConverter;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PlayerService playerService, DtoConverter dtoConverter) {
        this.teamRepository = teamRepository;
        this.playerService = playerService;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public TeamGetDto getTeamByTeamId(Long teamId) {

        return dtoConverter.convertToDto(teamRepository
                .getTeamByTeamId(teamId)
                .orElseThrow(() ->
                        new NotExistException("NotFound team with id - " + teamId)), TeamGetDto.class);

    }

    @Override
    public TeamGetDto getTeamByTeamName(TeamGetByTeamNameDto teamGetByTeamNameDto) {

        return dtoConverter.convertToDto(teamRepository
                .getTeamByTeamName(teamGetByTeamNameDto.getTeamName())
                .orElseThrow(() ->
                        new NotExistException("NotFound team with name - " + teamGetByTeamNameDto.getTeamName())), TeamGetDto.class);

    }

    @Override
    public Set<TeamGetDto> getAllTeams() {

        Set<TeamGetDto> teamGetDtoSet = teamRepository.getAllTeams();

        if (teamGetDtoSet.isEmpty()) throw new NotExistException("NotFound teams");

        return teamGetDtoSet;

    }

    @Override
    public TeamCreateDto createTeam(TeamCreateDto teamCreateDto) throws DataIntegrityViolationException {

        Team team = dtoConverter.convertToEntity(teamCreateDto, new Team());

        return dtoConverter.convertToDto(teamRepository.save(team), TeamCreateDto.class);

    }

    @Override
    public TeamUpdateDto updateTeam(TeamUpdateDto teamUpdateDto) throws DataIntegrityViolationException {

        Team team = dtoConverter.convertToEntity(teamUpdateDto, new Team());

        return dtoConverter.convertToDto(teamRepository.save(team), TeamUpdateDto.class);

    }

    @Override
    public TeamDeleted deleteTeamByTeamId(Long teamId) {

        Team team = dtoConverter.convertToEntity(getTeamByTeamId(teamId), new Team());

        playerService.getPlayersByTeamTeamId(teamId)
                .forEach(player -> playerService
                        .updatePlayerWithoutTeamId(
                                PlayerUpdateWithoutTeamDto
                                        .builder()
                                        .playerId(player.getPlayerId())
                                        .playerName(player.getPlayerName())
                                        .age(player.getAge())
                                        .experience(player.getExperience())
                                        .build()));

        teamRepository.delete(team);

        return dtoConverter.convertToDto(team, TeamDeleted.class);

    }

    @Override
    public TeamDeleted deleteTeamByTeamName(TeamDeleteByTeamNameDto teamDeleteByTeamNameDto) {

        Team team = dtoConverter.convertToEntity(getTeamByTeamName(
                new TeamGetByTeamNameDto(teamDeleteByTeamNameDto.getTeamName())), new Team());

        playerService.getPlayersByTeamTeamName(teamDeleteByTeamNameDto.getTeamName())
                .forEach(player -> playerService
                        .updatePlayerWithoutTeamId(
                                PlayerUpdateWithoutTeamDto
                                        .builder()
                                        .playerId(player.getPlayerId())
                                        .playerName(player.getPlayerName())
                                        .age(player.getAge())
                                        .experience(player.getExperience())
                                        .build()));

        teamRepository.delete(team);

        return dtoConverter.convertToDto(team, TeamDeleted.class);

    }

}
