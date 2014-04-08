import java.awt.*;
import java.applet.*;

public class Scribble extends Applet {

    private int xPos=0 , yPos=0;
    public boolean mouseDown(Event e, int x, int y) {
    
        xPos=x; yPos=y;
        
        Graphics g = getGraphics();
        for(int i=0; i<= 30; i+=5)
            g.drawOval(x-i,y-i,i*2,i*2);
        
        return true;
    
    }

    public boolean mouseUp(Event e, int x, int y){
    
        Graphics g = getGraphics();
        for(int i=0; i<= 30; i+=5)
            g.drawOval(x-i,y-i,i*2,i*2);
        
        return true;
    
    }
    
    public boolean mouseDrag(Event e, int x, int y) {
        Graphics g = getGraphics();
        g.drawLine(xPos, yPos, x, y);
        xPos = x; yPos = y;
        return true;
    }


}