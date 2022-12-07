package com.vykhovanok.manager.controller;

import com.vykhovanok.manager.dto.team.*;
import com.vykhovanok.manager.service.TeamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/getTeamByTeamId/{teamId}")
    public ResponseEntity<TeamGetDto> getTeamByTeamId(@NotNull @PathVariable Long teamId){

        return new ResponseEntity<>(teamService.getTeamByTeamId(teamId), HttpStatus.OK);

    }

    @GetMapping("/getTeamByTeamName")
    public ResponseEntity<TeamGetDto> getTeamByTeamName(@Valid @RequestBody TeamGetByTeamNameDto teamGetByTeamNameDto){

        return new ResponseEntity<>(teamService.getTeamByTeamName(teamGetByTeamNameDto), HttpStatus.OK);

    }

    @GetMapping("/getAllTeams")
    public ResponseEntity<Set<TeamGetDto>> getAllTeams(){

        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);

    }

    @PostMapping("/createTeamWithoutPlayers")
    public ResponseEntity<TeamCreateDto> createTeam(@Valid @RequestBody TeamCreateDto teamCreateDto){

        return new ResponseEntity<>(teamService.createTeam(teamCreateDto), HttpStatus.CREATED);

    }

    @PutMapping("/updateTeamWithoutPlayers")
    public ResponseEntity<TeamUpdateDto> updateTeam(@Valid @RequestBody TeamUpdateDto teamUpdateDto){

        return new ResponseEntity<>(teamService.updateTeam(teamUpdateDto), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/deleteTeamByTeamId/{teamId}")
    public ResponseEntity<TeamDeleted> deleteTeamByTeamId(@Valid @PathVariable Long teamId){

        return new ResponseEntity<>(teamService.deleteTeamByTeamId(teamId), HttpStatus.OK);

    }

    @DeleteMapping("/deleteTeamByTeamName")
    public ResponseEntity<TeamDeleted> deleteTeamByTeamName(@Valid @RequestBody TeamDeleteByTeamNameDto teamDeleteByTeamNameDto){

        return new ResponseEntity<>(teamService.deleteTeamByTeamName(teamDeleteByTeamNameDto), HttpStatus.OK);

    }
}
