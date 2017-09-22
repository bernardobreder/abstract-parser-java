package org.breder.parser.token;

public class NumberToken extends Token {

	public final double value;

	public NumberToken(double v) {
		super(WordToken.NUM);
		value = v;
	}

	public String toString() {
		return "" + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumberToken other = (NumberToken) obj;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

}
