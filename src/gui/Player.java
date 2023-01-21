package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Player extends JPanel {

	private static final long serialVersionUID = 1L;
	private int playerNumber;
	JLabel lblPlayerNumber;
	static int totalPlayers = 0; // we might need this number later on
	static HashMap<Integer, Integer> ledger = new HashMap<>();

	private int currentSquareNumber = 0; // where player is currently located on (0 - 19). initially zero
	private ArrayList<Integer> titleDeeds = new ArrayList<Integer>(); // squares that the player has
	private int wallet = 16; // initial money

	public ArrayList<Integer> getTitleDeeds() {
		return titleDeeds;
	}

	public int getWallet() {
		return wallet;
	}

	public void withdrawFromWallet(int withdrawAmount) {
		if (withdrawAmount > wallet) {
			setVisible(false);
			System.out.println("Player " + playerNumber + " went bankrupt!");
		} else {
			wallet -= withdrawAmount;
		}
	}

	public void depositToWallet(int depositAmount) {
		wallet += depositAmount;
		System.out.println("Pay for this round for player " + getPlayerNumber() + ". You earned 1!");
	}

	public int getCurrentSquareNumber() {
		return currentSquareNumber;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public boolean hasTitleDeed(int squareNumber) {
		return titleDeeds.contains(squareNumber) ? true : false;
	}

	public void buyTitleDeed(int squareNumber) {
		if (ledger.containsKey(squareNumber)) {
			System.out.println("It's already bought by someone. You cannot by here.");
		} else {
			titleDeeds.add(this.getCurrentSquareNumber());
			ledger.put(squareNumber, this.getPlayerNumber()); // everytime a player buys a title deed, it is written in
																// ledger, for example square 1 belongs to player 2

		}
	}

	public Player(int xCoord, int yCoord, int width, int height) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, 20, 20);
		this.setLayout(null);
	}

	public Player(int playerNumber, Color color) {
		// TODO Auto-generated constructor stub
		this.playerNumber = playerNumber;
		this.setBackground(color);
		lblPlayerNumber = new JLabel("" + playerNumber);
		lblPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblPlayerNumber.setForeground(Color.WHITE);
		this.add(lblPlayerNumber);
		this.setBounds(playerNumber * 30, 33, 20, 28); // need to fix here for adjustable player numbers
		totalPlayers++;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	int[] xLocationsOfPlayer1 = { 31, 191, 331, 486, 386, 486, 486, 356, 164, 24, 24, 24};

	int[] yLocationsOfPlayer1 = { 33, 33, 33, 33, 183, 333, 493, 493, 493, 493, 333, 233 };

	int[] xLocationsOfPlayer2 = { 31, 221, 361, 496, 386, 496, 496, 386, 194, 44, 44, 44};
	int[] yLocationsOfPlayer2 = { 33, 33, 33, 33, 283, 333, 493, 493, 493, 493, 333, 233 };

	int[] xLocationsOfPlayer3 ={ 31, 221, 361, 496, 386, 496, 496, 386, 194, 44, 44, 44};
	int[] yLocationsOfPlayer3 = { 33, 33, 33, 33, 283, 333, 493, 493, 493, 493, 333, 233 };
	
	int[] xLocationsOfPlayer4 = { 31, 221, 361, 496, 386, 496, 496, 386, 194, 44, 44, 44};
	int[] yLocationsOfPlayer4 = { 33, 33, 33, 33, 283, 333, 493, 493, 493, 493, 333, 233 };

	public void move(int dicesTotal) {
		if (currentSquareNumber + dicesTotal > 12) {
			depositToWallet(1);
		}
		System.out.println("currentSquareNumber:" + currentSquareNumber);
		System.out.println("dicesTotal:" + dicesTotal);
		int targetSquare = (currentSquareNumber + dicesTotal) % 12;
		currentSquareNumber = targetSquare;

		if (MonopolyMain.nowPlaying == 0) {
			this.setLocation(xLocationsOfPlayer1[targetSquare], yLocationsOfPlayer1[targetSquare]);
		} else if (MonopolyMain.nowPlaying == 1) {
			this.setLocation(xLocationsOfPlayer2[targetSquare], yLocationsOfPlayer2[targetSquare]);
		} else if (MonopolyMain.nowPlaying == 2) {
			this.setLocation(xLocationsOfPlayer3[targetSquare], yLocationsOfPlayer3[targetSquare]);
		} else {
			this.setLocation(xLocationsOfPlayer4[targetSquare], yLocationsOfPlayer4[targetSquare]);
		}

		if (ledger.containsKey(this.getCurrentSquareNumber())) {
			MonopolyMain.infoConsole
					.setText("This property belongs to player " + ledger.get(this.getCurrentSquareNumber()));
		}
		// ledger.put(this.getCurrentSquareNumber(), this.getPlayerNumber());
	}

	// by comparing player's coordinates according to the board, we will get it's
	// current square number
	// currently unused, found a better way
	public int getCurrentSquareNumberByCoordinates() {

		int x = this.getX();
		int y = this.getY();
		System.out.println("x:" + x);
		System.out.println("y:" + y);

		if (x < 100) {
			if (y < 100) {
				return 0;
			} else if (y > 100 && y < 200) {
				return 11;
			} else if (y > 300 && y < 400) {
				return 10;
			} else {
				return 9;
			}
		} else if (x > 100 && x < 200) {
			if (y < 100) {
				return 1;
			} else {
				return 8;
			}
		} else if (x > 200 && x < 400) {
			if (y < 100) {
				return 2;
			} else {
				return 7;
			}
		} else {
			if (y < 100) {
				return 3;
			} else if (y < 200) {
				return 4;
			} else if (y < 400) {
				return 5;
			} else {
				return 6;
			}
		}
	}

}
