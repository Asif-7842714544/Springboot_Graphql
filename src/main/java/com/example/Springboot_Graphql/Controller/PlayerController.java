package com.example.Springboot_Graphql.Controller;

import com.example.Springboot_Graphql.Entity.Player;
import com.example.Springboot_Graphql.Entity.Team;
import com.example.Springboot_Graphql.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @QueryMapping
    public List<Player> findAll() {
        return playerService.getAllPlayers();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument int id) {
        return playerService.findOne(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument String name,@Argument Team team) {
        return playerService.createPlayer(name, team);
    }

    @MutationMapping
    public Player updatePlayer(@Argument int id, @Argument String name, @Argument Team team) {
        return playerService.update(id, name, team);
    }

    @MutationMapping
    public Player deletePlayer(@Argument int id) {
        return playerService.deletePlayer(id);
    }
}
