import java.util.*;
class test{
	public static void main(String[]args){
		int x[][]={{11,13},{21,12}};
		int y[][]=new int[2][2];
		y[0][0]=x[0][0];
		y[0][1]=x[0][1];
		y[1][0]=x[1][0];
		y[1][1]=x[1][1];
		System.out.println(Arrays.deepEquals(x,y));
	}
}
