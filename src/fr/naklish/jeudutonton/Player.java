package fr.naklish.jeudutonton;

public class Player {
	private String nom;
	private int caillasse;
	private int choixCaillasse;
	private int pronostique;
	private boolean startPressed;
	private boolean choix;
	private boolean bet;
	private boolean next;
	

	public Player() {
		super();
		this.caillasse = 3;
		this.startPressed = false;
		this.choix = false;
		this.choixCaillasse = -1;
		this.pronostique = -1;
		this.next = false;
		this.bet = false;
	}

	
	
	public boolean isNext() {
		return next;
	}



	public void setNext(boolean next) {
		this.next = next;
	}



	public boolean isBet() {
		return bet;
	}



	public void setBet(boolean bet) {
		this.bet = bet;
	}



	public boolean isChoix() {
		return choix;
	}

	public void setChoix(boolean choix) {
		this.choix = choix;
	}

	public boolean isStartPressed() {
		return startPressed;
	}
	
	public void setStartPressed(boolean startPressed) {
		this.startPressed = startPressed;
	}

	public void decreCaillasse() {
		caillasse --;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCaillasse() {
		return caillasse;
	}

	public void setCaillasse(int caillasse) {
		this.caillasse = caillasse;
	}

	public int getChoixCaillasse() {
		return choixCaillasse;
	}

	public void setChoixCaillasse(int choixCaillasse) {
		this.choixCaillasse = choixCaillasse;
	}

	public int getPronostique() {
		return pronostique;
	}

	public void setPronostique(int pronostique) {
		this.pronostique = pronostique;
	}
	
	
}