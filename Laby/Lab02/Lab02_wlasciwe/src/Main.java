import java.math.BigInteger;

public class Main {

  private BigInteger p, q, n, fi, e, d;

  public Main(String p2, String q2) // konstruktor
  {
    p = new BigInteger(p2); // p
    q = new BigInteger(q2); // q
    n = p.multiply(q); // n=p*q
    // fi = (p-1)(q-1)
    fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    e = new BigInteger("3"); // początkowo 3 jako mała i nieparzysta liczba

    // dopoki NWD(e,fi) != 1, szukamy e
    while (fi.gcd(e).intValue() != 1) {
      e = e.add(BigInteger.ONE); // dodajemy jeden az znajdziemy NWD
    }

    System.out.printf("Klucz publiczny: (e, n) = %d, %d\n",e,n);


    // d = e^-1 mod fi
    d = e.modInverse(fi);
    System.out.printf("Klucz prywatny: (d, n) = %d, %d\n",d,n);

  }

  // metoda szyfrujaca przekaza wiadomosc w formie Stringa zgodnie z RSA
  public String szyfruj(String wiad) {
    System.out.println("\n######## SZYFROWANIE #########\n\nPrzekazana wiadomosc: " + wiad + "\n");
    String litery="";
    char litera;
    int ascii;
    for (int i = 0; i < wiad.length(); ++i) {
      System.out.print("Litera: " + wiad.charAt(i));
      ascii = wiad.charAt(i); // castowanie chara na inta
      System.out.print(", ta litera w ascii: " + ascii);
      litera = (char) new BigInteger("" + ascii).modPow(e, n).intValue(); // t^e mod n
      litery += litera;
      System.out.println(", po zaszyfrowaniu: " + litera);
    }
    System.out.println("");
    return litery;
  }

  // metoda rozszyfrowujaca wiadomosc
  public String rozszyfruj(String belkot) {
    System.out.println("\n######## DESZYFROWANIE #########\n\nOtrzymana wiadomosc:" + belkot + "\n");
    String tab_liter = "";
    char litera;
    int ascii;
    for (int i = 0; i < belkot.length(); ++i) {
      System.out.print("Litera zaszyfrowana: " + belkot.charAt(i));
      ascii = belkot.charAt(i);
      System.out.print(", po zmianie na liczbe: " + ascii);
      litera = (char) new BigInteger("" + ascii).modPow(d, n).intValue(); // dekodowanie
      tab_liter += litera;
      System.out.println(", po rozszyfrowaniu: " + litera);
    }
    System.out.println("");
    return tab_liter;
  }

  public static void main(String[] args) {
    Main RSA = new Main("397", "103");
    // p i q

    String zaszyfrowane = RSA.szyfruj("test");
    System.out.println("Zaszyfrowana wiadomosc: " + zaszyfrowane);

    String rozszyfrowane = RSA.rozszyfruj(zaszyfrowane);
    System.out.println("Rozszyfrowana wiadomosc: " + rozszyfrowane);
  }
}