
public class ExpandedFrame extends Frame {
	public ExpandedFrame() {
		super();

		this.scores = new int[3];
		this.scores[0] = -1;
		this.scores[1] = -1;
		this.scores[2] = -1;
	}

	protected boolean isScoreValid(int score, int position) {
		boolean valid = true;

		if(position > 2) {
			// Trying to score a higher position than the frame allows.
			valid = false;
		} else if(position == 1 && this.scores[0] == -1) {
			// Trying to score when a previous score wasn't set yet. 
			valid = false;
		} else if(position == 2 && this.scores[0] != 10 & (this.scores[0] + this.scores[1]) < 10) {
			// Trying to score the third position on frame ten when the player didn't get a strike or spare.
			valid = false;
		} else if(score > 10 || score < 0) {
			// Score is too small or too large.
			valid = false;
		}

		return valid;
	}

	public FrameState getFrameState() {
		FrameState frameState = null;

		if(this.scores[0] == -1 && this.scores[1] == -1 && this.scores[2] == -1) {
			frameState = FrameState.UNUSED;
		} else if(
				(this.scores[0] != -1 && this.scores[1] != -1 && (this.scores[0] + this.scores[1] < 10)) ||
				(this.scores[0] != -1 && this.scores[1] != -1 && this.scores[2] != -1)) {
			frameState = FrameState.COMPLETE;
		} else {
			frameState = FrameState.STARTED;
		}

		return frameState;
	}
}
