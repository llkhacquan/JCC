package core.warning;

import core.rules.Rule;

public class Warning {
	public final Position pos;
	public final String args[];
	public final WarningSpecify specify;
	public final Rule rule = null;

	public Warning(Position pos, WarningSpecify specify, String args[]) {
		this.pos = pos;
		this.specify = specify;
		this.args = args;
	}
	
	public Warning(Position pos, String args[]){
		this(pos, null, args);
	}
	
	public Warning(Position pos){
		this(pos, null, null);
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return args[0];
	}
}