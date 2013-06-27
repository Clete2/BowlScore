
public class GameView {
	private static GameController gameControl;

	public static void main(String []args) {
		gameControl = new GameController();
		Player clete = gameControl.addPlayer("Clete");
		System.out.println(gameControl.getRoundScore(clete));
		try {
			gameControl.setScore(clete, 5, 0, 0);
		} catch (InvalidScoreException e) {
			System.out.println("You can't set that score!");
		}
		System.out.println(gameControl.getRoundScore(clete));
	}
}
