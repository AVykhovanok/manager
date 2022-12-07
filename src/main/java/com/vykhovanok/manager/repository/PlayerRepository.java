package com.vykhovanok.manager.repository;

import com.vykhovanok.manager.dto.player.PlayerGetDto;
import com.vykhovanok.manager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> getPlayerByPlayerId(Long playerId);

    Optional<Player> getPlayerByPlayerName(String playerName);


    Optional<List<Player>> getPlayersByTeamTeamId(Long teamId);

    Optional<List<Player>> getPlayersByTeamTeamName(String teamName);

    @Query("select distinct new com.vykhovanok.manager.dto.player.PlayerGetDto(" +
            "p.playerId, " +
            "p.playerName, " +
            "p.age, " +
            "p.experience, " +
            "p.team) from Player p ")
    Set<PlayerGetDto> getAllPlayers();

    Boolean existsByPlayerId(Long playerId);


}
