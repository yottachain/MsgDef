
import com.ytfs.common.Function;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class NewClass {

    public static void main(String[] ar) {

        int ii = Integer.MAX_VALUE;
        System.out.println(ii);
        int index = ii % 21;
        System.out.println(index);
        ii = ii + 21;
        System.out.println(ii);
        index = ii % 21;
        System.out.println(index);
        
       
        long ll=Function.inttolong(ii);
        System.out.println(ll);
        int index1 = (int)(ll % 21);
        System.out.println(index1);
    }
}
