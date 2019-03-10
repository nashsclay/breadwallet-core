package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.event.TransferEvent;
import com.breadwallet.crypto.api.event.WalletEvent;
import com.breadwallet.crypto.api.event.WalletManagerEvent;

public interface WalletManagerListener {
    void handleManagerEvent(WalletManager manager, WalletManagerEvent event);
    void handleTransferEvent(WalletManager manager, TransferEvent event);
    void handleWalletEvent(WalletManager manager, WalletEvent event);
}
