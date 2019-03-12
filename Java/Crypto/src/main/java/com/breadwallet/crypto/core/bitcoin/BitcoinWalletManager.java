package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.api.Account;
import com.breadwallet.crypto.api.Network;
import com.breadwallet.crypto.api.WalletManager;
import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;
import com.breadwallet.crypto.api.event.WalletEvent;
import com.breadwallet.crypto.api.event.WalletEventBalanceUpdated;
import com.breadwallet.crypto.api.event.WalletEventCreated;
import com.breadwallet.crypto.api.event.WalletEventDeleted;
import com.breadwallet.crypto.api.event.WalletManagerEventConnected;
import com.breadwallet.crypto.api.event.WalletManagerEventDisconnected;
import com.breadwallet.crypto.api.event.WalletManagerEventSyncStarted;
import com.breadwallet.crypto.api.event.WalletManagerEventSyncStopped;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinChainParams;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinMasterPubKey;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinWalletManager;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinWalletManagerClient;
import com.breadwallet.crypto.core.common.CommonWalletManager;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

// TODO: Add parameter validation
// TODO: Review visibility (for class, methods, fields, etc.)
// TODO: Consider exposing this via a registered factory
public final class BitcoinWalletManager
        extends CommonWalletManager implements WalletManager, CoreBitcoinWalletManagerClient {

    protected final WeakReference<BitcoinWalletManagerListener> listener;
    protected final CoreBitcoinWalletManager coreWalletManager;

    protected final BitcoinPersistenceClient persistenceClient;
    protected final BitcoinBackendClient backendClient;

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
    }

    // WalletManager

    @Override
    public void connect() {
        coreWalletManager.connect();
    }

    @Override
    public void disconnect() {
        coreWalletManager.disconnect();
    }

    // CoreWalletManagerClient

    @Override
    public void handleTransactionAdded(long wid, long tid) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Implement this
            }
        });
    }

    @Override
    public void handleTransactionUpdated(long wid, long tid) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Implement this
            }
        });
    }

    @Override
    public void handleTransactionDeleted(long wid, long tid) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Implement this
            }
        });
    }

    @Override
    public void handleWalletCreated(long wid) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Get wallet using wid
                l.handleWalletEvent(this, null, new WalletEventCreated());
            }
        });
    }

    @Override
    public void handleWalletBalanceUpdated(long wid, long satoshi) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Get wallet using wid
                l.handleWalletEvent(this, null, new WalletEventBalanceUpdated(satoshi));
            }
        });
    }

    @Override
    public void handleWalletDeleted(long wid) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Get wallet using wid
                l.handleWalletEvent(this, null, new WalletEventDeleted());
            }
        });
    }

    @Override
    public void handleWalletManagerConnected() {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new WalletManagerEventConnected());
            }
        });
    }

    @Override
    public void handleWalletManagerDisconnected() {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new WalletManagerEventDisconnected());
            }
        });
    }

    @Override
    public void handleWalletManagerSyncStarted() {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new WalletManagerEventSyncStarted());
            }
        });
    }

    @Override
    public void handleWalletManagerSyncStopped(int errorCode) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new WalletManagerEventSyncStopped(errorCode));
            }
        });
    }
}
