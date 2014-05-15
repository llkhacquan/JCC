package core.checker;

public class MyIndentToken extends Token
{
  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public MyIndentToken(int kind, String image)
  {
     this.kind = kind;
     this.image = image;
  }

  int realKind = IndentCheckerConstants.GT;

  /**
   * Returns a new Token object.
  */

  public static final Token newToken(int ofKind, String tokenImage)
  {
    return new MyIndentToken(ofKind, tokenImage);
  }
}
