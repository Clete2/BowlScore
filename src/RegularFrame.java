
public class RegularFrame extends Frame {
	public RegularFrame() {
		super();
		
		this.scores = new int[2];
		this.scores[0] = -1;
		this.scores[1] = -1;
	}

	protected boolean isScoreValid(int score, int position) {
		boolean valid = true;

		if(position < 0 || position > 1) {
			// Trying to score a higher position than the frame allows.
			valid = false;
		} else if(position == 1 && this.scores[0] == -1) {
			// Trying to score when a previous score wasn't set yet. 
			valid = false;
		} else if(position == 1 && this.scores[0] == 10) {
			// Trying to set a score in the second position with a strike already recorded.
			valid = false;
		}else if(score > 10 || score < 0) {
			// Score is too small or too large.
			valid = false;
		}

		return valid;
	}

	public FrameState getFrameState() {
		FrameState frameState = null;

		if(this.scores[0] == -1 && this.scores[1] == -1) {
			frameState = FrameState.UNUSED;
		} else if((this.scores[0] != -1 && this.scores[1] != -1) ||
				this.getSum() == 10) {
			frameState = FrameState.COMPLETE;
		} else {
			frameState = FrameState.STARTED;
		}

		return frameState;
	}
}
