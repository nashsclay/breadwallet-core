package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.factories.AccountFactory;
import com.breadwallet.crypto.api.factories.NetworkFactory;
import com.breadwallet.crypto.api.factories.WalletManagerFactory;

// TODO: Add guard around initialization occuring once
public class CryptoApi {

    public interface Provider {

        AccountFactory accountFactory();

        WalletManagerFactory walletManagerFactory();

        NetworkFactory networkFactory();
    }

    private static Provider provider;

    public static void init(Provider provider) {
        CryptoApi.provider = provider;
    }

    /* package */ static Provider provider() {
        return provider;
    }
}
