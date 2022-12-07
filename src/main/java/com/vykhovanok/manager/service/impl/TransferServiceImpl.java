package com.vykhovanok.manager.service.impl;

import com.vykhovanok.manager.converter.DtoConverter;
import com.vykhovanok.manager.dto.player.PlayerGetByPlayerNameDto;
import com.vykhovanok.manager.dto.player.PlayerUpdateWithTeamIdDto;
import com.vykhovanok.manager.dto.team.TeamGetByTeamNameDto;
import com.vykhovanok.manager.dto.team.TeamUpdateDto;
import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferDto;
import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferWithIdsDto;
import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferWithStringsDto;
import com.vykhovanok.manager.exception.NotExistException;
import com.vykhovanok.manager.model.Player;
import com.vykhovanok.manager.model.Team;
import com.vykhovanok.manager.service.PlayerService;
import com.vykhovanok.manager.service.TeamService;
import com.vykhovanok.manager.service.TransferService;
import com.vykhovanok.manager.utils.OperationEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@Slf4j
public class TransferServiceImpl implements TransferService {

    private final PlayerService playerService;

    private final TeamService teamService;

    private final DtoConverter dtoConverter;

    @Autowired
    public TransferServiceImpl(PlayerService playerService, TeamService teamService, DtoConverter dtoConverter) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.dtoConverter = dtoConverter;
    }
    @Override
    public PlayerTeamTransferDto transferPlayerByPlayerIdAndBuyerTeamId(PlayerTeamTransferWithIdsDto playerTeamTransferWithIdsDto){

        Player player = dtoConverter.convertToEntity(playerService.getPlayerByPlayerId(playerTeamTransferWithIdsDto.getPlayerId()), new Player());

        Team buyerTeam = dtoConverter.convertToEntity(teamService.getTeamByTeamId(playerTeamTransferWithIdsDto.getTeamId()), new Team());

        return transferPlayerByPlayerAndTeam( PlayerTeamTransferDto
                .builder()
                .player(player)
                .team(buyerTeam)
                .build());

    }

    @Override
    public PlayerTeamTransferDto transferPlayerByPlayerNameAndBuyerTeamName(PlayerTeamTransferWithStringsDto playerTeamTransferWithStringsDto){

        Player player = dtoConverter
                .convertToEntity(playerService
                        .getPlayerByPlayerName(PlayerGetByPlayerNameDto
                                .builder()
                                .playerName(playerTeamTransferWithStringsDto
                                        .getPlayerName())
                                .build()), new Player());

        Team buyerTeam = dtoConverter
                .convertToEntity(teamService
                        .getTeamByTeamName(TeamGetByTeamNameDto
                                .builder()
                                .teamName(playerTeamTransferWithStringsDto
                                        .getTeamName())
                                .build()), new Team());

        return transferPlayerByPlayerAndTeam( PlayerTeamTransferDto
                .builder()
                .player(player)
                .team(buyerTeam)
                .build());

    }

    @Override
    public PlayerTeamTransferDto transferPlayerByPlayerAndTeam(PlayerTeamTransferDto playerTeamTransferDto){

        Player player = playerTeamTransferDto.getPlayer();
        Team buyerTeam = playerTeamTransferDto.getTeam();

        if(Objects.isNull(player.getTeam())) throw new NotExistException("NotExist player without team");

        double transferPrice = (player.getExperience() * 100000.0) / player.getAge();
        double commission = transferPrice * (player.getTeam().getCommission() / 100.0);
        double fullPrice = transferPrice + commission;

        transferUpdateTeam(buyerTeam, fullPrice, OperationEnum.MINUS);

        transferUpdateTeam(player.getTeam(), fullPrice, OperationEnum.PLUS);

        player = transferUpdatePlayer(player, buyerTeam.getTeamId());

        return PlayerTeamTransferDto
                .builder()
                .player(player)
                .team(buyerTeam)
                .build();

    }


    public void transferUpdateTeam(Team team, Double fullPrice, OperationEnum operationEnum){

        TeamUpdateDto newBuyerTeam = TeamUpdateDto
                .builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .commission(team.getCommission())
                .moneyAccount(calcMoneyAccount(team.getMoneyAccount(),fullPrice,operationEnum))
                .build();

        teamService.updateTeam(newBuyerTeam);

    }

    public Player transferUpdatePlayer(Player player, Long buyerId){

        PlayerUpdateWithTeamIdDto playerUpdateWithTeamIdDto = PlayerUpdateWithTeamIdDto
                .builder()
                .playerId(player.getPlayerId())
                .playerName(player.getPlayerName())
                .age(player.getAge())
                .experience(player.getExperience())
                .teamId(buyerId)
                .build();

        return dtoConverter.convertToEntity(playerService.updatePlayerWithTeamId(playerUpdateWithTeamIdDto), new Player());
    }

    public Double calcMoneyAccount(Double startMonetAccount, Double fullPrice, OperationEnum operationEnum){

        Double resultDouble = null;

        switch (operationEnum){
             case PLUS -> resultDouble = Math.round((startMonetAccount + fullPrice) * 1000.0) / 1000.0;
             case MINUS -> resultDouble = Math.round((startMonetAccount - fullPrice) * 1000.0) / 1000.0;
        }

        return resultDouble;

    }
}
