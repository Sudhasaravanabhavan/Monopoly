package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("unused")
public class MonopolyMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentIncluder;
	static JTextArea infoConsole;
	JPanel playerAssetsPanel;
	CardLayout c1 = new CardLayout();
	ArrayList<Player> players = new ArrayList<Player>();
	static int turnCounter = 0;
	JButton btnNextTurn;
	JButton btnRollDice;
	JButton btnPayRent;
	JButton btnBuy;
	JTextArea panelPlayer1TextArea;
	JTextArea panelPlayer2TextArea;
	JTextArea panelPlayer3TextArea;
	JTextArea panelPlayer4TextArea;
	Board gameBoard;
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	Boolean doubleDiceForPlayer1 = false;
	Boolean doubleDiceForPlayer2 = false;
	Boolean doubleDiceForPlayer3 = false;
	Boolean doubleDiceForPlayer4 = false;
	static int nowPlaying = 0;

	@SuppressWarnings("deprecation")
	public MonopolyMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1080, 720);
		contentIncluder = new JPanel();
		contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentIncluder);
		contentIncluder.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.setBounds(6, 6, 632, 630);
		contentIncluder.add(layeredPane);

		gameBoard = new Board(6, 6, 612, 612);
		gameBoard.setBackground(new Color(51, 255, 153));
		layeredPane.add(gameBoard, new Integer(0));

		player1 = new Player(1, Color.RED);
		players.add(player1);
		layeredPane.add(player1, new Integer(1));

		player2 = new Player(2, Color.BLUE);
		players.add(player2);
		layeredPane.add(player2, new Integer(1));

		player3 = new Player(3, Color.PINK);
		players.add(player3);
		layeredPane.add(player3, new Integer(1));

		player4 = new Player(4, Color.ORANGE);
		players.add(player4);
		layeredPane.add(player4, new Integer(1));

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightPanel.setBounds(634, 6, 419, 600);
		contentIncluder.add(rightPanel);
		rightPanel.setLayout(null);

		btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// turnCounter--; // decrease because we increased at the end of the rolldice
				Player currentPlayer = players.get(nowPlaying);
				System.out.println("Current Square Number:"+currentPlayer.getCurrentSquareNumber());
				infoConsole.setText("You bought "
						+ gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getName());
				currentPlayer.buyTitleDeed(currentPlayer.getCurrentSquareNumber());
				int withdrawAmount = gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getPrice();
				currentPlayer.withdrawFromWallet(withdrawAmount);
				btnBuy.setEnabled(false);
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				updatePanelPlayer3TextArea();
				updatePanelPlayer4TextArea();
				// turnCounter++;
			}
		});
		btnBuy.setBounds(81, 478, 117, 29);
		rightPanel.add(btnBuy);
		btnBuy.setEnabled(false);

		btnPayRent = new JButton("Pay Rent");
		btnPayRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// turnCounter--;
				Player currentPlayer = players.get(nowPlaying);
				Player ownerOfTheSquare = currentPlayer ;
				
				for(int x=0; x<players.size();x++) {
					Player xPlayer = players.get(x);
					if (xPlayer!=currentPlayer && xPlayer.ledger.containsKey(currentPlayer.getCurrentSquareNumber())) {
						ownerOfTheSquare =xPlayer;
						break;
					}
				}
				//Player ownerOfTheSquare = players
					//	.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber())) == 1 ? 0 : 1);
				infoConsole.setText("You paid to the player " + ownerOfTheSquare.getPlayerNumber());

				int withdrawAmount = gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber())
						.getRentPrice();
				System.out.println(withdrawAmount);
				currentPlayer.withdrawFromWallet(withdrawAmount);
				ownerOfTheSquare.depositToWallet(withdrawAmount);

				btnNextTurn.setEnabled(true);
				btnPayRent.setEnabled(false);
				// currentPlayer.withdrawFromWallet(withdrawAmount);
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				updatePanelPlayer3TextArea();
				updatePanelPlayer4TextArea();
				// turnCounter++;
				// gameBoard.getAllSquares().get(player1.getCurrentSquareNumber()).setRentPaid(true);
			}

		});
		btnPayRent.setBounds(210, 478, 117, 29);
		rightPanel.add(btnPayRent);
		btnPayRent.setEnabled(false);

		Dice dice1 = new Dice(244, 406, 40, 40);
		layeredPane.add(dice1, new Integer(1));

		Dice dice2 = new Dice(333, 406, 40, 40);
		layeredPane.add(dice2, new Integer(1));

		btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// for(int i=0;i<players[i];i++)

				if (nowPlaying == 0) {
					// player1's turn
					/*
					 * int dice1OldValue = dice1.getFaceValue1(); int dice2OldValue =
					 * dice2.getFaceValue2();
					 */
					dice1.rollDice(1);
					dice2.rollDice(2);
					int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
					System.out.println("dicesTotal:"+dicesTotal);
					/*
					 * if (dice1.getFaceValue() == dice2.getFaceValue()) { doubleDiceForPlayer1 =
					 * true; } else { doubleDiceForPlayer1 = false; }
					 */
					player1.move(dicesTotal);
					if (Player.ledger.containsKey(player1.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player1.getCurrentSquareNumber()) != player1.getPlayerNumber() // not
																												// by
																												// itself
					) {
						btnBuy.setEnabled(false);
						btnRollDice.setEnabled(false);
						btnNextTurn.setEnabled(false);
						btnPayRent.setEnabled(true);
					}
					if (Player.ledger.containsKey(player1.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player1.getCurrentSquareNumber()) == player1.getPlayerNumber()) { // and
																													// by
																													// itself
						btnBuy.setEnabled(false);
						btnPayRent.setEnabled(false);
						btnNextTurn.setEnabled(true);
					}
					if (gameBoard.getUnbuyableSquares()
							.contains(gameBoard.getAllSquares().get(player1.getCurrentSquareNumber()))) {
						btnBuy.setEnabled(false);
						btnNextTurn.setEnabled(true);
					} else if (!Player.ledger.containsKey(player1.getCurrentSquareNumber())) { // if not bought by
																								// someone
						btnBuy.setEnabled(true);
						btnNextTurn.setEnabled(true);
						btnPayRent.setEnabled(false);
					}

				} else if (nowPlaying == 1) {
					// player2's turn
					/*
					 * int dice1OldValue = dice1.getFaceValue1(); int dice2OldValue =
					 * dice2.getFaceValue2();
					 */
					dice1.rollDice(1);
					dice2.rollDice(2);
					int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
					System.out.println("dicesTotal:"+dicesTotal);
					/*
					 * if (dice1.getFaceValue() == dice2.getFaceValue()) { doubleDiceForPlayer2 =
					 * true; } else { doubleDiceForPlayer2 = false; }
					 */
					player2.move(dicesTotal);
					if (Player.ledger.containsKey(player2.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player2.getCurrentSquareNumber()) != player2.getPlayerNumber() // not
																												// by
																												// itself
					) {
						btnBuy.setEnabled(false);
						btnRollDice.setEnabled(false);
						btnNextTurn.setEnabled(false);
						btnPayRent.setEnabled(true);
					}
					if (Player.ledger.containsKey(player2.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player2.getCurrentSquareNumber()) == player2.getPlayerNumber()) { // and
																													// by
																													// itself
						btnBuy.setEnabled(false);
						btnPayRent.setEnabled(false);

					}
					if (gameBoard.getUnbuyableSquares()
							.contains(gameBoard.getAllSquares().get(player2.getCurrentSquareNumber()))) {
						btnBuy.setEnabled(false);
						btnNextTurn.setEnabled(true);
					} else if (!Player.ledger.containsKey(player2.getCurrentSquareNumber())) { // if not bought by
																								// someone
						btnBuy.setEnabled(true);
						btnNextTurn.setEnabled(true);
						btnPayRent.setEnabled(false);
					}

				} else if (nowPlaying == 2) {
					// player3's turn
					/*
					 * int dice1OldValue = dice1.getFaceValue1(); int dice2OldValue =
					 * dice2.getFaceValue2();
					 */
					dice1.rollDice(1);
					dice2.rollDice(2);
					int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
					/*
					 * if (dice1.getFaceValue() == dice2.getFaceValue()) { doubleDiceForPlayer3 =
					 * true; } else { doubleDiceForPlayer3 = false; }
					 */
					player3.move(dicesTotal);
					if (Player.ledger.containsKey(player3.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player3.getCurrentSquareNumber()) != player3.getPlayerNumber() // not
																												// by
																												// itself
					) {
						btnBuy.setEnabled(false);
						btnRollDice.setEnabled(false);
						btnNextTurn.setEnabled(false);
						btnPayRent.setEnabled(true);
					}
					if (Player.ledger.containsKey(player3.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player3.getCurrentSquareNumber()) == player3.getPlayerNumber()) { // and
																													// by
																													// itself
						btnBuy.setEnabled(false);
						btnPayRent.setEnabled(false);

					}
					if (gameBoard.getUnbuyableSquares()
							.contains(gameBoard.getAllSquares().get(player3.getCurrentSquareNumber()))) {
						btnBuy.setEnabled(false);
						btnNextTurn.setEnabled(true);
					} else if (!Player.ledger.containsKey(player3.getCurrentSquareNumber())) { // if not bought by
																								// someone
						btnBuy.setEnabled(true);
						btnNextTurn.setEnabled(true);
						btnPayRent.setEnabled(false);
					}

				} else {
					// player4's turn
					/*
					 * int dice1OldValue = dice1.getFaceValue1(); int dice2OldValue =
					 * dice2.getFaceValue2();
					 */
					dice1.rollDice(1);
					dice2.rollDice(2);
					int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
					System.out.println("dicesTotal:"+dicesTotal);
					/*
					 * if (dice1.getFaceValue() == dice2.getFaceValue()) { doubleDiceForPlayer4 =
					 * true; } else { doubleDiceForPlayer4 = false; }
					 */
					player4.move(dicesTotal);
					if (Player.ledger.containsKey(player4.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player4.getCurrentSquareNumber()) != player4.getPlayerNumber() // not
																												// by
																												// itself
					) {
						btnBuy.setEnabled(false);
						btnRollDice.setEnabled(false);
						btnNextTurn.setEnabled(false);
						btnPayRent.setEnabled(true);
					}
					if (Player.ledger.containsKey(player4.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player4.getCurrentSquareNumber()) == player4.getPlayerNumber()) { // and
																													// by
																													// itself
						btnBuy.setEnabled(false);
						btnPayRent.setEnabled(false);

					}
					if (gameBoard.getUnbuyableSquares()
							.contains(gameBoard.getAllSquares().get(player4.getCurrentSquareNumber()))) {
						btnBuy.setEnabled(false);
						btnNextTurn.setEnabled(true);
					} else if (!Player.ledger.containsKey(player4.getCurrentSquareNumber())) { // if not bought by
																								// someone
						btnBuy.setEnabled(true);
						btnNextTurn.setEnabled(true);
						btnPayRent.setEnabled(false);
					}

				}

				btnRollDice.setEnabled(false);
				if (doubleDiceForPlayer1 || doubleDiceForPlayer2 || doubleDiceForPlayer3 || doubleDiceForPlayer4) {
					infoConsole.setText("Click Next Turn to allow player "
							+ (nowPlaying == 0 ? 1 : (nowPlaying == 1 ? 2 : (nowPlaying == 2 ? 3 : 4)))
							+ " to Roll Dice!");
				} else {
					infoConsole.setText("Click Next Turn to allow player "
							+ (nowPlaying == 0 ? 2 : (nowPlaying == 1 ? 3 : (nowPlaying == 2 ? 4 : 1)))
							+ " to Roll Dice!");
				}

				// we have to add below 2 lines to avoid some GUI breakdowns.
				layeredPane.remove(gameBoard);
				layeredPane.add(gameBoard, new Integer(0));

				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				updatePanelPlayer3TextArea();
				updatePanelPlayer4TextArea();

			}
		});
		btnRollDice.setBounds(81, 413, 246, 53);
		rightPanel.add(btnRollDice);

		btnNextTurn = new JButton("Next Turn");
		btnNextTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnRollDice.setEnabled(true);
				btnBuy.setEnabled(false);
				btnPayRent.setEnabled(false);
				btnNextTurn.setEnabled(false);

				if (nowPlaying == 0 && doubleDiceForPlayer1) {
					nowPlaying = 0;
					doubleDiceForPlayer1 = false;
				} else if (nowPlaying == 1 && doubleDiceForPlayer2) {
					nowPlaying = 1;
					doubleDiceForPlayer2 = false;
				} else if (nowPlaying == 2 && doubleDiceForPlayer3) {
					nowPlaying = 2;
					doubleDiceForPlayer3 = false;
				} else if (nowPlaying == 3 && doubleDiceForPlayer4) {
					nowPlaying = 3;
					doubleDiceForPlayer4 = false;
				} else {
					nowPlaying = (nowPlaying + 1) % 4;
				}

				c1.show(playerAssetsPanel,
						"" + (nowPlaying == 0 ? 1 : (nowPlaying == 1 ? 2 : (nowPlaying == 2 ? 3 : 4)))); 
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				updatePanelPlayer3TextArea();
				updatePanelPlayer4TextArea();
				infoConsole.setText("It's now player "
						+ (nowPlaying == 0 ? 1 : (nowPlaying == 1 ? 2 : (nowPlaying == 2 ? 3 : 4))) + "'s turn!");
			}

		});

		btnNextTurn.setBounds(81, 519, 246, 53);
		rightPanel.add(btnNextTurn);
		btnNextTurn.setEnabled(false);

		JPanel test = new JPanel();
		test.setBounds(81, 312, 246, 68);
		rightPanel.add(test);
		test.setLayout(null);

		playerAssetsPanel = new JPanel();
		playerAssetsPanel.setBounds(81, 28, 246, 189);
		rightPanel.add(playerAssetsPanel);
		playerAssetsPanel.setLayout(c1);

		JPanel panelPlayer1 = new JPanel();
		panelPlayer1.setBackground(Color.RED);
		playerAssetsPanel.add(panelPlayer1, "1");
		panelPlayer1.setLayout(null);

		JLabel panelPlayer1Title = new JLabel("Player 1 Peter");
		panelPlayer1Title.setForeground(Color.WHITE);
		panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer1Title.setBounds(0, 6, 240, 16);
		panelPlayer1.add(panelPlayer1Title);

		panelPlayer1TextArea = new JTextArea();
		panelPlayer1TextArea.setBounds(10, 34, 230, 149);
		panelPlayer1.add(panelPlayer1TextArea);

		JPanel panelPlayer2 = new JPanel();
		panelPlayer2.setBackground(Color.BLUE);
		playerAssetsPanel.add(panelPlayer2, "2");
		panelPlayer2.setLayout(null);
		c1.show(playerAssetsPanel, "" + nowPlaying);

		JLabel panelPlayer2Title = new JLabel("Player 2 Billy");
		panelPlayer2Title.setForeground(Color.WHITE);
		panelPlayer2Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer2Title.setBounds(0, 6, 240, 16);
		panelPlayer2.add(panelPlayer2Title);

		panelPlayer2TextArea = new JTextArea();
		panelPlayer2TextArea.setBounds(10, 34, 230, 149);
		panelPlayer2.add(panelPlayer2TextArea);
		// player3
		JPanel panelPlayer3 = new JPanel();
		panelPlayer3.setBackground(Color.BLUE);
		playerAssetsPanel.add(panelPlayer3, "3");
		panelPlayer3.setLayout(null);
		c1.show(playerAssetsPanel, "" + nowPlaying);

		JLabel panelPlayer3Title = new JLabel("Player 3 name");
		panelPlayer3Title.setForeground(Color.WHITE);
		panelPlayer3Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer3Title.setBounds(0, 6, 240, 16);
		panelPlayer3.add(panelPlayer3Title);

		panelPlayer3TextArea = new JTextArea();
		panelPlayer3TextArea.setBounds(10, 34, 230, 149);
		panelPlayer3.add(panelPlayer3TextArea);

		// player4
		JPanel panelPlayer4 = new JPanel();
		panelPlayer4.setBackground(Color.BLUE);
		playerAssetsPanel.add(panelPlayer4, "4");
		panelPlayer4.setLayout(null);
		c1.show(playerAssetsPanel, "" + nowPlaying);

		JLabel panelPlayer4Title = new JLabel("Player 4 name");
		panelPlayer4Title.setForeground(Color.WHITE);
		panelPlayer4Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer4Title.setBounds(0, 6, 240, 16);
		panelPlayer4.add(panelPlayer4Title);

		panelPlayer4TextArea = new JTextArea();
		panelPlayer4TextArea.setBounds(10, 34, 230, 149);
		panelPlayer4.add(panelPlayer4TextArea);
		updatePanelPlayer1TextArea();
		updatePanelPlayer2TextArea();
		updatePanelPlayer3TextArea();
		updatePanelPlayer4TextArea();

		infoConsole = new JTextArea();
		infoConsole.setColumns(20);
		infoConsole.setRows(5);
		infoConsole.setBounds(6, 6, 234, 56);
		test.add(infoConsole);
		infoConsole.setLineWrap(true);
		infoConsole.setText("Player 1 starts the game by clicking Roll Dice!");

	}

	
	public void updatePanelPlayer1TextArea() {
		// TODO Auto-generated method stub
		String result = "";
		result += "Current Balance: " + player1.getWallet() + "\n";

		result += "Title Deeds: \n";
		for (int i = 0; i < player1.getTitleDeeds().size(); i++) {
			result += " - " + gameBoard.getAllSquares().get(player1.getTitleDeeds().get(i)).getName() + "\n";
		}

		panelPlayer1TextArea.setText(result);
	}
	public void updatePanelPlayer2TextArea() {
		// TODO Auto-generated method stub
		String result = "";
		result += "Current Balance: " + player2.getWallet() + "\n";

		result += "Title Deeds: \n";
		for (int i = 0; i < player2.getTitleDeeds().size(); i++) {
			result += " - " + gameBoard.getAllSquares().get(player2.getTitleDeeds().get(i)).getName() + "\n";
		}

		panelPlayer2TextArea.setText(result);
	}


	public void updatePanelPlayer3TextArea() {
		// TODO Auto-generated method stub
		String result = "";
		result += "Current Balance: " + player3.getWallet() + "\n";

		result += "Title Deeds: \n";
		for (int i = 0; i < player3.getTitleDeeds().size(); i++) {
			result += " - " + gameBoard.getAllSquares().get(player3.getTitleDeeds().get(i)).getName() + "\n";
		}

		panelPlayer3TextArea.setText(result);
	}

	public void updatePanelPlayer4TextArea() {
		// TODO Auto-generated method stub
		String result = "";
		result += "Current Balance: " + player4.getWallet() + "\n";

		result += "Title Deeds: \n";
		for (int i = 0; i < player4.getTitleDeeds().size(); i++) {
			result += " - " + gameBoard.getAllSquares().get(player4.getTitleDeeds().get(i)).getName() + "\n";
		}

		panelPlayer4TextArea.setText(result);
	}

	public static void errorBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {

		MonopolyMain frame = new MonopolyMain();
		frame.setVisible(true);

	}

}