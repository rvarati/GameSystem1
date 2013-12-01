package gamePlayEngine.model.gameElement;

import gamePlayEngine.controller.GameState;
import gamePlayEngine.controller.Message;
import gamePlayEngine.model.challenge.Quiz;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;

public class ChallengeStructure extends Observable{

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
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		 quiz.addObserver(o);		
	}

	public void challengeStart(GameState gameState) {
		// Handle Act specific activities in complex games
		setChanged();
		//gameState.setChallenge(this);
		gameState.setMessage(Message.Start);
		notifyObservers(gameState);
	}

	
	public void challengePlay(GameState gameState) throws InterruptedException {
		setChanged();
		//gameState.setChallenge(this);
		gameState.setMessage(Message.Play);
		notifyObservers(gameState);
		
	}
	
	
	
	
}
