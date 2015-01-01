package core.warning;

public class WarningIndent extends Warning {


	public WarningIndent(Position pos, WarningSpecify specify, String[] args) {
		super(pos, specify, args);
	}

	@Override
	public String toString() {
		String result;
		if (specify == WarningSpecify.INDENT_SINGLE_LINE_COMMENT) {
			result = "Comments should be in separated lines";
			return result;
		}
		if (specify == WarningSpecify.INDENT_INDENT) {
			result = "Not good indentation.";
			if (args[0] == "false") {
				result += " The indent should be " + args[1] + " tab(s)";
				return result;
			}
		}
		if (specify == WarningSpecify.INDENT_END_LINE_COMMENT) {
			result = "A new line should be placed after a comment";
			return result;
		}
		if (specify == WarningSpecify.INDENT_END_SPACE) {
			result = "Space shoud not be places at the end of line";
			return result;
		}
		if (specify == WarningSpecify.INDENT_COMMENT_SPACE) {
			if (Integer.valueOf(args[0]) == Integer.valueOf(1)) {
				result = "One space shoud be placed before comment";
			} else {
				result = "Space(s) shoud not be placed  before comment";
			}
			return result;
		}
		if (specify == WarningSpecify.INDENT_NEW_LINE) {
			result = "A return character should be placed in the end of the line";
			return result;
		}
		if (specify == WarningSpecify.INDENT_NOT_NEW_LINE) {
			result = "A return character should not be placed in the end of the line";
			return result;
		}
		if (specify == WarningSpecify.INDENT_BLANK_LINE) {
			result = "A blank line should be placed after this line";
			return result;
		}
		if (specify == WarningSpecify.INDENT_NOT_BLANK_LINE) {
			result = "A blank line should not be placed here";
			return result;
		}
		if (specify == WarningSpecify.INDENT_INNER_COMMNET) {
			result = "Comment should not be inside statement";
			return result;
		}
		if (specify == WarningSpecify.INDENT_SPACE) {

			result = "Number of spaces should be " + args[0] + " after '"
					+ args[1] + "'";
			return result;
		}

		return "";
	}
}