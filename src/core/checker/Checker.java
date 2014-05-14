package core.checker;

import java.io.InputStream;
import java.io.Reader;
import java.util.Vector;

import core.rules.RulesManager;
import core.warning.Warning;

public abstract class Checker {
	/**
	 * Start parsing
	 * 
	 * @throws ParseException
	 */
	public abstract void CompilationUnit() throws ParseException;

	/**
	 * Reinit Checker
	 * 
	 * @param stream
	 */
	public abstract void ReInit(InputStream stream);
	
	public abstract void ReInit(Reader reader);
	
	public abstract void ReInit(InputStream stream, String encoding);

	/** 
	 * Get the next Token. 
	 * The current token moves to next one 
	 */
	public abstract Token getNextToken();

	/**
	 * Get the i-th token.
	 * This method often be used as getToken(-1); getToken(0); getToken(1);
	 * The current token is not changed after call this method
	 */
	public abstract Token getToken(int i);

	public abstract void enable_tracing();

	public abstract void disable_tracing();

	public abstract ParseException generateParseException();

	// custom methods

	public final Vector<Warning> warnings = new Vector<Warning>();
	public RulesManager rm;
	final public State state = new State();

	public void resetVariables() {
		state.reset();
		warnings.clear();
	}

	public Vector<Warning> getWarnings() {
		return warnings;
	}

	public RulesManager getRulesManager() {
		return rm;
	}

	public State getState() {
		return state;
	}
}