import java.util.Set;


public class GameController {
	private Round round;

	public GameController() {
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
	
	public void setScore(Player player, int score, int scorePosition, int framePosition) throws InvalidScoreException {
		this.round.setScore(player, score, scorePosition, framePosition);
	}
	
	public int getRoundScore(Player player) {
		int score = 0;
		Frame[] frames = this.round.getFrames(player);
		for(int i = 0; i < frames.length; i++) {
			if(i == 0) {
				score += this.getFrameCompleteScore(frames[i], null, frames[i + 1]);
			} else if(i < frames.length - 1) {
				score += this.getFrameCompleteScore(frames[i], frames[i - 1], frames[i + 1]);
			} else {
				score += this.getFrameCompleteScore(frames[i], frames[i - 1], null);
			}
		}
		return score;
	}

	private int getFrameCompleteScore(Frame frame, Frame previousFrame, Frame nextFrame) {
		int score = 0;

		switch(frame.getOutcome()) {
		case NORMAL:
			if(previousFrame == null ||
			(previousFrame.getOutcome().equals(FrameOutcome.STRIKE) && frame.getFrameState().equals(FrameState.COMPLETE)) ||
			(previousFrame.getOutcome().equals(FrameOutcome.SPARE) && !frame.getFrameState().equals(FrameState.UNUSED))) {
				score += frame.getSum();
			}
			break;
		case SPARE:
			if(nextFrame != null && !nextFrame.getFrameState().equals(FrameState.UNUSED)) {
				score += frame.getSum();
				score += nextFrame.getScore(0);
			}
			break;
		case STRIKE:
			if(nextFrame != null && nextFrame.getFrameState().equals(FrameState.COMPLETE)) {
				score += frame.getSum();
				score += nextFrame.getSum();
			}
			// TODO: Bug here, what if next frame is 10th? We can calculate earlier
			break;
		}

		return score;
	}
}
