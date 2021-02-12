import java.math.BigInteger;
import java.util.Random;

//klasa jest final i pole num rowniez final w imie zasad tworzenia "immutable objects"
final class BigInt implements Cloneable {

  final private byte[] num;

  public BigInt(byte[] tab) {
    this.num = tab.clone();
  }

  public BigInt(String liczba) {
    this.num = new BigInteger(liczba).toByteArray();
  }

  public BigInt(BigInt obj) {
    this.num = obj.num.clone();
  }

  public BigInt clone() throws CloneNotSupportedException {
    return (BigInt) super.clone();
  }

  public String toString() {
    return new BigInteger(this.num).toString();
  }


  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() == BigInt.class) {
      BigInt objCopy = (BigInt) obj;
      return this.toString().equals(objCopy.toString());
    } else {
      return false;
    }
  }

  // byte to prymitywny typ - mozna zwrocic bezposrednio
  public byte[] getNum() {
    return this.num;
  }

  public BigInt add(BigInt obj) {
    BigInteger temp = new BigInteger(this.num);
    return new BigInt(temp.add(new BigInteger(obj.getNum())).toByteArray());
  }
}


public class Lab04 {

  public static void main(String[] args) {

    int i = 0;
    long a, b, sum;
    int counter = 0;
    Random rand = new Random();
    BigInt a2, b2, sum2;

    // 1000 testow
    System.out.println("Pierwsze 1000 testów:");
    while (i < 1000) {
      a = Math.abs(rand.nextLong());
      b = Math.abs(rand.nextLong());
      sum = a + b;
      if (sum < Long.MAX_VALUE && sum >= 0) {
        System.out.printf("\nIteracja nr %d: \n", i);
        System.out.println("Suma (long+long):\t\t\t" + a + " + " + b + " = " + sum);
        a2 = new BigInt(Long.toString(a));
        b2 = new BigInt("" + b);
        sum2 = new BigInt(a2.add(b2));
        System.out
            .println("Suma (BigInt+BigInt): " + a2 + " + " + b2 + " = " + sum2);
        if (sum2.equals(new BigInt(Long.toString(sum)))) {
          counter++;

        } else {
          System.out.printf("Test nr %d zakonczyl sie porazka, przerywam dzialanie petli..\n", i);
          break;
        }
        ++i;
      }
    }
    System.out.println("\n" + counter + " testow zakonczonych powodzeniem.");
    System.out.println((1000 - counter) + " testow zakonczonych porazka.");

    //test 1001.
    System.out.println("\nTest nr 1001:\n");
    a = 1L;
    b = 999999999999999999L;
    sum = a + b;
    System.out.println("Suma (long+long):\t\t\t" + a + " + " + b + " = " + sum);
    a2 = new BigInt(Long.toString(a));
    b2 = new BigInt("" + b);
    sum2 = a2.add(b2);
    System.out
        .println("Suma (BigInt+BigInt): " + a2 + " + " + b2 + " = " + sum2);
    if (sum2.equals(new BigInt(Long.toString(sum)))) {
      System.out.println("Test nr 1001 zakonczony powodzeniem.");
    } else {
      System.out.println("Test nr 1001 zakonczony porazka.");
    }

    //test 1002.
    sum = b + a;
    System.out.println("\nTest nr 1002:\n");
    System.out.println("Suma (long+long):\t\t\t" + b + " + " + a + " = " + sum);
    sum2 = b2.add(a2);
    System.out
        .println("Suma (BigInt+BigInt): " + b2 + " + " + a2 + " = " + sum2);
    if (sum2.equals(new BigInt(Long.toString(sum)))) {
      System.out.println("Test nr 1002 zakonczony powodzeniem.");
    } else {
      System.out.println("Test nr 1002 zakonczony porazka.");
    }

  }
}
