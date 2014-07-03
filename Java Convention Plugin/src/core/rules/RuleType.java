package core.rules;

import java.util.Vector;

import core.rules.implement.RuleCheckerImplement;
import sun.text.normalizer.RuleCharacterIterator;

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

	COMMENT_MustHaveJavaDoc("Javadoc comment should be used before %r %w"),

	COMMENT_MustHaveBlockComment("Block comment should be used before %r %w"),

	CLASS_MustHaveDefaultConstructor("Class '%w' must have default constructor"),

	WRAPLINE_NotAllowTooLongLine(
			"This line's length is larger than %r, it should be shorted"),

	NAMING_StartByLowerCase(
			"Name of %r should start by a lower case letter (%w)"),

	NAMING_StartByUpperCase(
			"Name of %r should start by an upper case letter (%w)"),

	WHILE_MustUseBracket("Brackets { } must be used in 'while' statement");

	public final static String RuleArg = "%r";
	public final static String WarningArg = "%w";
	/*
	 * This flag indicates at least one rules have this rule type
	 */
	private boolean loaded = false;

	public boolean isLoaded() {
		return loaded;
	}

	public void load() {
		loaded = true;
	}

	private final String message;

	/**
	 * %w(s) should be replaced by warning agrs %r(s) should be replaced by rule
	 * args
	 */
	public String getMessage(Vector<String> ruleArgs, boolean skipFirstArgs) {
		StringBuilder s = new StringBuilder(message);
		int i = 0;
		if (skipFirstArgs)
			i++;
		for (; i < ruleArgs.size(); i++) {
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