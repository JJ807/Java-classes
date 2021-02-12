
public class WallException extends Exception {

  String lancuch;

  WallException(String lancuch) {
    this.lancuch = lancuch;
  }

  @Override
  public String getMessage() {
    return lancuch;
  }
}
