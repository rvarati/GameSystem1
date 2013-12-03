/*The Game Play Engine has an active MVC (model, view, controller) architecture.
*The purpose of this file is to create the model, view and controller components and their interfaces.
*/

package gamePlayEngine;

import gamePlayEngine.controller.GameController;
import gamePlayEngine.model.gamemodel.GameModel;
import gamePlayEngine.model.gamemodel.GameModelBoundary;
import gamePlayEngine.view.GameView;

/**
 * edit game system
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
              
                //Test Game 1
                //gameModelBoundary.startGame("./GameXML/TestGame1_Variations/Game1-Final.xml");
                
                //Test Game 2
                //gameModelBoundary.startGame("./GameXML/TestGame2_Variations/TestGame2-Final.xml");
                
                //Test Game 3
                gameModelBoundary.startGame("./GameXML/TestGame3_Variations/TestGame3-Final.xml");
                
                //Test Game 4
               // gameModelBoundary.startGame("./GameXML/TestGame4_Variations/TestGame4-Final.xml");
                
                //Test Game 5
                //gameModelBoundary.startGame("./GameXML/TestGame1_Variations/Button-ChangeV1.4.xml");               
                //gameModelBoundary.startGame("./GameXML/TestGame2_Variations/Button-ChangeV1.4.xml");
                //gameModelBoundary.startGame("./GameXML/TestGame2_Variations/Button-Color-ChangeV1.2.xml");
                //
               // gameModelBoundary.startGame("./GameXML/TestGame3_Variations/Image-ChangeV1.3.xml");
             
                //gameModelBoundary.startGame("./GameXML/TestGame4_Variations/Color-ChangeV1.3.xml");
                //gameModelBoundary.startGame("./GameXML/TestGame3_Variations/BackGround-Color-ChangeV1.3.xml");
                //gameModelBoundary.startGame("./GameXML/TestGame5_Variations/TestGame5_new_1.xml");
                //gameModelBoundary.startGame("./GameXML/TestGame4_Variations/InformationBox-ChangeV1.4.xml");
     
                    
        }
}
