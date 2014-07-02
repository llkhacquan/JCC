package core.rules;

import java.io.File;
import java.io.InputStream;
import java.util.Vector;

public class RulesManager {
	public Vector<Rule> rules;

	private RulesManager() {
		rules = null;
	}

	private RulesManager(InputStream is) {
		RulesReader reader = null;
		reader = new RulesReader(is);
		try {
			reader.CompilationUnit();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		if (reader != null)
			rules = reader.rules;
	}

	private RulesManager(String fileName) {
		File f = new File(fileName);
		if (!f.exists() || f.isDirectory()) {
			rules = new Vector<Rule>();
			System.out.println(fileName + " not found");
			return;
		}
		RulesReader rulesReader = null;
		rulesReader = new RulesReader(fileName);
		try {
			rulesReader.CompilationUnit();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		if (rulesReader != null)
			rules = rulesReader.rules;

	}

	public static RulesManager createRulesManager(String fileName) {
		RulesManager rm = new RulesManager(fileName);
		return rm;
	}

	public static RulesManager createRulesManager(InputStream is) {
		RulesManager rm = new RulesManager(is);
		return rm;
	}

	public Vector<Rule> searchRule(RuleType rt, RuleRange range) {
		Vector<Rule> vr = new Vector<Rule>();
		for (Rule r : rules) {
			if (r.type == rt && r.range == range)
				vr.add(r);
		}
		return vr;
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