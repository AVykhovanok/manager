package com.vykhovanok.manager.repository;

import com.vykhovanok.manager.dto.team.TeamGetDto;
import com.vykhovanok.manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> getTeamByTeamId(Long teamId);


    Optional<Team> getTeamByTeamName(String teamName);

    @Query("select distinct new com.vykhovanok.manager.dto.team.TeamGetDto(" +
            "t.teamId," +
            " t.teamName," +
            " t.commission," +
            " t.moneyAccount) from Team t")
    Set<TeamGetDto> getAllTeams();

}
