package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

import static CounterStriker.common.ExceptionMessages.*;

public abstract class PlayerImpl implements Player {

    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
        this.isAlive = true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }

        this.username = username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }

        this.health = health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }

        this.armor = armor;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    public void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }

        this.gun = gun;
    }

    @Override
    public void takeDamage(int points) {
        int currentArmor = this.getArmor();
        int currentHealth = this.getHealth();

        if (currentArmor - points <= 0) {
            this.setArmor(0);

            if (currentHealth - (points - currentArmor) <= 0) {
                this.setHealth(0);
                this.setAlive(false);
            } else {
                this.setHealth(currentHealth - (points - currentArmor));
            }
        } else {
            this.setArmor(currentArmor - points);
        }
    }

    @Override
    public String toString() {
        String separator = System.lineSeparator();

        String builder = ": " +
                this.getUsername() +
                separator +
                "--Health: " +
                this.getHealth() +
                separator +
                "--Armor: " +
                this.getArmor() +
                separator +
                "--Gun: " +
                this.getGun().getName() +
                separator;
        return builder;
    }
}
