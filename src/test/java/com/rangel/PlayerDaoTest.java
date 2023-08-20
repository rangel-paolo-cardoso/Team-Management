package com.rangel;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rangel.dao.DocumentDao;
import com.rangel.dao.PlayerDao;
import com.rangel.dao.TeamDao;
import com.rangel.model.Document;
import com.rangel.model.Player;
import com.rangel.model.Team;

public class PlayerDaoTest {

    private PlayerDao playerDao;
    private TeamDao teamDao;
    private DocumentDao documentDao;

  public PlayerDaoTest() {
    this.playerDao = new PlayerDao();
    this.teamDao = new TeamDao();
    this.documentDao = new DocumentDao();
  }

    @BeforeEach
    @AfterEach
    public void init() {
        this.clearData();
    }

    @Test
    @DisplayName("5 - Must Save a Player.")
    public void mustSave() {
        this.playerDao.save(this.createPlayer("Player", "Striker", null, null));

        List<Player> result = this.playerDao.list();

        assertEquals(1, result.size());
        assertEquals("Player", result.get(0).getName());
        assertEquals("Striker", result.get(0).getPosition());
    }

    @Test
    @DisplayName("6 - Must Save a Player with a Team.")
    public void mustSaveWithATeam() {
        this.playerDao
                .save(this.createPlayer("Player", "Striker", this.createTeam("Team A"), null));

        List<Player> result = this.playerDao.list();

        assertEquals(1, result.size());
        assertEquals("Player", result.get(0).getName());
        assertEquals("Striker", result.get(0).getPosition());
        assertEquals("Team A", result.get(0).getTime().getName());

    }

    @Test
    @DisplayName("7 - Must Save a Player with a Document.")
    public void mustSaveWithADocument() {
        this.playerDao.save(
                this.createPlayer("Player", "Striker", null, this.createDocument("123", null, null)));

        List<Player> result = this.playerDao.list();

        assertEquals(1, result.size());
        assertEquals("Player", result.get(0).getName());
        assertEquals("Striker", result.get(0).getPosition());
        assertEquals("123", result.get(0).getDocument().getWorkCardNumber());

    }

    @Test
    @DisplayName("8 - Must List Players.")
    public void mustList() {
        for (int i = 0; i < 5; i++) {
            this.playerDao.save(this.createPlayer("Player", "Striker", this.createTeam("Team A" + i),
                    this.createDocument("123" + i, null, null)));
        }

        List<Player> result = this.playerDao.list();

        assertEquals(5, result.size());

        for (int i = 0; i < 5; i++) {
            assertEquals("Player", result.get(i).getName());
            assertEquals("Striker", result.get(i).getPosition());
            assertNotNull(result.get(i).getTeam());
            assertNotNull(result.get(i).getDocument());

        }
    }

    @Test
    @DisplayName("9 - Must edit a Player.")
    public void mustEdit() {
        this.playerDao.save(this.createPlayer("Player A", null, null, null));

        Player player = this.playerDao.list().get(0);

        player.setName("Player B");

        this.playerDao.editar(player);

        // VERIFICATION OF EXPECTED RESULT
        List<Player> resultList = this.playerDao.list();

        assertEquals(1, resultList.size());
        assertEquals("Player B", resultList.get(0).getName());
    }

    @Test
    @DisplayName("10 - Must Delete a Player.")
    public void mustDelete() {
        this.playerDao.save(this.createPlayer("Player A", null, null, null));

        Player player = this.playerDao.list().get(0);

        this.playerDao.delete(player.getId());

        // VERIFICATION OF EXPECTED RESULT
        List<Player> resultList = this.playerDao.list();

        assertEquals(0, resultList.size());
    }

    private void clearData() {
        this.clearPlayerData();
        this.clearTeamData();
        this.clearDocumentData();
    }

    private void clearPlayerData() {
        List<Player> objects = this.playerDao.list();

        for (Player object : objects) {
            this.playerDao.delete(object.getId());
        }
    }

    private void clearTeamData() {
        List<Team> objects = this.teamDao.list();

        for (Team object : objects) {
            this.teamDao.delete(object.getId());
        }
    }

    private void clearDocumentData() {
        List<Document> objects = this.documentDao.list();

        for (Document object : objects) {
            this.documentDao.delete(object.getId());
        }
    }

    private Player createPlayer(String name, String position, Team team, Document document) {
        Player player = new Player();
        player.setName(name);
        player.setPosition(position);
        player.setTeam(team);
        player.setDocument(document);

        return player;
    }

    private Team createTeam(String name) {
        Team team = new Team();
        team.setName(name);
        this.teamDao.save(team);

        return this.teamDao.list().get(0);
    }

    private Document createDocument(String workCardNumber, String cpf, String cbfNumber) {
        Document document = new Document();
        System.out.println(workCardNumber);
        document.setWorkCardNumber(workCardNumber);
        document.setCpf(cpf);
        document.setCbfNumber(cbfNumber);
        this.documentDao.save(document);

        return this.documentDao.list().get(0);
    }
}
