package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class GunRepository implements Repository {
    private Collection<Gun> models;

    public GunRepository() {
        this.models = new HashSet<>();
    }

    @Override
    public Collection <Gun> getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Object model) {
        this.models.add((Gun) model);
    }

    @Override
    public boolean remove(Object model) {
        return this.models.remove(model);
    }

    @Override
    public Gun findByName(String name) {
        return this.models.stream()
                .filter(g -> g.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
