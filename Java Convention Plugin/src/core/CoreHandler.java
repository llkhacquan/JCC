package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

import core.checker.Checker;
import core.checker.CommentChecker;
import core.checker.IndentChecker;
import core.checker.NamingChecker;
import core.checker.OtherChecker;
import core.rules.RulesManager;
import core.warning.Warning;

public class CoreHandler {
	private static CommentChecker commentChecker;
	private static IndentChecker indentChecker;
	private static NamingChecker namingChecker;
	private static OtherChecker otherChecker;
	private static Checker checker;

	private CoreHandler() {
		commentChecker = null;
		namingChecker = null;
		indentChecker = null;
		otherChecker = null;
		checker = null;
	}

	public static void main(String[] args) {
		new CoreHandler();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String inputFile = "src/core/input.txt";
		String rulesFile = "src/core/rules.txt";

		System.out.println("Default input file: " + inputFile);
		System.out.println("Default rules file: " + rulesFile);
		System.out.println("Rules file and input file are reloaded"
				+ " each time the Checker checks\n\n");

		Vector<Warning> warnings = null;
		int options = 1;
		while (options != 0) {
			System.out.println();
			for (CheckOptions o : CheckOptions.values()) {
				System.out.println("\t " + o.i + " - " + o.name());
			}
			System.out.println("\t 0 - Exit");
			System.out.print("\tWhat do you want?");
			options = in.nextInt();
			if (options == 0)
				return;

			RulesManager rm = RulesManager.createRulesManager(rulesFile);
			warnings = CoreHandler.check(inputFile, rm,
					CheckOptions.getByI(options));
			for (int i = 0; i < warnings.size(); i++) {
				System.out.println("At line "
						+ warnings.elementAt(i).pos.beginLine + ": "
						+ warnings.elementAt(i).toString());
			}
		}
	}

	public static Vector<Warning> check(InputStream is, RulesManager rm,
			CheckOptions o) {
		if (commentChecker == null) {
			commentChecker = new CommentChecker(is);
			namingChecker = new NamingChecker(is);
			indentChecker = new IndentChecker(is);
			otherChecker = new OtherChecker(is);
		}
		switch (o) {
		case CHECK_TYPE_COMMENT:
			checker = commentChecker;
			break;
		case CHECK_TYPE_INDENT:
			checker = indentChecker;
			break;
		case CHECK_TYPE_NAMING:
			checker = namingChecker;
			break;
		case CHECK_TYPE_OTHER:
		default:
			checker = otherChecker;
			break;
		}

		checker.setRm(rm);
		checker.ReInit(is);
		checker.resetVariables();

		return checker.checkAndGetWarnings();
	}

	/**
	 * 
	 * @param fileName
	 * @param rm
	 * @param o
	 * @return null if file it not found
	 */
	public static Vector<Warning> check(String fileName, RulesManager rm,
			CheckOptions o) {
		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " not found!");
			return null;
		}
		return check(is, rm, o);
	}

	public static Vector<Warning> check(String fileName, RulesManager rm) {
		Vector<Warning> result = new Vector<Warning>();
		for (CheckOptions o : CheckOptions.values()) {
			result.addAll(check(fileName, rm, o));
		}
		return null;
	}

	public static Vector<Warning> check(InputStream is, RulesManager rm) {
		Vector<Warning> result = new Vector<Warning>();
		for (CheckOptions o : CheckOptions.values()) {
			result.addAll(check(is, rm, o));
		}
		return result;
	}
}
