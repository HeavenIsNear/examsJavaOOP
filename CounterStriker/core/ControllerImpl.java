package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        if (type.equals("Pistol")) {
            gun = new Pistol(name, bulletsCount);
            guns.add(gun);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name, bulletsCount);
            guns.add(gun);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = guns.findByName(gunName);
        if (gun == null || gunName.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }

        Player player;
        if (type.equals("Terrorist")) {
            player = new Terrorist(username, health, armor, gun);
            players.add(player);
        } else if (type.equals("CounterTerrorist")) {
            player = new CounterTerrorist(username, health, armor, gun);
            players.add(player);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return this.field.start(players.getModels());
    }

    @Override
    public String report() {
        Collection<Player> collection = this.players.getModels();

        collection = collection.stream()
                .sorted((p1, p2) -> {
                    if (p1.getClass().getSimpleName().equals(p2.getClass().getSimpleName())) {
                        if (p2.getHealth() - p1.getHealth() == 0) {
                            return p1.getUsername().compareTo(p2.getUsername());
                        }
                        return p2.getHealth() - p1.getHealth();
                    }
                    return p1.getClass().getSimpleName().compareTo(p2.getClass().getSimpleName());
                })
                .collect(Collectors.toCollection(ArrayList::new));

        StringBuilder builder = new StringBuilder();
        for (Player player : collection) {
            builder.append(player.toString());
        }

        return builder.toString().trim();
    }
}
