package com.connsec.constants;

/**
 * Define int for boolean
 * 0  false
 * 1  true
 * 
 * @author Crystal.Sea
 *
 */
public class BOOLEAN {
	
	public final static int FALSE	=	0;
	
	public final static int TRUE	=	1;

	public static boolean isTrue(int value){
		return TRUE==value;
	}
	
	public static boolean isFalse(int value){
		return FALSE==value;
	}
	
}
