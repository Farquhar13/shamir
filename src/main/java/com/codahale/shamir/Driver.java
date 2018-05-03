package com.codahale.shamir;

import com.codahale.shamir.Scheme;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Driver {

    public static Map<Integer, byte[]> collected = new HashMap<Integer, byte[]>();
    public static final int n = 5;
    public static final int k = 3;

    // specify shares (n) and threshold (k)
    static final Scheme scheme = Scheme.of(n, k);
    // hardcoded secret string calling getBytes
    static final byte[] secret = "I am a secret123".getBytes(StandardCharsets.UTF_8);

    public static void main(String args[]){



        /*
         * creates a Map object called parts that contains the shares
         * requires calling scheme.split with the secret as parameter
         */
        final Map<Integer, byte[]> parts = scheme.split(secret);
        System.out.println("parts: " + parts);



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
     * addShare takes an integer and a share value from the javascript input and adds it to the collected
     * map object. If there is enough to meet the threshold, the parts are joined and the secret is returned.
     * If not, the number of shares entered is returned
     *
     * @param i the int key of the share value
     * @param b the share value
     * @return either the secret in form of a string or the number of shares entered
     */
    public static String addShare(String i, String b) {

        byte[] convertedByte = toBytes(b);                  //send b to be converted to a byte[]
        collected.put(Integer.parseInt(i) ,convertedByte);  //i must be parsed to an int

        if(collected.size() < k){
            return(collected.size() + " out of " + k + " shares needed.");
        } else {
            byte[] interpolated = scheme.join(collected);
            return("recovered secret: " + new String(interpolated, StandardCharsets.UTF_8));
        }

    }


    /*
     * toBytes is supposed to find a way to convert a string into a byte array so that it can be added
     * to the map object
     *
     * uncertain at this time if the bytes array is taking the the char as literals
     * this is the most drastic issue with the program currently
     *
     * @param input the string taken in by the program
     * @return the byte[] value of the converted string
     */
    public static byte[] toBytes(String input){
        byte[] newNewByte = null;
        byte newByte = 0;
        if(input.equals(null)) {
            return newNewByte;
        } else {
            for (int count = 0; count <= input.length(); count++) {
                char c = input.charAt(count);
                newByte = (byte)c;

                newNewByte[count] = newByte;
            }
            return newNewByte;
        }
    }
}
