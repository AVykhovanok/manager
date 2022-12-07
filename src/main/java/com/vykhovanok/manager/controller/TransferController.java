package com.vykhovanok.manager.controller;

import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferDto;
import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferWithIdsDto;
import com.vykhovanok.manager.dto.transfer.PlayerTeamTransferWithStringsDto;
import com.vykhovanok.manager.service.TransferService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PutMapping("/transferPlayerByPlayerIdAndBuyerTeamId")
    public ResponseEntity<PlayerTeamTransferDto> transferPlayerByPlayerIdAndBuyerTeamId(@Valid @RequestBody PlayerTeamTransferWithIdsDto playerTeamTransferWithIdsDto){

        return new ResponseEntity<>(transferService.transferPlayerByPlayerIdAndBuyerTeamId(playerTeamTransferWithIdsDto), HttpStatus.ACCEPTED);

    }

    @PutMapping("/transferPlayerByPlayerNameAndBuyerTeamName")
    public ResponseEntity<PlayerTeamTransferDto> transferPlayerByPlayerNameAndBuyerTeamName(@Valid @RequestBody PlayerTeamTransferWithStringsDto playerTeamTransferWithStringsDto){

        return new ResponseEntity<>(transferService.transferPlayerByPlayerNameAndBuyerTeamName(playerTeamTransferWithStringsDto),  HttpStatus.ACCEPTED);

    }

}
