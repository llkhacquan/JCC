package core.checker;
public class MyNamingToken extends Token
{
  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public MyNamingToken(int kind, String image)
  {
     this.kind = kind;
     this.image = image;
  }

  int realKind = NamingCheckerConstants.GT;

  /**
   * Returns a new Token object.
  */

  public static final Token newToken(int ofKind, String tokenImage)
  {
    return new MyNamingToken(ofKind, tokenImage);
  }
}
