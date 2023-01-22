package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Dice extends JPanel {
	int i = 0;
	int j = 0;
	private static final long serialVersionUID = 1L;

	int roll1[] = { 1, 3, 1, 1, 1, 2, 4, 2, 6, 3, 5, 2, 2, 2, 4, 4, 6, 1, 4, 2, 6, 2, 1, 5, 4, 5, 6, 5, 6, 3, 6, 4, 4,
			3, 5, 6, 2, 1, 6, 5, 1, 1, 6, 4, 5, 2, 2, 3, 5, 6 };
	int roll2[] = { 5, 2, 2, 1, 4, 1, 2, 1, 3, 1, 4, 3, 5, 2, 3, 1, 3, 1, 1, 3, 4, 2, 1, 3, 2, 3, 5, 5, 3, 2, 4, 5, 2,
			6, 5, 4, 3, 6, 2, 5, 5, 3, 2, 6, 5, 2, 6, 2, 6, 4, 5, 5, 6, 1, 6, 6, 2, 6, 4, 1, 1, 2, 6, 6, 6, 2, 1, 4, 6,
			3, 5, 4, 1, 4, 2, 1, 5, 5, 2, 3, 3, 3, 4, 1, 2, 4, 5, 4, 5, 2, 2, 2, 2, 6, 1, 5, 3, 6, 3, 2 };
	int faceValue = 1;

	public Dice(int xCoord, int yCoord, int width, int height) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, width, height);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (faceValue == 1) {

			g.fillOval(getWidth() / 2 - 5 / 2, getHeight() / 2 - 5 / 2, 5, 5);
		} else if (faceValue == 2) {

			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 + 10, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 - 15, 5, 5);

		} else if (faceValue == 3) {

			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 + 10, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 - 15, 5, 5);
			g.fillOval(getWidth() / 2 - 5 / 2, getHeight() / 2 - 5 / 2, 5, 5);
		} else if (faceValue == 4) {
			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 + 10, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 - 15, 5, 5);
			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 - 15, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 + 10, 5, 5);
		} else if (faceValue == 5) {
			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 + 10, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 - 15, 5, 5);
			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 - 15, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 + 10, 5, 5);
			g.fillOval(getWidth() / 2 - 5 / 2, getHeight() / 2 - 5 / 2, 5, 5);
		} else {
			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 + 10, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 - 15, 5, 5);
			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 - 15, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 + 10, 5, 5);
			g.fillOval(getWidth() / 2 - 15, getHeight() / 2 - 5 / 2, 5, 5);
			g.fillOval(getWidth() / 2 + 10, getHeight() / 2 - 5 / 2, 5, 5);
		}

	}

	public void rollDice(int diceNumber) {
		if (diceNumber == 1) {
			int len = roll1.length;

			faceValue = roll1[i++ % len];
		} else {
			int len = roll2.length;

			faceValue = roll2[j++ % len];
		}
		repaint();
	}

	public int getFaceValue() {

		return faceValue;

	}

	public Dice(int xCoord, int yCoord, int width, int height, String labelString) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, width, height);

	}

}
