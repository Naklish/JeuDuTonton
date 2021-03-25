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

public class GameStateBet1 extends BasicGameState{
	private Image img = null;
	private Image handRock = null;
	private Image handRockD = null;
	private Image betButton = null;
	private Image num0 = null;
	private Image num1 = null;
	private Image num2 = null;
	private Image num3 = null;
	private Image num4 = null;
	private Image num5 = null;
	private Image num6 = null;
	private int x = 0;
	private int y = 0;
	private boolean clicked = false;
	private boolean ready = false;
	private int buttonClicked = 0;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		img = new Image("data/background4.png");
		num1 = new Image("data/metal-number-1.png");
		num2 = new Image("data/metal-number-2.png");
		num3 = new Image("data/metal-number-3.png");
		num0 = new Image("data/metal-number-0.png");
		num4 = new Image("data/metal-number-4.png");
		num5 = new Image("data/metal-number-5.png");
		num6 = new Image("data/metal-number-6.png");
		handRock = new Image("data/hand_rock.png");
		handRockD = new Image("data/hand_RockD.png");
		betButton = new Image("data/select_button.png");
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		img.draw(0, 0, 800, 600);
		handRock.draw(350, 475, 100, 125);
		handRockD.draw(350, 0, 100, 125);
		betButton.draw(475, 500, 75, 45);
		g.drawString(SetupClass.player.getNom() + " -  ", 200, 280);
		g.drawString("How many Stones do you expect :", 200, 300);
		
		g.drawString(SetupClass.opponnent.getNom(), 300, 70);
		g.drawString(SetupClass.player.getNom(), 300, 500);
		
		switch (SetupClass.player.getCaillasse() + SetupClass.opponnent.getCaillasse()) {
			case 2 : 
				num1.draw(200, 320, 50, 50);
				num2.draw(250, 320, 50, 50);
				num0.draw(500, 320, 50, 50);
				break;
			case 3 :
				num1.draw(200, 320, 50, 50);
				num2.draw(250, 320, 50, 50);
				num3.draw(300, 320, 50, 50);
				num0.draw(500, 320, 50, 50);
				break;
			case 4 :
				num1.draw(200, 320, 50, 50);
				num2.draw(250, 320, 50, 50);
				num3.draw(300, 320, 50, 50);
				num4.draw(350, 320, 50, 50);
				num0.draw(500, 320, 50, 50);
				break;
			case 5 :
				num1.draw(200, 320, 50, 50);
				num2.draw(250, 320, 50, 50);
				num3.draw(300, 320, 50, 50);
				num4.draw(350, 320, 50, 50);
				num5.draw(400, 320, 50, 50);
				num0.draw(500, 320, 50, 50);
				break;
			case 6 :
				num1.draw(200, 320, 50, 50);
				num2.draw(250, 320, 50, 50);
				num3.draw(300, 320, 50, 50);
				num4.draw(350, 320, 50, 50);
				num5.draw(400, 320, 50, 50);
				num6.draw(450, 320, 50, 50);
				num0.draw(500, 320, 50, 50);
		}
		if(clicked) {
			g.drawString("Button : clicked", 50, 70);
			switch (buttonClicked) {
			case 0 : 
				num0.setImageColor(0, 100, 200);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(255, 255, 255);
				num4.setImageColor(255, 255, 255);
				num5.setImageColor(255, 255, 255);
				num6.setImageColor(255, 255, 255);break;
				
			case 1 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(0, 100, 200);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(255, 255, 255);
				num4.setImageColor(255, 255, 255);
				num5.setImageColor(255, 255, 255);
				num6.setImageColor(255, 255, 255);break;

			case 2 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(0, 100, 200);
				num3.setImageColor(255, 255, 255);
				num4.setImageColor(255, 255, 255);
				num5.setImageColor(255, 255, 255);
				num6.setImageColor(255, 255, 255);break;
			case 3 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(0, 100, 200);
				num4.setImageColor(255, 255, 255);
				num5.setImageColor(255, 255, 255);
				num6.setImageColor(255, 255, 255);break;

			case 4 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(255, 255, 255);
				num4.setImageColor(0, 100, 200);
				num5.setImageColor(255, 255, 255);
				num6.setImageColor(255, 255, 255);break;

			case 5 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(255, 255, 255);
				num4.setImageColor(255, 255, 255);
				num5.setImageColor(0, 100, 200);
				num6.setImageColor(255, 255, 255);break;

			case 6 :
				num0.setImageColor(255, 255, 255);
				num1.setImageColor(255, 255, 255);
				num2.setImageColor(255, 255, 255);
				num3.setImageColor(255, 255, 255);
				num4.setImageColor(255, 255, 255);
				num5.setImageColor(255, 255, 255);
				num6.setImageColor(0, 100, 200);break;

			}
		}
		if(ready) {
			g.drawString("Waiting for other player...", 300, 400);
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		x = Mouse.getX();
		y = Mouse.getY();
		Input input = container.getInput();
		
		if((x >= 210 && x <= 240) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				clicked = true;
				buttonClicked = 1;
				SetupClass.player.setPronostique(1);
			}
		}else if((x >= 260 && x <= 290) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				clicked = true;
				buttonClicked = 2;
				SetupClass.player.setPronostique(2);
			}
		}else if((x >= 310 && x <= 340) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				clicked = true;
				buttonClicked = 3;
				SetupClass.player.setPronostique(3);
			}
		}else if((x >= 360 && x <= 390) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				clicked = true;
				buttonClicked = 4;
				SetupClass.player.setPronostique(4);
		}
		}else if((x >= 410 && x <= 440) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				clicked = true;
				buttonClicked = 5;
				SetupClass.player.setPronostique(5);
		}
		}else if((x >= 460 && x <= 490) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				clicked = true;
				buttonClicked = 6;
				SetupClass.player.setPronostique(6);
		}
		}else if((x >= 510 && x <= 540) && (y >= 235 && y <= 270)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				clicked = true;
				buttonClicked = 0;
				SetupClass.player.setPronostique(0);
		}
		}
		if((x >= 475 && x <= 545) && (y >= 65 && y <= 90)) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && clicked == true) {
				SetupClass.player.setBet(true);
				ready = true;
			}
		}
		if(SetupClass.opponnent.isBet()) {
			sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
			clicked = false;
			ready = false;
			buttonClicked = -1;
			SetupClass.player.setChoix(false);
			SetupClass.player.setBet(false);
			SetupClass.player.setNext(false);
			SetupClass.opponnent.setNext(false);
			SetupClass.opponnent.setBet(false);
			SetupClass.opponnent.setChoix(false);
			
		}
	}

	public int getID() {
		return 3;
	}

}
