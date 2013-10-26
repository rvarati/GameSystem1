package gamePlayEngine.model.gameElement;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ChallengeStructure {

	private  List<GameElement> gameElements;

	@XmlElement(name="gameElement")
	public List<GameElement> getGameElements() {
		return gameElements;
	}

	public void setGameElements(List<GameElement> gameElements) {
		this.gameElements = gameElements;
	}
	
	
}
