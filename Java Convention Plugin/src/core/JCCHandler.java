package core;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

import jcc.preferences.PreferencesPageHandler;
import core.checker.Checker;
import core.checker.CommentChecker;
import core.checker.IndentChecker;
import core.checker.NamingChecker;
import core.checker.OtherChecker;
import core.rules.RulesManager;
import core.warning.Warning;

public class JCCHandler {
	final public static int CHECK_TYPE_COMMENT = 2;
	final public static int CHECK_TYPE_INDENT = 1;
	final public static int CHECK_TYPE_NAMING = 3;
	final public static int CHECK_TYPE_OTHER = 4;
	final public static int EXIT = 0;

	private static Scanner in = new Scanner(System.in);

	public static String rulesFile = PreferencesPageHandler.getRulesFile();

	public static void main(String[] args) {
		String inputFile = "input.txt";
		rulesFile = "src/rules.txt";
		
		
		Vector<Warning> warnings = null;
		int type = 1;
		while (type != EXIT) {
			System.out.println();
			System.out.println("\t " + JCCHandler.CHECK_TYPE_INDENT
					+ ". Check indent convention.");
			System.out.println("\t " + JCCHandler.CHECK_TYPE_COMMENT
					+ ". Check comment convention.");
			System.out.println("\t " + JCCHandler.CHECK_TYPE_NAMING
					+ ". Check naming convention.");
			System.out.println("\t " + JCCHandler.CHECK_TYPE_OTHER
					+ ". Check other convention.");
			System.out.println("\t " + JCCHandler.EXIT + ". Exit");
			System.out.print("\tWhat do you want?");
			type = in.nextInt();
			if (type == EXIT)
				return;

			InputStream inputStream = JCCHandler.class
					.getResourceAsStream(inputFile);

			JCCHandler jccHandler = new JCCHandler(inputStream);
			warnings = jccHandler.check(inputStream, type);
			for (int i = 0; i < warnings.size(); i++) {
				System.out.println("At line "
						+ warnings.elementAt(i).pos.beginLine + ": "
						+ warnings.elementAt(i).toString());
			}
		}
	}

	private Checker checker;

	private CommentChecker commentChecker;
	private IndentChecker indentChecker;
	private NamingChecker namingChecker;
	private OtherChecker otherChecker;

	public Vector<Warning> warnings;

	public JCCHandler(InputStream stream) {
		
		
		namingChecker = new NamingChecker(stream);
		commentChecker = new CommentChecker(stream);
		indentChecker = new IndentChecker(stream);
		otherChecker = new OtherChecker(stream);
	}

	public Vector<Warning> check(InputStream is, int type) {
		switch (type) {
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
			checker = otherChecker;
			otherChecker.rm = RulesManager.createRulesManager(rulesFile) ;
			break;
		default:
			return warnings;
		}

		try {
			checker.ReInit(is);
			checker.resetVariables();
			checker.CompilationUnit();
			warnings = checker.getWarnings();
		} catch (core.checker.ParseException e) {
			e.printStackTrace();
		}
		return warnings;
	}
}