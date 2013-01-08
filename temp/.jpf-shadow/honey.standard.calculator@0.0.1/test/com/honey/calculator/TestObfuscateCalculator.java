package com.honey.calculator;

import junit.framework.TestCase;

import com.honey.calculator.ObfuscateCalculator.Hash;
import com.honey.calculator.ObfuscateCalculator.HashArithmetic;
import com.honey.core.utils.RandomStringUtils;

public class TestObfuscateCalculator extends TestCase {

	public void test0(){
		String s =RandomStringUtils.randomAlphabetic(6);
		Hash bkdr =HashArithmetic.BKDRHash.getHash();
		int bkdrCode = bkdr.code(s, 31);
		Hash fnv =HashArithmetic.FNVHash.getHash();
		int fnvCode = fnv.code(s, 31);
		Hash java =HashArithmetic.NativeHash.getHash();
		int javaCode = java.code(s, 31);
		System.out.println("String                : "+s);
		System.out.println("  BKDR Hash Code      : "+bkdrCode);
		System.out.println("  FNV-1a 32 Hash Code : "+fnvCode);
		System.out.println("  Java Hash Code      : "+javaCode);
	}
	
	public void test1(){
		String s ="ABCDEFGHIJKLMJ*UOL#WFD#E#DSF##WE";
		Hash hash =HashArithmetic.BKDRHash.getHash();
		int h = hash.code(s, 131313);
		assertEquals(445366462, h);
	}
	
	public void test2(){
		String s ="ABCDEFGHIJKLMJ*UOL#WFD#E#DSF##WE";
		Hash hash =HashArithmetic.FNVHash.getHash();
		int h = hash.code(s, 131313);
		System.out.println(h);
		assertEquals(-472546987, h);
	}
	
	public void test3(){
		ObfuscateCalculator o = new ObfuscateCalculator();
		for(int i=0;i<100;i++ ){
			String s=RandomStringUtils.randomAlphabetic(8);
			String result =  o.calculator(true, s);
			System.out.println( s+" :   "+ result);
		}
	}
	
//	public void test_1(){
//		int inputNum = 60000; //Prime numbers will be: 2, 3, 5, 7, 11, 13, 17, 19,  
//        ArrayList primeNumList = getPrimeNumber(inputNum);  
//        for (int i = 0; i < primeNumList.size();i ++)  
//        {  
//            System.out.print(primeNumList.get(i) + ", ");  
//        }  
//	}
//	
//	public ArrayList getPrimeNumber(int inputNum)  
//    {  
//        ArrayList primeNumList = new ArrayList();  
//        //第一个循环遍历正整数A下除1外所有的正整数  
//        for (int i = 2; i <= inputNum; i++)  
//        {  
//            primeNumList.add(i);  
//            //内层循环判断第一个循环的当前整数是否是质数  
//            for (int j = i-1; j > 1; j--)  
//            {  
//                //当目前的整数i能够整除0,和i之间任一个整数时，这个数i不是质数，移除出数组。  
//                if (i%j == 0)  
//                {  
//                    primeNumList.remove(primeNumList.indexOf(i));  
//                    break;  
//                }  
//            }  
//        }  
//          
//        return primeNumList;  
//    }
}
