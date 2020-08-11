package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.*;

public class PlayerRepository implements Repository{
    private Collection<Player> models;

    public PlayerRepository() {
        this.models = new HashSet<>();
    }

    @Override
    public Collection<Player> getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Object model) {
        this.models.add((Player) model);
    }

    @Override
    public boolean remove(Object model) {
        return this.models.remove(model);
    }

    @Override
    public Object findByName(String name) {
        return this.models.stream()
                .filter(p -> p.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }

}
