/**
*Controller for Game Play Engine. Receives messages from View and sends to appropriate model boundary for update.
*This file contains the Controller class for the Game Play Engine
*/
package gamePlayEngine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import gamePlayEngine.model.act.Act;
import gamePlayEngine.model.challenge.Challenge;
import gamePlayEngine.model.challenge.Quiz;
import gamePlayEngine.model.gameElement.GameElement;
import gamePlayEngine.model.gameElement.graphic.Prop;
import gamePlayEngine.model.gameElement.player.reward.RewardBoundary;
import gamePlayEngine.model.gamemodel.GameModel;
import gamePlayEngine.model.gamemodel.GameModelBoundary;
import gamePlayEngine.model.scene.Scene;
import gamePlayEngine.model.screen.Screen;
import gamePlayEngine.view.GameView;
import gamePlayEngine.view.GameViewBoundary;

/**
 * Controller class for Game Play Engine
 */
public class GameController implements ActionListener {

	/* Additional Boundary class members are to be added as needed */
	private GameModelBoundary gameBoundary;
	private RewardBoundary rewardBoundary;
	// adding view class boundary
	private GameViewBoundary gameViewBoundary;

	private static int currentAct;
	private static int currentScene;
	private static int currentScreen;
	private static int currentGameElement;
	private static int currentQuiz;
	private static Act actToStart = new Act();
	private static Scene sceneToStart = new Scene();
	private static Screen screenToStart = new Screen();
	private static GameElement gameElementToStart = new GameElement();
	private static Challenge challengeToStart = new Challenge();
	private static Quiz quizToStart = new Quiz();


	/**
	 * Initializes GameBoundary and RewardBoundary
	 * 
	 * @param game
	 */
	public GameController(GameModel gameModel, GameView gameView) {
		System.out.println("controller has been initialized");
		this.gameBoundary = new GameModelBoundary(gameModel);
		this.gameViewBoundary = new GameViewBoundary(gameView);
		System.out.println("controller knows about model and view");
	}

	/**
	 * Core method that processes both internally generated and externally
	 * generated messages Parses the message and then calls appropriate control
	 * and action. This method is to be updated to support more games with new
	 * messages.
	 * 
	 * @param messageType
	 * @param message
	 * @throws InterruptedException
	 */
	public void gameControllerPlay(MessageType messageType, String message)
			throws InterruptedException {
		if (messageType == MessageType.Internal) {
			// In future add processing for Timer generated internal messages
			// Then parse message parameter to get Control and Action to be
			// called.
			gameBoundary.gmbPlay();
		} else if (messageType == MessageType.External) {
			if (message.equals("start")) {
				// start(gameState);
				gameBoundary.gmbPlay();
				end();
			} else if (message.equals("exit")) {
				// Writing following line to console is for demo purpose and
				// should be removed when view is built
				// System.out.println("Controller: Received user input to end the game");
				System.exit(0);
			} else {
				// Writing following line to console is for demo purpose and
				// should be removed when view is built
				// System.out.println("Controller : Received user input from view "
				// + message);
				String[] input = message.split("[.]");
				if (input[0].equals("Reward") && input[1].equals("AddPoints")) {
					rewardBoundary.adddPoints(Integer.parseInt(input[2]));
				}
			}
		}
	}

	public void start(GameState gameState) {
		gameBoundary.gmbStart(gameState);
	}

	public void end() {
		gameBoundary.gmbEnd();
	}

	/* this method returns the model boundary */
	public GameModelBoundary getModelBoundary() {
		return gameBoundary;
	}

	public GameViewBoundary getViewBoundary() {
		return gameViewBoundary;
	}

	//@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	// --------------ACT ---------------------------
	public static boolean actToStart(GameState gameState) {

		boolean isActStart = false;
		if (currentAct < GameModel.acts.size()) {
			isActStart = true;
			currentScene = 0;
			actToStart = GameModel.acts.get(currentAct);
			try {
				actToStart.actStart(gameState);

			} catch (Exception e) {
				System.err.print(e);
			}
		}
		// System.out.println("Iniyan..." + currentAct);
		return isActStart;
	}

