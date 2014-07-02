package core.rules.implement;

import core.checker.Checker;
import core.checker.Token;
import core.rules.Rule;
import core.rules.RuleRange;
import core.rules.RuleType;
import core.warning.Position;
import core.warning.Warning;

public class CLASS_MustHaveDefaultConstructor extends RuleCheckerImplement {

	@Override
	public Warning check(Checker c, Token t, String[] args) {
		Warning warning = null;
		RuleRange[] rgs = new RuleRange[] { RuleRange.FILE, RuleRange.CLASS,
				RuleRange.INTERFACE, RuleRange.ENUM };
		for (RuleRange rg : rgs) {
			if (!c.getState().isInRange(rg))
				break;
			for (Rule r : c.getRm().searchRule(
					RuleType.CLASS_MustHaveDefaultConstructor, rg)) {
				if (!r.active)
					continue;
				if (!c.getState().hasDefaultConstructor) {
					Position pos = new Position(t);
					String s[] = new String[] { r.getMessage(new String[] { c
							.getState().inClass }) };
					warning = new Warning(pos, null, s);
					return warning;
				}
			}
		}
		return warning;
	}

}
