package hw2yigitkucukcinar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class BallPanel extends JPanel {

	double x = 50;
	double y = 50;
	double xVector;
	double yVector;
	double xPressed;
	double yPressed;
	boolean canRun = true;
	BallThread ballThread = new BallThread();

	void move() {
		if (x + xVector < 0) {
			xVector = -1 * xVector;
		} else if (x + xVector > getWidth() - 50) {
			xVector = -1 * xVector;
		} else if (y + yVector < 0) {
			yVector = -1 * yVector;
		} else if (y + yVector > getHeight() - 50) {
			yVector = -1 * yVector;
		}
		x = x + xVector;
		y = y + yVector;
		repaint();
	}

	public BallPanel() {
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getX() - 25;
				y = e.getY() - 25;
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}

		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ballThread.stopThread();
				x = e.getX() - 25;
				y = e.getY() - 25;
				xPressed = e.getX() - 25;
				yPressed = e.getY() - 25;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				canRun = true;
				ballThread = new BallThread();
				ballThread.start();
				headTowards(e.getX() - 25, e.getY() - 25);
				repaint();

			}

		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D pen = (Graphics2D) g;
		pen.setColor(Color.red);
		pen.fillArc((int) x, (int) y, (int) 50, (int) 50, (int) 0, (int) 360);
	}

	public void headTowards(int xReleased, int yReleased) {

		if (xReleased - xPressed < 0) {
			xVector = -1;
		} else {
			xVector = 1;
		}

		if (yReleased - yPressed < 0) {
			yVector = -1;
		} else {
			yVector = 1;
		}
	}

	class BallThread extends Thread {

		boolean canRun = true;

		@Override
		public void run() {

			while (true) {
				if (canRun) {
					try {
						move();
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
		}

		public void stopThread() {
			canRun = false;
		}

	}

}
