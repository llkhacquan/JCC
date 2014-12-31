package core;

public enum CheckOptions {
	CHECK_TYPE_COMMENT(1), CHECK_TYPE_INDENT(2), CHECK_TYPE_NAMING(3), CHECK_TYPE_OTHER(
			4);

	public final int i;

	private CheckOptions(int k) {
		i = k;
	}

	public static CheckOptions getByI(int i) {
		for (CheckOptions o : CheckOptions.values()) {
			if (i == o.i)
				return o;
		}
		return null;
	}
}
