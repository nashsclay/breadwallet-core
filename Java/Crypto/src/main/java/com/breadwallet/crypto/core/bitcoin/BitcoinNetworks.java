package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.api.Network;

public class BitcoinNetworks {
    public static Network TESTNET = new Network(
            new Network.Bitcoin("BTC Testnet", 0x40, BitcoinChainParams.TESTNET));
}
