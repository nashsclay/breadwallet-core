package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.core.common.jni.JniReference;

import java.lang.ref.WeakReference;

// TODO: Add parameter validation
// TODO: Hook up listener callbacks
// TODO: Review visibility (for class, methods, fields, etc.)
public class CoreBitcoinWalletManager extends JniReference {

    private static native void initializeNative();

    private static native long createBitcoinWalletManager(CoreBitcoinMasterPubKey mpk,
                                                          CoreBitcoinChainParams params,
                                                          int earliestKeyTime,
                                                          String storagePath);

    static { initializeNative(); }

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

    protected native void disposeNative();
}
