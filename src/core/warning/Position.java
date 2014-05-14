package core.warning;

import core.checker.Token;

public class Position {
	/**
	 * The lines or columns
	 */
	public final int beginLine, beginColumn, endLine, endColumn;
	/**
	 * Type of openable item that includes this Postion For example: class,
	 * method <em>type</em> maybe equals to null
	 */
	public final String type;
	/**
	 * Name of the type. <em>name</em> maybe equals to null
	 */
	public final String name;

	public Position(Position p, String type, String name) {
		beginLine = p.beginLine;
		endLine = p.endLine;
		beginColumn = p.beginColumn;
		endColumn = p.endColumn;
		this.type = type;
		this.name = name;
	}

	public Position(Token t, String type, String name) {
		beginLine = t.beginLine;
		endLine = t.endLine;
		beginColumn = t.beginColumn;
		endColumn = t.endColumn;
		this.type = type;
		this.name = name;
	}

	public Position(int bLine, int eLine, int bCol, int eCol) {
		beginLine = bLine;
		endLine = eLine;
		beginColumn = bCol;
		endColumn = eCol;
		this.type = null;
		this.name = null;
	}

	public Position(Token t) {
		this(t, null, null);
	}
}
