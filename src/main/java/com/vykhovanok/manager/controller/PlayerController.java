package com.vykhovanok.manager.controller;

import com.vykhovanok.manager.dto.player.*;
import com.vykhovanok.manager.service.PlayerService;
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
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getPlayerByPlayerId/{playerId}")
    public ResponseEntity<PlayerGetDto> getPlayerByPlayerId(@NotNull @PathVariable Long playerId){

        return new ResponseEntity<>(playerService.getPlayerByPlayerId(playerId), HttpStatus.OK);

    }

    @GetMapping("/getPlayerByPlayerName")
    public ResponseEntity<PlayerGetDto> getPlayerByPlayerName(@Valid @RequestBody PlayerGetByPlayerNameDto playerGetByPlayerNameDto){

        return new ResponseEntity<>(playerService.getPlayerByPlayerName(playerGetByPlayerNameDto), HttpStatus.OK);

    }

    @GetMapping("/getAllPlayers")
    public ResponseEntity<Set<PlayerGetDto>> getAllPlayers(){

        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);

    }

    @PostMapping("/createPlayerWithoutTeamId")
    public ResponseEntity<PlayerCreateDto> createPlayerWithoutTeamId(@Valid @RequestBody PlayerCreateWithoutPlayerIdDto playerCreateWithoutPlayerIdDto){

        return new ResponseEntity<>(playerService.createPlayerWithoutTeamId(playerCreateWithoutPlayerIdDto), HttpStatus.CREATED);

    }

    @PostMapping("/createPlayerWithTeamId")
    public ResponseEntity<PlayerCreateDto> createPlayerWithTeamId(@Valid @RequestBody PlayerCreateWithTeamIdDto playerCreateWithTeamIdDto){

        return new ResponseEntity<>(playerService.createPlayerWithTeamId(playerCreateWithTeamIdDto),HttpStatus.CREATED);

    }

    @PutMapping("/updatePlayerWithoutTeamId")
    public ResponseEntity<PlayerUpdateDto> updatePlayerWithoutTeamId(@Valid @RequestBody PlayerUpdateWithoutTeamDto playerUpdateWithoutTeamDto){

        return new ResponseEntity<>(playerService.updatePlayerWithoutTeamId(playerUpdateWithoutTeamDto), HttpStatus.ACCEPTED);

    }

    @PutMapping("/updatePlayerWithTeamId")
    public ResponseEntity<PlayerUpdateDto> updatePlayerWithTeamId(@Valid @RequestBody PlayerUpdateWithTeamIdDto playerUpdateWithTeamIdDto){

        return new ResponseEntity<>(playerService.updatePlayerWithTeamId(playerUpdateWithTeamIdDto), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/deletePlayerByPlayerId/{playerId}")
    public ResponseEntity<PlayerDeleted> deletePlayerByPlayerId(@NotNull @PathVariable Long playerId){

        return new ResponseEntity<>(playerService.deletePlayerByPlayerId(playerId), HttpStatus.OK);

    }

    @DeleteMapping("/deletePlayerByPlayerName")
    public ResponseEntity<PlayerDeleted> deletePlayerByPlayerName(@Valid @RequestBody PlayerDeleteByPlayerNameDto playerDeleteByPlayerNameDto){

        return new ResponseEntity<>(playerService.deletePlayerByPlayerName(playerDeleteByPlayerNameDto), HttpStatus.OK);

    }

}
