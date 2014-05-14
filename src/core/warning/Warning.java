package core.warning;

import core.checker.Token;
import core.rules.Rule;

public abstract class Warning {
	public final Position pos;
	public final String args[];
	public final WarningType type;
	public final WarningSpecify specify;
	public final Rule rule = null;

	public Warning(Position pos, WarningType type, WarningSpecify specify, String args[]) {
		this.pos = pos;
		this.type = type;
		this.specify = specify;
		this.args = args;
	}
	
	protected String makeMessage(){
		
		return null;
	}
}