package game;

import graphics.Render;
import java.awt.Color;

/**
 *
 * @author Jeremy Bassi
 */
public class Player 
{
    public int index;
    public int col;
    public int x;
    public int y;
    public int under;
    
    private Render render;
    
    private int width;
    private int height;
    
    public Player(Render render)
    {
        this(0, Color.BLUE, render);
    }
    
    public Player(int index, Color color, Render render)
    {
        this(index, color.getRGB(), render);
    }
    
    public Player(int index, int col, Render render)
    {
        
        this.render = render;
        this.index = index;
        this.col = col;
        under = render.pixels[index];
        System.out.println(Color.green.getRGB() + "  " + under);
        width = render.width;
        height = render.height;
        if (y == 0)
        {
            x = index;
        }
        else
        {
            x = index / (y * width);
        }
        
        y = (index - x)/ width;
        
        
    }
    
    public void tick(boolean up, boolean down, boolean left, boolean right)
    {
        under = render.pixels[index];
        if (up)
        {
            //if (y > 0)
            render.pixels[index] = under; 
                y--;
                
                
        }
        if (down)
        {
            //if (y * width < height)
            render.pixels[index] = under;
            y++;
        }
        if (left)
        {
            //if (x > 0)
            render.pixels[index] = under;
            x--;
        }
        if (right)
        {
            //if (x < width)
            render.pixels[index] = under;
            x++;
        }
        
        index = x + y * width;
        
        under = render.pixels[index];
    }
    
    

}
