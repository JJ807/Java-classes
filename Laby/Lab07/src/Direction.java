/**
 * Klasa enum reprezentujaca kierunek przejscia po planszy
 */
public enum Direction {
  LEFT,
  RIGHT,
  UP,
  DOWN;

  /**
   * wspolrzedne wektora przejscia, zmiana x'a to zmiana wiersza w gore/dol,
   * zmiana y'ka to zmiana kolumny w prawo/lewo
   */
  private int x;
  private int y;


  /**
   * konstruktor
   */
  Direction() {
    switch (name()) {
      case "LEFT":
        y = -1;
        x = 0;
        break;
      case "RIGHT":
        y = 1;
        x = 0;
        break;
      case "UP":
        y = 0;
        x = -1;
        break;
      case "DOWN":
        y = 0;
        x = 1;
        break;
    }
  }

  /**
   * Metoda dostepowa do wspolrzednej x
   *
   * @return wspolrzedna reprezentujaca numer wiersza
   */
  public int getX() {
    return x;
  }

  /**
   * Metoda dostepowa do wspolrzednej y
   *
   * @return wspolrzedna reprezentujaca numer kolumny
   */
  public int getY() {
    return y;
  }

  /**
   * Metoda zmieniajaca obiekt o typie klasy na typ String
   *
   * @return wektor przesuniecia w []
   */
  @Override
  public String toString() {
    return "[" + x + ", " + y + "]";
  }
}