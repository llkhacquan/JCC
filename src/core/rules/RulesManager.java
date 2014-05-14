package core.rules;

import java.io.File;
import java.util.Vector;

public class RulesManager {
	public Vector<Rule> rules;

	private RulesManager() {
		rules = null;
	}

	private RulesManager(String fileName) {
		File f = new File(fileName);
		if (!f.exists() || f.isDirectory()) {
			rules = new Vector<Rule>();
			System.out.println(fileName + " not found");
			return;
		}
		RulesReader reader = null;
		reader = new RulesReader(fileName);
		try {
			reader.CompilationUnit();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		if (reader != null)
			rules = reader.rules;

	}

	public static RulesManager createRulesManager(String fileName) {
		RulesManager rm = new RulesManager(fileName);

		return rm;
	}

	public boolean isActive(RuleType rt, RuleRange range) {
		Rule r = searchRule(rt, range);
		if (r != null)
			return r.active;
		else
			return false;
	}

	public Rule searchRule(RuleType rt, RuleRange range) {
		for (Rule r : rules) {
			if (r.type == rt && r.range == range)
				return r;
		}
		return null;
	}

	public Vector<Rule> searchRule(RuleType rt) {
		Vector<Rule> vr = new Vector<Rule>();
		for (Rule r : rules) {
			if (r.type == rt)
				vr.add(r);
		}
		return vr;
	}
}