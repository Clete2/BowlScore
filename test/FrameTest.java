import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FrameTest {
	private Frame regularFrame;
	private Frame tenthFrame;

	@Test
	public void testFrame() {
		this.regularFrame = new RegularFrame();
		this.tenthFrame = new ExpandedFrame();
		assertEquals(Frame.class, this.regularFrame.getClass().getSuperclass());
		assertEquals(Frame.class, this.tenthFrame.getClass().getSuperclass());
	}

	@Test
	public void testGetFrameState() throws InvalidScoreException {
		this.regularFrame = new RegularFrame();
		this.tenthFrame = new ExpandedFrame();

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

		this.regularFrame = new RegularFrame();

		this.tenthFrame = new ExpandedFrame();
		this.tenthFrame.setScore(5, 0);
		this.tenthFrame.setScore(5, 1);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);
		this.tenthFrame.setScore(1, 2);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.COMPLETE);

		this.tenthFrame = new ExpandedFrame();
		this.tenthFrame.setScore(10, 0);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);
		this.tenthFrame.setScore(10, 1);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.STARTED);
		this.tenthFrame.setScore(10, 2);
		assertEquals(this.tenthFrame.getFrameState(), FrameState.COMPLETE);
	}

	@Test
	public void testGetFrameOutcome() throws InvalidScoreException {
		this.regularFrame = new RegularFrame();
		this.tenthFrame = new ExpandedFrame();
		
		assertEquals(FrameOutcome.NORMAL, this.regularFrame.getFrameOutcome());
		this.regularFrame.setScore(0, 0);
		this.regularFrame.setScore(0, 1);
		assertEquals(FrameOutcome.NORMAL, this.regularFrame.getFrameOutcome());
		this.regularFrame.setScore(10, 0);
		assertEquals(FrameOutcome.STRIKE, this.regularFrame.getFrameOutcome());
		this.regularFrame.setScore(5, 0);
		this.regularFrame.setScore(5, 1);
		assertEquals(FrameOutcome.SPARE, this.regularFrame.getFrameOutcome());
		
		assertEquals(FrameOutcome.NORMAL, this.tenthFrame.getFrameOutcome());
		this.tenthFrame.setScore(0, 0);
		this.tenthFrame.setScore(0, 1);
		assertEquals(FrameOutcome.NORMAL, this.tenthFrame.getFrameOutcome());
		this.tenthFrame.setScore(10, 0);
		assertEquals(FrameOutcome.STRIKE, this.tenthFrame.getFrameOutcome());
		this.tenthFrame.setScore(5, 0);
		this.tenthFrame.setScore(5, 1);
		assertEquals(FrameOutcome.SPARE, this.tenthFrame.getFrameOutcome());
	}

	@Test
	public void testGetFrameScore() {
		this.regularFrame = new RegularFrame();
		this.tenthFrame = new ExpandedFrame();

		// Regular scoring
		try {
			this.regularFrame.setScore(2, 0);
			this.regularFrame.setScore(4, 1);
			this.tenthFrame.setScore(2, 0);
			this.tenthFrame.setScore(4, 1);
			assertEquals(2, this.regularFrame.getScore(0));
			assertEquals(4, this.regularFrame.getScore(1));
			assertEquals(2, this.tenthFrame.getScore(0));
			assertEquals(4, this.tenthFrame.getScore(1));


			// Three bowls
			this.tenthFrame.setScore(5, 0);
			this.tenthFrame.setScore(5, 1);
			this.tenthFrame.setScore(10, 2);
			assertEquals(5, this.tenthFrame.getScore(0));
			assertEquals(5, this.tenthFrame.getScore(1));
			assertEquals(10, this.tenthFrame.getScore(2));

			// Three strikes
			this.tenthFrame.setScore(10, 0);
			this.tenthFrame.setScore(10, 1);
			this.tenthFrame.setScore(10, 2);
			assertEquals(10, this.tenthFrame.getScore(0));
			assertEquals(10, this.tenthFrame.getScore(1));
			assertEquals(10, this.tenthFrame.getScore(2));
		} catch(Exception e) {
			fail("Exception should not occur.");
		}

		// TODO: Illegal moves
	}

	@Test
	public void testGetFrameSum() {
		this.regularFrame = new RegularFrame();
		this.tenthFrame = new ExpandedFrame();

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