	public static void actToPlay(GameState gameState) {
		if (currentAct < GameModel.acts.size()) {
			actToStart = GameModel.acts.get(currentAct);
			try {
				actToStart.actPlay(gameState);

			} catch (Exception e) {
				System.err.print(e);
			}
		}
	}

	public static void actToEnd(GameState gameState) {
		currentAct++;
		try {
			actToStart.actEnd(gameState);

		} catch (Exception e) {
			System.err.print(e);
		}
	}

	// ---------------SCENE----------------------------
	public static boolean sceneToStart(GameState gameState) {

		boolean isSceneStart = false;
		if (currentScene < actToStart.getScenes().size()) {
			isSceneStart = true;
			currentScreen = 0;
			sceneToStart = (actToStart.getScenes().get(currentScene));
			try {
				sceneToStart.sceneStart(gameState);

			} catch (Exception e) {
				System.err.print(e);
			}
		}
		return isSceneStart;
	}

	public static boolean isSceneStart() {
		boolean isSceneStart = false;
		if (currentScene < actToStart.getScenes().size()) {
			isSceneStart = true;
		}
		return isSceneStart;
	}

	public static void sceneToPlay(GameState gameState) {
		if (currentScene < actToStart.getScenes().size()) {
			sceneToStart = (actToStart.getScenes().get(currentScene));
			try {
				sceneToStart.scenePlay(gameState);
			} catch (Exception e) {
				System.err.print(e);
			}
		}
	}

	public static void sceneToEnd(GameState gameState) {
		currentScene++;
		try {
			sceneToStart.sceneEnd(gameState);
		} catch (Exception e) {
			System.err.print(e);
		}
	}

	public static void startNextScene(String sceneName, GameState gameState) {

		// TODO Write code for starting next scene.//written by rithika
		/*List<Scene> sceneList = gameState.getAct().getScenes();
		Scene resultScene = new Scene();
		boolean flag = false;
		for (Scene scene : sceneList) {
			for (GameElement gameElement : scene.getGameElements()) {
				if (gameElement.getGameElementIdentifier().getId().trim()
						.equals(sceneName.trim())) {
					resultScene = scene;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}
		if (resultScene != null) {

			// System.out.print(resultScreen.getGameElements().get(0).getGameElementIdentifier().getId());
			System.out.println("Result Scene is"
					+ resultScene.getGameElements().size());
			sceneToStart = resultScene;
			currentGameElement = 0;
			resultScene.sceneStart(gameState);

		}*/

	}

	// --------------------SCREEN------------------------
	public static boolean screenToStart(GameState gameState) {

		boolean isScreenStart = false;
		// System.out.println("Iniyan.." + sceneToStart.getScreens().size());
		if (currentScreen < sceneToStart.getScreens().size()) {
			isScreenStart = true;
			currentGameElement = 0;
			screenToStart = sceneToStart.getScreens().get(currentScreen);
			try {
				screenToStart.screenStart(gameState);

			} catch (Exception e) {
				System.err.print(e);
			}
		}
		return isScreenStart;
	}

	public static boolean isScreenStart() {
		boolean isScreenStart = false;
		if (currentScreen < sceneToStart.getScreens().size()) {
			isScreenStart = true;
		}
		return isScreenStart;

	}

	public static void screenToPlay(GameState gameState) {

		if (currentScreen < sceneToStart.getScreens().size()) {
			// screenToStart = sceneToStart.getScreens().get(currentScreen);
			try {

				screenToStart.screenPlay(gameState);

			}

			catch (Exception e) {
				System.err.print(e);
			}

		}
	}

	public static void screenToEnd(GameState gameState) {

		currentScreen++;
		try {
			screenToStart.screenEnd(gameState);

		} catch (Exception e) {
			System.err.print(e);
		}

	}

