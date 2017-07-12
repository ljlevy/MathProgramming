package TestMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Erathostene extends JFrame {

	private int width= 300;
	private int height= 300;

	private int xPos=50;
	private int yPos=350;


	// Instantiation of a button objet
	private JButton button = new JButton("Restart");

	private volatile boolean threadRunning = false;	
	
	private boolean drawing=true;

	// Default constructor	
	public Erathostene(int width, int height){

	 /*
	  Composition of a window:

	  - RootPane:The main container that contains the other components;
	  - LayeredPane: Layer that just forms a panel composed of the global container and the menu bar;
	  - MenuBar: The menu bar, when there is one ;
	  - ContentPane: It is in this that we will place our components;
	  - GlassPane: The layer used to intercept user actions before they reach the components.	  
		 */	  
	
		//do some job	            	  
		this.setTitle("Eratosthène screen from Jacques LEVY"); // Set title
		this.setSize(width, height); // Set window size
		this.setLocationRelativeTo(null); //center
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit when clicking
		this.setLocation(xPos,yPos); // Set position
		
		this.setResizable(false); //Disable resizing     
		//this.setResizable(true);  //Allow resizing 

		this.setAlwaysOnTop(true); //Always on top

		this.setUndecorated(false); // Remove decoration       

		// Add button
		JPanel buttonPane = new JPanel();
		JButton button = new JButton("Click to start Sieve");

		Font font = new Font("Courier", Font.BOLD, 30);
		button.setFont(font);
		button.setBackground(new Color(255,255,200));
		button.setBorderPainted(true);
				
		System.out.println("### button.getBounds: "+button.getBounds()+"");

		// Definition of action button
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("Initial Click: threadRunning is "+threadRunning+"");														
														
				drawing = true; 
				
				threadRunning = true;
				
				startThread();
								
			}
		});    

		buttonPane.add(button);
		
		setLayout(new BorderLayout());	
		getContentPane().add(buttonPane, BorderLayout.CENTER);

		// Set visible
		this.setVisible(true);	
		
		// do not Run animation in the thread
		threadRunning = false;
		
	}
	
	private void startThread(){

		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				
				threadRunning = true;

				   
				while(threadRunning){
					
					// Instantiation of a JPanel objet
					Grid grid = new Grid();
					
					//Definition of font color
					setBackground(Color.BLUE); 
					
					setSize(800, 800);

					// Fill ContentPane  
					setContentPane(grid); 	
					
					// Make all visible
					setVisible(true);
				
					System.out.println("Runing thread");
					
					//Run animation 
					animateEratosthene(grid); 
				}
			}
		});
		t.start();
	}          


	private boolean IsPrimeNumbers(int nb)
	{
		int nb_divisors=0; 
		boolean is_prime=true;

		for (int i=1; i<= nb; i++)
		{
			if ((nb % i) == 0 )
			{
				nb_divisors++; 
			}
		}

		if (nb_divisors > 2 )
		{
			is_prime = false;
		}

		return (is_prime);
	}



	private void animateEratosthene(Grid grid){	 
					
		for(int j = 0; (j < grid.getNbSquarePerLine()) && (true == drawing); j++)
		{ 	    
			for(int i = 0; (i < grid.getNbSquarePerLine()) && (true == drawing); i++)
			{	 				
				/* Get current position */
				int x = grid.getPosX(), y = grid.getPosY();
				int nb= (i+1)+10*j;
				int k=0;
				int l=0;

				if (true == IsPrimeNumbers(nb) && (nb>1) )	
				{	            	 
					// paint circle number
					x = grid.getX1() + (i+1)*(grid.getSizeSquare()+grid.getOffset());
					y = grid.getY1() + j*(grid.getSizeSquare()+grid.getOffset());
					grid.setPosX(x);
					grid.setPosY(y);
					
					grid.animateCircle(grid.getGraphics(),grid.getSizeSquare());

				    // Display text
					grid.SetTitle("We remove multiple of "+nb+" "); 
					grid.DrawTitle(grid.getGraphics(), grid.getTitle(), 80, 50);
					 

					grid.repaint();  	          

					for (k = nb; (k<=grid.getNbSquarePerLine()*grid.getNbSquarePerLine()) && (true == drawing) ; k++ )
					{	             
						if ( (0 == k % nb ) && (k !=nb) )
						{							
							int Xpos; 

							if  (0 == k%10) 
							{
								Xpos =10;            
							}
							else
							{
								Xpos= k%10;  
							}

							x = grid.getX1() + (Xpos)*(grid.getSizeSquare()+grid.getOffset());
							y = grid.getY1() + ((k-1)/10)*(grid.getSizeSquare()+grid.getOffset());	            		 

							System.out.println("grid.getX1()is "+grid.getX1()+", grid.getY1() is "+grid.getY1()+" nb is "+nb+", k is "+k+", k % 10 is "+k % 10+", k/10 is "+k/10+", x = "+x+", y = "+y+", ");

							// Remove all multiples 		             
							grid.setCPosX(x,k-1);
							grid.setCPosY(y, k-1);	    	             

							grid.repaint();

							try 
							{
								Thread.sleep(1000);
							} 
							catch (InterruptedException e) 
							{
								e.printStackTrace();
							}

						}
					}

					try 
					{
						grid.SetTitle("");
						grid.repaint();
						System.out.println("Removing title");
						Thread.sleep(5000);	                  
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}

				} // end if  IsPrimeNumbers()    	  

			} // end for line   


		} //    end for column
	}   

}
