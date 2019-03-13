package com.breadwallet.crypto.api.bitcoin;

import com.breadwallet.crypto.api.CryptoApi;
import com.breadwallet.crypto.api.Currency;
import com.breadwallet.crypto.api.Network;
import com.breadwallet.crypto.api.Unit;

public interface Bitcoin {

    Currency CURRENCY = new Currency ("BTC", "\u20BF", "Bitcoin", 8, "SAT", "sat");

    Unit SAT = CURRENCY.baseUnit;
    Unit BITCOIN = CURRENCY.defaultUnit;

    Network TESTNET = CryptoApi.cryptoApiProvider().networkProvider().testnet();

    Network MAINNET = CryptoApi.cryptoApiProvider().networkProvider().mainnet();
}
