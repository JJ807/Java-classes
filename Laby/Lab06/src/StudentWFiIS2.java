public class StudentWFiIS2 implements StudentUSOS {
    private String[] przedmioty;
    private int rok;
    private Student stud;

    public StudentWFiIS2(String n, String s, int id, int rok, String p1, double lp1, String p2,
        double lp2,
        String p3, double lp3) {
        stud = new Student(n, s, id, lp1, lp2, lp3);
        this.rok = rok;
        przedmioty = new String[] {p1, p2 , p3};
    }

    public String toString() {
        return "[" + this.rok + "] "+ stud.toString();
    }

    public double srednia() {
        return stud.average();
    }

    public void listaPrzedmiotow() {
        for (int i = 0; i < przedmioty.length; ++i) {
            System.out.println("\t" + (i + 1) + ". " + przedmioty[i] + ": " + stud.getGrade(i));
        }
    }

}
