package core.rules;

import java.util.Vector;

public enum RuleType {
	IF_NotAllowElse("'else' is not allow"),

	IF_MustUseBracket("Brackets { } must be used in 'if' statement"),
	
	FOR_MustUseBracket("Brackets { } must be used in 'for' statement"),

	SWITCH_MustHaveDefaultBranch("'switch' must have default branch"),

	WHILE_MustUseTrueConstantCondition(
			"'while' loop must be used as 'while(true)' (use 'break' to exit loop)"),

	FILE_MustHavePackageDeclaration("File must have be packed in package"),

	FILE_MustHaveBlankLinesInEOF("File must have a blink line in the end"),

	GENERAL_MustUseAnnotation("Annotation should be used here"),

	COMMENT_MustHaveJavaDoc("Javadoc comment should be used here"),

	COMMENT_MustHaveBlockComment("Block comment should be used here"),

	CLASS_MustHaveDefaultConstructor("Class '%w' must have default constructor"),

	WRAPLINE_NotAllowTooLongLine(
			"This line's length is larger than %r, it should be shorted"),
	
	NAMING_(
			"Name of %r '%w' does not follow naming style, it should be '%w'");
	
	public final static String RuleArg = "%r";
	public final static String WarningArg = "%w";
	/*
	 * This flag indicates at least one rules have this rule type
	 */
	private boolean loaded = false;
	
	public boolean isLoaded(){
		return loaded;
	}
	
	public void load(){
		loaded = true;
	}
	
	private final String message;
	
	/**
	 * %w(s) should be replaced by warning agrs
	 * %r(s) should be replaced by rule args
	 */
	public String getMessage(Vector<String> ruleArgs){
		StringBuilder s = new StringBuilder(message);
		for (int i = 0; i < ruleArgs.size(); i++) {
			int index = s.indexOf(RuleArg);
			if (index < 0)
				break;
			s.replace(index, index + RuleArg.length(), ruleArgs.elementAt(i));
		}		
		return s.toString();
	}

	private RuleType(String s) {
		message = s;
	}
}