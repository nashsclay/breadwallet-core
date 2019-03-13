package com.breadwallet.crypto.api;

public final class AccountFactory {

    public static Account create(String phrase) {
        return CryptoApi
                .getCryptoApiProvider()
                .accountProvider()
                .create(phrase);
    }

    public static Account create(byte[] seed) {
        return CryptoApi
                .getCryptoApiProvider()
                .accountProvider()
                .create(seed);
    }
}
