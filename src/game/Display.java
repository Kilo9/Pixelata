package game;

import graphics.Screen;
import input.InputHandler;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Jeremy Bassi
 * 
 * Main Class :
 *  -game loop
 *  -render loop
 *  -display
 */
public class Display extends Canvas implements Runnable
{
    
    public static final int DWIDTH = 800;
    public static final int DHEIGHT = 600;
    public static final int SCALE = 40;
    
    private static final String title = "Pixelata Pre-Alpha";
    private static final String iconPath = "/main/icon.png";
    
    private static BufferedImage icon;
    
    private volatile boolean running;
    
    private BufferedImage img;
    private Screen screen;
    private int[] pixels;
    private InputHandler input;
    private Game game;
    private Thread thread;
    private Player p;
    
    public Display()
    {
        screen = new Screen(DWIDTH / SCALE, DHEIGHT / SCALE);
        p = new Player(screen);
        game = new Game(p);
       
        img = new BufferedImage(DWIDTH / SCALE, DHEIGHT / SCALE, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        input = new InputHandler();
        
        //image
        try 
        {    
            icon = resize(ImageIO.read(Display.class.getResource(iconPath)), 256, 256);   
        } catch (IOException ex) 
        {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addKeyListener(input);
        addMouseListener(input);
        addFocusListener(input);
        addMouseMotionListener(input);
    }
    
    public synchronized void start()
    {
        if (running) 
        {
            return;
        }
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop()
    {
        if (!running)
        {
            return;
        }
        
        running = false;
        try
        {
            thread.join();
        } catch(InterruptedException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            tick();
            render();
        }
        
    }
    
    private void tick()
    {
        if (hasFocus())
        {
            game.tick(input.keys);
        }
    }
    
    private void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        
        screen.render(game);
        
        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(img, 0, 0, DWIDTH, DHEIGHT, null);
        g.dispose();
        bs.show();
    }
    
    /**
     * utility method to resize images so that they can be kept original size
     * in their files.
     * @param image - image to be resized
     * @param width - new width
     * @param height - new height
     * @return - resized image
     */
    private static BufferedImage resize(BufferedImage image, int width, int height) 
    {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }


    /**
     * Creates the JFrame and prepares the game
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Display disp = new Display();
        
        JFrame frame = new JFrame(title);
        frame.add(disp);
        
        //set size properly
        frame.setResizable(false);
        frame.setSize(DWIDTH, DHEIGHT);
        frame.setVisible(true);
        Insets insets = frame.getInsets();
        int insetwidth = insets.left + insets.right;
        int insetheight = insets.top + insets.bottom;
        Dimension real = new Dimension(DWIDTH + insetwidth, DHEIGHT + insetheight);
        frame.setSize(real);
        frame.setPreferredSize(real);
        frame.setMinimumSize(real);
        frame.setMaximumSize(real);
        //------------------

        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        
        
        disp.start();
        disp.requestFocus();
        
    }
}
