package core.checker;

public class MyCommentToken extends Token
{
  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public MyCommentToken(int kind, String image)
  {
     this.kind = kind;
     this.image = image;
  }

  int realKind = CommentCheckerConstants.GT;

  /**
   * Returns a new Token object.
  */

  public static final Token newToken(int ofKind, String tokenImage)
  {
    return new MyCommentToken(ofKind, tokenImage);
  }
}
