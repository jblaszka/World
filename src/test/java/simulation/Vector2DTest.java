package simulation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

	private final Vector2D vector2D = new Vector2D(1, 2);

	@BeforeEach
	void setUp() {
		System.out.println("@BeforeEach");
	}

	@AfterEach
	void tearDown() {
		System.out.println("@AfterEach");
	}

	@Test
	@DisplayName("Add: Should print (2, 3)")
	void add() {
		assertEquals(new Vector2D(2,3),
			vector2D.add(new Vector2D(1, 1)));
	}

	@Test
	@DisplayName("Subtract: Should print (0, 1)")
	void subtract() {
		assertEquals(new Vector2D(0,1),
			vector2D.subtract(new Vector2D(1, 1)));
	}

	@Test
	@DisplayName("Opposite: Should print (-1, -2)")
	void opposite() {
		assertEquals(new Vector2D(-1,-2), vector2D.opposite());
	}

	@Test
	@DisplayName("toString: Should print (1, 2)")
	void testToString() {
		assertEquals("(1, 2)", vector2D.toString());
	}

	@Test
	@DisplayName("toString: Should check all items in the list")
	void shouldCheckAllItemsInTheList() {
		List<Vector2D> numbers = List.of(
			new Vector2D(1, 2),
			new Vector2D(3, 4),
			new Vector2D(6, 23),
			new Vector2D(32, 45));
		Assertions.assertAll(
			() -> assertEquals("(1, 2)", numbers.get(0).toString()),
			() -> assertEquals("(3, 4)", numbers.get(1).toString()),
			() -> assertEquals("(6, 23)", numbers.get(2).toString()),
			() -> assertEquals("(32, 45)", numbers.get(3).toString()));
	}

	// One of the limitations of value sources is that they only support these types:

	// short (with the shorts attribute)
	// byte (bytes attribute)
	// int (ints attribute)
	// long (longs attribute)
	// float (floats attribute)
	// double (doubles attribute)
	// char (chars attribute)
	// java.lang.String (strings attribute)
	// java.lang.Class (classes attribute)

	// Also, we can only pass one argument to the test method each time.
	// We can't pass null through a @ValueSource, even for String and Class.

	@ParameterizedTest(name = "{0}") // this test will be performed first
	@DisplayName("Should create vectors with different coordinates")
	@ValueSource(ints = {3, 4, 5, 8, 14})
	void shouldCreateVectorsWithParametrizedX(int expectedX) {
		Vector2D vector2D = new Vector2D(expectedX, 1);
		assertEquals(expectedX, vector2D.x());
	}
}
