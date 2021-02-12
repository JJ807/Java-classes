/**
 * Klasa Student dziedziczaca po Man, reprezentuje studenta uczelni
 */
public class Student extends Man {

  /**
   * pierwsza ocena
   */
  private final double oc1;
  /**
   * druga ocena
   */
  private final double oc2;
  /**
   * trzecia ocena
   */
  private final double oc3;
  /**
   * numer albumu studenta
   */
  private final int nrAlbumu;

  /**
   * Konstruktor wywoluje konstruktor z klasy bazowej Man i ustawia wartosci pol
   *
   * @param n   imie
   * @param s   nazwisko
   * @param nr  numer albumu
   * @param oc1 1. ocena
   * @param oc2 2. ocena
   * @param oc3 3. ocena
   */
  public Student(String n, String s, int nr, double oc1, double oc2, double oc3) {
    super(n, s);
    nrAlbumu = nr;
    this.oc1 = oc1;
    this.oc2 = oc2;
    this.oc3 = oc3;
  }

  /**
   * Metoda nadpisana z klasy bazowej Man, zwraca studenta o wyzszej sredniej
   *
   * @param obcy obiekt typu Student z ktorym porownujemy srednia obecnego studenta
   * @return Student o wyzszej sredniej
   */
  @Override
  public Man compare(Man obcy) {
    try {
      Student temp = (Student) obcy;
      if (this.average() >= temp.average()) {
        return this;
      }
      return obcy;
    } catch (Throwable e) {
      return null;
    }
  }

  /**
   * Metoda liczy srednia z ocen studenta
   *
   * @return srednia ocen
   */
  @Override
  public double average() {
    return (oc1 + oc2 + oc3) / 3.0;
  }

  /**
   * Metoda konwertujaca klase na lancuch znakowy z imieniem, nazwiskiem, nr albumu i ocenami
   *
   * @return String z powyzszymi elementami
   */
  @Override
  public String toString() {
    return super.toString() + ", id number: " + nrAlbumu + ", grades: [" + oc1 + ", " + oc2 + ", "
        + oc3 + "]";
  }

}
