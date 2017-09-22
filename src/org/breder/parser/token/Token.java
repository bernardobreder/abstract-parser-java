package org.breder.parser.token;

public class Token {

	public final int tag;

	public Token(int t) {
		tag = t;
	}

	public String toString() {
		return "" + (char) tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tag;
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
		Token other = (Token) obj;
		if (tag != other.tag)
			return false;
		return true;
	}

}
