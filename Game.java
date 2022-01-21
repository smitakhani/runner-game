/*
<applet code="Game" height="650" width="1300">
</applet>
*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends Applet implements KeyListener,Runnable{
		
		Thread t;
		boolean flag;
		boolean GAMEOVER=false;
		int k=1;
		int w=0;
		int y=480;
		int x1=0;
		int x2=1300;
		int rockX=1300;
		Image back;
		Image back1;
		Image obj;
		Image img[]=new Image[7];
		public void paint(Graphics g){
			g.drawImage(back,x1,0,this); 
			g.drawImage(back1,x2,0,this); 
			g.drawImage(img[k],w,y,this);
			g.drawImage(obj,rockX,560,this);
			g.setFont(new Font("TimesNewRoman", Font.PLAIN, 40));
			g.setColor(Color.red);
			if(GAMEOVER)
			{ 
				g.setColor(Color.red);
				g.drawString("GAME OVER",550,322);
				flag=true;
			}
		}
		public void init(){
			for(int i=1;i<=6;i++)
			{
				img[i]=getImage(getCodeBase(),i+".png");
			}
			back=getImage(getCodeBase(),"B1.jpg");
			back1=getImage(getCodeBase(),"B2.jpg");
			obj=getImage(getCodeBase(),"rock.png");
			addKeyListener(this);
			setFocusable(true);
		}
		public void start(){
			t=new Thread(this);
			if(!GAMEOVER)
			{
				flag=false;
			}
			t.start();
		}
		public void run(){
			
			try{	
				for(;;)
				{	
						if(flag) break;
						k++;
						x1-=20;
						x2-=20;
						rockX-=30;
						if(w<=100)
						{
							w+=20;
						}	
						if(k==6)
						{
							k=1;
							y=480;
							w=120; 
						}
						if(rockX<=0)
						{
							rockX=1300;
						}
						if(x2==0)
						{
							x1=1300;
						}
						else if(x1==0)
						{
							x2=1300;
						}
						if(w==120 && (rockX>=110 && rockX<=210))
						{
							GAMEOVER=true;
							flag=true;
							repaint();
							continue;
						}
						repaint();
						Thread.sleep(100);
				}
			}catch(Exception e) {}
			
		}
		public void stop(){
			t=null;
		}
		public void keyPressed(KeyEvent e) {  
			if(e.getKeyCode()==KeyEvent.VK_UP && !GAMEOVER && y>400)
        	{
        		k=1;
        		y=y-150;
        		w=w+45;
        		repaint();
        	}
    	}  
    	public void keyReleased(KeyEvent e) {      	
    	}  
    	public void keyTyped(KeyEvent e) {      
    	}
}