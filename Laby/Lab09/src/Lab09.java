import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Lab09 {

  public static String[] splitArgs(String arg) {
    return Arrays.stream(arg.split("[\\s+(),]")).filter(w -> w.isEmpty() == false)
        .toArray(String[]::new);
  }

  public static void main(String[] args) {
    try {
      String[] splitted = splitArgs(args[0]);
      Method fk;
      if (splitted.length == 3) {
        fk = Math.class.getMethod("" + splitted[0], double.class, double.class);
        System.out.println("Wynik: " +
            fk.invoke(null, Double.parseDouble(splitted[1]), Double.parseDouble(splitted[2])));
      } else if (splitted.length == 2) {
        fk = Math.class.getMethod(splitted[0], double.class);
        System.out.println("Wynik: " + fk.invoke(null, Double.parseDouble(splitted[1])));
      } else {
        throw new IllegalArgumentException(
            "Zla liczba argumentow funkcji! Podaj jedna lub dwie liczby.");
      }

    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Nie podano nic do obliczenia!");
    } catch (NumberFormatException e) {
      System.out.println("Argumenty funkcji musza byc liczbami!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      System.out.println("Nie ma takiej metody!");
    }
  }
}
