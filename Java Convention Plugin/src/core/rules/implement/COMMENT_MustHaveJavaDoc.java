package core.rules.implement;

import java.util.Vector;

import core.checker.Checker;
import core.checker.Token;
import core.rules.Rule;
import core.rules.RuleRange;
import core.rules.RuleType;
import core.warning.Position;
import core.warning.Warning;

public class COMMENT_MustHaveJavaDoc extends RuleCheckerImplement {

	public Warning check(Checker c, Token t, String[] args) {
		Warning warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (!c.getState().isInRange(rg))
				break;
			if (args == null || args.length < 1)
				break;
			Vector<Rule> rv = c.getRm().searchRule(
					RuleType.COMMENT_MustHaveJavaDoc, rg);
			for (Rule r : rv) {
				if (!r.active)
					break;
//				System.out.println(args[0] + " " + r.ruleArgs.get(1)
//						+ args[0].compareTo(r.ruleArgs.get(1)));
				if (args[0].toLowerCase().compareTo(r.ruleArgs.get(1).toLowerCase()) != 0)
					continue;
				Position pos = new Position(t);
				String s[] = new String[] { r
						.getMessage(new String[] { t.image }) };
				warning = new Warning(pos, null, s);
				return warning;
			}
		}
		return warning;
	}

}
