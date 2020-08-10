package computers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    private Computer computer;

    @Before
    public void setUp() {
        computer = new Computer("Test_Name");
    }

   @Test (expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWithNullAsName() {
       new Computer(null);
   }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWithEmptyName() {
        new Computer("");
    }

    @Test
    public void testConstructorShouldSetCorrectName() {
        Computer computer = new Computer("Test_Name");

        assertEquals("Test_Name", computer.getName());
    }

    @Test
    public void testShouldReturnCollection() {
       assertNotNull(computer.getParts());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testShouldReturnUnmodifiableCollection() {
        this.computer.getParts().add(createTestPart());
    }

    @Test
    public void testGetTotalPriceShouldReturnZeroWithEmptyCollection() {
        double totalPrice = this.computer.getTotalPrice();
        assertEquals(0, totalPrice, 0);
    }

    @Test
    public void testGetTotalPriceShouldReturnCorrectSum() {
        addPartsToComputer();
        assertEquals(10 ,computer.getTotalPrice(), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddPartShouldThrowWhenPartIsNull() {
        this.computer.addPart(null);
    }

    @Test
    public void testAddPartShouldCorrectlyAddPart() {
        Part actual = createTestPart();
        this.computer.addPart(actual);

        assertEquals(1, computer.getParts().size());

        Part expected = computer.getPart("test_name");
        assertNotNull(expected);

        assertPartsAreEqual(actual, expected);
    }

    @Test
    public void testGetPartShouldBeNullIfPartIsNonExistent() {
        addPartsToComputer();
        Part actual = this.computer.getParts().stream()
                .filter(p -> p.getName().equals("NonExistingName"))
                .findFirst()
                .orElse(null);

        assertNull(actual);
    }

    @Test
    public void testGetPartShouldReturnCorrectPart() {
        Part expected = new Part("third", 3);

        computer.addPart(new Part("first", 1));
        computer.addPart(new Part("second", 2));
        computer.addPart(expected);

        Part actual = this.computer.getParts().stream()
                .filter(p -> p.getName().equals("third"))
                .findFirst()
                .orElse(null);

        assertNotNull(actual);

        assertPartsAreEqual(actual, expected);
    }

    @Test
    public void testRemovePartShouldBeTrueForCorrectPart() {
        Part testPart = createTestPart();

        computer.addPart(testPart);
        assertTrue(computer.removePart(testPart));
    }

    @Test
    public void testRemovePartShouldBeFalseForIncorrectPart() {
        assertFalse(computer.removePart(createTestPart()));
    }

    private void addPartsToComputer() {
        for (int i = 0; i < 10; i++) {
            Part part = new Part("test_parts", 1);
            computer.addPart(part);
        }
    }

    private Part createTestPart() {
        return new Part("test_name", 14.3);
    }

    private void assertPartsAreEqual(Part actual, Part expected) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice(), 0);
    }
}