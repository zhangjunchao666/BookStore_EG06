package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class TestBigDecimal {

	@Test
	public void test() {
		double d1 = 0.1;
		double d2 = 0.2;
		System.out.println(d1+d2);
		//如果开发中涉及到 金融，不推荐直接使用 浮点型运算
		//可以使用bigDecimal解决
		BigDecimal bd1 = new BigDecimal(d1+"");//调用String类型的构造器
		BigDecimal bd2 = new BigDecimal(d2+"");//调用String类型的构造器
		
		BigDecimal add = bd1.add(bd2);
		System.out.println(add.doubleValue());//将计算后的结果转为double类型
		BigDecimal multiply = bd1.multiply(bd2);
		BigDecimal divide = bd1.divide(bd2);
		BigDecimal subtract = bd1.subtract(bd2);
	}

}
