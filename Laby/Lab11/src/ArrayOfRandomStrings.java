import java.util.Random;

/**
 * Klasa reprezentujaca 3 tablice losowych lancuchow
 */
class ArrayOfRandomStrings {

  int[] range = new int[]{5, 20};
  int leftBound = 48; // '0''
  int rightBound = 122; // 'z'
  String[] t1;
  String[] t2;
  String[] t3;


  /**
   * Konstruktor
   *
   * @param t1_length t1_length liczba elementow dla tablicy t1
   * @param t2_length t2_length liczba elementow dla tablicy t2 i t3
   */
  ArrayOfRandomStrings(int t1_length, int t2_length) {

    Random random = new Random();
    t1 = new String[t1_length];
    t2 = new String[t2_length];
    t3 = new String[t2_length];

    System.out.println("Losowanie " + t1_length + " lancuchow.");
    //wypelnienie tablicy t1 o dlugosci t1_length losowymi lancuchami z przedzialu range
    createRandomStrings(random, t1, t1_length, false);

    System.out.println("Testowanie dla " + t2_length + " lancuchow.");
    //utworzenie tablicy t2 z m-lancuchow wylosowanych z t1
    for (int j = 0; j < t2_length; ++j) {
      t2[j] = t1[random.nextInt(t1_length)];
    }

    //wypelnienie tablicy t3 o dlugosci t2_length losowymi lancuchami z przedzialu range
    createRandomStrings(random, t3, t2_length, true);

  }

  /**
   * Funkcja do losowania lancuchow i wypelniania tablic
   *
   * @param rand   obiekt typu Random (ziarno generatora)
   * @param arr    tablica ktora chcemy wypelnic
   * @param length dlugosc tejze tablicy
   */
  public void createRandomStrings(Random rand, String[] arr, int length, boolean flag) {

    //wylosowanie losowych stringow dla tablicy t1
    for (int i = 0; i < length; ++i) {

      //ustalenie losowej dlugosci stringa z przedzialu [5,20]
      int stringLength = rand.nextInt(this.range[1]) + this.range[0];
      String readyString;

      //flaga decyduje czy wykonac ta czesc kodu (wykonac tylko dla tablicy t3)
      if (flag) {
        int count = 0;
        do {

          //wylosowanie znakow dla danego stringa o dlugosci stringLength
          readyString = createString(rand, stringLength);

          //tutaj sprawdzamy czy ten wygenerowany lancuch powtarza sie z jakimkolwiek lancuchem z tablicy t1,
          //jesli tak to powtorz kod powyzej, jesli nie to wyjdz z petli
          for (String x : this.t1) {
            if (x.equals(readyString)) {
              ++count;
            }
          }

          //lancuch nie powtorzyl sie, czyli count==0 -> zakoncz petle while
        } while (count != 0);
      } else {
        readyString = createString(rand, stringLength);
      }

      //wstawienie elementu
      arr[i] = readyString;
    }
  }

  /**
   * Funkcja sklejaca losowe znaki w lancuch
   * @param rand obiekty typu Random
   * @param stringLength dlugosc lancucha
   * @return gotowy losowy lancuch
   */
  private String createString(Random rand, int stringLength) {
    StringBuilder buffer = new StringBuilder(stringLength);
    for (int j = 0; j < stringLength; j++) {
      int limitedInt = this.leftBound + (int)
          (rand.nextFloat() * (this.rightBound - this.leftBound + 1));
      buffer.append((char) limitedInt);
    }
    return buffer.toString();
  }

}
