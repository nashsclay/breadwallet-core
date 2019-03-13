package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.api.Account;
import com.breadwallet.crypto.api.Network;
import com.breadwallet.crypto.api.WalletManager;
import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;
import com.breadwallet.crypto.api.events.wallet.BalanceUpdatedWalletEvent;
import com.breadwallet.crypto.api.events.wallet.CreatedWalletEvent;
import com.breadwallet.crypto.api.events.wallet.DeletedWalletEvent;
import com.breadwallet.crypto.api.events.walletmanager.ConnectedWalletManagerEvent;
import com.breadwallet.crypto.api.events.walletmanager.DisconnectedWalletManagerEvent;
import com.breadwallet.crypto.api.events.walletmanager.SyncStartedWalletManagerEvent;
import com.breadwallet.crypto.api.events.walletmanager.SyncStoppedWalletManagerEvent;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinChainParams;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinMasterPubKey;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinWalletManager;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinWalletManagerClient;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// TODO: Add parameter validation
// TODO: Review visibility (for class, methods, fields, etc.)
// TODO: Consider exposing this via a registered factory
public final class BitcoinWalletManager
        implements WalletManager, CoreBitcoinWalletManagerClient {

    protected static Executor DEFAULT_EXECUTOR = Executors.newSingleThreadExecutor();

    protected final WeakReference<BitcoinWalletManagerListener> listener;
    protected final CoreBitcoinWalletManager coreWalletManager;

    protected final BitcoinPersistenceClient persistenceClient;
    protected final BitcoinBackendClient backendClient;

    protected final Account account;
    protected final Network network;
    protected final Mode mode;
    protected final long earliestKeyTime;
    protected final String storagePath;
    protected final Executor listenerExecutor;

    protected State state;

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

    public BitcoinWalletManager(Executor listenerExecutor,
                                BitcoinWalletManagerListener listener,
                                Account account,
                                Network network,
                                Mode mode,
                                int earliestKeyTime,
                                String storagePath,
                                BitcoinPersistenceClient persistenceClient,
                                BitcoinBackendClient backendClient) {
        CoreBitcoinChainParams chainParams = BitcoinChainParamsAdapter.from(network.chainParams()).chainParams;
        CoreBitcoinMasterPubKey masterPubKey = BitcoinMasterPubKeyAdapter.from(account.masterPublicKey()).masterPubKey;

        this.listenerExecutor = listenerExecutor;
        this.account = account;
        this.network = network;
        this.mode = mode;
        this.earliestKeyTime = earliestKeyTime;
        this.storagePath = storagePath;
        this.state = State.CREATED;

        this.persistenceClient = persistenceClient;
        this.backendClient = backendClient;

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
                l.handleWalletEvent(this, null, new CreatedWalletEvent());
            }
        });
    }

    @Override
    public void handleWalletBalanceUpdated(long wid, long satoshi) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Get wallet using wid
                l.handleWalletEvent(this, null, new BalanceUpdatedWalletEvent(satoshi));
            }
        });
    }

    @Override
    public void handleWalletDeleted(long wid) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                // TODO: Get wallet using wid
                l.handleWalletEvent(this, null, new DeletedWalletEvent());
            }
        });
    }

    @Override
    public void handleWalletManagerConnected() {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new ConnectedWalletManagerEvent());
            }
        });
    }

    @Override
    public void handleWalletManagerDisconnected() {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new DisconnectedWalletManagerEvent());
            }
        });
    }

    @Override
    public void handleWalletManagerSyncStarted() {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new SyncStartedWalletManagerEvent());
            }
        });
    }

    @Override
    public void handleWalletManagerSyncStopped(int errorCode) {
        listenerExecutor.execute(() -> {
            BitcoinWalletManagerListener l = listener.get();
            if (l != null) {
                l.handleManagerEvent(this, new SyncStoppedWalletManagerEvent(errorCode));
            }
        });
    }
}
