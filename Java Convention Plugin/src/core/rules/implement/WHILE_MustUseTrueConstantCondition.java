package core.rules.implement;

import core.checker.Checker;
import core.checker.Token;
import core.rules.Rule;
import core.rules.RuleRange;
import core.rules.RuleType;
import core.warning.Position;
import core.warning.Warning;

public class WHILE_MustUseTrueConstantCondition extends RuleCheckerImplement {

	/**
	 * token t after the token "(" of while statement
	 */
	@Override
	public Warning check(Checker c, Token t, String[] args) {
		Warning warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (!c.getState().isInRange(rg))
				break;
			for (Rule r : c.getRm().searchRule(
					RuleType.WHILE_MustUseTrueConstantCondition, rg)) {
				if (!r.active)
					continue;
				if (c.getToken(1).image != "true" || c.getToken(2).image != ")") {
					Position pos = new Position(c.getToken(0));
					String s[] = new String[] { r.getMessage(null) };
					warning = new Warning(pos, null, s);
					return warning;
				}
			}
		}
		return warning;
	}

}
