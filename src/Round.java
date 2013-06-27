import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Round {
	private Map<Player, Frame[]> participants;

	public Round() {
		this.participants = new HashMap<Player, Frame[]>();
	}

	public void addPlayer(Player playerToAdd) {
		Frame[] playerFrames = new Frame[10];
		for(int i = 0; i < 10; i++) {
			if(i != 9) {
				playerFrames[i] = new Frame(false);
			} else {
				playerFrames[i] = new Frame(true);
			}
		}
		this.participants.put(playerToAdd, playerFrames);
		
	}

	public Set<Player> getPlayers() {
		return this.participants.keySet();
	}

	public Frame[] getFrames(Player player) {
		return this.participants.get(player);
	}
	
	public void setScore(Player player, int score, int scorePosition, int framePosition) throws InvalidScoreException {
		if(this.canSetScore(player, framePosition)) {
			this.participants.get(player)[framePosition].setScore(score, scorePosition);
		} else {
			String message = "Invalid frame position specified. The previous frame either isn't started or completed.";
			throw new InvalidScoreException(message);
		}
	}
	
	private boolean canSetScore(Player player, int framePosition) {
		boolean canSetScore = true;
		
		if(framePosition != 0 &&
				this.participants.get(player)[framePosition - 1].getFrameState() != FrameState.COMPLETE) {
			canSetScore = false;
		} else if(framePosition < 0 || framePosition > 9) {
			canSetScore = false;
		}
		
		return canSetScore;
	}
}
