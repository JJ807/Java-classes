import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Glowna klasa programu
 */
public class Lab11 {

  //listy przechowujace pary: [nazwa kolekcji, czas wykonania]
  private static final ArrayList<AbstractMap.SimpleEntry<String, Double>> generate = new ArrayList<>();
  private static final ArrayList<AbstractMap.SimpleEntry<String, Double>> remove = new ArrayList<>();
  private static final ArrayList<AbstractMap.SimpleEntry<String, Double>> search = new ArrayList<>();
  private static final ArrayList<AbstractMap.SimpleEntry<String, Double>> searchNOT = new ArrayList<>();
  private static final ArrayList<AbstractMap.SimpleEntry<String, Double>> arrLoops = new ArrayList<>();
  private static final ArrayList<AbstractMap.SimpleEntry<String, Double>> linkLoops = new ArrayList<>();

  public static void countForMap(ArrayOfRandomStrings arr, AbstractMap<String, Integer> map) {

    //1. wypelnienie - funkcja put()
    long t0 = System.nanoTime();
    for (int i = 0; i < arr.t1.length; ++i) {
      map.put(arr.t1[i], i);
    }
    long t1 = System.nanoTime() - t0;

    //2. wyszukanie w kolekcji elementow tablicy t2 - funkcja containsKey()
    long t2 = System.nanoTime();
    for (String x : arr.t2) {
      map.containsKey(x);
    }

    long t3 = System.nanoTime() - t2;

    //3. wyszukanie w kolekcji elementow tablicy t3 (nie ma tam zadnego)
    long t4 = System.nanoTime();
    for (String x : arr.t3) {
      map.containsKey(x);
    }
    long t5 = System.nanoTime() - t4;

    //4. usuniecie z kolekcji elementow naraz - funkcja clear()
    long t6 = System.nanoTime();
    map.clear();
    long t7 = System.nanoTime() - t6;

    // wstawienie par [klucz : wartosc]
    //dzielimy przez 1e6 zeby z nano otrzymac mili
    if (map instanceof TreeMap) {

      String className = map.getClass().getSimpleName();

      generate.add(new SimpleEntry<>(className, ((double) t1) / 1e6));
      search.add(new SimpleEntry<>(className, ((double) t3) / 1e6));
      searchNOT.add(new SimpleEntry<>(className, ((double) t5) / 1e6));
      remove.add(new SimpleEntry<>(className, ((double) t7) / 1e6));

    } else if (map instanceof HashMap) {

      String className = map.getClass().getSimpleName();

      generate.add(new SimpleEntry<>(className, ((double) t1) / 1e6));
      search.add(new SimpleEntry<>(className, ((double) t3) / 1e6));
      searchNOT.add(new SimpleEntry<>(className, ((double) t5) / 1e6));
      remove.add(new SimpleEntry<>(className, ((double) t7) / 1e6));

    } else {
      System.out.println("Nieznany typ");
    }
  }

  public static void countForList(ArrayOfRandomStrings arr, AbstractList<String> list) {

    //1. wypelnienie - funkcja add()
    long t0 = System.nanoTime();
    for (int i = 0; i < arr.t1.length; ++i) {
      list.add(arr.t1[i]);
    }
    long t1 = System.nanoTime() - t0;

    //2. wyszukanie w kolekcji elementow tablicy t2 - funkcja contains()
    long t2 = System.nanoTime();
    for (String x : arr.t2) {
      list.contains(x);
    }
    long t3 = System.nanoTime() - t2;

    //3. wyszukanie w kolekcji elementow tablicy t3 (nie ma tam zadnego)
    long t4 = System.nanoTime();
    for (String x : arr.t3) {
      list.contains(x);
    }
    long t5 = System.nanoTime() - t4;

    //Dodatkowe dla list - 3 petle:
    //1. petla for, metody get() i size()
    long td1 = System.nanoTime();
    for (int i = 0; i < list.size(); ++i) {
      list.get(i);
    }
    long td2 = System.nanoTime() - td1;

    //2. petla foreach
    long td3 = System.nanoTime();
    //list.forEach(i -> list.get(list.indexOf(i)));
    list.forEach((e) -> {});
    long td4 = System.nanoTime() - td3;

    //3. petla z iteratorem
    long td5 = System.nanoTime();
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
      it.next();
    }
    long td6 = System.nanoTime() - td5;

