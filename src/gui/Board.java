package gui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Square> allSquares = new ArrayList<Square>();

	private HashMap<String, Square> boardDetails = new HashMap<String, Square>();
	private ArrayList<Square> unbuyableSquares = new ArrayList<Square>(); 

	public ArrayList<Square> getUnbuyableSquares() {
		return unbuyableSquares;
	}

	public ArrayList<Square> getAllSquares() {
		return allSquares;
	}

	public Square getSquareAtIndex(int location) {
		return allSquares.get(location);
	}

	private static String typeProperty = "property";

	public Board(int xCoord, int yCoord, int width, int height) {
		setBorder(new LineBorder(new Color(3, 3, 3)));
		setBounds(xCoord, yCoord, 612, 612);
		this.setLayout(null);
		initializeSquares();
	}

	private void initializeSquares() {

		ArrayList<int[]> dimentions = new ArrayList<>();
		dimentions.add(new int[] { 6, 6, 150, 150, 135 });
		dimentions.add(new int[] { 156, 6, 150, 150, 180 });
		dimentions.add(new int[] { 306, 6, 150, 150, 180 });
		dimentions.add(new int[] { 456, 6, 150, 150, -135 });
		dimentions.add(new int[] { 456, 156, 150, 150, -90 });
		dimentions.add(new int[] { 456, 306, 150, 150, -90 });
		dimentions.add(new int[] { 456, 456, 150, 150, 45 });
		dimentions.add(new int[] { 306, 456, 150, 150, 0 });
		dimentions.add(new int[] { 156, 456, 150, 150, 0 });
		dimentions.add(new int[] { 6, 456, 150, 150, 45 });
		dimentions.add(new int[] { 6, 306, 150, 150, 90 });
		dimentions.add(new int[] { 6, 156, 150, 150, 90 });
		int i = 0;
		try {
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("board.json"));

			for (Object o : jsonArray) {
				JSONObject squareDetail = (JSONObject) o;
				int[] dimention = dimentions.get(i++ % 12);
				String name = squareDetail.get("name").toString();
				String type = squareDetail.get("type").toString();
				int price = Integer
						.parseInt(squareDetail.get("price") != null ? squareDetail.get("price").toString() : "0");
				int rentprice = Integer.parseInt(
						squareDetail.get("rentprice") != null ? squareDetail.get("rentprice").toString() : "0");
				Square square = new Square(dimention[0], dimention[1], dimention[2], dimention[3], name, dimention[4]);
				square.setPrice(price);
				square.setRentPrice(rentprice);
				this.add(square);
				allSquares.add(square);
				if (!typeProperty.equalsIgnoreCase(type)) {
					unbuyableSquares.add(square);
				}
				boardDetails.put(squareDetail.get("name").toString(), square);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		JLabel lblMonopoly = new JLabel("WOVEN MONOPOLY") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				AffineTransform aT = g2.getTransform();
				Shape oldshape = g2.getClip();
				double x = getWidth() / 2.0;
				double y = getHeight() / 2.0;
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
		lblMonopoly.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblMonopoly.setBounds(179, 277, 263, 55);
		this.add(lblMonopoly);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
