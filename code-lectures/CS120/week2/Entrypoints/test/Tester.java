
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class Tester {


	@Test
	public void testAdder() {

		double received_answer = Entrypoints.AdderFunction(3, 5);
		double expected_answer = 8;

		assertEquals(expected_answer, received_answer);


	}
	
	@Test
	public void aTestThatFails() {
		double received_answer = Entrypoints.AdderFunction(3, 5);
		double expected_answer = 12;

		assertEquals(expected_answer, received_answer, "Got the wrong answer (but our expected was wrong)");
	}
	
}