package com.algorithm.redblacktree;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Client
{

	public static void main(String[] args)
	{
//		System.out.println("specify the length and the max:");
//		Scanner scanner = new Scanner(System.in);
//		int len = scanner.nextInt();
//		int max = scanner.nextInt();
//		
//		int[] array = new int[len];
//		Random random = new Random();
//		for(int i = 0; i < len; ++i)
//			array[i] = random.nextInt(max);
		
		Scanner scanner = new Scanner(System.in);
//		int[] array = {27, 37, 52, 42, 28, 49, 11, 79, 84, 85};
		int[] array = {27, 27, 52, 42, 28, 49, 11, 79, 84, 85};

		System.out.println(Arrays.toString(array));
		
		RedBlackTree tree = new RedBlackTree();
		for(int i : array)
			tree.insert(i);
		
//		List<Integer> list = tree.midOrderVisit();
//		System.out.println( Arrays.toString(list.toArray(new Integer[]{})) );
//
		tree.layerVisit();

		System.out.println("*******************");
		
//		int deleteKey = 0;
//		do{
//			System.out.println("the element you wanna delete: ");
//			deleteKey = scanner.nextInt();
//
//			if(deleteKey < 0)
//				break;
//
//			if(!tree.delete(deleteKey))
//				System.out.println("not in the tree");
//			else
//				tree.layerVisit();
//		}while(deleteKey >= 0);
//
//		scanner.close();
//		System.out.println("Exit!!!");
		
	}

}
