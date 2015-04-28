package data;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jeremy
 */
public class DataReader 
{
    private Scanner sc;
    
    public DataReader(String filePath)
    {
        //get the file path
        URL url = getClass().getResource(filePath);
        File file = new File(url.getPath());
        
        //scanner
        try
        {
            sc = new Scanner(file);
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public Object[] read()
    {
        ArrayList<String> arr = new ArrayList<>();
        
        while (sc.hasNext())
        {
            arr.add(sc.next());
        }
        
        return arr.toArray();
    }

}
