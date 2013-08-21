import java.util.Set;


public class Game {
	private Round round;

	public Game() {
		this.round = new Round();
	}

	public Player addPlayer(String name) {
		Player newPlayer = new Player(name);
		this.round.addPlayer(newPlayer);
		return newPlayer;
	}
	
	public Set<Player> getPlayers() {
		return this.round.getPlayers();
	}
	
	public void setScore(Player player, int score, int scorePosition, int framePosition) throws InvalidScoreException, InvalidFrameException {
		this.round.setScore(player, score, scorePosition, framePosition);
	}
}
