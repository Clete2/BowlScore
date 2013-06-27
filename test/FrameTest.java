import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FrameTest {
	private Frame regularFrame;
	private Frame tenthFrame;

	@Before
	public void setUp() throws Exception {
		this.regularFrame = new Frame(false);
		this.tenthFrame = new Frame(true);
	}

	@After
	public void tearDown() throws Exception {
		this.regularFrame = null;
		this.tenthFrame = null;
	}

	@Test
	public void testFrame() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetFrameState() throws InvalidScoreException {
		assertEquals(this.regularFrame.getFrameState(), FrameState.UNUSED);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.UNUSED);

		this.regularFrame.setScore(1, 0);
		this.tenthFrame.setScore(1, 0);

		assertEquals(this.regularFrame.getFrameState(), FrameState.STARTED);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);

		this.regularFrame.setScore(2, 1);
		this.tenthFrame.setScore(2, 1);

		assertEquals(this.regularFrame.getFrameState(), FrameState.COMPLETE);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.COMPLETE);

		this.regularFrame = new Frame(false);

		this.tenthFrame = new Frame(true);
		this.tenthFrame.setScore(5, 0);
		this.tenthFrame.setScore(5, 1);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);
		this.tenthFrame.setScore(1, 2);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.COMPLETE);

		this.tenthFrame = new Frame(true);
		this.tenthFrame.setScore(10, 0);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);
		this.tenthFrame.setScore(10, 1);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);
		this.tenthFrame.setScore(10, 2);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);
	}

	@Test
	public void testGetOutcome() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsTenthFrame() {
		assertEquals(regularFrame.isTenthFrame(), false);
		assertEquals(tenthFrame.isTenthFrame(), true);
	}

	@Test
	public void testGetFrameScore() {
		// Regular scoring
		try {
			this.regularFrame.setScore(2, 0);
			this.regularFrame.setScore(4, 1);
			this.tenthFrame.setScore(2, 0);
			this.tenthFrame.setScore(4, 1);
		} catch(Exception e) {
			fail("Exception should not occur.");
		}
		assertEquals(2, this.regularFrame.getScore(0));
		assertEquals(4, this.regularFrame.getScore(1));
		assertEquals(2, this.tenthFrame.getScore(0));
		assertEquals(4, this.tenthFrame.getScore(1));

		// Three bowls
		try {
			this.tenthFrame.setScore(5, 0);
			this.tenthFrame.setScore(5, 1);
			this.tenthFrame.setScore(10, 2);
		} catch(Exception e) {
			fail("Exception should not occur.");
		}
		assertEquals(5, this.tenthFrame.getScore(0));
		assertEquals(5, this.tenthFrame.getScore(1));
		assertEquals(10, this.tenthFrame.getScore(2));

		// Three strikes
		try {
			this.tenthFrame.setScore(10, 0);
			this.tenthFrame.setScore(10, 1);
			this.tenthFrame.setScore(10, 2);
		} catch(Exception e) {
			fail("Exception should not occur.");
		}
		assertEquals(10, this.tenthFrame.getScore(0));
		assertEquals(10, this.tenthFrame.getScore(1));
		assertEquals(10, this.tenthFrame.getScore(2));

		// TODO: Illegal moves
	}

	@Test
	public void testGetFrameSum() {
		// Regular scoring
		try {
			this.regularFrame.setScore(2, 0);
			this.regularFrame.setScore(4, 1);
			this.tenthFrame.setScore(2, 0);
			this.tenthFrame.setScore(4, 1);
		} catch(Exception e) {
			fail("Exception should not occur.");
		}
		assertEquals(this.regularFrame.getSum(), 6);
		assertEquals(this.tenthFrame.getSum(), 6);
		
		// Three bowls
		try {
			this.tenthFrame.setScore(5, 0);
			this.tenthFrame.setScore(5, 1);
			this.tenthFrame.setScore(10, 2);
		} catch(Exception e) {
			fail("Exception should not occur.");
		}
		assertEquals(this.tenthFrame.getSum(), 20);

		// Three strikes
		try {
			this.tenthFrame.setScore(10, 0);
			this.tenthFrame.setScore(10, 1);
			this.tenthFrame.setScore(10, 2);
		} catch(Exception e) {
			fail("Exception should not occur.");
		}
		assertEquals(this.tenthFrame.getSum(), 30);

		// TODO: Illegal moves
	}

	@Test
	public void testSetScore() {
		fail("Not yet implemented"); // TODO
	}

}
