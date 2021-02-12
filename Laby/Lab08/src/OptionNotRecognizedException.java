public class OptionNotRecognizedException extends Exception {

  String lancuch;

  OptionNotRecognizedException(String lancuch) {
    this.lancuch = lancuch;
  }

  @Override
  public String getMessage() {
    return lancuch;
  }
}
