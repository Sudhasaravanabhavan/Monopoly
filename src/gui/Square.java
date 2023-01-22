package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Square extends JPanel {

	private static final long serialVersionUID = 1L;
	int number;
	private String name;

	private String type;
	private String colour;
	String description;
	JLabel nameLabel;
	static int totalSquares = 0;
	private int price;
	private int rentPrice;
	
	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}
	
	public int getRentPrice() {
		return rentPrice;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@SuppressWarnings("serial")
	public Square(int xCoord, int yCoord, int width, int height, String labelString, int rotationDegrees) {
		number = totalSquares;
		totalSquares++;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, width, height);
		name = labelString;
		this.setLayout(null);

		if(rotationDegrees == 0) {
			nameLabel = new JLabel(labelString);
			nameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nameLabel.setBounds(0,20,this.getWidth(),20);
			this.add(nameLabel);
		} else {	
						
			nameLabel = new JLabel(labelString) {
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					double x = getWidth()/2.0;
					double y = getHeight()/2.0;
					aT.rotate(Math.toRadians(rotationDegrees), x, y);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paintComponent(g);
				}
			};
			if(rotationDegrees == 90) {
				nameLabel.setBounds(20, 0, this.getWidth(), this.getHeight());
			}
			if(rotationDegrees == -90) {
				nameLabel.setBounds(-10, 0, this.getWidth(), this.getHeight());
			}
			if(rotationDegrees == 180) {
				nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
			}
			if(rotationDegrees == 135 || rotationDegrees == -135 || rotationDegrees == -45 || rotationDegrees == 45) {
				nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
			}
			nameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			this.add(nameLabel);
		} 

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.number == 1 || this.number == 2 ) {
			g.drawRect(0, this.getHeight()-20, this.getWidth(), 20);
			g.setColor(Color.BLACK);
			g.fillRect(0, this.getHeight()-20, this.getWidth(), 20);
		}
		if(this.number == 4 || this.number == 5 ) {
			g.drawRect(0, 0, 20, this.getHeight());
			g.setColor(Color.RED);
			g.fillRect(0, 0, 20, this.getHeight());
		}
		if(this.number == 0 || this.number == 3 || this.number == 6 || this.number == 9) {
			g.drawRect(0, 0, this.getWidth(), 20);
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, this.getWidth(), 20);
		}
		if(this.number == 7 || this.number == 8 ) {
			g.drawRect(0, 0, this.getWidth(), 20);
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, this.getWidth(), 20);
		}
		if(this.number == 10 || this.number == 11 ) {
			g.drawRect(this.getWidth()-20, 0, 20, this.getHeight());
			g.setColor(Color.BLUE);
			g.fillRect(this.getWidth()-20, 0, 20, this.getHeight());
		}

	}

	private boolean isRentPaid = false;
	public boolean isRentPaid() {
		return isRentPaid;
	}
	public void setRentPaid(boolean pay) {
		isRentPaid = pay;
	}

}
