package com.common.tryfinally;

/**
 *  *
 在try语句中，在执行return语句时，要返回的结果已经准备好了，就在此时，程序转到finally执行了。
 在转去之前，try中先把要返回的结果存放到不同于x的局部变量中去，执行完finally之后，在从中取出返回结果，
 因此，即使finally中对变量x进行了改变，但是不会影响返回结果。
 它应该使用栈保存返回值。

 */
public class FinallyTest
{
	public static void main(String[] args) {
		 
		System.out.println(new FinallyTest().test());;
	}

	static int test()
	{
		int x = 1;
		try
		{
			x++;
			System.out.println("try x = " + x );
			x = 1 / 0;
			return x;
		}catch (Throwable e){
			x = x + 10;
			System.out.println("throwbale x = " +x );
			throw new RuntimeException(e);
//			return x;
		} finally
		{
			++x;
			System.out.println("finnaly x = " + x);
//			return x;
		}
	}
}