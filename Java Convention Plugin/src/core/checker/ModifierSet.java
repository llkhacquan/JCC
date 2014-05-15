package core.checker;

public final class ModifierSet {
	public static final int PUBLIC = 0x0001;
	public static final int PROTECTED = 0x0002;
	public static final int PRIVATE = 0x0004;
	public static final int ABSTRACT = 0x0008;
	public static final int STATIC = 0x0010;
	public static final int FINAL = 0x0020;
	public static final int SYNCHRONIZED = 0x0040;
	public static final int NATIVE = 0x0080;
	public static final int TRANSIENT = 0x0100;
	public static final int VOLATILE = 0x0200;
	public static final int STRICTFP = 0x1000;

	public static boolean isPublic(int modifiers) {
		return (modifiers & PUBLIC) != 0;
	}

	public static boolean isProtected(int modifiers) {
		return (modifiers & PROTECTED) != 0;
	}

	public static boolean isPrivate(int modifiers) {
		return (modifiers & PRIVATE) != 0;
	}

	public static boolean isStatic(int modifiers) {
		return (modifiers & STATIC) != 0;
	}

	public static boolean isAbstract(int modifiers) {
		return (modifiers & ABSTRACT) != 0;
	}

	public static boolean isFinal(int modifiers) {
		return (modifiers & FINAL) != 0;
	}

	public static boolean isNative(int modifiers) {
		return (modifiers & NATIVE) != 0;
	}

	public static boolean isStrictfp(int modifiers) {
		return (modifiers & STRICTFP) != 0;
	}

	public static boolean isSynchronized(int modifiers) {
		return (modifiers & SYNCHRONIZED) != 0;
	}

	public static boolean isTransient(int modifiers) {
		return (modifiers & TRANSIENT) != 0;
	}

	public static boolean isVolatile(int modifiers) {
		return (modifiers & VOLATILE) != 0;
	}

	public static int removeModifier(int modifiers, int mod) {
		return modifiers & ~mod;
	}
}