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
	INDENT_END_LINE_COMMENT ("Should have new line after comment"),
	
	COMMENT_BEGIN ("beginning of the file"),
	COMMENT_CLASS ("Class"),
	COMMENT_INTERFACE ("Interface"),
	COMMENT_ENUM ("Enum"),
	COMMENT_FIELD ("Field"),
	COMMENT_VARIABLE ("Variable"),
	COMMENT_CONSTANT ("Constant"),
	COMMENT_METHOD ("Method"),
	COMMENT_CONSTRUCTOR ("Constructor"),
	
	NAMING_CLASS ("Class"),
	NAMING_INTERFACE ("Interface"),
	NAMING_ENUM ("Enum"),
	NAMING_FIELD ("Field"),
	NAMING_VARIABLE ("Variable"),
	NAMING_CONSTANT ("Constant"),
	NAMING_METHOD ("Method");
	
	final public String content;
	private WarningSpecify(String s){
		content = s;
	}
	
	public String toString(){
		return content;
		
	}
}