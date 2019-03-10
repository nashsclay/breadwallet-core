package com.breadwallet.crypto.core.common;

import com.breadwallet.crypto.api.Account;
import com.breadwallet.crypto.api.Network;
import com.breadwallet.crypto.api.WalletManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// TODO: Add parameter validation
// TODO: Review visibility (for class, methods, fields, etc.)
public abstract class CommonWalletManager implements WalletManager {

    protected static Executor DEFAULT_EXECUTOR = Executors.newSingleThreadExecutor();

    protected final Account account;
    protected final Network network;
    protected final Mode mode;
    protected final long earliestKeyTime;
    protected final String storagePath;
    protected final Executor listenerExecutor;

    protected State state;

    public CommonWalletManager(Executor listenerExecutor,
                               Account account,
                               Network network,
                               Mode mode,
                               long earliestKeyTime,
                               String storagePath) {
        // TODO: Check arguments for validity (i.e. null, correctness, etc.)
        this.listenerExecutor = listenerExecutor;
        this.account = account;
        this.network = network;
        this.mode = mode;
        this.earliestKeyTime = earliestKeyTime;
        this.storagePath = storagePath;
        this.state = State.CREATED;
    }


//    public interface Listener {
//
//    }
//
//    public interface WalletManagerPersistenceClient {
//
//    }
//
//    public interface BackendClient {
//
//    }
//
//    public final Account account;
//
//    public final Network network;
//
//    public final com.breadwallet.crypto.WalletManager.Listener listener;

/*
    /// The listener receives Wallet, Transfer and perhaps other asynchronous events.
    var listener: WalletManagerListener { get }

    /// The account
    var account: Account { get }

    /// The network
    var network: Network { get }

    /// The primaryWallet
    var primaryWallet: Wallet { get }

    /// The managed wallets - often will just be [primaryWallet]
    var wallets: [Wallet] { get }

    // The mode determines how the manager manages the account and wallets on network
    var mode: WalletManagerMode { get }

    // The file-system path to use for persistent storage.
    var path: String { get }  // persistent storage

    var state: WalletManagerState { get }

    #if false
    /// The default WalletFactory for creating wallets.
    var walletFactory: WalletFactory { get set }
    #endif

    /// Connect to network and begin managing wallets for account
    func connect ()

    /// Disconnect from the network.
    func disconnect ()

    /// isConnected
    /// sync(...)
    /// isSyncing

    /// sign(transfer)
    /// submit(transfer)
*/
//
//    protected WalletManager (com.breadwallet.crypto.WalletManager.Listener listener, Account account, Network network) {
//        this.listener = listener;
//        this.account = account;
//        this.network = network;
//    }
}
