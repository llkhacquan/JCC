package core.rules.implement;

import core.checker.Checker;
import core.checker.Token;
import core.warning.Warning;

public abstract class RuleCheckerImplement {
	public abstract Warning check(Checker c, Token t, String[] args);
	
	public Warning check(Checker c, Token t, String arg){
		return check(c, t, new String[]{arg});
	}
	
	public Warning check(Checker c, Token t){
		return check(c, t, new String[]{});
	}
	
	public Warning check(Checker c){
		return check(c, null, new String[]{});
	}
}
