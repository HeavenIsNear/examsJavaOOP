package halfLife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTests {

    private Player player;
    private Gun gun;

    @Before
    public void setUp() {
        this.player = new Player("test_name", 10);
        this.gun = new Gun("test_gun", 30);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWhenNullUsername() {
        Player player = new Player(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWhenEmptyUsername() {
        Player player = new Player("     ", 10);
    }

    @Test
    public void testConstructorShouldBuildPlayerWithCorrectName() {
        String name = "test_name";
        Player player = new Player(name, 10);

        assertEquals(name, player.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealthShouldThrowWhenLessThanZero() {
        Player player = new Player("test_name", -1);
    }

    @Test
    public void testSetHealthShouldBuildCorrectPlayer() {
        Player player = new Player("test_name", 0);
        assertEquals(0, player.getHealth());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testGetGunsShouldReturnUnmodifiableCollection() {
        Player player = new Player("test_name", 10);
        Gun gun = new Gun("test_gun", 30);
        player.getGuns().add(gun);
    }

    @Test (expected = IllegalStateException.class)
    public void testTakeDamageShouldThrowWhenHealthIsZeroOrLess() {
        Player player = new Player("test_name", 0);
        player.takeDamage(5);
    }

    @Test
    public void testTakeDamageShouldSetHealthToZeroIfMoreDamageThanHealth() {
        Player player = new Player("test_name", 10);
        player.takeDamage(15);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testTakeDamageShouldSetHealthToCorrectValue() {
        this.player.takeDamage(5);

        assertEquals(5, this.player.getHealth());
    }

    @Test (expected = NullPointerException.class)
    public void testAddGunShouldThrowIfGunIsNull() {
        this.gun = null;
        player.addGun(gun);
    }

    @Test
    public void testAddGunShouldAddCorrectGun() {
        player.addGun(gun);

        assertEquals(1, player.getGuns().size());
        assertGunsAreEqual(gun, player.getGun(gun.getName()));
    }

    @Test
    public void testRemoveGunShouldWorkCorrectly() {
        player.addGun(gun);
        player.addGun(new Gun("test_gun_two", 10));
        Gun nonExistingGun = createNonExistingGun();

        assertFalse(player.removeGun(nonExistingGun));
        assertTrue(player.removeGun(gun));
        assertEquals(1, player.getGuns().size());
    }

    @Test
    public void testGetGunShouldBeNullWithNoWeaponMatching() {
        player.addGun(gun);
        Gun nonExistingGun = createNonExistingGun();

        assertNull(player.getGun(nonExistingGun.getName()));
    }

    @Test
    public void testGetGunShouldReturnCorrectGun() {
        Gun nonExistingGun = createNonExistingGun();
        player.addGun(nonExistingGun);
        player.addGun(gun);

        assertEquals(gun, player.getGun(gun.getName()));
    }

    private void assertGunsAreEqual(Gun gun, Gun secondGun) {
        assertEquals(gun.getName(), secondGun.getName());
        assertEquals(gun.getBullets(), secondGun.getBullets());
    }

    private Gun createNonExistingGun() {
        return new Gun("non_existing_gun", 100);
    }
}