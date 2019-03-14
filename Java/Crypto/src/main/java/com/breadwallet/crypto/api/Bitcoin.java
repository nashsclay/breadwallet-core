package com.breadwallet.crypto.api;

public interface Bitcoin {

    Currency CURRENCY = new Currency ("BTC", "\u20BF", "Bitcoin", 8, "SAT", "sat");

    Unit SATOSHI = CURRENCY.baseUnit;
    Unit BITCOIN = CURRENCY.defaultUnit;

    Network TESTNET = CryptoApi.provider().networkFactory().testnet();

    Network MAINNET = CryptoApi.provider().networkFactory().mainnet();
}
