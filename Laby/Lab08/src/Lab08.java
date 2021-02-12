import java.util.Scanner;
import java.util.Random;
import java.lang.Exception;

interface Checkstep {

  /**
   * Sprawdza czy dany ruch jest mozliwy, czyli czy pole, w ktore gracz chce przejsc nie jest
   * oznaczone znakiem 'X' lub nie jest koncem planszy
   *
   * @param tab plansza gry
   * @param i0  indeks obecnego wiersza gracza
   * @param j0  indeks obecnej kolumny gracza
   * @return true albo false
   */
  boolean test(char[][] tab, int i0, int j0);
}

public class Lab08 {

  /**
   * liczba wierszy i kolumn planszy
   */
  private final int nx;

  /**
   * tablica znakow reprezentujaca plansze
   */
  private final char[][] board;

  /**
   * wspolrzedne gracza
   */
  private int player_x;
  private int player_y;


  /**
   * Konstruktor gry
   * @param nx rozmiar planszy (nx x nx)
   */
  Lab08(int nx) {

    //ustawienie rozmiaru planszy i alokacja dla planszy
    this.nx = nx;
    board = new char[nx][nx];

    //ustawienie poczatkowych wspolrzednych dla gracza - przedostatni wiersz i "druga"
    //kolumna zeby umiescic go w lewym dolnym rogu planszy
    player_x = nx - 2;
    player_y = 1;

    //wypelnienie planszy
    setBoard();
  }

  /**
   * ustawienie wartosci poczatkowych planszy,
   * tutaj uzyte prawdopodobienstwo z jakim wstawiamy znak 'X' na planszy
   */
  public void setBoard() {

    for (int i = 0; i < nx; ++i) {
      for (int j = 0; j < nx; ++j) {
        double prob = 0.3;
        if (new Random().nextDouble() < prob) {
          board[i][j] = 'X';
        } else {
          board[i][j] = ' ';
        }
      }
    }

    // ustawienie poczatkowej pozycji gracza
    board[player_x][player_y] = 'o';
  }

  /**
   * Realizuje przejscie po planszy jesli jest to mozliwe,
   * jesli nie - wyrzuca wyjatki o niemoznosci wykonania danego ruchu
   *
   * @param dir   kierunek w ktorym gracz ma sie poruszyc
   * @param check wyrazenie lambda sprawdzajace czy taki ruch jest mozliwy
   */
  public void step(Direction dir, Checkstep check) {

    // jesli jest to mozliwe - wykonaj ruch
    try {
      if (check.test(board, player_x, player_y)) {
        board[player_x][player_y] = ' ';
        player_x += dir.getX();
        player_y += dir.getY();
        board[player_x][player_y] = 'o';
      }
      // jesli sciana 'X' - wyrzuc wyjatek WallException
      else if (board[player_x + dir.getX()][player_y] == 'X'
          || board[player_x][player_y + dir.getY()] == 'X') {
        throw new WallException("BLAD: Nie mozna przechodzic przez sciany.");
      }
      //obsluga wyjatku spowodowanego wyjsciem poza tablice board
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("BLAD: Nie mozna wyjsc poza plansze.");
    } catch (WallException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("BLAD: Niespodziewany wyjatek!");
    }
  }

  /**
   * Wyswietla tablice po kazdej iteracji glownej petli
   */
  public void printBoard() {
    for (int i = 0; i < nx; ++i) {
      for (int j = 0; j < nx; ++j) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * Glowna funkcja programu
   */
  public static void main(String[] args) {

    //pomocnicze zmienne dla faktycznego obiektu gry, inputu, rozmiaru planszy
    Lab08 Game;
    Scanner sc;
    int nx;
    String test;
    char z;

    //flaga do przerwania petli
    boolean flag = false;

    //pierwsza petla do pobrania odpowiedniego rozmiaru planszy
    while (true) {

      //poczatkowy komunikat
      System.out.println("\n> Podaj liczbe calkowita wieksza od 1:");

      //Scanner przyjmuajcy input od uzytkownika
      sc = new Scanner(System.in);
      test = sc.nextLine();

      try {
        nx = Integer.parseInt(test);
        if (nx < 2) {
          System.out.println("BLAD: Zbyt mala wartosc nx: " + nx + "!");
          continue;
        }
      } catch (NumberFormatException e) {
        System.out.println("BLAD: Podaj liczbe calkowita!");
        continue;
      }
      break;
    }

    //jesli input jest dobry zainicjalizuj gre
    Game = new Lab08(nx);

    //wypisanie dostepnych opcji - przed petla bo tylko raz wypisujemy
    System.out.println("\nWszystkie opcje:");
    for (Option c : Option.values()) {
      System.out.println(c);
    }
    System.out.println("\n");

    //druga nieskonczona petla gry (az do nacisniecia klawisza 'q')
    while (true) {

      //wyswietlenie planszy
      Game.printBoard();

      //przerwanie petli jesli flaga jest ustawiona
      if (flag) {
        break;
      }

      System.out.print("Wybierz jedna z opcji: ");

      // pobieramy znak od uzytkownika reprezentujacy ruch
      z = sc.next().charAt(0);

      try {
        switch (z) {
          case 'q':
            flag = true;
            System.out.println("Opusciles gre.");
            break;
          case 'w':
            Direction up = Direction.UP;
            Game.step(up,
                (board, player_x, player_y) -> (board[player_x + up.getX()][player_y])
                    == ' ');
            break;
          case 's':
            Direction down = Direction.DOWN;
            Game.step(down,
                (board, player_x, player_y) -> (board[player_x + down.getX()][player_y])
                    == ' ');
            break;
          case 'a':
            Direction left = Direction.LEFT;
            Game.step(left,
                (board, player_x, player_y) -> (board[player_x][player_y + left.getY()])
                    == ' ');
            break;
          case 'd':
            Direction right = Direction.RIGHT;
            Game.step(right,
                (board, player_x, player_y) -> (board[player_x][player_y + right.getY()])
                    == ' ');
            break;
          default:
            throw new OptionNotRecognizedException("BLAD: Nie ma takiego ruchu.");
        }
      } catch (OptionNotRecognizedException e) {
        System.out.println(e.getMessage());
      }
    }
    sc.close();
  }
}
