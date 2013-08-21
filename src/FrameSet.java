import java.util.ArrayList;
import java.util.List;

public class FrameSet {
	List <Frame> frames;

	public FrameSet() {
		this.frames = new ArrayList <Frame>();

		for(int i = 0; i < 9; i++) {
			this.frames.add(new RegularFrame());
		}
		this.frames.add(new ExpandedFrame());
	}

	public void setScore(int framePosition, int scorePosition, int score) throws InvalidScoreException, InvalidFrameException {
		if(this.canSetScore(framePosition)) {
			this.frames.get(framePosition).setScore(score, scorePosition);
		} else {
			throw new InvalidFrameException();
		}
	}

	public int getRoundScore() {
		int score = 0;
		for(int i = 0; i < this.frames.size(); i++) {
			if(i == 0) {
				score += this.getFrameCompleteScore(this.frames.get(i), null, this.frames.get(i + 1), this.frames.get(i + 2));
			} else if(i < frames.size() - 2) {
				score += this.getFrameCompleteScore(this.frames.get(i), this.frames.get(i - 1),
						this.frames.get(i + 1), this.frames.get(i + 2));
			} else if (i < frames.size() - 1) {
				score += this.getFrameCompleteScore(this.frames.get(i), this.frames.get(i - 1),
						this.frames.get(i + 1), null);
			} else {
				score += this.getFrameCompleteScore(this.frames.get(i), this.frames.get(i - 1), null, null);
			}
		}
		return score;
	}

	private int getFrameCompleteScore(Frame frame, Frame previousFrame, Frame nextFrame, Frame followingFrame) {
		int score = 0;

		switch(frame.getFrameOutcome()) {
		case NORMAL:
			if(previousFrame == null ||
			(previousFrame.getFrameOutcome().equals(FrameOutcome.NORMAL))|| 
			(previousFrame.getFrameOutcome().equals(FrameOutcome.STRIKE) && frame.getFrameState().equals(FrameState.COMPLETE)) ||
			(previousFrame.getFrameOutcome().equals(FrameOutcome.SPARE) && !frame.getFrameState().equals(FrameState.UNUSED))) {
				score += frame.getSum();
			}
			break;
		case SPARE:
			try {
				score += frame.getScore(0);
			} catch (UnexpectedDataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(nextFrame != null && !nextFrame.getFrameState().equals(FrameState.UNUSED)) {
				try {
					score += frame.getScore(1);
					score += nextFrame.getScore(0);
				} catch (UnexpectedDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case STRIKE:
			if (nextFrame != null && nextFrame.getFrameState().equals(FrameState.COMPLETE) &&
			!nextFrame.getFrameOutcome().equals(FrameOutcome.STRIKE)) {
				// Next frame is finished, next frame is complete, and they didn't get a strike.
				score += frame.getSum();
				score += nextFrame.getSum();
			} else if (nextFrame != null && nextFrame.getFrameState().equals(FrameState.COMPLETE) &&
					nextFrame.getFrameOutcome().equals(FrameOutcome.STRIKE) && followingFrame != null &&
					!followingFrame.getFrameState().equals(FrameState.UNUSED)) {
				score += frame.getSum();
				try {
					score += nextFrame.getScore(0);
					score += followingFrame.getScore(0);
				} catch (UnexpectedDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(nextFrame == null) {
				// TODO: This seems to work but I am not sure it is correct.
				score += frame.getSum();
				score += frame.getSum();
			}
			break;
		}

		return score;
	}

	private boolean canSetScore(int framePosition) {
		boolean canSetScore = true;

		if(framePosition != 0 &&
				this.frames.get(framePosition - 1).getFrameState() != FrameState.COMPLETE) {
			canSetScore = false;
		} else if(framePosition < 0 || framePosition > 9) {
			canSetScore = false;
		}

		return canSetScore;
	}
}
