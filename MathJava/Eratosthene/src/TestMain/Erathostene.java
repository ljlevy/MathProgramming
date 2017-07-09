package TestMain;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Erathostene extends JFrame {
	
	private int width= 400;
	private int height= 400;
	
	private int xPos=50;
	private int yPos=350;
	
	// Instanciation d'un objet JPanel
	private Grid grid = new Grid();
	
  // Constructeur par defaut	
  public Erathostene(int width, int height){

	  /*
	  Composition d'une fenêtre:
	  
	  - RootPane: Le conteneur principal qui contient les autres composants ;
	  - LayeredPane: qui forme juste un panneau composé du conteneur global et de la barre de menu (MenuBar) ;
	  - MenuBar, la barre de menu, quand il y en a une ;
	  - ContentPane: c'est dans celui-ci que nous placerons nos composants ;
	  - GlassPane(en transparence), couche utilisée pour intercepter les actions de l'utilisateur avant qu'elles ne parviennent aux composants.	  
*/	  
	/*this.width = width;
	this.height = height;*/
	  
    this.setTitle("Eratosthène screen from Jacques LEVY"); // Set title
    this.setSize(width, height); // Set window size
    this.setLocationRelativeTo(null); //center
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit when clicking
    this.setLocation(xPos,yPos); // Set position
    
    this.setResizable(false); //Disable resizing     
    //this.setResizable(true);  //Allow resizing 
    
    
    this.setAlwaysOnTop(true); //Always on top
    
    this.setUndecorated(false); // Remove decoration       
    
    
    //Définition de sa couleur de fond
    grid.setBackground(Color.BLUE); 
    
    //On prévient notre JFrame que notre JPanel sera son content pane
    //this.setContentPane(grid);  
    
    //this.setContentPane(new Panneau());   
    this.setContentPane(grid); 
    
    // Set visible
    this.setVisible(true);
    
    animateEratosthene();  

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
  
  private void removeCase(int nb)
  {
	  
  }
  
  private void animateEratosthene(){	   
 
	    for(int j = 0; j < grid.getNbSquarePerLine(); j++)
	    { 	    
	       for(int i = 0; i < grid.getNbSquarePerLine(); i++)
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
	             	             
	             grid.SetTitle("We remove multiple of "+nb+" "); 
	             //grid.setCruxColor(new Color(255-2*nb,50+2*nb,200-nb+50));
	             
	             grid.repaint();  	          
	             
	             for (k = nb; k<=grid.getNbSquarePerLine()*grid.getNbSquarePerLine(); k++ )
	             {	             
	            	 if ( (0 == k % nb ) && (k !=nb) )
	            	 {
	            		 int Xpos; 
	            		 int Ypos; 
	            		 
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
	             	                  
	           } // end for line      	  
	    	  
	       } // end for column
	    	
	    	  
	    } // end if  IsPrimeNumbers()  
	  }   
  
}
