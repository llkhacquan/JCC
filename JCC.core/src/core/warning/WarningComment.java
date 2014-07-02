package core.warning;

public class WarningComment extends Warning {



	public WarningComment(Position pos, WarningSpecify specify, String[] args) {
		super(pos, specify, args);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("");
		s.append("A block comment should be before " + specify);
			if (specify != WarningSpecify.COMMENT_BEGIN && args != null && args[0] != null) {
			s.append(" definition '" + args[0] + "' to describe about it");
		}
		return s.toString();
	}

}
