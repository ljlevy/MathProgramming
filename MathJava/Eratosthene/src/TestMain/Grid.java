package TestMain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;
import javax.imageio.ImageIO;



// File for image
import java.io.File;
import java.io.IOException;

 
public class Grid extends JPanel {
	
	
	// Non modifiable values
	final int size_square=52;
    final int nb_square_per_lines=10;
    private int x1; 
    private int y1;
	final  int offset=5;
		
	/* Position for animation circle */
	private int posX;
	private int posY;	

	/* Position for crux */
	private int posCX[] = new int[100];
	private int posCY[]= new int[100];	
	
	private String title;
	
	private Color cruxColor;
	
	// Constructor
	public Grid()
	{
		this.posX=70;
		this.posY=70;
		this.SetX1(70);
		this.SetY1(70);	
		this.title="";
				
		for (int i=0; i<nb_square_per_lines*nb_square_per_lines ; i++)
		{
			this.posCX[i] = -100;
			this.posCY[i]=-100;	
		}
		
		this.cruxColor=new Color(0,0,255);			
	}
	
    public void DrawRectangle(Graphics g, int x1, int y1, int width, int height, int taillePolice, String text)
   {	   
	   // Draw rectangle
	    g.setColor(new Color(255, 255, 200));
	    g.drawRect(x1, y1, width, height);
	    g.fillRect(x1, y1, width, height);
	   	 
	   // Manage font
	   g.setColor(Color.red);
	   Font font = new Font("Courier", Font.BOLD, taillePolice);
	   g.setFont(font);
	   g.drawString(text,  x1+width/2, y1+height/2);		   

   }  
   
   public void DrawCrux(Graphics g, int x1, int y1, int x2, int y2, Color color)
   {
	    Graphics2D g2d = (Graphics2D)g;
	    int thin = 5;
	    g2d.setStroke(new BasicStroke(thin));	    
	    g2d.setColor(color);
	    g2d.drawLine(x1+thin, y1+thin, x2-thin, y2-thin);	
	    g2d.drawLine(x2-thin, y1+thin, x1+thin, y2-thin);
   }   
   
   public void DrawTitle(Graphics g, String title, int x1, int y1)
   {
	    Graphics2D g2d = (Graphics2D)g;
	    int thin = 5;
	    g2d.setStroke(new BasicStroke(thin));	    
	    g2d.setColor(new Color(0, 0,255));
	    g2d.drawString(title, x1, y1);
   }    
   
   public void DrawCircle(Graphics g, int x1, int y1, int diameter)
   {
	    Graphics2D g2d = (Graphics2D)g;
	    int thin = 5;
	    g2d.setStroke(new BasicStroke(thin));
	    
	    g2d.setColor(new Color(0, 255,0));
	    g2d.drawOval(x1, y1, diameter, diameter);  
   }
   
  public void animateCircle(Graphics g, int diameter)
   {
	    int alpha = 127; // 50% transparent
	    Color myColour = new Color(255, 0, 0, alpha);	   
	    g.setColor(myColour);
	    g.fillOval(posX, posY, diameter, diameter);	   
   }
   
   public Color getCruxColor()
   {
	   return cruxColor; 
   }
   
   public void setCruxColor(Color color)
   {
		this.cruxColor = color; 
   }    
   
   public String getTitle()
   {
	   return title; 
   }
   
   public void SetTitle(String title)
   {
	    this.title = "";
		this.title = title; 
   }  
   
   public int getX1()
   {
	   return x1; 
   }
   
   public void SetX1(int x1)
   {
		this.x1 = x1; 
   }   
   
   public void SetY1(int y1)
   {
		this.y1 = y1; 
   }     
   
   public int getY1()
   {
	   return y1; 
   }
   
   public int getOffset()
   {
	   return offset; 
   }   
        
   public int getSizeSquare()
   {
	   return size_square;
   }
   
   public int getNbSquarePerLine()
   {
	   return nb_square_per_lines;
   }   
   
   public int getPosX() 
   {
	    return posX;
   }

   public void setPosX(int posX) 
   {
	   this.posX = posX;
   }

   public int getPosY() 
   {
	    return posY;
   }

   public void setPosY(int posY) 
   {
	    this.posY = posY;
   }	
   
   public int getCPosX(int i) 
   {
	    return posCX[i];
   }  
   
   public int getCPosY(int i) 
   {
	    return posCY[i];
   }   

   public void setCPosY(int posCY, int i) 
   {
	    this.posCY[i] = posCY;
   }
   
   public void setCPosX(int posCX, int i) 
   {
	   this.posCX[i] = posCX;
   }  
 
   
  public void paintComponent(Graphics g){
	  
	  // Clear all
	  Color bgColor = new Color(200,200,200);
	  g.setColor(bgColor);//background color
	  g.fillRect(0,0,this.getWidth(),this.getHeight());
	  
	  
	   
	   // Size of Panel
	   x1 = (this.getWidth()-size_square)/nb_square_per_lines; 
	   y1 = (this.getHeight()-size_square)/nb_square_per_lines;
	   	    	    
	   final int time_to_sleep = 10000; // Time to wait
	   
	   for (int j=0; j<nb_square_per_lines; j++)
	   {
	      for (int i=1; i<=nb_square_per_lines ; i++)
	      {
	         DrawRectangle(g, x1+i*(size_square+offset), y1+j*(size_square+offset), size_square, size_square, 20, Integer.toString(i+10*j));
	      } 
	   }
	    	     	   	   
	   System.out.println("getPosX(): "+getPosX()+", getPosY(): "+getPosY()+", getTitle: "+getTitle()+"");
	   
	
	    
	   animateCircle(g,size_square);	   
	
	   for (int i=0; i<nb_square_per_lines*nb_square_per_lines; i++)
	   {
	      DrawCrux(g, getCPosX(i), getCPosY(i), getCPosX(i)+size_square, getCPosY(i)+size_square, getCruxColor());
	   } 	   	   
	   	 
	   // Display text
	   DrawTitle(g, getTitle(), 80, 50);	   	      
	   	      	  
	   
  }  
  
  
}