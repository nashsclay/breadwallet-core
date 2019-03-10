package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.core.common.jni.JniReference;

import java.lang.ref.WeakReference;

public class CoreBitcoinWalletManager
        extends JniReference implements CoreBitcoinWalletManagerClient {

    private static native long createBitcoinWalletManager(CoreBitcoinMasterPubKey mpk,
                                                          CoreBitcoinChainParams params,
                                                          int earliestKeyTime,
                                                          String storagePath);

    private final WeakReference<CoreBitcoinWalletManagerClient> client;

    public CoreBitcoinWalletManager(CoreBitcoinWalletManagerClient client,
                                    CoreBitcoinMasterPubKey mpk,
                                    CoreBitcoinChainParams params,
                                    int earliestKeyTime,
                                    String storagePath) {
        // client needs to be in place before BRWalletManagerNew is invoked, as it calls back
        this.client = new WeakReference<>(client);
        this.jniReferenceAddress = createBitcoinWalletManager(mpk, params, earliestKeyTime, storagePath);
    }

    public native CoreBitcoinPeerManager getPeerManager();

    public native CoreBitcoinWallet getWallet();

    public native void connect();

    // CoreBitcoinWalletManagerClient

    @Override
    public void handleTransactionEvent() {
        CoreBitcoinWalletManagerClient c = client.get();
        if (null != c) {
            c.handleTransactionEvent();
        }
    }

    @Override
    public void handleWalletEvent() {
        CoreBitcoinWalletManagerClient c = client.get();
        if (null != c) {
            c.handleWalletEvent();
        }
    }

    @Override
    public void handleWalletManagerEvent() {
        CoreBitcoinWalletManagerClient c = client.get();
        if (null != c) {
            c.handleWalletManagerEvent();
        }
    }
}
