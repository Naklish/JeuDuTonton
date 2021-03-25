package fr.naklish.jeudutonton;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateMenu extends BasicGameState {
	
	private Image img = null;
	private Image buttonStart = null;
	private int x = 0;
	private int y = 0;
	boolean ready = false;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		img = new Image("data/background4.png");
		buttonStart = new Image("data/start_button.png");
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		img.draw(0, 0, 800, 600);
		buttonStart.draw(300, 250, 200, 100);
		
		if(ready) {
			g.drawString("Waiting for other player...", 300, 400);
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		x = Mouse.getX();
		y = Mouse.getY();
		Input input = container.getInput();
		if((x > 300 && x < 500) && (y > 250 && y < 350) ) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				SetupClass.player.setStartPressed(true);
				ready = true;
			}
		}
		if(SetupClass.start == true) {
			sbg.enterState(1);
		}
	}

	public int getID() {
		return 0;
	}

}
