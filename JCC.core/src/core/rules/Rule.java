package core.rules;

import java.util.Vector;

/**
 * This class handle a <em>rule</em> A <em>rule</em> has type, range, arguments
 * as properties
 */
public class Rule {
	/**
	 * <em>active</em> indicates status of the rule, active or deactive
	 */
	final public boolean active;
	/**
	 * <em>range</em> indicates range of rule, if <em>range==null</em>, rule
	 * have no specified range
	 */
	public RuleRange range;
	/**
	 * <em>ruleArgs</em> is a vector of rule's arguments
	 */
	final public Vector<String> ruleArgs;
	/**
	 * This flag indicates if <em>args[0]</em> is a <em>RuleRange</em> or not
	 */
	public final boolean specifiedRangeInArgs;
	/**
	 * <em>type</em> indicates type of rule
	 */
	final public RuleType type;

	/**
	 * Create a rule, if range is not specify, range = FILE
	 * @param type
	 * @param active
	 * @param args
	 */
	public Rule(RuleType type, boolean active, Vector<String> args) {
		this.type = type;
		this.active = active;
		this.ruleArgs = args;
		args.trimToSize();

		type.load();
		if (args.size() == 0)
			range = null;
		else
			try {
				range = RuleRange.valueOf(args.elementAt(0).toUpperCase());
			} catch (IllegalArgumentException e) {
				range = null;
			}
		if (range == null)
			specifiedRangeInArgs = false;
		else
			specifiedRangeInArgs = true;
		if (range == null)
			range = RuleRange.FILE;
	}

	public String getMessage(String warningArgs[]) {
		StringBuilder s = new StringBuilder(type.getMessage(ruleArgs));
		if (warningArgs != null) {
			for (int i = 0; i < warningArgs.length; i++) {
				int index = s.indexOf("%w");
				if (index < 0)
					break;
				s.replace(index, index + 2, warningArgs[i]);
			}
		}
		return s.toString();
	}

	public String getRule() {
		StringBuilder s = new StringBuilder(type.name());
		s.append("(");
		s.append(active ? "active" : "deactive");
		for (int i = 0; i < ruleArgs.size(); i++) {
			s.append(", ");
			s.append(ruleArgs.elementAt(i));
		}
		s.append(");");

		return s.toString();
	}
}