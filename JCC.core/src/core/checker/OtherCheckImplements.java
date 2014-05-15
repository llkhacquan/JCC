package core.checker;

import core.rules.Rule;
import core.rules.RuleRange;
import core.rules.RuleType;
import core.warning.Position;
import core.warning.Warning;
import core.warning.WarningOther;

public class OtherCheckImplements {

	/**
	 * Return a warning if there is a rule generates one t = token of the next
	 * token after "(" Condition() ")" or "else" This method is call when
	 */
	public static Warning IF_MustUseBracket(Checker c) {
		Token t = c.getToken(1);
		WarningOther warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (!c.rm.isActive(RuleType.IF_MustUseBracket, rg))
				break;
			if (t.image.equals("{"))
				break;
			if (!c.state.isInRange(rg))
				break;

			Rule r = c.rm.searchRule(RuleType.IF_MustUseBracket, rg);
			Position pos = new Position(t);
			String s[] = new String[] { r.getMessage(null) };
			warning = new WarningOther(pos, null, s);
			return warning;
		}
		return warning;
	}

	/**
	 * Return a warning if there is a rule generates one
	 * 
	 * @param t
	 *            = token of "else"
	 */
	public static Warning IF_NotAllowElse(Checker c) {
		Token t = c.getToken(0);
		WarningOther warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (c.rm.isActive(RuleType.IF_NotAllowElse, rg)) {
				Rule r = c.rm.searchRule(RuleType.IF_NotAllowElse, rg);
				if (!c.state.isInRange(rg))
					break;
				Position pos = new Position(t);
				String s[] = new String[] { r.getMessage(null) };
				warning = new WarningOther(pos, null, s);
				return warning;
			}
		}
		return warning;
	}

	/**
	 * Return a warning if there is a rule with type
	 * SWITCH_MustHaveDefaultBranch When call this method, client must sure that
	 * there was not default branch
	 * 
	 * @param t
	 *            = the last "}" of the switch statement
	 */
	public static Warning SWITCH_MustHaveDefaultBranch(Checker c) {
		WarningOther warning = null;
		Token t = c.getToken(1);
		for (RuleRange rg : RuleRange.values()) {
			if (c.rm.isActive(RuleType.SWITCH_MustHaveDefaultBranch, rg)) {
				Rule r = c.rm.searchRule(RuleType.SWITCH_MustHaveDefaultBranch,
						rg);
				if (!c.state.isInRange(rg) || c.state.switchHasDefault)
					break;
				Position pos = new Position(t);
				String s[] = new String[] { r.getMessage(null) };
				warning = new WarningOther(pos, null, s);
				return warning;
			}
		}
		return warning;
	}

	/**
	 * token t after the token "(" of while statement
	 */
	public static Warning WHILE_MustUseTrueConstantCondition(Checker c) {
		WarningOther warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (c.rm.isActive(RuleType.WHILE_MustUseTrueConstantCondition, rg)) {
				Rule r = c.rm.searchRule(
						RuleType.WHILE_MustUseTrueConstantCondition, rg);
				if (!c.state.isInRange(rg))
					break;
				if (c.getToken(1).image != "true" || c.getToken(2).image != ")") {
					Position pos = new Position(c.getToken(0));
					String s[] = new String[] { r.getMessage(null) };
					warning = new WarningOther(pos, null, s);
					return warning;
				}
			}
		}
		return warning;
	}

	/**
	 * 
	 */
	public static Warning FILE_MustHavePackageDeclaration(Checker c) {
		WarningOther warning = null;
		RuleRange rg = RuleRange.FILE;
		if (c.rm.isActive(RuleType.FILE_MustHavePackageDeclaration, rg)) {
			Rule r = c.rm.searchRule(RuleType.FILE_MustHavePackageDeclaration,
					rg);
			if (c.state.inPackage == null) {
				Position pos = new Position(c.getToken(0));
				String s[] = new String[] { r.getMessage(null) };
				warning = new WarningOther(pos, null, s);
				return warning;
			}
		}

		return warning;
	}

	public static Warning CLASS_MustHaveDefaultConstructor(Checker c) {
		WarningOther warning = null;
		RuleRange[] rgs = new RuleRange[] { RuleRange.FILE, RuleRange.CLASS,
				RuleRange.INTERFACE, RuleRange.ENUM };
		for (RuleRange rg : rgs) {
			if (c.rm.isActive(RuleType.CLASS_MustHaveDefaultConstructor, rg)) {
				Rule r = c.rm.searchRule(
						RuleType.CLASS_MustHaveDefaultConstructor, rg);
				if (!c.state.isInRange(rg))
					break;
				if (!c.state.hasDefaultConstructor) {
					Position pos = new Position(c.getToken(0));
					String s[] = new String[] { r
							.getMessage(new String[] { c.state.inClass }) };
					warning = new WarningOther(pos, null, s);
					return warning;
				}
			}
		}
		return warning;
	}

	/**
	 * Return a warning if there is a rule generates one
	 */
	public static Warning FOR_MustUseBracket(Checker c) {
		Token t = c.getToken(1);
		WarningOther warning = null;
		for (RuleRange rg : RuleRange.values()) {
			if (!c.rm.isActive(RuleType.FOR_MustUseBracket, rg))
				break;
			if (t.image.equals("{"))
				break;
			if (!c.state.isInRange(rg))
				break;

			Rule r = c.rm.searchRule(RuleType.FOR_MustUseBracket, rg);
			Position pos = new Position(t);
			String s[] = new String[] { r.getMessage(null) };
			warning = new WarningOther(pos, null, s);
			return warning;
		}
		return warning;
	}
}
