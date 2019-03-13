package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.provider.CryptoApiProvider;

// TODO: Review visibility (for class, methods, fields, etc.)
public class CryptoApi {

    private static CryptoApiProvider cryptoApiProvider;

    public static void setProvider(CryptoApiProvider provider) {
        cryptoApiProvider = provider;
    }

    public static CryptoApiProvider getCryptoApiProvider() {
        return cryptoApiProvider;
    }
}
