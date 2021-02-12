import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;

class Main
{
  public static void func(int liczba_pkt, float r_max)
  {
    float x,y;
    Random rand = new Random();

    try
    {
      FileWriter f = new FileWriter("circle.dat");
      for (int i = 0; i < liczba_pkt; i++)
      {
        x = rand.nextFloat() * r_max;
        y = rand.nextFloat() * r_max;
        
        if(x*x + y*y <= r_max*r_max)
        {
          System.out.println(x + " " + y + "\n");
          f.write(x + " " + y + "\n");
        } 
      }
      f.close();
    }
    catch(IOException e)
    {
      System.out.println("Nie znaleziono pliku.\n");
    }
    
  }

   public static void main(String[] args)
    {
      int n = Integer.parseInt(args[0]);
      float r_m = Float.parseFloat(args[1]);
      func(n, r_m);
    }

  
  
}
