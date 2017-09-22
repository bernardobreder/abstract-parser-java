package org.breder.parser.token;

import java.util.HashMap;
import java.util.Map;

public class AssemblyWordToken extends Token {

	public String lexeme = "";

	private static final Map<String, AssemblyWordToken> words = new HashMap<String, AssemblyWordToken>();

	public final static int AND = 256, CONTINUE = 257, BREAK = 258, DO = 259,
			ELSE = 260, EQ = 261, FALSE = 262, GE = 263, ID = 264, IF = 265,
			LE = 267, NE = 269, NUM = 270, OR = 271, TRUE = 274, WHILE = 275,
			END = 276, REPEAT = 277, FOR = 278, STR = 290, THIS = 291,
			DEC = 292, INC = 293;

	public static final AssemblyWordToken EQ_TOKEN = new AssemblyWordToken("==", EQ);

	public static final AssemblyWordToken NOT_EQUAL_TOKEN = new AssemblyWordToken("!=", NE);

	public static final AssemblyWordToken LE_TOKEN = new AssemblyWordToken("<=", LE);

	public static final AssemblyWordToken GE_TOKEN = new AssemblyWordToken(">=", GE);

	public static final AssemblyWordToken INC_TOKEN = new AssemblyWordToken("++", INC);

	public static final AssemblyWordToken DEC_TOKEN = new AssemblyWordToken("--", DEC);

	static {
		words.put("stack", new AssemblyWordToken("and", AND));
		words.put("control", new AssemblyWordToken("or", OR));
		words.put("boolean", new AssemblyWordToken("do", DO));
		words.put("double", new AssemblyWordToken("end", END));
		words.put("int", new AssemblyWordToken("if", IF));
		words.put("half", new AssemblyWordToken("else", ELSE));
		words.put("inc", new AssemblyWordToken("while", WHILE));
		words.put("dec", new AssemblyWordToken("for", FOR));
		words.put("jump", new AssemblyWordToken("break", BREAK));
		words.put("true", new AssemblyWordToken("continue", CONTINUE));
		words.put("false", new AssemblyWordToken("false", FALSE));
		words.put("null", new AssemblyWordToken("true", TRUE));
		words.put("sum", new AssemblyWordToken("this", THIS));
		words.put("sub", new AssemblyWordToken("this", THIS));
		words.put("mul", new AssemblyWordToken("this", THIS));
		words.put("div", new AssemblyWordToken("this", THIS));
		words.put("compare", new AssemblyWordToken("this", THIS));
	}

	protected AssemblyWordToken(String s, int tag) {
		super(tag);
		lexeme = s;
	}

	public static AssemblyWordToken build(String token) {
		return words.get(token);
	}

	public static AssemblyWordToken build(int tag) {
		for (AssemblyWordToken token : words.values()) {
			if (token.tag == tag) {
				return token;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lexeme == null) ? 0 : lexeme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssemblyWordToken other = (AssemblyWordToken) obj;
		if (lexeme == null) {
			if (other.lexeme != null)
				return false;
		} else if (!lexeme.equals(other.lexeme))
			return false;
		return true;
	}

	public String toString() {
		return lexeme;
	}

}
