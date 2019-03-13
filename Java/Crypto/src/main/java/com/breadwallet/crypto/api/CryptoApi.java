package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.provider.CryptoApiProvider;

// TODO: Add guard around initialization occuring once
// TODO: Review visibility (for class, methods, fields, etc.)
public class CryptoApi {

    private static CryptoApiProvider cryptoApiProvider;

    public static void init(CryptoApiProvider provider) {
        cryptoApiProvider = provider;
    }

    public static CryptoApiProvider cryptoApiProvider() {
        return cryptoApiProvider;
    }
}
