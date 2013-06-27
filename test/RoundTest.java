import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Clete2
 *
 */
public class RoundTest {
	private Round myRound;
	
	@Before
	public void setUp() throws Exception {
		myRound = new Round();
	}
	
	@After
	public void tearDown() throws Exception {
		myRound = null;
	}

	@Test
	public void testRound() {
	
	}

	@Test
	public void testAddPlayer() {
		Player newPlayer = new Player("Test Player");
		myRound.addPlayer(newPlayer);
		Set<Player> players = myRound.getPlayers();
		assertEquals(1, players.size());
		assertEquals(newPlayer, players.toArray()[0]);
		// TODO: In-depth testing.
	}

	@Test
	public void testGetPlayers() {
		assertEquals(0, myRound.getPlayers().size());
		Player newPlayer = new Player("Test Player");
		myRound.addPlayer(newPlayer);
		Set<Player> players = myRound.getPlayers();
		assertEquals(1, players.size());
		assertEquals(newPlayer, players.toArray()[0]);
	}

	@Test
	public void testGetFrames() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetScore() {
		fail("Not yet implemented"); // TODO
	}

}
