import java.awt.Color;		
import java.awt.Graphics;

import java.awt.event.ActionListener;	
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;	
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;	
import java.awt.event.KeyEvent;
		
import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerExample 
{
	public TimerExample()
	{
	}	
	
	public static void main (String[] args) 
	{
		TimerExample te = new TimerExample();
		te.run();
	}
	
	public void run() 
	{
		JFrame frame = new JFrame("Timer Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create JPanel and add to frame
		DrawPanel panel = new DrawPanel();
		frame.getContentPane().add(panel);	// add panel to frame
				
		frame.setSize(500, 600);	// explicitly set size in pixels
		frame.setLocation(600,0);
		frame.setVisible(true);		// set to false to make invisible
	}
}	// end class TimerExample

// JPanel with a private ActionListener class called "Mover"
class DrawPanel extends JPanel 
{
	private int x, y, count;
	private boolean left, up;
	// create a Timer field variable
	private Timer timer;
	
	//	Create a class called Mover that implements ActionListener 
	private class Mover implements ActionListener, MouseListener, KeyListener
	{
		public Mover()
		{
			addMouseListener(this); // add the listener	
			addKeyListener(this);
		}

		public void actionPerformed(ActionEvent evt)
		{
			// moves the ball right and left
			if (!left && x < getWidth()-50)  //note, this.getWidth will not work here.
				x++;
			else 
			{ 
				left = true;
				x--; 
			}
			if ( left && x > 0 ) 
				x--;
			else 
			{ 
				left = false; 
				x++; 
			}
		
			// moves the ball up and down
			if (!up && y < getHeight()-50) 
			{
				y++;
			}
			else 
			{ 
				up = true; 
				y--; 
			}
			if ( up && y > 0 ) 
				y--;
			else 
			{ 
				up = false; 
				y++; 
			}
			repaint();
			requestFocusInWindow();
		}
	
		public void mousePressed(MouseEvent evt)
		{
			count++;
			if(count%2 == 1)
			{	
				//timer.stop();
				timer.setDelay(50);
			}
			else
			{
				//timer.start();
				timer.setDelay(5);
			}
		 }
	 
		public void mouseReleased(MouseEvent evt){}
		public void mouseClicked(MouseEvent evt){}
		public void mouseEntered(MouseEvent evt){}
		public void mouseExited(MouseEvent evt){}

		public void keyPressed(KeyEvent evt)
		{
			timer.setDelay(0);
		}
		public void keyReleased(KeyEvent evt)
		{
			timer.setDelay(5);
		}
		public void keyTyped(KeyEvent evt){}
	}
	
	// The JPanel's constructor
	public DrawPanel () 
	{
		count = 0;
		x = 0; y = 30;			// initial upper left corner location of red ball
		left = up = false;		// initialize ball going right and down
		setBackground(Color.BLUE);
		
		//	Declare and initialize
		//	- an ActionListener object
		//	- a Timer
		Mover mover = new Mover();
		timer = new Timer(5, mover);

		//	Start the timer
		timer.start();
	}
	
	// paintComponent() draws the circle and increments the location.
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 50, 50);
	}
}	// end class DrawPanel