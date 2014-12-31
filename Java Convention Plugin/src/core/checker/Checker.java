package core.checker;

import java.io.InputStream;
import java.io.Reader;
import java.util.Vector;

import core.rules.RulesManager;
import core.warning.Warning;

public abstract class Checker {
	private RulesManager rm;

	final protected State state = new State();

	protected final Vector<Warning> warnings = new Vector<Warning>();
	
	public Vector<Warning> checkAndGetWarnings(){
		try {
			CompilationUnit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return warnings;
	}

	/**
	 * Start parsing
	 * 
	 * @throws ParseException
	 */
	public abstract void CompilationUnit() throws ParseException;

	public abstract void disable_tracing();

	public abstract void enable_tracing();

	public abstract ParseException generateParseException();

	/**
	 * Get the next Token. The current token moves to next one
	 */
	public abstract Token getNextToken();

	public RulesManager getRm() {
		return rm;
	}

	// custom methods

	public State getState() {
		return state;
	}
	/**
	 * Get the i-th token. This method often be used as getToken(-1);
	 * getToken(0); getToken(1); The current token is not changed after call
	 * this method
	 */
	public abstract Token getToken(int i);
	public Vector<Warning> getWarnings() {
		return warnings;
	}

	/**
	 * Reinit Checker
	 * 
	 * @param stream
	 */
	public abstract void ReInit(InputStream stream);

	public abstract void ReInit(InputStream stream, String encoding);

	public abstract void ReInit(Reader reader);

	public void resetVariables() {
		state.reset();
		warnings.clear();
	}

	public void setRm(RulesManager rm) {
		this.rm = rm;
	}
}