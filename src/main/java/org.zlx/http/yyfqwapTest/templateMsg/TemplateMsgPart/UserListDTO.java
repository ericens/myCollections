package org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ${linxin} on 25/07/2017.
 */
public class UserListDTO {
    long count;
    long total;
    OpenID_LIST data;
    String  next_openid;



    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public OpenID_LIST getData() {
        return data;
    }

    public void setData(OpenID_LIST data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public class OpenID_LIST{
        List<String> openid=new LinkedList();

        public List<String> getOpenid() {
            return openid;
        }

        public void setOpenid(List<String> openid) {
            this.openid = openid;
        }
    }
}
