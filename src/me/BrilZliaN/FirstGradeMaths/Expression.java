package me.BrilZliaN.FirstGradeMaths;

import java.util.Random;

public class Expression {
	
	public static int firstMax = 20;
	public static int secondMax = 10;
	public static int thirdMax = 10;
	public static int answerMax = 20;
	
	private int first;
	private int second;
	private int third;
	private Sign firstsecond;
	private Sign secondthird;
	
	private int answer;
	
	public Expression(int first, int second, int third, Sign firstsecond, Sign secondthird) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.firstsecond = firstsecond;
		this.secondthird = secondthird;
		if (firstsecond == Sign.PLUS) {
			answer = first + second;
		} else {
			answer = first - second;
		}
		if (secondthird == Sign.PLUS) {
			answer += third;
		} else {
			answer -= third;
		}
	}
	
	public static Expression generate() {
		Random random = new Random();
		int one = random.nextInt(firstMax);
		Sign first = random.nextBoolean() ? Sign.PLUS : Sign.MINUS;
		int two = random.nextInt(secondMax);
		Sign second = random.nextBoolean() ? Sign.PLUS : Sign.MINUS;
		int three = random.nextInt(thirdMax);
		Expression test = new Expression(one, two, 0, first, second);
		Expression e = new Expression(one, two, three, first, second);
		if (test.getAnswer() < 0 || test.getAnswer() > answerMax || e.getAnswer() < 0 || e.getAnswer() > answerMax) {
			e = Expression.generate();
		}
		return e;
	}
	
	public int getAnswer() {
		return answer;
	}
	
	public String getExpression() {
		return first + firstsecond.getSign() + second  + secondthird.getSign() + third + " =";
	}

}
