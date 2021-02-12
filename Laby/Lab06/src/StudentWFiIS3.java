public class StudentWFiIS3 extends Student {

  private StudentUSOS stud;

  public StudentWFiIS3(String n, String s, int id, int rok, String p1, double lp1, String p2,
      double lp2,
      String p3, double lp3) {
    super(n, s, id, lp1, lp2, lp3);

    stud = new StudentUSOS() {

      @Override
      public double srednia() {
        return StudentWFiIS3.super.average();
      }

      @Override
      public void listaPrzedmiotow() {
        String[] przedmioty = new String[] {p1, p2 , p3};
        for (int i = 0; i < przedmioty.length; ++i) {
          System.out.println(
              "\t" + (i + 1) + ". " + przedmioty[i] + ": " + StudentWFiIS3.super.getGrade(i));
        }
      }

      @Override
      public String toString() {
        return "[" + rok + "] " + StudentWFiIS3.super.toString();
      }
    };

  }

  public double srednia() {
    return stud.srednia();
  }

  public void listaPrzedmiotow() {
    stud.listaPrzedmiotow();
  }

  public String toString() {
    return stud.toString();
  }

}

