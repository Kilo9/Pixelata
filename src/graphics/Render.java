package graphics;

import game.Display;

/**
 *
 * @author Jeremy Bassi
 */
public class Render 
{
    public int width;
    public int height;
    
    public int[] pixels;
    
    public Render(int w, int h)
    {
        width = w;
        height = h;
        pixels = new int[width * height];
    }
    
    public void draw(Render render, int xOffs, int yOffs)
    {
        for (int y = 0; y < render.height; y++)
        {
            int yPix = y + yOffs;
            
            if (yPix < 0 || yPix > Display.DHEIGHT)
            {
                continue;
            }
            
            for (int x = 0; x < render.width; x++)
            {
                int xPix = x + xOffs;
                
                if (xPix < 0 || xPix > Display.DWIDTH)
                {
                    continue;
                }
                
                int src = render.pixels[x + y * render.width];
                pixels[xPix + yPix * width] = src;
            }
        }
    }
}
