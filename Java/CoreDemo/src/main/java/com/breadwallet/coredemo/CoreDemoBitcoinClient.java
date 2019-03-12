package com.breadwallet.coredemo;

import android.util.Log;

import com.breadwallet.crypto.api.Network;
import com.breadwallet.crypto.api.Transfer;
import com.breadwallet.crypto.api.Wallet;
import com.breadwallet.crypto.api.WalletManager;
import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;
import com.breadwallet.crypto.api.event.TransferEvent;
import com.breadwallet.crypto.api.event.WalletEvent;
import com.breadwallet.crypto.api.event.WalletManagerEvent;
import com.breadwallet.crypto.core.Account;
import com.breadwallet.crypto.core.CoreCrypto;
import com.breadwallet.crypto.core.bitcoin.BitcoinWalletManager;

import java.util.Map;

public class CoreDemoBitcoinClient
        implements BitcoinWalletManagerListener, BitcoinBackendClient, BitcoinPersistenceClient {

    private static final String TAG = "CoreDemoBitcoinClient";

    static { CoreCrypto.init(); }

    private final WalletManager walletManager;

    public CoreDemoBitcoinClient(Network network, String storagePath, String paperKey) {
        this.walletManager = new BitcoinWalletManager(
                this,
                new Account(paperKey),
                network,
                WalletManager.Mode.API_WITH_P2P_SUBMIT,
                1543190400,
                storagePath,
                this,
                this);
    }

    public void connect() {
        walletManager.connect();
    }

    public void disconnect() {
        walletManager.disconnect();
    }

    // BitcoinWalletManagerListener

    @Override
    public void handleManagerEvent(WalletManager manager, WalletManagerEvent event) {
        Log.d(TAG, "handleManagerEvent: " + event.toString());
    }

    @Override
    public void handleTransferEvent(WalletManager manager, Wallet wallet, Transfer transfer, TransferEvent event) {
        Log.d(TAG, "handleTransferEvent: " + event.toString());
    }

    @Override
    public void handleWalletEvent(WalletManager manager, Wallet wallet, WalletEvent event) {
        Log.d(TAG, "handleWalletEvent: " + event.toString());
    }

    // BitcoinBackendClient

    @Override
    public boolean isReachable() {
        return true;
    }

    // BitcoinPersistenceClient

    @Override
    public void savePeers(WalletManager walletManager, Map<String, String> data) {

    }

    @Override
    public void saveBlocks(WalletManager walletManager, Map<String, String> data) {

    }

    @Override
    public void changeTransaction(WalletManager walletManager, ChangeType type, String hash, String data) {

    }
}
