package com.multitheftauto.sdk.util;

import com.multitheftauto.sdk.model.Authentication;

public class Base64 {
    public static String encode(Authentication authentication) {
        return "Basic " + java.util.Base64.getEncoder().encodeToString(
                (authentication.getUsername() + ":" + authentication.getPassword()).getBytes()
        );
    }
}
