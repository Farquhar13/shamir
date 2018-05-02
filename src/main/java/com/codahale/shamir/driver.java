package com.codahale.shamir;

import com.codahale.shamir.Scheme;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class driver {

    public static void main(String args[]){
        // specify shares (n) and threshold (k)
        final Scheme scheme = Scheme.of(5, 3);
        // hardcoded secret string calling getBytes
        final byte[] secret = "123".getBytes(StandardCharsets.UTF_8);

        /*
         * creates a Map object called parts that contains the shares
         * requires calling scheme.split with the secret as parameter
         */
        final Map<Integer, byte[]> parts = scheme.split(secret);

        final Map<Integer, byte[]> newParts = new HashMap<>(5);

        /*
         * recovered is the reconstructed secret
         * scheme.join() takes parts as a parameter
         * parts is a Map object of shares
         * To properly reconstruct the secret parts must consist of at least k shares
          */
        final byte[] recovered = scheme.join(parts);
        System.out.println("recovered secret: " + new String(recovered, StandardCharsets.UTF_8));
    }

    /*
    final Map<Integer, byte[]> shares = new HashMap<>(5);
    public void reconstruct(int key, byte[] share){
        shares.replace(key, share);
        k_check = 0;
        for (i=0; i <=shares.length(
            //check how may shares are in the map object, if greater than k, send to scheme.join
        }
    }
*/


}
