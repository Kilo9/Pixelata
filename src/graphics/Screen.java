package graphics;

import game.Game;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jeremy Bassi
 */
public class Screen extends Render
{
    private Render render;

    public Screen(int width, int height)
    {
        super(width, height);
        
        render = new Render(width, height); 
        
        /*
        for (int i = 0; i < width * height; i++)
        {
            render.pixels[i] = Color.green.getRGB();
        } */
        
        // fancy world generator
        Random rnd = new Random();
        Random white = new Random();
        
        boolean wasWhite = false;
        for (int i = 0; i < width * height; i++)
        {
            int wh = white.nextInt(5);
            
            if (wasWhite && wh > 1)
            {
                render.pixels[i] = Color.white.getRGB();
                wasWhite = true;
            }
            else if (wh == 0)
            {
                render.pixels[i] = Color.white.getRGB();
                wasWhite = true;
            }
            else
            {
               render.pixels[i] = rnd.nextInt(); 
               wasWhite = false;
            }
            
        }
    }
    
    public void render(Game game)
    { 
       render.pixels[game.player.index] = game.player.col;

        draw(render, 0, 0);
    }
}
