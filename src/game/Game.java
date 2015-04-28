package game;

import java.awt.event.KeyEvent;

/**
 *
 * @author Jeremy Bassi
 */
public class Game 
{
    public Player player;
    
    public Game(Player p)
    {
        player = p;
    }
    
    public void tick(boolean[] keys)
    {
        boolean up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        boolean down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        boolean left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        boolean right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        
        player.tick(up, down, left, right);
        
        for (int i = 0; i < keys.length; i++)
        {
            keys[i] = false;
        }
    }
}