    //4. usuniecie z kolekcji elementow naraz
    long t6 = System.nanoTime();
    list.clear();
    long t7 = System.nanoTime() - t6;

    // wstawienie par [klucz : wartosc]
    if (list instanceof LinkedList) {

      String className = list.getClass().getSimpleName();

      generate.add(new SimpleEntry<>(className, ((double) t1) / 1e6));
      search.add(new SimpleEntry<>(className, ((double) t3) / 1e6));
      searchNOT.add(new SimpleEntry<>(className, ((double) t5) / 1e6));
      remove.add(new SimpleEntry<>(className, ((double) t7) / 1e6));
      linkLoops.add(new SimpleEntry<>("for", ((double) td2) / 1e6));
      linkLoops.add(new SimpleEntry<>("for-each", ((double) td4) / 1e6));
      linkLoops.add(new SimpleEntry<>("iterator", ((double) td6) / 1e6));

    } else if (list instanceof ArrayList) {

      String className = list.getClass().getSimpleName();

      generate.add(new SimpleEntry<>(className, ((double) t1) / 1e6));
      search.add(new SimpleEntry<>(className, ((double) t3) / 1e6));
      searchNOT.add(new SimpleEntry<>(className, ((double) t5) / 1e6));
      remove.add(new SimpleEntry<>(className, ((double) t7) / 1e6));
      arrLoops.add(new SimpleEntry<>("for", ((double) td2) / 1e6));
      arrLoops.add(new SimpleEntry<>("for-each", ((double) td4) / 1e6));
      arrLoops.add(new SimpleEntry<>("iterator", ((double) td6) / 1e6));
    } else {
      System.out.println("Nieznany typ");
    }

  }

  public static void main(String[] args) {

    //ustawienie rozmiarow tablic
    int n = 0, m = 0;
    try {
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);

    } catch (NumberFormatException e) {
      System.err.println("Nie przekazano 2 liczb calkowitych w argumentach wywolania programu.");
      System.exit(-1);
    }
    catch(ArrayIndexOutOfBoundsException e){
      System.err.println("Nie podano argumentów wywolania programu.");
    }

    ArrayOfRandomStrings obj = new ArrayOfRandomStrings(n, m);

    //4 puste typy danych
    ArrayList<String> arrList = new ArrayList<>();
    LinkedList<String> linkList = new LinkedList<>();
    TreeMap<String, Integer> tmap = new TreeMap<>();
    HashMap<String, Integer> hmap = new HashMap<>();

    //wszystkie 4 operacje dla map
    countForMap(obj, tmap);
    countForMap(obj, hmap);

    //wszystkie 4 operacje dla list
    countForList(obj, arrList);
    countForList(obj, linkList);

    //wypisanie wynikow

    System.out.println("Generate:");
    for (var x : generate) {
      System.out.println("\t" + x.getKey() + "(" + x.getValue() + " ms)");
    }

    //liczba m odpowiada poczatkowym rozmiarom tablicy
    System.out.println(
        "\nPoczatek, rozmiary: " + m + ", " + m + ", " + m + ", "
            + m + "\n");

    System.out.println("Search:");
    for (var x : search) {
      System.out.println("\t" + x.getKey() + "(" + x.getValue() + " ms)");
    }

    System.out.println("SearchNOT:");
    for (var x : searchNOT) {
      System.out.println("\t" + x.getKey() + "(" + x.getValue() + " ms)");
    }

    System.out.println("ArrayList:");
    for (var x : arrLoops) {
      System.out.println("\t" + x.getKey() + "(" + x.getValue() + " ms)");
    }

    System.out.println("LinkedList:");
    for (var x : linkLoops) {
      System.out.println("\t" + x.getKey() + "(" + x.getValue() + " ms)");
    }

    System.out.println("Remove:");
    for (var x : remove) {
      System.out.println("\t" + x.getKey() + "(" + x.getValue() + " ms)");
    }

    System.out.println(
        "\nKoniec, rozmiary: " + arrList.size() + ", " + linkList.size() + ", " + tmap.size() + ", "
            + hmap.size() + "\n");
  }
}
