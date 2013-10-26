/**
 * RewarControl class used to define process for Reward Model
 */
package gamePlayEngine.model.gameElement.player.reward;

public class RewardControl {

	private Reward reward;
	public RewardControl(Reward reward) {
		this.reward = reward;
	}
	public void addPoints(int points) {
		reward.addPoints(points);		
	}
}
