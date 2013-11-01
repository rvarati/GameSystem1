/*
*Quiz class extends Challenge base class
*/
package gamePlayEngine.model.challenge;

import java.util.Observer;

import gamePlayEngine.controller.GameState;
import gamePlayEngine.controller.Message;
import gamePlayEngine.model.gameElement.StemDescription;
import gamePlayEngine.model.gameElement.StemOption;
import gamePlayEngine.model.gameElement.StemQuestion;

import javax.xml.bind.annotation.XmlElement;

public class Quiz extends Challenge {
	
		private StemDescription description;
		private StemOption option;
		private StemQuestion question;
		private String timer;
		
						
		@XmlElement(name = "timer")
		public String getTimer() {
			return timer;
		}

		public void setTimer(String timer) {
			this.timer = timer;
		}

		public void quizStart(GameState gameState) {
			// Handle Act specific activities in complex games
			setChanged();
			gameState.setQuiz(this);
			gameState.setMessage(Message.Start);
			notifyObservers(gameState);
		}

		public void quizPlay(GameState gameState) throws InterruptedException {
			setChanged();
			gameState.setQuiz(this);
			gameState.setMessage(Message.Play);
			notifyObservers(gameState);
			
		}

		public void quizEnd(GameState gameState) {
			setChanged();
			gameState.setMessage(Message.End);
			notifyObservers(gameState);
		}

		public void quizpause() {
			throw new UnsupportedOperationException();
		}

		public void quizResume() {
			throw new UnsupportedOperationException();
		}

		public void quizSave() {
			throw new UnsupportedOperationException();
		}

	}

