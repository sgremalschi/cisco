package cisco.java.challenge.count;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleWordCounterTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalParameters() {
		new SimpleWordCounter(null);
		new SimpleWordCounter("");
		new SimpleWordCounter("non/existing/path/to/file.txt");
	}

	@Test
	public void testWordCounter() {
		SimpleWordCounter counter = new SimpleWordCounter("src/test/resources/sample_word_file.txt");
		assertNotNull(counter);
		counter.printWordsByFrequency();
	}

}
