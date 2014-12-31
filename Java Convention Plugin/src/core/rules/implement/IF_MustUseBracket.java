package core.rules.implement;

import core.checker.Checker;
import core.checker.Token;
import core.rules.Rule;
import core.rules.RuleRange;
import core.rules.RuleType;
import core.warning.Position;
import core.warning.Warning;

public class IF_MustUseBracket extends RuleCheckerImplement {

	/**
	 * Return a warning if there is a rule generates one t = token of the next
	 * token after "(" Condition() ")" or "else" This method is call when
	 */
	@Override
	public Warning check(Checker c, Token t, String[] args) {
		Warning warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (t.image.equals("{"))
				break;
			if (!c.getState().isInRange(rg))
				break;
			for (Rule r : c.getRm().searchRule(RuleType.IF_MustUseBracket, rg)) {
				if (!r.active)
					continue;
				Position pos = new Position(t);
				String s[] = new String[] { r.getMessage(null) };
				warning = new Warning(pos, null, s);
				return warning;
			}
		}
		return warning;
	}

}
