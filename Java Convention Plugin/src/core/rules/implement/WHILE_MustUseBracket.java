package core.rules.implement;

import core.checker.Checker;
import core.checker.Token;
import core.rules.Rule;
import core.rules.RuleRange;
import core.rules.RuleType;
import core.warning.Position;
import core.warning.Warning;

public class WHILE_MustUseBracket extends RuleCheckerImplement {

	@Override
	public Warning check(Checker c, Token t, String[] args) {
		Warning warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (t.image.equals("{"))
				break;
			if (!c.getState().isInRange(rg))
				break;
			for (Rule r : c.getRm().searchRule(RuleType.WHILE_MustUseBracket, rg)) {
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
