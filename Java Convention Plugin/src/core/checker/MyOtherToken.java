package core.checker;

public class MyOtherToken extends Token
{
  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public MyOtherToken(int kind, String image)
  {
     this.kind = kind;
     this.image = image;
  }

  int realKind = OtherCheckerConstants.GT;

  /**
   * Returns a new Token object.
  */

  public static final Token newToken(int ofKind, String tokenImage)
  {
    return new MyOtherToken(ofKind, tokenImage);
  }
}
