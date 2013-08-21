public abstract class Frame {

	protected int[] scores;

	public Frame() {
		super();
	}

	public FrameOutcome getFrameOutcome() {
		FrameOutcome outcome = FrameOutcome.NORMAL;

		if(this.scores[0] == 10) {
			outcome = FrameOutcome.STRIKE;
		} else if(this.scores[0] + this.scores[1] == 10) {
			outcome = FrameOutcome.SPARE;
		}

		return outcome;
	}
	
	public int getScore(int position) throws UnexpectedDataException {
		if(position < this.scores.length) {
			return this.scores[position];
		} else {
			throw new UnexpectedDataException("Score position is invalid.");
		}
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
			// Update the frame's state
		}else{
			throw new InvalidScoreException("The score submitted is invalid.");
		}
	}

	public abstract FrameState getFrameState();
	
	protected abstract boolean isScoreValid(int score, int position);
}