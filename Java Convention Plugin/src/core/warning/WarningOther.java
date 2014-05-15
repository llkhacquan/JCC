package core.warning;

public class WarningOther extends Warning {
	public WarningOther(Position p, WarningSpecify specify, String[] args) {
		super(p, WarningType.OTHER, specify, args);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return args[0];
	}
}
