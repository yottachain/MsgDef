package com.ytfs.service.packet.user;

import io.yottachain.nodemgmt.core.vo.SuperNode;
import java.util.List;

public class ListSuperNodeResp {
 
 
    public SuperNode[] getSuperList() {
        return superList;
    }

 
    public void setSuperList(SuperNode[] superList) {
        this.superList = superList;
    }
    private SuperNode[] superList;
    
    
   
     
     
     
 
}
