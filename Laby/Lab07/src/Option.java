/**
 * Klasa enum reprezentujaca opcje wybrana przez uzytkownika,
 * uzywamy jej tylko do pojedynczego wyswietlenia dostepnych opcji
 */
public enum Option {
  LEFT,
  RIGHT,
  UP,
  DOWN,
  RESET,
  EXIT;

  /**
   * wybrana opcja przez uzytkownika
   */
  private char znak;
  /**
   * opis czynnosci
   */
  private String opis;
  /**
   * kierunek w ktorym ma gracz ma sie poruszyc lub null dla stalych EXIT i RESET
   */
  private Direction kierunek;

  /**
   * konstruktor
   */
  Option() {
    switch (name()) {
      case "LEFT":
        znak = 'a';
        opis = "Przesun w lewo";
        kierunek = Direction.LEFT;
        break;
      case "RIGHT":
        znak = 'd';
        opis = "Przesun w prawo";
        kierunek = Direction.RIGHT;
        break;
      case "UP":
        znak = 'w';
        opis = "Przesun w gore";
        kierunek = Direction.UP;
        break;
      case "DOWN":
        znak = 's';
        opis = "Przesun w dol";
        kierunek = Direction.DOWN;
        break;
      case "RESET":
        znak = '0';
        opis = "Reset planszy";
        kierunek = null;
        break;
      case "EXIT":
        znak = 'q';
        opis = "Zakonczenie gry";
        kierunek = null;
        break;
    }
  }

  /**
   * Metoda dostepowa do znaku
   *
   * @return wybrana opcja w formie znaku
   */
  public char getZnak() {
    return znak;
  }

  /**
   * Metoda dostepowa do opisu wybranej czynnosci
   *
   * @return opis czynnosci
   */
  public String getOpis() {
    return opis;
  }

  /**
   * Metoda dostepowa do kierunku ruchu gracza
   *
   * @return wybrany kierunek
   */
  public Direction getKierunek() {
    return kierunek;
  }

  /**
   * Metoda zmieniajaca obiekt o typie klasy na typ String
   *
   * @return opis dostepnych czynnosci
   */
  @Override
  public String toString() {
    if (znak == 'q' || znak == '0') {
      return "'" + znak + "' ==> opcja " + name() + ", opis: " + opis;
    }
    return "'" + znak + "' ==> opcja " + name() + ", opis: " + opis + ", wektor przesuniecia: "
        + kierunek;
  }
}
