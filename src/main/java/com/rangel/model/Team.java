package com.rangel.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private List<Player> players;
    private List<Fan> fans;

    public Team() {
    }

    public Team(Integer id, String name, List<Player> players, List<Fan> fans) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.fans = fans;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Fan> getFans() {
        return fans;
    }

    public void setFans(List<Fan> fans) {
        this.fans = fans;
    }
}
