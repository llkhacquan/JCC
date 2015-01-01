package core.warning;

public enum WarningSpecify{
	INDENT_INDENT ("Indentation"),
	INDENT_NEW_LINE ("Should be go to a new line"),
	INDENT_NOT_NEW_LINE ("should not go to a new line"),
	INDENT_BLANK_LINE ("should have a blank line"),
	INDENT_NOT_BLANK_LINE ("should not have a blank line"),
	INDENT_END_SPACE ("Space at the end of a statement"),
	INDENT_SPACE ("Space between tokens"),
	INDENT_COMMENT_SPACE ("Space before comment"),
	INDENT_INNER_COMMNET ("Comment inside statement"),
	INDENT_SINGLE_LINE_COMMENT ("Comments should be in separated lines"),
	INDENT_END_LINE_COMMENT ("Should have new line after comment");

	final public String content;
	private WarningSpecify(String s){
		content = s;
	}
}