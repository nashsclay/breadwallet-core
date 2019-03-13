package com.breadwallet.crypto.api.provider;

import com.breadwallet.crypto.api.Account;

public interface AccountProvider {

    Account create(String phrase);

    Account create(byte[] seed);
}
