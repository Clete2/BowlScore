
public class Frame {
	private int[] scores;
	private boolean tenthFrame;
	private FrameState frameState;

	public Frame(boolean tenthFrame) {
		this.frameState = FrameState.UNUSED;
		this.tenthFrame = tenthFrame;
		if(!tenthFrame){
			this.scores = new int[2];
			this.scores[0] = -1;
			this.scores[1] = -1;
		} else {
			this.scores = new int[3];
			this.scores[0] = -1;
			this.scores[1] = -1;
			this.scores[2] = -1;
		}
	}

	public FrameState getFrameState() {
		return this.frameState;
	}

	public FrameOutcome getOutcome() {
		FrameOutcome outcome = FrameOutcome.NORMAL;

		if(this.scores[0] == 10) {
			outcome = FrameOutcome.STRIKE;
		} else if(this.scores[0] + this.scores[1] == 10) {
			outcome = FrameOutcome.SPARE;
		}

		return outcome;
	}

	public boolean isTenthFrame() {
		return this.tenthFrame;
	}

	public int getScore(int position) {
		return this.scores[position];
	}

	public int getSum() {
		int sum = 0;

		for(int score : this.scores) {
			if(score != -1) {
				sum += score;
			}
		}

		return sum;
	}

	public void setScore(int score, int position) throws InvalidScoreException {
		if(this.isScoreValid(score, position)) {
			// Set score
			this.scores[position] = score;
			// Update status
			this.updateStatus();
		}else{
			throw new InvalidScoreException("The score submitted is invalid.");
		}
	}

	private boolean isScoreValid(int score, int position) {
		boolean valid = true;

		if(position < 0 || (!this.tenthFrame && position > 1) || (this.tenthFrame && position > 2)) {
			// Trying to score a higher position than the frame allows.
			valid = false;
		} else if(position >= 1 && this.scores[position - 1] == -1) {
			// Trying to score when a previous score wasn't set yet. 
			valid = false;
		} else if(this.tenthFrame && position == 2 && (this.scores[0] + this.scores[1]) < 10) {
			// Trying to score the third position on frame ten when the player didn't get a strike or spare.
			valid = false;
		} else if(score > 10 || score < 0) {
			// Score is too small or too large.
			valid = false;
		}

		return valid;
	}

	private void updateStatus() {
		if((!this.tenthFrame && this.scores[0] == -1 && this.scores[1] == -1) ||
				(this.tenthFrame && this.scores[0] == -1 && this.scores[1] == -1 && this.scores[2] == -1)) {
			this.frameState = FrameState.UNUSED;
		} else if((!this.tenthFrame && this.scores[0] != -1 && this.scores[1] != -1) ||
				(!this.tenthFrame && this.getSum() == 10) ||
				(this.tenthFrame && this.scores[0] != -1 && this.scores[1] != -1 && this.scores[0] + this.scores[1] < 10) ||
				(this.tenthFrame && this.scores[0] != -1 && this.scores[1] != -1 && this.scores[2] != -1)) {
			this.frameState = FrameState.COMPLETE;
		} else {
			this.frameState = FrameState.STARTED;
		}
	}
}
