package fr.naklish.jeudutonton;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameStateOver extends BasicGameState {
	private Image img = null;
	private Image handRaised = null;
	private Image handRaisedD = null;
	//private Image continueButton = null;
	private int x = 0;
	private int y = 0;
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		img = new Image("data/background4.png");
		handRaised = new Image("data/hand.png");
		handRaisedD = new Image("data/handD.png");
	//	continueButton = new Image("data/continue.png");
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		img.draw(0, 0, 800, 600);
		handRaised.draw(350, 475, 100, 125);
		handRaisedD.draw(350, 0, 100, 125);
		
		if(GameStateResult.gameOver && SetupClass.player.getCaillasse() > 0) {
			g.drawString(SetupClass.player.getNom() + " WINS !", 300, 270);
		//	continueButton.draw(300, 290, 150, 100);
			
		}else if(GameStateResult.gameOver && SetupClass.opponnent.getCaillasse() > 0) {
			g.drawString(SetupClass.opponnent.getNom() + " WINS !", 300, 270);
			//continueButton.draw(300, 290, 150, 100);
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		x = Mouse.getX();
		y = Mouse.getY();
		Input input = container.getInput();
		if((x > 305 && x < 415) && (y > 125 && y < 185) ) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			}
		}
	}

	public int getID() {
		return 5;
	}

}
