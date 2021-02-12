public class StudentWFiIS1 extends Student implements StudentUSOS {

  private String[] przedmioty;
  private int rok;


  public StudentWFiIS1(String n, String s, int id, int rok, String p1, double lp1, String p2,
      double lp2,
      String p3, double lp3) {
    super(n, s, id, lp1, lp2, lp3);
    this.rok = rok;
    przedmioty = new String[] {p1, p2 , p3};
  }

  public String toString() {
    return "[" + this.rok + "] " + super.toString();
  }

  public double srednia() {
    return super.average();
  }

  public void listaPrzedmiotow() {
    for (int i = 0; i < przedmioty.length; ++i) {
      System.out.println("\t" + (i + 1) + ". " + przedmioty[i] + ": " + super.getGrade(i));
    }
  }
}
