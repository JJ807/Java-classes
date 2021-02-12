/**
 * Abstrakcyjna klasa bazowa, reprezentuje osobe/czlowieka
 */
abstract class Man {

  /**
   * imie osoby
   */
  private final String name;
  /**
   * nazwisko osoby
   */
  private final String surname;


  /**
   * Konstruktor
   *
   * @param name1    imie
   * @param surname1 nazwisko
   */
  public Man(String name1, String surname1) {
    name = name1;
    surname = surname1;
  }

  /**
   * Metoda dostepowa do pola String z imieniem
   *
   * @return imie
   */
  public String getName() {
    return this.name;
  }

  /**
   * Metoda dostepowa do pola String z nazwiskiem
   *
   * @return nazwisko
   */
  public String getSurname() {
    return this.surname;
  }

  /**
   * Metoda konwertujaca klase Man na String
   *
   * @return lancuch znakowy z imieniem i nazwiskiem osoby
   */
  public String toString() {
    return this.getName() + " " + this.getSurname();
  }

  /**
   * Metoda porownujaca cechy 2 osob
   * @param ob Druga osoba z ktora porownujemy obecna
   * @return Osoboa ktorej cecha spelnia dany warunek
   */
  abstract public Man compare(Man ob);

  /**
   * Metoda liczaca srednia z czegos
   * @return 0.0
   */
  public double average() {
    return 0.0;
  }
}
