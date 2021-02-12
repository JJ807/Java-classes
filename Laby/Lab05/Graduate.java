/**
 * Klasa Graduate dziedziczaca po Student, reprezentuje absolwenta uczelni
 */
public class Graduate extends Student {

  /**
   * stopien naukowy absolwenta
   */
  private final String stopien;
  /**
   * rok ukonczenia studiow
   */
  private final int rok;

  /**
   * Konstruktor, wywoluje konstruktor z klasy bazowej Student
   *
   * @param st       stopien naukowy
   * @param imie     imie
   * @param nazwisko nazwisko
   * @param nr       numer albumu
   * @param rok      rok ukonczenia studiow
   * @param oc1      1. ocena
   * @param oc2      2. ocena
   * @param oc3      3. ocena
   */
  public Graduate(String st, String imie, String nazwisko, int nr, int rok, double oc1, double oc2,
      double oc3) {
    super(imie, nazwisko, nr, oc1, oc2, oc3);
    this.stopien = st;
    this.rok = rok;
  }

  /**
   * Metoda konwertujaca klase na lancuch znakowy
   *
   * @return Lancuch ze stopniem naukowym i rokiem ukonczenia studiow oraz z elementami ktore
   * posiada kazdy Student
   */
  @Override
  public String toString() {
    return stopien + " " + super.toString() + ", year of graduation: " + rok;
  }

}
