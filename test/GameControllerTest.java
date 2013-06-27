import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GameControllerTest {
	private GameController gameController;
	
	@Before
	public void setUp() throws Exception {
		this.gameController = new GameController();
	}

	@After
	public void tearDown() throws Exception {
		this.gameController = null;
	}

	@Test
	public void testGameController() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddPlayer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetPlayers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetScore() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRoundScore() throws InvalidScoreException {
		Player player = this.gameController.addPlayer("test");
		
		this.gameController.setScore(player, 4, 0, 0);
		assertEquals(4, this.gameController.getRoundScore(player));
		
		this.gameController.setScore(player, 3, 1, 0);
		assertEquals(7, this.gameController.getRoundScore(player));
		
		this.gameController.setScore(player, 10, 0, 1);
		assertEquals(7, this.gameController.getRoundScore(player));
		
		this.gameController.setScore(player, 4, 0, 2);
		assertEquals(7, this.gameController.getRoundScore(player));
		
		this.gameController.setScore(player, 2, 1, 2);
		assertEquals(29, this.gameController.getRoundScore(player));
	}
}
