package core.rules.implement;

import java.util.Vector;

import core.checker.Checker;
import core.checker.Token;
import core.rules.Rule;
import core.rules.RuleRange;
import core.rules.RuleType;
import core.warning.Position;
import core.warning.Warning;

public class NAMING_StartByUpperCase extends RuleCheckerImplement {

	@Override
	public Warning check(Checker c, Token t, String[] args) {
		Warning warning = null;
		if (t.image.charAt(0) == Character.toUpperCase(t.image.charAt(0)))
			return warning;
		for (RuleRange rg : RuleRange.values()) {
			if (!c.getState().isInRange(rg))
				break;
			if (args == null || args.length < 1)
				break;
			Vector<Rule> rv = c.getRm().searchRule(
					RuleType.NAMING_StartByUpperCase, rg);
			for (Rule r : rv) {
				if (!r.active)
					break;
				// args[0] = loai cua Token t (t la ten cua class hay interface
				// r.ruleargs.get(1) la loai check dc dac ta trong rule
				if (args[0].toLowerCase().compareTo(
						r.ruleArgs.get(1).toLowerCase()) != 0)
					continue;
				Position pos = new Position(t);
				char character = t.image.charAt(0);
				StringBuilder newName = new StringBuilder(t.image);
				newName.deleteCharAt(0);
				newName.insert(0, Character.toUpperCase(character));
				String s[] = new String[] { r.getMessage(new String[] { newName
						.toString() }) };
				warning = new Warning(pos, s);
				return warning;
			}
		}
		return warning;
	}

}
