package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.api.Account;
import com.breadwallet.crypto.api.Network;
import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinChainParams;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinMasterPubKey;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinPeerManager;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinWallet;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinWalletManager;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinWalletManagerClient;
import com.breadwallet.crypto.core.common.CommonWalletManager;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

// TODO: It would be nice to have this exposed via a factory since the ctors are all the same
public final class BitcoinWalletManager
        extends CommonWalletManager implements CoreBitcoinWalletManagerClient {

    private final WeakReference<BitcoinWalletManagerListener> listener;
    private final CoreBitcoinWalletManager coreWalletManager;
    private final CoreBitcoinPeerManager corePeerManager;
    private final CoreBitcoinWallet coreWallet;

    private final BitcoinPersistenceClient persistenceClient;
    private final BitcoinBackendClient backendClient;

    public BitcoinWalletManager(BitcoinWalletManagerListener listener,
                                Account account,
                                Network network,
                                Mode mode,
                                int earliestKeyTime,
                                String storagePath,
                                BitcoinPersistenceClient persistenceClient,
                                BitcoinBackendClient backendClient) {
        this(DEFAULT_EXECUTOR, listener, account, network, mode, earliestKeyTime, storagePath, persistenceClient, backendClient);
    }

    public BitcoinWalletManager(Executor listenerExector,
                                BitcoinWalletManagerListener listener,
                                Account account,
                                Network network,
                                Mode mode,
                                int earliestKeyTime,
                                String storagePath,
                                BitcoinPersistenceClient persistenceClient,
                                BitcoinBackendClient backendClient) {
        super(listenerExector, account, network, mode, earliestKeyTime, storagePath);

        this.persistenceClient = persistenceClient;
        this.backendClient = backendClient;

        CoreBitcoinChainParams chainParams = BitcoinChainParamsAdapter.from(network.chainParams()).chainParams;
        CoreBitcoinMasterPubKey masterPubKey = BitcoinMasterPubKeyAdapter.from(account.masterPublicKey()).masterPubKey;

        this.listener = new WeakReference<>(listener);
        this.coreWalletManager = new CoreBitcoinWalletManager(this, masterPubKey, chainParams, earliestKeyTime, storagePath);
        this.corePeerManager = coreWalletManager.getPeerManager();
        this.coreWallet = coreWalletManager.getWallet();
    }

    // WalletManager

    @Override
    public void connect() {
        coreWalletManager.connect();
        corePeerManager.connect();
    }

    // CoreWalletManagerClient

    @Override
    public void handleTransactionEvent() {
        // TODO: implement me
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleTransferEvent(this, null);
            }
        });
    }

    @Override
    public void handleWalletEvent() {
        // TODO: implement me
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleWalletEvent(this, null);
            }
        });
    }

    @Override
    public void handleWalletManagerEvent() {
        // TODO: implement me
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, null);
            }
        });
    }
}
