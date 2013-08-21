import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FrameSetTest {
	private FrameSet frames;
	
	@Before
	public void setUp() throws Exception {
		this.frames = new FrameSet();		
	}

	@After
	public void tearDown() throws Exception {
		this.frames = null;
	}

	@Test
	public void testFrameSet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetScore() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRoundScore() throws InvalidScoreException, InvalidFrameException {
		this.frames.setScore(0, 0, 5);
		assertEquals(5, this.frames.getRoundScore());
		this.frames.setScore(0, 1, 3);
		assertEquals(8, this.frames.getRoundScore());
		this.frames.setScore(1, 0, 5);
		assertEquals(13, this.frames.getRoundScore());
		this.frames.setScore(1, 1, 5);
		// Can't calculate total score due to spare.
		assertEquals(13, this.frames.getRoundScore());
		this.frames.setScore(2, 0, 2);
		// Score with added spare.
		assertEquals(22, this.frames.getRoundScore());
		this.frames.setScore(2, 1, 2);
		assertEquals(24, this.frames.getRoundScore());
		this.frames.setScore(3, 0, 10);
		// Can't calculate total score due to strike.
		assertEquals(24, this.frames.getRoundScore());
		this.frames.setScore(4, 0, 4);
		assertEquals(24, this.frames.getRoundScore());
		this.frames.setScore(4, 1, 3);
		// Score with added strike.
		assertEquals(48, this.frames.getRoundScore());
		this.frames.setScore(5, 0, 10);
		assertEquals(48, this.frames.getRoundScore());
		this.frames.setScore(6, 0, 10);
		assertEquals(48, this.frames.getRoundScore());
		this.frames.setScore(7, 0, 10);
		assertEquals(78, this.frames.getRoundScore());
		this.frames.setScore(8, 0, 2);
		assertEquals(100, this.frames.getRoundScore());
		this.frames.setScore(8, 1, 3);
		assertEquals(120, this.frames.getRoundScore());
		this.frames.setScore(9, 0, 1);
		assertEquals(121, this.frames.getRoundScore());
		
		this.frames = new FrameSet();
		
		this.frames.setScore(0, 0, 10);
		this.frames.setScore(1, 0, 10);
		this.frames.setScore(2, 0, 10);
		this.frames.setScore(3, 0, 10);
		this.frames.setScore(4, 0, 10);
		this.frames.setScore(5, 0, 10);
		this.frames.setScore(6, 0, 10);
		this.frames.setScore(7, 0, 10);
		this.frames.setScore(8, 0, 10);
		this.frames.setScore(9, 0, 10);
		this.frames.setScore(9, 1, 10);
		this.frames.setScore(9, 2, 10);
		assertEquals(300, this.frames.getRoundScore());
	}

}
