package CounterStriker.models.guns;

public class Rifle extends GunImpl {
    private static final int MAXIMUM_BULLETS_FIRED = 10;

    public Rifle(String name, int bulletsCount) {
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
