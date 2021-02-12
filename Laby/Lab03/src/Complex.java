public class Complex {

  private double re;
  private double im;

  static public String ZERO = "0.0";
  static public String ONE = "1.0";
  static public String I = "1.0i";

  Complex(double re, double im) {
    {
      this.re = 0.0;
      this.im = 0.0;
    }
    this.re = re;
    this.im = im;
  }

  Complex(double re) {
    this(re, 0.0);
  }

  Complex() {
    this(0.0, 0.0);
  }

  public static Complex multiply(Complex obj, Complex obj2) {
    //(ax + b) * (cx + d) = re: (b*d - a*c), im:(a*d + b*cx)
    double temp_re = obj.getRe() * obj2.getRe() - obj.getIm() * obj2.getIm();
    double temp_im = obj.getIm() * obj2.getRe() + obj.getRe() * obj2.getIm();
    return new Complex(temp_re, temp_im);
  }

  public static Complex multiply(Complex obj, double re) {
    return new Complex(obj.getRe() * re, obj.getIm() * re);
  }

  public static Complex divide(Complex obj, Complex obj2) {
    Complex temp = new Complex(obj2.getRe(), -obj2.getIm());
    Complex wynik = multiply(obj, temp);
    double mian = Math.pow(obj2.getRe(), 2) + Math.pow(obj2.getIm(), 2);
    wynik.re = wynik.getRe() / mian;
    wynik.im = wynik.getIm() / mian;
    return wynik;
  }

  public double mod() {
    return Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im, 2));
  }

  public static Complex sqrt(double x) {
    if (x > 0) {
      return new Complex(Math.sqrt(x));
    } else {
      return new Complex(0.0, Math.sqrt(Math.abs(x)));
    }
  }

  public boolean equals(Object nr) {
    return (nr.getClass() == Complex.class);
  }

  public boolean equals(Complex nr) {
    return ((this.re == nr.getRe()) && (this.im == nr.getIm()));
  }

  public static Complex add(Complex obj, Complex obj2) {
    return new Complex(obj2.getRe() + obj.getRe(), obj2.getIm() + obj.getIm());
  }

  public static Complex subtract(Complex first, Complex second) {
    return new Complex(first.getRe() - second.getRe(), first.getIm() - second.getIm());
  }


  public double getRe() {
    return re;
  }

  public double getIm() {
    return im;
  }

  public void conjugate() {
    im = -im;
  }

  public void opposite() {
    re = -re;
    im = -im;
  }

  public String toString() {
    if (re == 0.0 && im == 0.0) {
      return "" + 0.0;
    } else if (im == 0.0) {
      return "" + this.re;
    } else if (re == 0.0) {
      return "" + this.im + "i";
    } else if (im < 0.0) {
      return re + " - " + (-im) + "i";
    }
    return "" + this.re + " + " + this.im + "i";
  }

}