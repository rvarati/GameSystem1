/*The Game Play Engine has an active MVC (model, view, controller) architecture.
*The purpose of this file is to create the model, view and controller components and their interfaces.
*/

package gamePlayEngine;

import gamePlayEngine.controller.GameController;
import gamePlayEngine.model.gamemodel.GameModel;
import gamePlayEngine.model.gamemodel.GameModelBoundary;
import gamePlayEngine.view.GameView;

/**
 * Core GamePlayEngine class - Creates Model, View and Controller and sets up 
 * interaction amongst them.
 */
public class GamePlayEngine {

	/** Main entry point of the GamePlayEngine - Sets up the game and starts it up.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception  {
		GameModel gameModel=new GameModel();
		//create view
		//Testing Isaac updates 
		GameView gameView=new GameView();
		//create Game controller and inform it about view and model
		GameController gameController=new GameController(gameModel,gameView);
		//inform model about view
		GameModelBoundary gameModelBoundary=gameController.getModelBoundary();
		gameModelBoundary.setView(gameView);		
		//start the game by calling modelboundary
		//gameModelBoundary.startGame("./GameXML/TestGame1_Variations/BackGround-Color-ChangeV1.2.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame1_Variations/Game1-Final.xml");

		gameModelBoundary.startGame("./GameXML/TestGame2_Variations/TestGame2-Final.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame2_Variations/InformationBox-Color-ChangeV1.2.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame2_Variations/Button-Color-ChangeV1.2.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame3_Variations/InformationBox-ColorChangeV1.3.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame3_Variations/TestGame3_new_10.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame4_Variations/TestGame4-Final.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame4_Variations/BackGround-Color-ChangeV1.4.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame3_Variations/BackGround-Color-ChangeV1.3.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame5_Variations/TestGame5_new_1.xml");
		//gameModelBoundary.startGame("./GameXML/TestGame4_Variations/InformationBox-ChangeV1.4.xml");
		//System.out.println("Points are:" + gameModel.getCharacter().getReward().getPoints());
		
		//this code is for testing
				/*GameCharacter gc=gameModel.getCharacter();
				Reward reward=gc.getReward();
				//Player player=(Player) gc;
				//System.out.println("the player is: "+player.getName());
				System.out.println("rewards are: "+reward.getPoints());
				
				//this is test code and please delete it after testing
				List<Act> actList=gameModel.getActs();
				if(actList==null)
					System.out.println("list is null");
				System.out.println("number of acts is: "+actList.size());
				for (int i = 0; i < actList.size(); i++) {
					Act currentAct=actList.get(i);
					List<Scene> sceneList=currentAct.getScenes();
					System.out.println("number of scenes in act"+i+" is"+sceneList.size());
					for (int j = 0; j < sceneList.size(); j++) {
						Scene currentScene=sceneList.get(j);
						List<Screen> screenList=currentScene.getScreens();
						System.out.println("number of screens in scene"+j+" of act "+i+" is:"+screenList.size());
						for (int k = 0; k < screenList.size(); k++) {
							Screen currentScreen=screenList.get(k);
							List<GameElement> elementList=currentScreen.getGameElements();
							System.out.println("number of elements in scene"+j+" of act "+i+" and screen "+k+" is: "+elementList.size());
							for (int l = 0; l < elementList.size(); l++) {
								GameElement currentElement=elementList.get(l);
								if(currentElement instanceof Prop){
									Prop currentProp=(Prop) currentElement;
									System.out.println("element is prop: "+currentProp.getBehavior());
									System.out.println(" location is: "+currentProp.getLocation());
									System.out.println(" color is: "+currentProp.getColor());
									System.out.println(" text is: "+currentProp.getText());
									System.out.println("Next is: "+currentProp.getNext());
									System.out.println("Font is: "+currentProp.getFont());
									System.out.println("text size is: "+currentProp.getTextSize());
									//handling types
									Type currentType=currentProp.getType();
									
										System.out.println("Type is: "+currentType.getTypeName());
										List<Event> eventList=currentType.getEvents();
										//handle events
										for(Event currentEvent: eventList){
											System.out.println("animation: "+currentEvent.getAnimation());
											System.out.println("event name: "+currentEvent.getEventName());
											System.out.println("event time: "+currentEvent.getTime());
										}
								}
								if(currentElement instanceof Backdrop){
									Backdrop currentBack=(Backdrop) currentElement;
									System.out.println("element is backdrop: "+currentBack.getName());
								}
							}
						}
					}
				}*/
				
				//this is test code and please delete it after tsting	
	}
}
