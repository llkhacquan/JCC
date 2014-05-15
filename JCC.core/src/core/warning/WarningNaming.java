package core.warning;

public class WarningNaming extends Warning {

	/*
	 * specify = "class"/"method"/"enum"/"field"/"variable"/... args[0] =
	 * "oldName" args[1] = "newName"
	 */

	public WarningNaming(Position p, WarningSpecify specify, String[] args) {
		super(p, WarningType.NAMING, specify, args);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("");
		s.append(specify + " '" + args[0] + "' should be named as '" + args[1]
				+ "'\n");
		s.append("By java convention: " + specify + " names should be ");
		if (specify == WarningSpecify.NAMING_CLASS
				|| specify == WarningSpecify.NAMING_ENUM
				|| specify == WarningSpecify.NAMING_INTERFACE) {
			s.append("noun, in mixed case with the first letter of each internal word capitalized.");
		} else if (specify == WarningSpecify.NAMING_FIELD
				|| specify == WarningSpecify.NAMING_VARIABLE) {
			s.append("nour, in mixed case with a lower case first letter. Internal words start with capital letters.");
		} else if (specify == WarningSpecify.NAMING_METHOD) {
			s.append("verb, in mixed case with the first letter lowercase, with the first letter of each internal word capitalized.");
		} else if (specify == WarningSpecify.NAMING_CONSTANT) {
			s.append("nour, in all upper case with words separated by underscores.");
		}
		return s.toString();
	}

}
