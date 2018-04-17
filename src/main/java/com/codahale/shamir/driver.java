package com.codahale.shamir;

import com.codahale.shamir.Scheme;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class driver {

    public static void main(String args[]){
        System.out.println("Hello");

        final Scheme scheme = Scheme.of(5, 3);
        final byte[] secret = "I am a secret123".getBytes(StandardCharsets.UTF_8);
        final Map<Integer, byte[]> parts = scheme.split(secret);
        final byte[] recovered = scheme.join(parts);
        System.out.println(new String(recovered, StandardCharsets.UTF_8));
        System.out.println(parts);
    }
}
