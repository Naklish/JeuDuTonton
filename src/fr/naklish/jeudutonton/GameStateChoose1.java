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

public class GameStateChoose1 extends BasicGameState {
	
	private Image img = null;
	private Image handRaised = null;
	private Image handRaisedD = null;
	private Image chooseButton = null;
	private Image num1 = null;
	private Image num2 = null;
	private Image num3 = null;
	private Image num0 = null;
	private int x = 0;
	private int y = 0;
	private int buttonClicked = 0;
	private boolean clicked = false;
	private boolean ready = false;
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		img = new Image("data/background4.png");
		handRaised = new Image("data/hand.png");
		handRaisedD = new Image("data/handD.png");
		chooseButton = new Image("data/choose_button.png");
		num1 = new Image("data/metal-number-1.png");
		num2 = new Image("data/metal-number-2.png");
		num3 = new Image("data/metal-number-3.png");
		num0 = new Image("data/metal-number-0.png");
		
		
	}
	
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		
		x = Mouse.getX();
		y = Mouse.getY();
		Input input = container.getInput();
		
		if((x >= 210 && x <= 240) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				buttonClicked = 1;
				clicked = true;
				SetupClass.player.setChoixCaillasse(1);
			}
		}else if((x >= 260 && x <= 290) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				buttonClicked = 2;
				clicked = true;
				SetupClass.player.setChoixCaillasse(2);
			}
		}else if((x >= 310 && x <= 340) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				buttonClicked = 3;
				clicked = true;
				SetupClass.player.setChoixCaillasse(3);
			}
		}else if((x >= 360 && x <= 390) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				buttonClicked = 0;
				clicked = true;
				SetupClass.player.setChoixCaillasse(0);
		}
		}
		if((x >= 475 && x <= 550) && (y >= 60 && y <= 95)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && clicked == true) {
				this.setReady(true);
				System.out.println("set ready");
				SetupClass.player.setChoix(true);
			}
		}
		if(SetupClass.opponnent.isChoix()) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			clicked = false;
			buttonClicked = -1;
			ready = false;
		}
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		img.draw(0, 0, 800, 600);
		handRaised.draw(350, 475, 100, 125);
		handRaisedD.draw(350, 0, 100, 125);
		chooseButton.draw(465, 500, 95, 50);
		g.drawString(SetupClass.player.getNom() + " - ", 200, 280);
		g.drawString("How many Stones in your hand :", 200, 300);
		
		g.drawString(SetupClass.opponnent.getNom(), 300, 70);
		g.drawString(SetupClass.player.getNom(), 300, 500);
		
		if(SetupClass.player.getCaillasse() == 1) {
			num1.draw(200, 320, 50, 50);
			num0.draw(350, 320, 50, 50);
		}else if(SetupClass.player.getCaillasse() == 2) {
			num1.draw(200, 320, 50, 50);
			num2.draw(250, 320, 50, 50);
			num0.draw(350, 320, 50, 50);
		}else if(SetupClass.player.getCaillasse() == 3) {
			num1.draw(200, 320, 50, 50);
			num2.draw(250, 320, 50, 50);
			num3.draw(300, 320, 50, 50);
			num0.draw(350, 320, 50, 50);
		}
		if(clicked) {
			g.drawString("Button : clicked", 50, 70);
			switch (buttonClicked) {
			case 0 : 
				num0.setImageColor(0, 100, 200);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(255, 255, 255);break;
			case 1 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(0, 100, 200);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(255, 255, 255);break;
			case 2 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(0, 100, 200);
				num3.setImageColor(255, 255, 255);break;
			case 3 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(0, 100, 200);break;
			}
		}
		
		if(this.isReady()) {
			g.drawString("Waiting for other player...", 300, 400);
		}
		
	}


	public int getID() {
		return 2;
	}
}