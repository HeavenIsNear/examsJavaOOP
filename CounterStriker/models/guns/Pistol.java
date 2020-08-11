package CounterStriker.models.guns;

public class Pistol extends GunImpl {
    private static final int MAXIMUM_BULLETS_FIRED = 1;

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int currentBullets = this.getBulletsCount();
        if (currentBullets < MAXIMUM_BULLETS_FIRED) {
            return 0;
        }
        this.setBulletsCount(currentBullets - MAXIMUM_BULLETS_FIRED);
        return MAXIMUM_BULLETS_FIRED;
    }
}
