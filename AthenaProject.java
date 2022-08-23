/*Laney Goldman
  AthenaProject.java
  5.18.18
*/

import java.awt.*;  
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.event.*;  

	//this class contains the levels panel, win panel, how to panel, 
	//lose panel, and the game panel
	public class AthenaProject extends JFrame
	{
		CardLayout cards;
		JPanel whole;
		int speed, milliseconds;
		boolean med, har;
		boolean[] cut = new boolean[9];
		
		//labels frame 
		public AthenaProject()
		{
			super("Athena Project");
			run();
		}

		//runs the whole program
		public static void main(String[] args) 
		{
			AthenaProject ce = new AthenaProject(); 
		}

		//sets the frame and layout
		public void run() 
		{ 
			speed = 0;
			
			
			med = har = false;
			
			cards = new CardLayout();
			whole = new JPanel();
			whole.setLayout(cards);
			
			MenuPanel menu  = new MenuPanel();
			whole.add(menu, "menu");
			
			LevelsPanel levels  = new LevelsPanel();
			whole.add(levels, "levels");
			
			HowPanel how  = new HowPanel();
			whole.add(how, "how");
			
			
			WinPanel win  = new WinPanel();
			whole.add(win, "win");
			
			LosePanel lose  = new LosePanel();
			whole.add(lose, "lose");
			
			cards.show(whole, "menu"); 
			add(whole);


			setSize( 600, 600);				
			setDefaultCloseOperation(DISPOSE_ON_CLOSE); 		
			setLocation(400,50);
			setResizable(false);
			
			setVisible(true);
		}
		
		//sets up opening panel
		class MenuPanel extends JPanel
		{
			JLabel label;
			JButton levels, how;//, about;
			Timer laserTimer;
			double laserX1, laserY1, laserX2, laserY2, 
			laserX1b, laserY1b,  laserX2b, laserY2b,
			laserX1c, laserY1c,  laserX2c, laserY2c,
			laserX1d, laserY1d,  laserX2d, laserY2d,
			laserX1e, laserY1e,  laserX2e, laserY2e,
			laserX1g, laserY1g,  laserX2g, laserY2g,
			laserX1h, laserY1h,  laserX2h, laserY2h;
			boolean one, two, three, four, five, six, seven, last;
			
			//sets layout and adds buttons
			public MenuPanel()
			{					
				setLayout(null);
				
				one = true;
				two = three = four = five = six = seven = last = false;
				//g.drawLine(14*25+5, 3*25-10, 600-18, 3*25-10);
				laserX1 =14*25+5;
				laserY1 = 3*25-10;
				laserX2 =600-8;
				laserY2 = 3*25-10;
				
				//g.drawLine((int)laserX2-17, (int)laserY2+3, (int)19*25-2, (int)5*25+2);
				laserX1b = laserX2-17;
				laserY1b = laserY2+3;
				laserX2b = 19*25-2;
				laserY2b = 5*25+2+5;
				
				//g.drawLine((int)laserX1b, (int)laserY1b, 21*25-8, 3*25-6);
				laserX1c = 19*25;
				laserY1c = 5*25;
				laserX2c = 21*25-8;
				laserY2c =  3*25-6;
				
				//g.drawLine((int)laserX2c+7, (int)laserY2c-11, 555, 20 );
				laserX1d = 21*25-8+7;
				laserY1d = 3*25-6-11;
				laserX2d = 555;
				laserY2d =  20;
				
				//g.drawLine((int)laserX2d-4, (int)laserY2d+5, (int)laserX2d-10, 25*3-17 );
				laserX1e = 555-4;
				laserY1e = 25;
				laserX2e = 555-10;
				laserY2e =  25*3-17;
				
				//g.drawLine((int)laserX2e-3, (int)laserY2e+12, (int)laserX2e-6, (int)laserY2e+24 );
				laserX1g = 555-10-3;
				laserY1g = 25*3-17+12-2;
				laserX2g = 555-10-6;
				laserY2g =  25*3-17+24+2;
				
				//g.drawLine((int)laserX2g-2, (int)laserY2g+11, (int)laserX2g-11, (int)laserY2g+55  );
				laserX1h = 555-10-6-2;
				laserY1h = 25*3-17+24+11;
				laserX2h = 555-10-6-11;
				laserY2h =  25*3-17+24+55;
				
				
				label = new JLabel("Athena Project", JLabel.CENTER);
				label.setBounds(200, 80, 200, 100);
				label.setFont(new Font("Ariel", Font.PLAIN, 20));
				add(label);
				
				levels = new JButton("Levels");
				LevelsHandler levhand = new LevelsHandler();
				levels.addActionListener(levhand);
				levels.setBounds(200, 200, 200, 50);
				add(levels);
				
				how = new JButton("How to Play");
				HowHandler howhand = new HowHandler();
				how.addActionListener(howhand);
				how.setBounds(200, 300, 200, 50);
				add(how);
				
				LaserMover lmover = new LaserMover();
				laserTimer = new Timer(4, lmover);
				//System.out.println("About to start a new timer");
				laserTimer.start();
				
			}
			
			//sets background panel
			public void paintComponent(Graphics g)	
			{
				super.paintComponent (g);
				setBackground(Color.white);
				
				//g.setColor(Color.BLACK);
				//for(int i=0; i<600; i+=25) g.drawLine(i, 0, i, 600);
				//for(int k=0; k<600; k+=25) g.drawLine(0, k, 600, k);
				float color = 1f;
				for(int i=200; i<600; i+=10)
				{
					g.setColor(new Color(.5f, .5f, 1f, 1-color));
					g.fillRect(0, i, 600, 10);
					color-=0.1/10f;
				}
					
				Image mountains = new ImageIcon("mountains.png").getImage();
				g.drawImage(mountains, 0, 400, 600, 200, null);
				
				
				Image logo = new ImageIcon("logo.png").getImage();
				g.drawImage(logo, 10, 0, 600, 150, null);
				
				Graphics2D g2 = (Graphics2D)g;
				g.setColor(Color.WHITE);
				if(!four && !five && !six && !seven && !last)g.fillRect(518, 67, 5, 3);
				if(!four && !five && !six && !seven && !last) g.fillRect(520, 59, 3, 3);
				if(!six && !seven && !last)g.fillRect(541, 59, 5, 3);
				if(!six && !seven && !last) g.fillRect(540, 67, 5, 3);
				if( !seven && !last)g.fillRect(537, 86, 2, 2);
				
				//cover
				g2.setStroke( new BasicStroke(10.0F) );
				//1
				
				g.drawLine((int)laserX1, (int)laserY1, (int)laserX2, (int)laserY2);
				//2
				g2.setStroke( new BasicStroke(13.0F) );
				g.drawLine((int)laserX1b, (int)laserY1b, (int)laserX2b, (int)laserY2b);
				//3	
				g2.setStroke( new BasicStroke(5.0F) );
				if(!four && !five && !six && !seven && !last)
				{
					
					g.drawLine((int)laserX1c, (int)laserY1c, (int)laserX2c, (int)laserY2c);
				}
				//4
				g2.setStroke( new BasicStroke(6.5F) );
				if(!five&& !six && !seven && !last)
				{
					g.drawLine((int)laserX1d, (int)laserY1d, (int)laserX2d, (int)laserY2d );
				}
				
				//5
				if(!six && !seven && !last)
				{
					
					g.drawLine((int)laserX1e, (int)laserY1e, (int)laserX2e, (int)laserY2e );
					
				}
				//6
				if(!seven && !last)
				{
					
					g2.setStroke( new BasicStroke(3.5F) );
					//g.drawLine((int)laserX2e-3, (int)laserY2e+12, (int)laserX2e-6, (int)laserY2e+24 );
					g.drawLine((int)laserX1g, (int)laserY1g, (int)laserX2g, (int)laserY2g );
				}
				
				//7
					
				g2.setStroke( new BasicStroke(2.5F) );
				//g.drawLine((int)laserX2g-2, (int)laserY2g+11, (int)laserX2g-11, (int)laserY2g+55  );
				g.drawLine((int)laserX1h, (int)laserY1h, (int)laserX2h, (int)laserY2h  );
			
				
	
				
				//laser
				g.setColor(Color.RED);
				if(one)
				{
					g.fillOval((int)laserX1-15, (int)laserY1-5, 10, 10);
					
					g2.setStroke( new BasicStroke(5.0F) );
					Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
					g.setColor(laserRed);
					g.drawLine(300, 600, (int)laserX1-10, (int)laserY1);
				}
				if(two)
				{
					g.fillOval((int)laserX1b, (int)laserY1b-7, 10, 10);
					
					g2.setStroke( new BasicStroke(5.0F) );
					Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
					g.setColor(laserRed);
					g.drawLine(300, 600, (int)laserX1b+5, (int)laserY1b);
				}
				
				if(three)
				{
					g.fillOval((int)laserX1c-5, (int)laserY1c-7, 10, 10);
					
					g2.setStroke( new BasicStroke(5.0F) );
					Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
					g.setColor(laserRed);
					g.drawLine(300, 600, (int)laserX1c, (int)laserY1c);
				}
				
				if(four)
				{
					g.fillOval((int)laserX1d-5, (int)laserY1d-7, 10, 10);
					
					g2.setStroke( new BasicStroke(5.0F) );
					Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
					g.setColor(laserRed);
					g.drawLine(300, 600, (int)laserX1d, (int)laserY1d);
				}
				
				if(five)
				{
					g.fillOval((int)laserX1e-5, (int)laserY1e-7, 10, 10);
					
					g2.setStroke( new BasicStroke(5.0F) );
					Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
					g.setColor(laserRed);
					g.drawLine(300, 600, (int)laserX1e, (int)laserY1e);
				}
				
				if(six)
				{
					g.fillOval((int)laserX1g-5, (int)laserY1g-7, 10, 10);
					
					g2.setStroke( new BasicStroke(5.0F) );
					Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
					g.setColor(laserRed);
					g.drawLine(300, 600, (int)laserX1g, (int)laserY1g);
				}
				
				if(seven)
				{
					g.fillOval((int)laserX1h-5, (int)laserY1h-7, 10, 10);
					
					g2.setStroke( new BasicStroke(5.0F) );
					Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
					g.setColor(laserRed);
					g.drawLine(300, 600, (int)laserX1h, (int)laserY1h);
				}
				
				
				
			}
			
			//containes the action performed method for the level button
			class LevelsHandler implements ActionListener
			{
				//goes back to the levels panel
				public void actionPerformed(ActionEvent e)
				{
					//laserTimer.stop();
					cards.show(whole, "levels");
				}
			}
			
			//contains the action performed method for the How To button
			class HowHandler implements ActionListener
			{
				//will go to how to panel
				public  void actionPerformed(ActionEvent e)
				{
					//laserTimer.stop();
					cards.show(whole, "how");
				}
			}
			
			//Contains the action performed method for the laser timer	
		    class LaserMover implements ActionListener 
			{
				//etches the logo with the laser
				public void actionPerformed(ActionEvent e) 
				{
					//g.drawLine((int)laserX2-27, (int)laserY2+8, (int)19*25-2, (int)5*25+2);
					
					if((laserX1<=laserX2) && one)
					{
						laserX1++;
						if(laserX1>laserX2)
						{
							one = false;
							two = true;
						}
					}
					if(two && (laserX1>=laserX2))
					{
						//94/-52
						laserX1b-=0.94;
						laserY1b+=0.56;
						if(laserX1b<laserX2b)
						{
							two=false;
							three = true;
						}
					}
					
					if(three && (laserX1c<=laserX2c))
					{
						//-42 / 56
						laserX1c+=0.42;
						laserY1c-=0.56;
						if(laserX1c>laserX2c)
						{
							three=false;
							four = true;
						}
					}
					
					if(four && (laserX1d<=laserX2d))
					{
						//-42 / 56
						laserX1d+=0.46;
						laserY1d-=0.53;
						if(laserX1d>laserX2d)
						{
							four=false;
							five = true;
						}
					}
					
					if(five && (laserX1e>=laserX2e))
					{
						//6 / -33
						laserX1e-=0.06;
						laserY1e+=0.33;
						if(laserX1e<laserX2e)
						{
							five=false;
							six = true;
						}
					}
					
					if(six && (laserX1g>=laserX2g))
					{
						//6 / -33
						laserX1g-=0.06;
						laserY1g+=0.33;
						if(laserX1g<laserX2g)
						{
							six=false;
							seven = true;
						}
					}
					
					if(seven && (laserX1h>=laserX2h))
					{
						//6 / -33
						laserX1h-=0.06;
						laserY1h+=0.33;
						if(laserX1h<laserX2h)
						{
							seven=false;
							last = true;
							
						}
					}
	

					repaint();
				}
			}
					  	
		}
		
		//the panel with instructions
		class HowPanel extends JPanel
		{
			JLabel label;//, label2, label3, label4, label5;
			JButton menu;
			int droneX, droneY;
			Timer droneTimer, flipTimer;
			boolean flip, left;
			
			//sets layout and adds buttons
			public HowPanel()
			{
				left=false;
				flip = false;
				droneX = 200;
				droneY = -30;
				setLayout(null);
				label = new JLabel("How to Play");
				label.setFont(new Font("Ariel", Font.PLAIN, 20));
				
				label.setBounds(250, 150, 200, 20);
				
				
				add(label);
				
				menu = new JButton("Back to Menu");
				MenuHandler menhand = new MenuHandler();
				menu.addActionListener(menhand);
				menu.setBounds(225, 350, 150, 30);
				
				DroneMover mover = new DroneMover();
				droneTimer = new Timer(18, mover);
				//System.out.println("About to start a new timer");
				droneTimer.start();
				
				FlipMover flipper = new FlipMover();
				flipTimer = new Timer(3, flipper);
				//System.out.println("About to start a new timer");
				flipTimer.start();
				
				add(menu);
				
			}
			
			 //contains action performed method for flipTimer
			 class FlipMover implements ActionListener 
			 {
				 //changes boolean that will animate propellers
				  public void actionPerformed(ActionEvent e) 
				  {
					  flip = !flip;
					  repaint();
				  }
			  }
			 
			 //moves drone back and forth 
			class DroneMover implements ActionListener 
			 {
				  
				  //carries out the above ^
				  public void actionPerformed(ActionEvent e) 
				  {
					  if(left)
					  {
						  if(droneX-1>=0) droneX--;
						  else left = false;
					  }
					  else 
					  {
						  if(droneX+1<=390) droneX++;
						  else left = true;
					  }
				  }
			  }
			//sets background for panel
			public void paintComponent(Graphics g)	
			{
				super.paintComponent (g);
				setBackground(Color.WHITE);
				
				//for(int i=0; i<600; i+=25) g.drawLine(i, 0, i, 600);
				//for(int k=0; k<600; k+=25) g.drawLine(0, k, 600, k);
				
				float color = 1f;
				for(int i=200; i<600; i+=10)
				{
					g.setColor(new Color(.5f, .5f, 1f, 1-color));
					g.fillRect(0, i, 600, 10);
					color-=0.1/10f;
				}
					
					
				Image mountains = new ImageIcon("mountains.png").getImage();
				g.drawImage(mountains, 0, 400, 600, 200, null);
				
				g.setColor(Color.BLACK);
				
				Font textFont = new Font("Seriff", Font.PLAIN, 15);
				g.setFont(textFont);
				
				g.drawString("The goal of the game is to shoot down a drone with a laser.", 100, 200);
				g.drawString("The laser is controlled by the arrow keys.", 160, 220);
				g.drawString("To shoot down the drone, the laser needs to hover over the", 93, 240);
				g.drawString("drone for more time each level.", 200, 260);
				g.drawString("If it takes longer than 125 seconds (about 2 minutes) to", 115, 280);
				g.drawString("shoot down the drone, the player loses.", 165, 300);
				
				Image drone = new ImageIcon("drone.png").getImage();
				//Image drone2 = new ImageIcon("drone2.png").getImage();
				Image drone3 = new ImageIcon("drone3.png").getImage();
				if(flip)g.drawImage(drone, droneX, droneY, 200, 200, null);
				else g.drawImage(drone3, droneX, droneY, 200, 200, null);
				
			}
			
			//contains the action performed method for the menu button
			class MenuHandler implements ActionListener
			{
				//goes back to the menu panel
				public  void actionPerformed(ActionEvent e)
				{
					cards.show(whole, "menu");
				}
			}
			
			
			
		}
		
		//this is the panel for when player shoots down drone
		class WinPanel extends JPanel
		{	
			JButton jb, lev;
			
			//sets layout and adds button
			public WinPanel()	
			{
				setLayout(null);
				jb = new JButton("Back to Menu");
				JBHandler handler = new JBHandler();
				jb.addActionListener(handler);
				jb.setBounds(225, 350, 150, 30);
				add(jb);
				
				lev = new JButton("Back to Levels");
				LevHandler levhandler = new LevHandler();
				lev.addActionListener(levhandler);
				lev.setBounds(225, 280, 150, 30);
				add(lev);
				
				setVisible(true);  
			}

			//tells the user how they did in the game
			public void paintComponent(Graphics g)	
			{
				super.paintComponent (g);
				setBackground(Color.WHITE);
				
				//for(int i=0; i<600; i+=25) g.drawLine(i, 0, i, 600);
				//for(int k=0; k<600; k+=25) g.drawLine(0, k, 600, k);
				float color = 1f;
				for(int i=200; i<600; i+=10)
				{
					g.setColor(new Color(.5f, .5f, 1f, 1-color));
					g.fillRect(0, i, 600, 10);
					color-=0.1/10f;
				}
					
				Image mountains = new ImageIcon("mountains.png").getImage();
				g.drawImage(mountains, 0, 400, 600, 200, null);
				
				Font textFont = new Font("Ariel", Font.PLAIN, 25);
				g.setFont(textFont);
				g.setColor(Color.BLACK);
				
				g.drawString("You Win!", 250, 150);
				textFont = new Font("Ariel", Font.PLAIN, 20);
				g.setFont(textFont);
				g.drawString("It only took you "+milliseconds/1000+" seconds to shoot down the drone!", 72, 225);
			}
			
			//contains the action performed menu for the menu button
			class JBHandler implements ActionListener
			{
				//goes from win to the menu panel
				public  void actionPerformed(ActionEvent e)
				{
					cards.show(whole, "menu");
				}
			}
			
			//contains the action performed menu for the levels button
			class LevHandler implements ActionListener
			{
				//goes from win to the levels panel
				public  void actionPerformed(ActionEvent e)
				{
					cards.show(whole, "levels");
				}
			}
	
		}
		
		//the panel for if they run out of time
		class LosePanel extends JPanel
		{	
			JButton jb, lev;
			
			//sets layout and adds button
			public LosePanel()	
			{
				setLayout(null);
				jb = new JButton("Back to Menu");
				JBHandler handler = new JBHandler();
				jb.addActionListener(handler);
				jb.setBounds(225, 330, 150, 30);
				add(jb);
				
				lev = new JButton("Back to Levels");
				LevHandler levhandler = new LevHandler();
				lev.addActionListener(levhandler);
				lev.setBounds(225, 250, 150, 30);
				add(lev);
				
				setVisible(true);  
			}

			//tells the user how they did in the game
			public void paintComponent(Graphics g)	
			{
				super.paintComponent (g);
				setBackground(Color.WHITE);
				
				float color = 1f;
				for(int i=200; i<600; i+=10)
				{
					g.setColor(new Color(.5f, .5f, 1f, 1-color));
					g.fillRect(0, i, 600, 10);
					color-=0.1/10f;
				}
				Image mountains = new ImageIcon("mountains.png").getImage();
				g.drawImage(mountains, 0, 400, 600, 200, null);
				Font textFont = new Font("Ariel", Font.PLAIN, 20);
				g.setFont(textFont);
				g.setColor(Color.BLACK);
				
				g.drawString("You Lose!", 250, 200);
			}
			
			//contains the action performed method for the menu button
			class JBHandler implements ActionListener
			{
				//goes from lose to the menu panel
				public  void actionPerformed(ActionEvent e)
				{
					cards.show(whole, "menu");
				}
			}
			
			//contains the action performed menu for the level button
			class LevHandler implements ActionListener
			{
				//goes from lose to the level panel
				public  void actionPerformed(ActionEvent e)
				{
					cards.show(whole, "levels");
				}
			}
	
		}
 
		//sets up the panel to choose levels
		class LevelsPanel extends JPanel
		{	
			JButton easy;
			JButton medium;
			JButton hard;
			JButton menu;
			JLabel label;
			
			//sets the layout and adds buttons
			public LevelsPanel()	
			{
				setLayout(null);
				
				
				menu = new JButton("Back to Menu");
				MenuHandler menuhand = new MenuHandler();
				menu.addActionListener(menuhand);
				menu.setBounds(225, 290, 150, 40);
				add(menu);
				
				easy = new JButton("Easy");
				HandlerE bE = new HandlerE();
				easy.addActionListener(bE);
				easy.setBounds(75, 200, 100, 50);
				add(easy);
				
				
				medium = new JButton("Medium");
				HandlerM bM = new HandlerM();
				medium.addActionListener(bM);
				medium.setBounds(250, 200, 100, 50);
				add(medium);
				
				
				
				
				hard = new JButton("Hard");
				HandlerH bH = new HandlerH();
				hard.addActionListener(bH);
				hard.setBounds(425, 200, 100, 50);
				add(hard);
				
				
				
				setVisible(true);  
			}

			//paints the background and words for the levels panel
			public void paintComponent(Graphics g)	
			{
				super.paintComponent (g);
				setBackground(Color.WHITE);
				
				if(med) medium.setEnabled(true);
				else medium.setEnabled(false);
				
				if(har) hard.setEnabled(true);
				else hard.setEnabled(false);
				
				float color = 1f;
				for(int i=200; i<600; i+=10)
				{
					g.setColor(new Color(.5f, .5f, 1f, 1-color));
					g.fillRect(0, i, 600, 10);
					color-=0.1/10f;
				}
				
				Image mountains = new ImageIcon("mountains.png").getImage();
				g.drawImage(mountains, 0, 400, 600, 200, null);
				
				//for(int i=0; i<600; i+=25) g.drawLine(i, 0, i, 600);
				//for(int k=0; k<600; k+=25) g.drawLine(0, k, 600, k);
				Font textFont = new Font("Ariel", Font.PLAIN, 20);
				g.setFont(textFont);
				g.setColor(Color.BLACK);
				//for(int i=50; i<600; i+=50) g.drawLine(i, 0, i, 600);
				g.drawString("Levels", 271, 100);
			}
			
			//contains the action performed method for the menu button
			class MenuHandler implements ActionListener
			{
				//changes to menu panel
				public  void actionPerformed(ActionEvent e)
				{
					cards.show(whole, "menu");
					
				}
			}
			
			//containes the action performed method for the easy button
			class HandlerE implements ActionListener
			{
				//changes the speed of the drone(level) and runs the game
				public  void actionPerformed(ActionEvent e)
				{
					speed = 20;
					GamePanel game = new GamePanel(); 
					whole.add(game, "game");
					// seconds = 0;
					milliseconds = 0;
					
					cards.show(whole, "game");
					
				}
			}
			
			//containes the action performed method for the medium button
			class HandlerM implements ActionListener
			{
				//changes the speed of the drone(level) and runs the game
				public  void actionPerformed(ActionEvent e)
				{
					speed = 13;
					GamePanel game = new GamePanel(); 
					whole.add(game, "game");
					// seconds = 0;
					
					milliseconds = 0;
					cards.show(whole, "game");
				}
			}
			
			//containes the action performed method for the hard button
			class HandlerH implements ActionListener
			{
				//changes the speed of the drone(level) and runs the game
				public  void actionPerformed(ActionEvent e)
				{
					
					speed = 10;
					GamePanel game = new GamePanel(); 
					whole.add(game, "game");
					// seconds = 0;
					milliseconds = 0;
					cards.show(whole, "game");
				}
			}
		}

		//this class sets up the actual game
		class GamePanel extends JPanel implements KeyListener
		{
			 private Timer droneTimer, flipTimer;
			 int droneX, droneY, randX, randY, laserX, laserY, timeBar;//, milliseconds;
			 boolean prevContact, flip;
			 String prev;
			 double health;
			 JButton levels;
			 
			 
			 //gives the variables values
			 public GamePanel() 
			 { 
				  setLayout(null);
				  prev = "";
				 
				  for(int i=0; i<9; i++)
				  {
					  cut[i] = false;
				  }
				  
				  droneX = 200;
				  if(speed==20) droneY = 250;
				  else droneY=200;
				  prevContact = flip = false;
				  health=500;
				  timeBar = 500;
				  milliseconds = 0;
				  //seconds = 0;
				  if(speed==13 || speed==10) randX = randY = 200;
				  else 
				  {
					  randX=200;
					  randY=250;
				  }
				  laserX = laserY = 300;
				  addKeyListener(this);

				  DroneMover mover = new DroneMover();
				  droneTimer = new Timer(speed, mover);
				  //System.out.println("About to start a new timer");
				  droneTimer.start();
				  
				  FlipMover flipper = new FlipMover();
				  flipTimer = new Timer(3, flipper);
				  //System.out.println("About to start a new timer");
				  flipTimer.start();
				  
				  
				  levels = new JButton("Back to Levels");
				  LevelsHandler handler = new LevelsHandler();
				  levels.addActionListener(handler);
				  levels.setBounds(440, 540, 150, 30);
				  add(levels);

				  
			 }
			  
			  //containes the action performed method for the propellers 
			  class FlipMover implements ActionListener 
			 {
				 //changes boolean that will animate propellers
				  public void actionPerformed(ActionEvent e) 
				  {
					  flip = !flip;
					  repaint();
				  }
			  }
			  
			 //this moves the drone, generates the position of the drone, determines 
			 //the amount of time that has passed, switches to the correct panel 
			 //when the game is over
			 class DroneMover implements ActionListener 
			 {
				  
				  //carries out the above ^
				  public void actionPerformed(ActionEvent e) 
				  {
					   //System.out.println("FIRE!");
					   //seconds = milliseconds/1000;
					   
					   if (milliseconds/1000>=125 && health!=0)
					   {
						    flipTimer.stop();
						    droneTimer.stop();
							cards.show(whole, "lose");	
							
					   }
						
					   if(health<=0 )
					   {
						   droneY+=5;
						   if( droneY>=700 ) 
						   {
							    if(speed==20) med =  true;
							    if(speed==13) har = true;
							    flipTimer.stop();
							    droneTimer.stop();
								cards.show(whole, "win");	
											   
						   }
					   } 
					   
					   else
					   { 
						   if(speed==13)
						   {
							   
							   if(prevContact==false && health+1<=500) health++;
							   if(( (laserX>=droneX+5 && laserX<=droneX+5+180) && (laserY>=droneY+45 && laserY<=droneY+45+60) ) || ( (laserX>=droneX+60 && laserX<=droneX+60+80) && (laserY>=droneY+105 && laserY<=droneY+105+50) ) ) 
							   {
								   
								   prevContact = true;
								   health-=1/1.154;
								   
								   
							   }
							   else prevContact = false;
						   }
						   
						   if(speed==10)
						   {
							   //g.drawRect(droneX, droneY+10, 200, 55);
							   //g.drawRect(droneX+72, droneY+30, 58, 55);
							   
							   if(prevContact==false && health+1<=500) health++;
							   if(( (laserX>=droneX && laserX<=droneX+200) && (laserY>=droneY+10 && laserY<=droneY+10+55) ) || ( (laserX>=droneX+72 && laserX<=droneX+72+58) && (laserY>=droneY+30 && laserY<=droneY+30+55) ) ) 
							   {
								  
								   prevContact = true;
								  
								   if(cut[8]==true) health-=5;
								   else health-=1/2.1;
								   
								   
							   }
							   else prevContact = false;
						   }
						   
						   if(speed==20)
						   {
							   //g.drawRect(droneX+30, droneY, 140, 40);
							   //g.drawRect(droneX+52, droneY+20, 90, 65);
							   
							   if(prevContact==false && health+1<=500) health++;
							   if(( (laserX>=droneX+30 && laserX<=droneX+30+140) && (laserY>=droneY && laserY<=droneY+40) ) || ( (laserX>=droneX+52 && laserX<=droneX+50+90) && (laserY>=droneY+20 && laserY<=droneY+20+65) ) ) 
							   {
								   
								   prevContact = true;
								   
								   health-=1/0.55;
								   
								   
							   }
							   else prevContact = false;
						   }
						   
						   
						   
						   
						   
						   if(droneX==randX && droneY==randY) 
						   {
							   //600-drone width/length = 400
							   //60 is the amount of room off limits for the drone
							   randX = (int)(Math.random()*400);
							   randY = (int)(Math.random()*300+100);
						   }
						   
						   int rand  = (int)(Math.random()*2+1);
						   if(rand==2)
						   {
							   
							    if(droneX>randX) droneX--;
							   else if(droneX<randX) droneX++;
							   else if(droneY<randY) droneY++;
							   else if(droneY>randY) droneY--;
						   }
						   
						   else
						   {
							    if(droneX<randX) droneX++;
							   else if(droneX>randX) droneX--;
							   else if(droneY<randY) droneY++;
							   else if(droneY>randY) droneY--;
						   }
							   
						   
						   
						   
						  
						   
						   
						   
					   }
					   
					   if(health>0)
					   {
						   //since it goes into this method every /speed/ milliseconds
						   milliseconds+=speed;
						   
						   //each pixel of the bar should represent .25 of a second
						   //the whole bar should represent 125 seconds
						   if(milliseconds%250==0)
						   {
							   timeBar--;
						   }
					   }
					   
					   repaint();
					   grabFocus();
				  }
			} 
		 
			//paints the laser, drone, health bar, time bar,  and the background
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				setBackground(Color.WHITE);

				//background
				if(speed==20)
				{
					Image santaCruz = new ImageIcon("SantaCruz.jpg").getImage();
					g.drawImage(santaCruz, 0, 0, 600, 600, null);
				}
				if(speed==13)
				{
					Image whiteSands = new ImageIcon("whiteSands.jpg").getImage();
					g.drawImage(whiteSands, 0, 0, 600, 600, null);
				}
				if(speed==10)
				{
					Image golanHeights = new ImageIcon("golanHeights.jpg").getImage();
					g.drawImage(golanHeights, 0, 0, 600, 600, null);
				}
				

				g.setColor(Color.BLUE);
				//g.drawRect(droneX+5, droneY+45, 180, 60);
				//g.drawRect(droneX+60, droneY+105, 80, 50);


				//draw drone
				if(speed==20)
				{
					Image easyDrone = new ImageIcon("easyDrone.png").getImage();
					//Image drone2 = new ImageIcon("drone2.png").getImage();
					Image easyDrone2 = new ImageIcon("easyDrone2.png").getImage();
					if(flip)g.drawImage(easyDrone, droneX, droneY, 200, 85, null);
					else g.drawImage(easyDrone2, droneX, droneY, 200, 85, null);
					//g.drawRect(droneX+30, droneY, 140, 40);
					//g.drawRect(droneX+52, droneY+20, 90, 65);
				}
					
				if(speed==13 )
				{
					Image drone = new ImageIcon("drone.png").getImage();
					//Image drone2 = new ImageIcon("drone2.png").getImage();
					Image drone3 = new ImageIcon("drone3.png").getImage();
					if(flip)g.drawImage(drone, droneX, droneY, 200, 200, null);
					else g.drawImage(drone3, droneX, droneY, 200, 200, null);
				}
				
				if(speed==10)
				{
					Image blackDrone = new ImageIcon("blackDrone.png").getImage();
					Image blackDrone2 = new ImageIcon("blackDrone2.png").getImage();
					if(flip)g.drawImage(blackDrone, droneX, droneY, 200, 100, null);
					else g.drawImage(blackDrone2, droneX, droneY, 200, 100, null);
					//g.drawRect(droneX, droneY+10, 200, 55);
					//g.drawRect(droneX+72, droneY+30, 58, 55);
				}
				
				//smoke
				if(health<=450)
				{
					if(speed==20)
					{
						Image smoke = new ImageIcon("smoke.gif").getImage();
						int length = (int)((500-health)/2)-50;
						int add = 200-length;
						g.drawImage(smoke, (droneX)+add/2, (droneY-100)+add-60, length, length, null);
					}
						
					if(speed==13 )
					{
						Image smoke = new ImageIcon("smoke.gif").getImage();
						int length = (int)((500-health)/2)-50;
						int add = 200-length;
						g.drawImage(smoke, (droneX)+add/2, (droneY-100)+add, length, length, null);
					}
					if(speed==10)
					{
						Image smoke = new ImageIcon("smoke.gif").getImage();
						int length = (int)((500-health)/2)-50;
						int add = 200-length;
						g.drawImage(smoke, (droneX)+add/2, (droneY-100)+add-60, length, length, null);
					}
				}
			
				
				//flame
				if(health<=0 )//&& time<0)
				{
					if(speed==13)
					{
						Image flame2 = new ImageIcon("flame2.gif").getImage();
						g.drawImage(flame2, droneX+(50), droneY, 100, 100, null);
					}
					if(speed==10 || speed==20)
					{
						Image flame2 = new ImageIcon("flame2.gif").getImage();
						g.drawImage(flame2, droneX+(50), droneY-60, 100, 100, null);
					}
				}

				//draw laser point
				g.setColor(Color.RED);
				g.fillOval(laserX, laserY, 10, 10);
				
				Font textFont = new Font("Ariel", Font.PLAIN, 20);
				g.setFont(textFont);
				
				//draw health bar
				if (health>0)
				{
					g.setColor(Color.gray);
					g.fillRect( 5, 25, (int)health, 20);
					g.setColor(Color.RED);
					g.fillRect( 10, 20, (int)health, 20);
					g.setColor(Color.BLACK);
					g.drawString("Health", 520, 40);
				}
				
				//draw time bar
				if (milliseconds/1000<125) 
				{
					g.setColor(Color.gray);
					g.fillRect( 5, 60, 4*(125-milliseconds/1000), 20);
					g.setColor(Color.BLACK);
					g.drawString("Time", 520, 80);
					g.setColor(Color.RED);
					g.fillRect( 10, 55, 4*(125-milliseconds/1000), 20);
					
				}
				
				//laser beam 
				Graphics2D g2 = (Graphics2D)g;
				g2.setStroke( new BasicStroke(5.0F) );
				Color laserRed = new Color(1.0f, 0.0f, 0.0f, 0.2f);
				g.setColor(laserRed);
				g.drawLine(300, 600, laserX+5, laserY+5);
				
				//Font 
				textFont = new Font("Ariel", Font.PLAIN, 40);
				g.setFont(textFont);
				g.setColor(Color.BLACK);
				//System.out.print("\n"+milliseconds);
				//g.drawString(""+milliseconds/1000, 300, 300);
				
				//System.out.print(milliseconds/1000+"\n");
				
				
				
		 
			} 
			
			//empty method needed to compile
			public void keyTyped(KeyEvent e) {} 

			//This method controls the position of the laser
			public void keyPressed(KeyEvent evt) 
			{
				int move = 0;
				if(speed == 10) move = 20;
				if(speed == 13) move = 15;
				if(speed == 20) move = 10;
				
				if(evt.getKeyCode()==KeyEvent.VK_UP && laserY-move>80) laserY-=move;
				if(evt.getKeyCode()==KeyEvent.VK_DOWN && laserY+move<580)laserY+=move;
				
				if(evt.getKeyCode()==KeyEvent.VK_RIGHT && laserX+move<600)laserX+=move;
				if(evt.getKeyCode()==KeyEvent.VK_LEFT && laserX-10>0)laserX-=move;
				
				char cmd = (char)evt.getKeyCode();
				
				
				if(cmd=='j' || cmd=='J') cut[0] = true;
				if((cmd=='o' || cmd=='O') && cut[0]==true ) cut[1] = true;
				//else cut[1] = false;
				if((cmd=='e' || cmd=='E') && cut[1]==true ) cut[2] = true;
				//else cut[2] = false;
				if((cmd=='b' || cmd=='B') && cut[2]==true ) cut[3] = true;
				//else cut[3] = false;
				if((cmd=='o' || cmd=='O') && cut[3]==true) cut[4] = true;
				//else cut[4] = false;
				if((cmd=='b' || cmd=='B') && cut[4]==true) cut[5] = true;
				//else cut[5] = false;
				if((cmd=='k' || cmd=='K') && cut[5]==true) cut[6] = true;
				//else cut[6] = false;
				if((cmd=='i' || cmd=='I') && cut[6]==true) cut[7] = true;
				//else cut[7] = false;
				if((cmd=='m' || cmd=='M') && cut[7]==true) cut[8] = true;
				//else cut[8] = false;
				
				
				
				if( cut[0] && cut[1] && cut[2] && cut[3] && cut[4] && cut[5] && cut[6] && cut[7] && cut[8]) return;
				
				else if(!( cmd=='j' || cmd=='J' || cmd=='o' || cmd=='O' || cmd=='e' || cmd=='E' || cmd=='b' || cmd=='B' || cmd=='k' || cmd=='K' || cmd=='i' || cmd=='I' || cmd=='m' || cmd=='M')) 
				{
					for(int i=0; i<9; i++)
					{
						cut[i] = false;
					}
				}
				
				if(prev.equals(""+cmd) && (cmd != 'j' && cmd != 'J'))
				{
					for(int i=0; i<9; i++)
					{
						cut[i] = false;
					}
				}
				prev = ""+cmd;
				
				
				
			}
			
			//empty method needed to compile
			public void keyReleased(KeyEvent e) {}
			
			//contains the action performed method for the levels button
			class LevelsHandler implements ActionListener
			{
				//switches from game to the levels panel
				public  void actionPerformed(ActionEvent e)
				{
					flipTimer.stop();
					droneTimer.stop();
					cards.show(whole, "levels");
				}
			}
	 
		}
			
	}





