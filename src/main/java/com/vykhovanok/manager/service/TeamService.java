package com.vykhovanok.manager.service;

import com.vykhovanok.manager.dto.team.*;

import java.util.Set;

public interface TeamService {

    TeamGetDto getTeamByTeamId(Long teamId);

    TeamGetDto getTeamByTeamName(TeamGetByTeamNameDto teamGetByTeamNameDto);

    Set<TeamGetDto> getAllTeams();

    TeamCreateDto createTeam(TeamCreateDto teamCreateDto);

    TeamUpdateDto updateTeam(TeamUpdateDto teamUpdateDto);

    TeamDeleted deleteTeamByTeamId(Long teamId);

    TeamDeleted deleteTeamByTeamName(TeamDeleteByTeamNameDto teamDeleteByTeamNameDto);

}
