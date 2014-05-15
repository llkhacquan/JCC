package core.checker;

public final class NameChecking {
	public enum JavaElement {
		COMPILATION_UNIT, PACKAGE,

		CLASS, INTERFACE, ENUM,

		METHOD, FIELD,

		VARIABLE,
	}

	public static boolean isGoodClassName(String s) {
		return s.compareTo(transformToGoodClassName(s)) == 0;
	}

	public static String transformToGoodClassName(String s) {
		StringBuffer result = new StringBuffer(s);
		/* This while handle the _ character in names */
		int i;
		while ((i = result.indexOf("_")) >= 0) {
			result.delete(i, i + 1);
			if (result.length() >= i + 1)
				result.setCharAt(i, Character.toUpperCase(result.charAt(i)));
		}
		result.setCharAt(0, Character.toUpperCase(result.charAt(0)));
		return result.toString();
	}

	public static boolean isGoodVariableName(String s) {
		return s.compareTo(transformToGoodVariableName(s)) == 0;
	}

	public static String transformToGoodVariableName(String s) {
		StringBuffer result = new StringBuffer(s);
		/* This while handle the _ character in names */
		int i;
		while ((i = result.indexOf("_")) >= 0) {
			result.delete(i, i + 1);
			if (result.length() >= i + 1)
				result.setCharAt(i, Character.toUpperCase(result.charAt(i)));
		}
		result.setCharAt(0, Character.toLowerCase(result.charAt(0)));
		return result.toString();
	}

	public static boolean isGoodConstantName(String s) {
		return s.compareTo(transformToGoodConstantName(s)) == 0;
	}

	public static String transformToGoodConstantName(String s) {
		return s.toUpperCase();
	}
}