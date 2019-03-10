package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.api.Network;

// TODO: Review visibility (for class, methods, fields, etc.)
public class BitcoinNetworks {

    public static Network TESTNET = new Network(
            new Network.Bitcoin("BTC Testnet", 0x40, BitcoinChainParams.TESTNET));
}