	// Get the screen name from the view and start that screen.
	public static void startNextScreen(String screenName, GameState gameState) {

		List<Screen> screenList = gameState.getScene().getScreens();
		Screen resultScreen = new Screen();
		boolean flag = false;
		for (Screen screen : screenList) {
			for (GameElement gameElement : screen.getGameElements()) {
				if (gameElement.getGameElementIdentifier().getId().trim()
						.equals(screenName.trim())) {
					resultScreen = screen;
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		if (resultScreen != null) {
			System.out.println("Result Screen is" + resultScreen.getGameElements().size());
			screenToStart = resultScreen;
			currentGameElement = 0;
			resultScreen.screenStart(gameState);

		}

	}

	// ------------------GAME ELEMENT--------------------------

	public static boolean gameElementToStart(GameState gameState) {
		boolean isGameElementStart = false;
		if (currentGameElement < screenToStart.getGameElements().size()) {
			System.out.println("GameElement id "+currentGameElement+" is... >>> "
					+ screenToStart.getGameElements().get(0)
							.getGameElementIdentifier().getId());
			isGameElementStart = true;
			gameElementToStart = screenToStart.getGameElements().get(
					currentGameElement);
			try {
				gameElementToStart.gameElementStart(gameState);
			} catch (Exception e) {
				System.err.print(e);
			}
		}
		return isGameElementStart;
	}

	public static void gameElementToPlay(GameState gameState) {
		System.out.println("GameState is:" + gameElementToStart);
		gameElementToStart.gameElementPlay(gameState);
		

	}

	public static void gameElementToEnd(GameState gameState) {
		currentGameElement++;
		try {
			if (gameElementToStart != null) {
				gameElementToStart.gameElementEnd(gameState);
			}
		} catch (Exception e) {
			System.out.println("Exception in GameController gameElementToEnd():" + e);
		}
	}
	
	public static boolean quizToStart(GameState gameState) {

		boolean isQuizStart = false;
		if (currentQuiz < challengeToStart.getQuiz().size()) {
			isQuizStart = true;
			currentQuiz = 0;
			quizToStart = challengeToStart.getQuiz().get(currentQuiz);
			try {

				quizToStart.quizStart(gameState);
			}

			catch (Exception e) {
				System.err.print(e);
			}

		}
		return isQuizStart;
	}
	
	/*public static boolean isQuizStart() {
		boolean isQuizStart = false;
		if (currentQuiz == quizToStart.getQuiz().size()) {
			isQuizStart = true;
		}
		return isQuizStart;

	}
	
	public static void quizToPlay(GameState gameState) {

		if (currentScreen < quizToStart.getQuiz().size()) {
			// screenToStart = sceneToStart.getScreens().get(currentScreen);
			try {

				quizToStart.quizPlay(gameState);

			}

			catch (Exception e) {
				System.err.print(e);
			}

		}
	}*/
	
	public static void quizToEnd(GameState gameState) {
		currentScreen++;
		try {
			quizToStart.quizEnd(gameState);

		} catch (Exception e) {
			System.err.print(e);
		}
	}
	

	// ----------------PROP----------------------------

	/*
	 * public static boolean propToStart(GameState gameState) { boolean
	 * isPropStart = false; if (currentProp <
	 * gameElementToStart.getGameElements().size()) { isGameElementStart = true;
	 * gameElementToStart = gameElementToStart.getProp().get(
	 * currentGameElement); try {
	 * gameElementToStart.gameElementStart(gameState); } catch (Exception e) {
	 * System.err.print(e); } } return isGameElementStart; }
	 * 
	 * public static void propToPlay(GameState gameState) {
	 * 
	 * try { gameElementToStart.gameElementPlay(gameState); } catch (Exception
	 * e) { System.err.print(e); } }
	 * 
	 * public static void propToEnd(GameState gameState) { currentGameElement++;
	 * try { gameElementToStart.gameElementEnd(gameState); } catch (Exception e)
	 * { System.err.print(e); } }
	 */
	// ------------------PROP END---------------------------------

	public static void displayNext(Prop prop, GameState gameState) {
		if (prop != null) {
			String next = prop.getNext();
			System.out.println("Next to display is" + prop.getNext());

			// Start the nextscreen
			
			if (next != null) {
				if (next.contains("screen")) {					
					GameController.startNextScreen(next, gameState);
				}
				//added by rithika..to start next scene
				if(next.contains("scene")) {
					GameController.startNextScene(next, gameState);
				}
			} else {
				System.out.println("Need to start sequencing");
			}	
		}
	}
	
	public static void viewListener(Observable observable, GameState gameState) {

		Message message = gameState.getMessage();
		if (GameModel.class.isInstance(observable)) {
			if (message == Message.StartComplete) {
				System.out.println("Controller :GameModelStartComplete message is received");
				GameModel gameModel = (GameModel) observable;
				try {
					gameModel.gameModelPlay(gameState);
				} catch (InterruptedException e) {
					System.out.println("Exception" + e);
				}

			}

		}

		if (Act.class.isInstance(observable)) {

			if (message == Message.StartComplete) {
				System.out.println("Controller :ActStartComplete message is received");
				actToPlay(gameState);
			}

			if (message == Message.PlayComplete) {
				System.out
						.println("Controller :ActPlayComplete message is received");

				if (!sceneToStart(gameState))
					System.out.println("Scenes are over");

			}
			if (message == Message.EndComplete) {
				System.out.println("Controller :ActEndComplete message is received");
				// Check if anymore acts left to start. If yes. Play those
				// acts.
				// otherwise print message "Acts are over".
				if (!actToStart(gameState)) {
					System.out.println("All acts are over");
				}

			}

		}

		if (Scene.class.isInstance(observable)) {

			if (message == Message.StartComplete) {
				System.out.print("Controller :SceneStartComplete message is received");
				sceneToPlay(gameState);
			}
			if (message == Message.PlayComplete) {
				System.out.println("Controller :ScenePlayComplete message is received");
				screenToStart(gameState);
			}
			if (message == Message.EndComplete) {
				System.out.println("Controller :SceneEndComplete message is received");
								
				if (isSceneStart()) {
					sceneToStart(gameState);
				}
				else {
					actToEnd(gameState);
				}

			}

		}

		if (Screen.class.isInstance(observable)) {
			if (message == Message.StartComplete) {
				System.out
						.println("Controller :ScreenStartComplete message is received");
				screenToPlay(gameState);
				// System.out.println("Screens are over");

			}
			if (message == Message.PlayComplete) {
				System.out
						.println("Controller :ScreenPlayComplete message is received");
				if (!gameElementToStart(gameState)){
					System.out.println("GameElements are over");
				}								
			}
			if (message == Message.EndComplete) {
				System.out
						.println("Controller: Screen end complete message is received.");
				//displayNext((Prop) gameState.getGameElement(),gameState);
				if (isScreenStart())
					screenToStart(gameState);
				else
					sceneToEnd(gameState);
			}
		}

		if (GameElement.class.isInstance(observable)) {

			if (message == Message.StartComplete) {
				System.out
						.println("Controller :GameElementStartComplete message is received");
				gameElementToPlay(gameState);

			}

			if (message == Message.PlayComplete) {
				System.out
						.println("Controller :GameElementPlayComplete message is received");
				gameElementToEnd(gameState);
			
			}

			if (message == Message.EndComplete) {
				System.out.println("Controller :GameElementEndComplete message is received");

				// 1. To check whether there are any new gameElements to start.
				// If there is any, start that new game element.
				// Otherwise End the current screen.
				// 2. If there is no next tag in xml, follow the sequencing
				// approach.

				System.out.println("Game sequence is" + GameModel.isSequence);
				if (GameModel.isSequence) {
					if (!gameElementToStart(gameState)) {
						System.out.println("No more game elements to start");
						screenToEnd(gameState);
					}
				}
			}
		}
		

		if (Quiz.class.isInstance(observable)) {
			if (message == Message.StartComplete) {
				System.out
						.println("Controller :ScreenStartComplete message is received");
				quizToStart(gameState);
				

			}
			if (message == Message.PlayComplete) {
				System.out
						.println("Controller :ScreenPlayComplete message is received");
				//quizToPlay(gameState);							
			}
			if (message == Message.EndComplete) {
				System.out
						.println("Controller: Screen end complete message is received.");
				//displayNext((Prop) gameState.getGameElement(),gameState);
				/*if (isQuizStart())
					quizToStart(gameState);
				else
					quizToEnd(gameState);*/
			}
	   }
	}
}