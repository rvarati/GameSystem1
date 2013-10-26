package gamePlayEngine;

import java.util.List;

import gamePlayEngine.model.act.Act;
import gamePlayEngine.model.gameElement.GameElement;
import gamePlayEngine.model.gameElement.graphic.Backdrop;
import gamePlayEngine.model.gameElement.graphic.Event;
import gamePlayEngine.model.gameElement.graphic.Prop;
import gamePlayEngine.model.gameElement.graphic.Type;
import gamePlayEngine.model.gamemodel.GameModel;
import gamePlayEngine.model.scene.Scene;
import gamePlayEngine.model.screen.Screen;

public class TestGameEngine {
  public void testGame(GameModel gameModel){

		/*//this code is for testing
				GameCharacter gc=gameModel.getCharacter();
				Reward reward=gc.getReward();
				//Player player=(Player) gc;
				//System.out.println("the player is: "+player.getName());
				System.out.println("rewards are: "+reward.getPoints());
				*/
				//this is test code and please delete it after testing
				List<Act> actList=gameModel.getActs();
				if(actList==null)
					System.out.println("list is null");
				System.out.println("number of acts is: "+actList.size());
				for (int i = 0; i < actList.size(); i++) {
					Act currentAct=actList.get(i);
					System.out.println("timer of act "+ i +" is: "+currentAct.getTimer());
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
									//System.out.println("element is prop: "+currentProp.getBehavior());
									System.out.println(" location is: "+currentProp.getLocation());
									System.out.println(" color is: "+currentProp.getColor());
									System.out.println("size is: "+currentProp.getSize());
									//System.out.println(" text is: "+currentProp.getText());
									//System.out.println("Next is: "+currentProp.getNext());
									//System.out.println("Font is: "+currentProp.getFont());
									//System.out.println("text size is: "+currentProp.getTextSize());
									//handling types
									Type currentType=currentProp.getType();
									
										
										/*List<Event> eventList=currentType.getEvents();
										//handle events
										for(Event currentEvent: eventList){
											System.out.println("animation: "+currentEvent.getAnimation());
											System.out.println("event name: "+currentEvent.getEventName());
											System.out.println("event time: "+currentEvent.getTime());
										}*/
								}
								else if(currentElement instanceof Backdrop){
									Backdrop currentBack=(Backdrop) currentElement;
									System.out.println("element is backdrop: "+currentBack.getName());
								}
								else{
									System.out.println("other game element: "+currentElement.getClass().toString());
								}
							}
						}
					}
				}
				
				//this is test code and please delete it after tsting
  }
}
