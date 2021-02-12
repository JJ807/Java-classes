import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Lab12 {

  public static void main(String[] args) throws IOException {
    Scanner myObj = new Scanner(System.in);
    System.out.println("Wpisz nazwy dwoch plikow tekstowych (oddzielone spacja): ");
    String fileNames = myObj.nextLine();
    String[] files = fileNames.split(" ");
    String line = null;

    FileInputStream fileInputStream1 = new FileInputStream(files[0]);
    FileInputStream fileInputStream2 = new FileInputStream(files[1]);
     BufferedReader in
         = new BufferedReader(new InputStreamReader(fileInputStream1));


    try {
      BufferedReader br = Files.newBufferedReader(Paths.get("filename.txt"));
      BufferedReader br2= Files.newBufferedReader(Paths.get("filename.txt"));

      // read line by line
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }

    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }
}
