import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Klasa Stack programu reprezentujaca stos
 *
 * @param <T>
 */
class Stack<T> {

  private final T[] tab;
  private int currentSize = 0;
  private final int maxSize;

  Stack(int max, Class<T[]> classT) {
    this.maxSize = max;
    tab = classT.cast(Array.newInstance(classT.getComponentType(), max));
  }

  public boolean isEmpty() {
    return currentSize == 0;
  }

  public boolean isFull() {
    return currentSize == maxSize;
  }

  /**
   * Dodaje element do stosu
   *
   * @param x element stosu
   * @throws StackOverflowException proba dodania elementu do pelnego stosu
   */
  public void push(T x) throws StackOverflowException {
    if (this.isFull()) {
      throw new StackOverflowException("Stos jest juz pelny.");
    }
    tab[currentSize] = x;
    ++currentSize;
  }

  /**
   * Usuwa element ze stosu
   *
   * @return usunięty element
   * @throws StackUnderflowException proba usuniecia elementu z pustego stosu
   */
  public T pop() throws StackUnderflowException {
    if (this.isEmpty()) {
      throw new StackUnderflowException(
          "BLAD DANYCH WEJSCIOWYCH! Na stosie jest za malo elementow, zeby wykonac operacje!");
    }
    --currentSize;
    return tab[currentSize];

  }

  /**
   *
   * @return lancuch zlozony z elementow znajdujacych sie aktualnie na stosie
   */
  public String toString() {
    StringBuilder msg = new StringBuilder();
    if (currentSize == 1) {
      msg.append(tab[currentSize - 1]);
    } else {
      msg.append("BLAD DANYCH WEJSCIOWYCH! Koniec algorytmu, a stos nie jest pusty: ");
      for (int i = 0; i < currentSize; ++i) {
        if (i == currentSize - 1) {
          msg.append(tab[i]);
        } else {
          msg.append(tab[i]).append(", ");
        }
      }
      System.out.println(msg);
      System.exit(-1);
    }

    return msg.toString();
  }
}

public class Lab10 {

  public static void main(String[] args) {
    String[] temp = Arrays.stream(args[0].split("")).filter(w -> w.isEmpty() == false)
        .toArray(String[]::new);

    Stack<String> obj = new Stack<>(temp.length, String[].class);
    try {
      for (String x : temp) {
        if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) {
          String second = obj.pop();
          String first = obj.pop();
          String concat = "(" + first + x + second + ")";
          obj.push(concat);
        } else {
          obj.push(x);
        }
      }
      System.out.println("Wynik: " + obj.toString());
    } catch (StackUnderflowException | StackOverflowException e) {
      System.out.println(e.getMessage());
    }
  }
}

