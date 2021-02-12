import java.math.BigInteger;

public class RSA {

  private BigInteger p, q, n, fi, e, d;

  public RSA(String p2, String q2) {
    this.p = new BigInteger(p2);  //p
    this.q = new BigInteger(q2);  //q
    n = p.multiply(q); //n=pq
    fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); //fi = (p-1)(q-1)
    e = new BigInteger("3"); //zaczniemy od 3 dla e (mala liczba i nieparzysta)

    //dopoki NWD(e,fi) !=1, szukamy e
    while (fi.gcd(e).intValue() != 1) {
      e = e.add(BigInteger.ONE);
    }
    System.out.println("e = :" + e);
    d = e.modInverse(fi);// d = e^-1 mod fi

  }

  public String[] szyfruj(String wiad) {
    String[] tab_liter = new String[wiad.length()];
    String litera;
    int tmp;
    for (int i = 0; i < wiad.length(); ++i) {
      System.out.println("litera: " + wiad.charAt(i));
      tmp = wiad.charAt(i);
      System.out.println("Po zmianie na liczbe: " + tmp);
      litera = new BigInteger("" + tmp).modPow(e, n).toString();
      tab_liter[i] = litera;
      System.out.println("Po zakryptowaniu: " + litera);
    }

    String na_wyjsciu = "Wiadomosc zaszyfrowana na wyjsciu: ";
    for (String x : tab_liter) {
      na_wyjsciu += x;
    }
    // c = m^e mod n
    return tab_liter;
  }

  public String rozszyfruj(String[] belkot) {
    String tab_liter = "";
    char litera;
    int tmp;
    for (int i = 0; i < belkot.length; ++i) {
      System.out.println("String: " + belkot[i]);
      tmp = Integer.parseInt(belkot[i]);
      System.out.println("Po zmianie na liczbe: " + tmp);
      litera = (char) new BigInteger("" + tmp).modPow(d, n).intValue();
      tab_liter += litera;
      System.out.println("Po rozszyfrowaniu: " + litera);
    }

    System.out.println("Wiadomosc rozszyfrowana na wyjsciu: " + tab_liter);
    return tab_liter;
  }


  public static void main(String[] args) {
    RSA obj = new RSA("397", "103");

    String[] zaszyfrowane = obj.szyfruj("wiadomosc");

    String polaczenie = "";
    for (String x : zaszyfrowane) {
      polaczenie += x;
    }
    System.out.println("Zaszyfrowana wiadomosc w mainie: " + polaczenie);

    String rozszyfrowane = obj.rozszyfruj(zaszyfrowane);
    System.out.println("Rozszyfrowana wiadomosc w mainie: " + rozszyfrowane);

  }
}
