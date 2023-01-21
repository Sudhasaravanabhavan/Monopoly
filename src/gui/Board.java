package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Square> allSquares = new ArrayList<Square>();
	private ArrayList<Square> unbuyableSquares = new ArrayList<Square>(); // squares like "Go"
	
	public ArrayList<Square> getUnbuyableSquares(){
		return unbuyableSquares;
	}
	
	public ArrayList<Square> getAllSquares(){
		return allSquares;
	}
	
	public Square getSquareAtIndex(int location) {
		return allSquares.get(location);
	}

	public Board(int xCoord, int yCoord, int width, int height) {
		setBorder(new LineBorder(new Color(3, 3, 3)));
		setBounds(xCoord, yCoord, 612, 612);
		this.setLayout(null);
		initializeSquares();
	}

	private void initializeSquares() {
		// TODO Auto-generated method stub
		String[] squareNames = 
			{
				"Go",
				 "The Burvale Price:1",
				 "Fast Kebabs",
				 "rest",
				 "The Grand Tofu",
				 "Lanzhou Beef Noodle",
				 "rest",
				 "Betty's Burgers",
				 "YOMG",
				 "rest",
				 "Gami Chicken",
				 "Massizim "
				 
				
						
		};
		

		// squares on the top
		Square square00 = new Square(6,6,150,150,squareNames[0],135);
		this.add(square00);
		allSquares.add(square00);
		unbuyableSquares.add(square00);
		
		Square square01 = new Square(156,6,150,150,squareNames[1],180);
		this.add(square01);
		allSquares.add(square01);
		
		Square square02 = new Square(306,6,150,150,squareNames[2],180);
		this.add(square02);
		allSquares.add(square02);
		
		
		Square square03 = new Square(456,6,150,150,squareNames[3],-135);
		this.add(square03);
		allSquares.add(square03);
		unbuyableSquares.add(square03);
		//right side
		
		Square square04 = new Square(456,156,150,150,squareNames[4],-90);
		this.add(square04);
		allSquares.add(square04);
		
		Square square05 = new Square(456,306,150,150,squareNames[5],-90);
		this.add(square05);
		allSquares.add(square05);
		//unbuyableSquares.add(square05);

		// squares on the bottom
		Square square06 = new Square(456,456,150,150,squareNames[6],45);
		this.add(square06);
		allSquares.add(square06);
		unbuyableSquares.add(square06);

		
		Square square07 = new Square(306,456,150,150,squareNames[7],0);
		this.add(square07);
		allSquares.add(square07);
		//unbuyableSquares.add(square07);
		
		Square square08 = new Square(156,456,150,150,squareNames[8],0);
		this.add(square08);
		allSquares.add(square08);
		
		Square square09 = new Square(6,456,150,150,squareNames[9],45);
		this.add(square09);
		allSquares.add(square09);
		unbuyableSquares.add(square09);
//squares on left
		
		Square square10 = new Square(6,306,150,150,squareNames[10],90);
		this.add(square10);
		allSquares.add(square10);
		
		
		Square square11 = new Square(6,156,150,150,squareNames[11],90);
		this.add(square11);
		allSquares.add(square11);
		
		// setting prices
		square01.setPrice(1);
		square02.setPrice(1);
		
		
		square04.setPrice(2);
		square05.setPrice(2);
		
		
		square07.setPrice(3);
		square08.setPrice(3);
		
		square10.setPrice(4);
		square11.setPrice(4);
		
		
		// setting rent prices
		square01.setRentPrice(1);
		square02.setRentPrice(1);
		
		square04.setRentPrice(1);
		square05.setRentPrice(1);
		
      	square07.setRentPrice(1);
		square08.setRentPrice(1);
		
		square10.setRentPrice(1);
		square11.setRentPrice(1);
		
		

		JLabel lblMonopoly = new JLabel("MONOPOLY"){
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				AffineTransform aT = g2.getTransform();
				Shape oldshape = g2.getClip();
				double x = getWidth()/2.0;
				double y = getHeight()/2.0;
				aT.rotate(Math.toRadians(-35), x, y);
				g2.setTransform(aT);
				g2.setClip(oldshape);
				super.paintComponent(g);
			}
		};
		lblMonopoly.setForeground(Color.WHITE);
		lblMonopoly.setBackground(Color.RED);
		lblMonopoly.setOpaque(true);
		lblMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonopoly.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblMonopoly.setBounds(179, 277, 263, 55);
		this.add(lblMonopoly);
		
	}
                                   
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}




}
