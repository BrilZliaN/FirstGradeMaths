package me.BrilZliaN.FirstGradeMaths;

public enum Sign {
	
	PLUS(" + "),
	MINUS(" ─ ");
	
	private String sign;
	
	Sign(String sign) {
		this.sign = sign;
	}
	
	public String getSign() {
		return sign;
	}

}
