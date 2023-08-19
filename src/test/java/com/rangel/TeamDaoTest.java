package com.rangel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rangel.dao.FanDao;
import com.rangel.dao.TeamDao;
import com.rangel.model.Fan;
import com.rangel.model.Team;

public class TeamDaoTest {

    private static final String PRE_FIX_PLAYER = "PLAYER";
    private static final String PRE_FIX_FAN = "FAN";

    private TeamDao teamDao;
    private PlayerDao playerDao;
    private FanDao fanDao;

  public TeamDaoTest() {
    this.teamDao = new TeamDao();
    this.playerDao = new PlayerDao();
    this.fanDao = new FanDao();
  }

    @BeforeEach
    @AfterEach
    public void init() {
        this.clearData();
    }

    @Test
    @DisplayName("11 - Must Save a Team.")
    public void mustSave() {
        this.teamDao.save(this.createObject("My team", null, null));

        List<Team> result = this.teamDao.list();

        assertEquals(1, result.size());
        assertEquals("My team", result.get(0).getName());
    }

    @Test
    @DisplayName("12 - Must Save a Team with Players.")
    public void mustSaveWithPlayers() {
        int amountOfPlayers = 11;

        this.teamDao
                .save(this.createObject("My team", this.createPlayers(amountOfPlayers), null));

        List<Team> result = this.teamDao.list();

        assertEquals(1, result.size());
        assertEquals("My team", result.get(0).getName());
        assertEquals(amountOfPlayers, result.get(0).getPlayers().size());
    }

    @Test
    @DisplayName("13 - Must Save a Team with Fans.")
    public void mustSaveWithFans() {
        int amountOfFans = 5;

        this.teamDao
                .save(this.createObject("My team", null, this.createFan(amountOfFans)));

        List<Team> result = this.teamDao.list();

        assertEquals(1, result.size());
        assertEquals("My team", result.get(0).getName());
        assertEquals(amountOfFans, result.get(0).getFans().size());
    }

    @Test
    @DisplayName("14 - Must List the Teams.")
    public void mustList() {
        int amountOfTeams = 10;

        for (int x = 0; x < amountOfTeams; x++) {
            this.teamDao.save(this.createObject("My team", null, null));
        }

        List<Team> result = this.teamDao.list();

        assertEquals(amountOfTeams, result.size());
    }

    @Test
    @DisplayName("15 - Must List a Team with Players.")
    public void mustListWithPlayers() {
        int amountOfPlayers = 11;

        this.teamDao
                .save(this.createObject("My teams", this.createPlayers(amountOfPlayers), null));

        List<Team> result = this.teamDao.list();

        assertEquals(1, result.size());
        assertEquals("My teams", result.get(0).getName());
        assertEquals(amountOfPlayers, result.get(0).getPlayers().size());
    }

    @Test
    @DisplayName("16 - Must List a Team with Fans.")
    public void mustListWithFans() {
        int amountOfFans = 5;

        this.teamDao
                .save(this.createObject("My team", null, this.createFan(amountOfFans)));

        List<Team> result = this.teamDao.list();

        assertEquals(1, result.size());
        assertEquals("My team", result.get(0).getName());
        assertEquals(amountOfFans, result.get(0).getFans().size());

    }

    @Test
    @DisplayName("17 - Must Edit a Team.")
    public void mustEdit() {
        this.teamDao.save(this.createObject("Team A", null, null));

        Team team = this.teamDao.list().get(0);

        team.setName("Team B");

        this.teamDao.edit(team);

        // VERIFICATION OF EXPECTED RESULT
        List<Team> resultList = this.teamDao.list();

        assertEquals(1, resultList.size());
        assertEquals("Team B", resultList.get(0).getName());

    }

    @Test
    @DisplayName("18 - Must Delete a Team")
    public void mustDelete() {
        this.teamDao.save(this.createObject("Team A", null, null));

        Team time = this.teamDao.list().get(0);

        this.teamDao.delete(time.getId());

        // VERIFICATION OF EXPECTED RESULT
        List<Team> resultList = this.teamDao.list();

        assertEquals(0, resultList.size());
    }

    private void clearData() {
        this.cleaTeamData();
        this.clearPlayersData();
        this.clearFansData();
    }

    private void cleaTeamData() {
        List<Team> objetos = this.teamDao.list();

        for (Team documento : objetos) {
            this.teamDao.delete(documento.getId());
        }
    }

    private void clearPlayersData() {
        List<Player> jogadores = this.playerDao.list();

        for (Player jogador : jogadores) {
            this.playerDao.delete(jogador.getId());
        }
    }

    private void clearFansData() {
        List<Fan> torcedores = this.fanDao.list();

        for (Fan jogador : torcedores) {
            this.fanDao.delete(jogador.getId());
        }
    }

    private Team createObject(String nome, List<Player> jogadores, List<Fan> torcedores) {
        Team time = new Team();
        time.setName(nome);
        time.setJogadores(jogadores);
        time.setTorcedores(torcedores);

        return time;
    }

    @SuppressWarnings("unused")
    private List<Player> createPlayers(Integer qtd) {
        List<Player> jogadores = new ArrayList<>();

        for (int x = 0; x < qtd; x++) {
            Player jogador = new Player();
            jogador.setName(PRE_FIXO_JOGADOR + x);
            this.playerDao.save(jogador);
        }

        return this.playerDao.list();
    }

    @SuppressWarnings("unused")
    private List<Fan> createFan(Integer qtd) {
        List<Fan> torcedores = new ArrayList<>();

        for (int x = 0; x < qtd; x++) {
            Fan torcedor = new Fan();
            torcedor.setName(PRE_FIXO_TORCEDOR + x);
            this.fanDao.save(torcedor);
        }

        return this.fanDao.list();
    }
}
