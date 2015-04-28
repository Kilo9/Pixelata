package input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Jeremy Bassi
 */
public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener
{
    public boolean[] keys = new boolean[65536];

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code > 0 && code < keys.length)
        {
            keys[code] = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
       int code = e.getKeyCode();
       if (code > 0 && code < keys.length)
       {
           keys[code] = true;
       }
    }

    @Override
    public void focusGained(FocusEvent e) 
    {
        
    }

    @Override
    public void focusLost(FocusEvent e) 
    {
       for (int i = 0; i < keys.length; i++)
       {
           keys[i] = false;
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
       
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        
    }

}
