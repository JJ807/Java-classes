/**
 * Klasa bazowa dla wyjatkow stosu
 */
public class StackException extends Exception {

  private final String msg;

  public StackException(String text) {
    msg = text;
  }

  @Override
  public String getMessage() {
    return msg;
  }
}

/**
 * Klasa reprezentujaca wyjatek wyrzucany przy probie przepelnienia stosu
 */
class StackOverflowException extends StackException {

  StackOverflowException(String txt) {
    super(txt);
  }
}

/**
 * Klasa reprezentujaca wyjatek wyrzucany przy probie usuniecia elementu z pustego stosu
 */
class StackUnderflowException extends StackException {

  StackUnderflowException(String txt) {
    super(txt);
  }
}
