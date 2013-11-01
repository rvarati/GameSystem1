package gamePlayEngine.model.gameElement;

import gamePlayEngine.model.challenge.Quiz;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ChallengeStructure {

	private  List<GameElement> gameElements;
	private Quiz quiz;

	@XmlElement(name="gameElement")
	public List<GameElement> getGameElements() {
		return gameElements;
	}

	public void setGameElements(List<GameElement> gameElements) {
		this.gameElements = gameElements;
	}
	
	@XmlElement(name = "quiz")
	public Quiz getQuiz() {
		return quiz;
	}
	
	public void setQuiz() {
		this.quiz = quiz;
	}
	
	
	
	
}
