import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Round {
	private Map<Player, FrameSet> participants;

	public Round() {
		this.participants = new HashMap<Player, FrameSet>();
	}

	public void addPlayer(Player playerToAdd) {
		this.participants.put(playerToAdd, new FrameSet());
	}

	public Set<Player> getPlayers() {
		return this.participants.keySet();
	}

	public FrameSet getFrames(Player player) {
		return this.participants.get(player);
	}

	public void setScore(Player player, int score, int scorePosition, int framePosition) throws InvalidScoreException, InvalidFrameException {
		this.participants.get(player).setScore(framePosition, scorePosition, score);
	}
}
