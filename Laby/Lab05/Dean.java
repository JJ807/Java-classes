/**
 * Klasa Dean dziedziczaca po Man, reprezentuje dziekana uczelni
 */
public class Dean extends Man {

  /**
   * stopien naukowy dziekana
   */
  private final String stopien;
  /**
   * poczatek kadencji
   */
  private final int begin;
  /**
   * koniec kadencji
   */
  private final int end;

  /**
   * Konstruktor, wywoluje konstruktor z klasy bazowej Man i ustawia wartosci pol
   *
   * @param st     stopien naukowy
   * @param n      imie
   * @param s      nazwisko
   * @param pocz   poczatek kadencji
   * @param koniec koniec kadencji
   */
  Dean(String st, String n, String s, int pocz, int koniec) {
    super(n, s);
    stopien = st;
    begin = pocz;
    end = koniec;
  }

  /**
   * Porownuje ktory z dziekanow konczy pozniej swoja kadencje
   *
   * @param ob Drugi dziekan (obiekt typu Dean)
   * @return dziekan (obiekt typu Dean), ktorego kadencja konczy sie pozniej
   */
  @Override
  public Man compare(Man ob) {
    try {
      Dean temp = (Dean) ob;
      if (this.end >= temp.end) {
        return this;
      }
      return ob;
    } catch (Throwable e) {
      return null;
    }
  }

  /**
   * Konwertuje klase na lancuch znakow z danymi elementami klasy Man i klasy Dean
   *
   * @return Lancuch znakow z informacjami o dziekanie
   */
  @Override
  public String toString() {
    return stopien + " " + super.toString() + ", Dean of the Faculty from " + begin + " to "
        + end + ".";
  }

  /**
   * Wypisuje komunikat o braku sredniej i wywoluje metode average() z klasy bazowej
   *
   * @return srednia ocen dziekana, czyli domyslnie z klasy bazowej: 0.0
   */
  @Override
  public double average() {
    System.out.print("  [Average not applicable]");
    return super.average();
  }

}
