import java.util.Scanner;
import java.util.Random;

interface Checkstep {

  /**
   * Sprawdza czy dany ruch jest mozliwy, czyli czy pole, w ktore gracz chce przejsc nie jest
   * oznaczone znakiem 'X'
   *
   * @param tab plansza gry
   * @param i0  indeks obecnego wiersza gracza
   * @param j0  indeks obecnej kolumny gracza
   * @return true albo false
   */
  boolean test(char[][] tab, int i0, int j0);
}


public class Lab7 {

  /**
   * prawdopodobienstwo z jakim wstawiamy 'X'
   */
  private final double prob;
  /**
   * liczba wierszy planszy
   */
  private final int nx;
  /**
   * liczba kolumn planszy
   */
  private final int ny;
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
   * wspolrzedne wyjscia z labiryntu
   */
  private final int win_x;
  private final int win_y;


  /**
   * Konstruktor
   *
   * @param nx   liczba wierszy planszy
   * @param ny   liczba kolumn planszy
   * @param prob prawdopodobienstwo z jakim wstawiamy w dane pole znak 'X'
   */
  Lab7(int nx, int ny, double prob) {

    //ustawienie rozmiaru planszy, prawdopodobienstwa i alokacja dla planszy
    this.nx = nx;
    this.ny = ny;
    this.prob = prob;
    board = new char[nx][ny];

    //ustawienie poczatkowych wspolrzednych dla gracza - przedostatni wiersz i "druga"
    //kolumna zeby umiescic go w lewym dolnym rogu planszy
    player_x = nx - 2;
    player_y = 1;

    //ustawienie miejsca wygranej - srodek gornej krawedzi planszy
    win_x = 0;
    win_y = ny / 2;

    //wypelnienie planszy
    setBoard();
  }

  /**
   * ustawienie wartosci poczatkowych planszy tutaj uzyte prawdopodobienstwo z jakim wstawiamy znak
   * 'X' na planszy
   */
  public void setBoard() {

    for (int i = 0; i < nx; ++i) {
      for (int j = 0; j < ny; ++j) {
        if (i == 0 || j == 0 || i == nx - 1 || j == ny - 1) {
          board[i][j] = 'X';
        } else if (new Random().nextDouble() < prob) {
          board[i][j] = 'X';
        } else {
          board[i][j] = ' ';
        }
      }
    }

    //ustawienie wyjscia z labiryntu
    board[0][ny / 2] = ' ';

    // ustawienie poczatkowej pozycji gracza
    board[player_x][player_y] = 'o';
  }

  /**
   * Resetuje pozycje gracza - potrzebne dla opcji RESET
   */
  public void resetUserPos() {
    player_x = nx - 2;
    player_y = 1;
  }

  /**
   * Realizuje przejscie po planszy jesli jest to mozliwe, jesli nie - wyswietla komunikat o
   * niemoznosci wykonania takiego ruchu
   *
   * @param dir   kierunek w ktorym gracz ma sie poruszyc
   * @param check wyrazenie lambda sprawdzajace czy taki ruch jest mozliwy
   */
  public void step(Direction dir, Checkstep check) {
    if (check.test(board, player_x, player_y)) {
      board[player_x][player_y] = ' ';
      player_x += dir.getX();
      player_y += dir.getY();
      board[player_x][player_y] = 'o';
    } else {
      System.out.println("NIE UDALO SIE WYKONAC TAKIEGO RUCHU.");
    }
  }

  /**
   * Wyswietla tablice
   */
  public void printBoard() {
    for (int i = 0; i < nx; ++i) {
      for (int j = 0; j < ny; ++j) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * glowna funkcja programu
   *
   * @param args argumenty wywolania czyli rozmiar planszy (int, int) i prawdopodobienstwo
   */
  public static void main(String[] args) {

    //inicjalizacja planszy
    int nx = 0, ny = 0;
    double prob = 0.0;

    //sprawdzenie czy argumenty wywolania programu sa poprawne
    if (args.length > 0) {
      try {
        nx = Integer.parseInt(args[0]);
        ny = Integer.parseInt(args[1]);
        prob = Double.parseDouble(args[2]);
        if (nx < 3 || ny < 3 || prob > 1) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        System.err.println("Argumentami musza byc: integer >=3, integer >=3 i double <=1.");
        System.exit(1);
      }
    }

    //wypisanie dostepnych opcji - przed petla bo tylko raz wypisujemy
    System.out.println("Wszystkie opcje:");
    for (Option c : Option.values()) {
      System.out.println(c);
    }

    //wlasciwa gra - inicjalizacja planszy i prawdopodobienstwa
    Lab7 Game = new Lab7(nx, ny, prob);

    //Scanner przyjmuajcy input od uzytkownika
    Scanner sc = new Scanner(System.in);
    char z;

    //flaga do przerwania petli
    boolean flag = false;

    while (true) {

      //wyswietlenie planszy po kazdym ruchu
      Game.printBoard();

      //sprawdzenie czy gracz przeszedl labirynt
      if (Game.player_x == Game.win_x && Game.player_y == Game.win_y) {
        System.out.println("Brawo, przeszedles gre!");
        flag = true;
      }

      //przerwanie petli jesli flaga jest ustawiona
      if (flag) {
        break;
      }

      System.out.print("Wybierz jedna z opcji: ");
      z = sc.next().charAt(0);

      switch (z) {
        case 'q':
          flag = true;
          System.out.println("Opusciles gre.");
          break;
        case '0':
          Game.resetUserPos();
          Game.setBoard();
          System.out.println("Zresetowales plansze.");
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
          System.out.println("Nie ma takiej opcji.");
      }
    }
  }
}
