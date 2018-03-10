package org.zlx.tableSplit;

/**
 * Created by @author linxin on 09/03/2018.  <br>



 */
public class SplitTable {

    String searilNO_pattern="systemCode_areaCode_bissinessCode_machineCode_timeStamp";





    String getOrderId(String userID){
        //按照不同的维度来分。
        return userID.hashCode()%50+getMachineCode()+System.currentTimeMillis()+getSearialNO();
    }











    private String getMachineCode(){
        return "machine code";
    }


    int  lastCount=0;
    private synchronized int getSearialNO(){
        long timeStamp;
        int  count=0;
        while(count<=lastCount){
            count++;
        }
        lastCount=count;
        return count;
    }

}
