package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinChainParams;

// TODO: Add MAINNET
// TODO: Review visibility (for class, methods, fields, etc.)
public class BitcoinChainParams implements com.breadwallet.crypto.api.bitcoin.BitcoinChainParams {

    public static BitcoinChainParams TESTNET = new BitcoinChainParams(CoreBitcoinChainParams.TESTNET);

    /* package */ final CoreBitcoinChainParams chainParams;

    private BitcoinChainParams(CoreBitcoinChainParams chainParams) {
        this.chainParams = chainParams;
    }
}
