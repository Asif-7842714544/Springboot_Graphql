package com.example.Springboot_Graphql.Service;

import com.example.Springboot_Graphql.Entity.Player;
import com.example.Springboot_Graphql.Entity.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> getAllPlayers() {
        return players;
    }

    public Optional<Player> findOne(Integer id) {
        return players
                .stream()
                .filter(player -> player.id() == id)
                .findFirst();
    }

    public Player createPlayer(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player deletePlayer(Integer id) {
        Player player = players.stream().filter(p -> p.id() == id).findFirst().orElseThrow(() -> new IllegalArgumentException());
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> optional = players.stream().filter(p -> p.id() == id).findFirst();
        if (optional.isPresent()) {
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index, updatedPlayer);

        } else {
            throw new IllegalArgumentException("Invalid player");
        }
        return updatedPlayer;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(1, "Ms Dhoni", Team.CSK));
        players.add(new Player(2, "Rohit Sharma", Team.MI));
        players.add(new Player(3, "Bumrah", Team.MI));
        players.add(new Player(4, "Kohli", Team.RCB));
        players.add(new Player(5, "Rishab panth", Team.GT));
    }

}
