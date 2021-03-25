package fr.naklish.jeudutonton;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateJoueur1 extends BasicGameState {
	
	private Image img = null;
	private Image handRaised = null;
	private Image handRaisedD = null;
	public static TextField textField = null;
	private boolean enterKey = false;

	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		img = new Image("data/background4.png");
		handRaised = new Image("data/hand.png");
		handRaisedD = new Image("data/handD.png");
		textField = new TextField(container, container.getDefaultFont(), 300, 280, 125, 20);
	}
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		img.draw(0, 0, 800, 600);
		handRaised.draw(350, 475, 100, 125);
		handRaisedD.draw(350, 0, 100, 125);
		g.drawString("Nickname :", 300, 260);
		textField.render(container, g);
		
		if(enterKey) {
			g.drawString("Waiting for nicknames...", 300, 300);
		}
		
	}
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			SetupClass.player.setNom(textField.getText());
			textField.setText("");
			enterKey = true;
		}
		if(SetupClass.opponnent.getNom() != null) {
			sbg.enterState(2);
		}
				
	}
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
