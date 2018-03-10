package org.zlx.interview;

import java.math.BigDecimal;


public class ZhiXingLi {
  // 1 *2 *3 *4......98*99*100  只能用string。

	public static void main(String args[]) {
		ZhiXingLi zhiXingLi=new ZhiXingLi();
		long t1=System.currentTimeMillis();
		zhiXingLi.iter();
		long t2=System.currentTimeMillis();
		zhiXingLi.forTest();
		long t3=System.currentTimeMillis();
		System.out.println((t2-t1)+"  : " +(t3-t2));

		zhiXingLi.noBig();
	}
	//no big
	/*
	 * 数组保存 最终的乘积，也保存中间过程的乘积，每个位置，为一位
	 * 每次乘以 一个数 i, 都拿最小位开始乘， y= x[i]*1,  x[i]*2,x[i]*99, x[i]*100;
	 * 	如果，y>100,  记录百位，十位，   相应高位上，乘积后，累加该数值
	 *  如果，y>10 ,  记录十位，
	 */

	public void noBig(){
		int []ac=new int[200];
		for(int i=0;i<200;i++){
			ac[i]=1;
		}

		int maxIndex=1;
		ac[maxIndex]=1;
		int k;
		//一直乘到一百
		for(int i=2;i<=100;i++)
		{
			int []over=new int[202];
			int thisIndex=0;
			//位置
			for(int j=0;j<200;j++){
				k=ac[j]*i;
				if(k>=100){
					over[j+2]=over[j+2]+k/100;
					over[j+1]=over[j+1]+k%100/10;
					thisIndex=k%100%10+over[j];
					ac[j]=thisIndex;
				}
				else if(k>=10){
					over[j+1]=over[j+1]+k%100/10;
					thisIndex=k%100%10+over[j];
					ac[j]=thisIndex;
				}
			}
		}
		for(int i=0;i<200;i++){
			System.out.print(ac[200-i-1]);

		}
	}
	//for test
	public void forTest(){

		BigDecimal ac=new BigDecimal(2);
		for(int i=3;i<=100;i++){
			BigDecimal x=new BigDecimal(i);
			ac=ac.multiply(x);
		}
		System.out.println("for :"+ac.toString());
	}
	//iterator test
	public void iter(){
		System.out.println("iter:"+iteratorTest(new BigDecimal(1),100));
	}

	public BigDecimal iteratorTest(BigDecimal xx ,int index){
		if(index==1){
			return xx;
		}
		BigDecimal ac=new BigDecimal(index);
		return ac.multiply(  iteratorTest( xx, index-1));

	}
}
