package core.rules;

public enum Keywords {
	ABSTRACT("abstract"), ASSERT("assert"), BOOLEAN("boolean"), BREAK("break"), BYTE(
			"byte"), CASE("case"), CATCH("catch"), CHAR("char"), CLASS("class"), CONST(
			"const"), CONTINUE("continue"), _DEFAULT("default"), DO("do"), DOUBLE(
			"double"), ELSE("else"), ENUM("enum"), EXTENDS("extends"), FALSE(
			"false"), FINAL("final"), FINALLY("finally"), FLOAT("float"), FOR(
			"for"), GOTO("goto"), IF("if"), IMPLEMENTS("implements"), IMPORT(
			"import"), INSTANCEOF("instanceof"), INT("int"), INTERFACE(
			"interface"), LONG("long"), NATIVE("native"), NEW("new"), NULL(
			"null"), PACKAGE("package"), PRIVATE("private"), PROTECTED(
			"protected"), PUBLIC("public"), RETURN("return"), SHORT("short"), STATIC(
			"static"), STRICTFP("strictfp"), SUPER("super"), SWITCH("switch"), SYNCHRONIZED(
			"synchronized"), THIS("this"), THROW("throw"), THROWS("throws"), TRANSIENT(
			"transient"), TRUE("true"), TRY("try"), VOID("void"), VOLATILE(
			"volatile"), WHILE("while");

	public final String image;
	
	private Keywords(String s) {
		image = s;
	}
}
