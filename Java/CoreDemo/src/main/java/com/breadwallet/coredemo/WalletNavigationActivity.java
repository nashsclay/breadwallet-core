package com.breadwallet.coredemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.breadwallet.core.ethereum.BREthereumNetwork;
import com.breadwallet.crypto.core.bitcoin.BitcoinNetworks;

import java.io.File;

public class WalletNavigationActivity extends AppCompatActivity {

    CoreDemoBitcoinClient btcClient = null;
    CoreDemoEthereumClient ethClient = null;

    private void deleteRecursively (File file) {
        if (file.isDirectory())
            for (File child : file.listFiles()) {
                deleteRecursively(child);
            }
        file.delete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println ("Starting");

        // Bitcoin

        {
            File storageFile = new File(getFilesDir(), "core");
            if (storageFile.exists()) deleteRecursively(storageFile);
            storageFile.mkdirs();

            btcClient = new CoreDemoBitcoinClient(BitcoinNetworks.TESTNET,
                    storageFile.getAbsolutePath(),
                    "boring head harsh green empty clip fatal typical found crane dinner timber");

            btcClient.connect();

            System.gc();
        }

        // Ethereum

        {
            File storageFile = new File(getFilesDir(), "core");
            if (storageFile.exists()) deleteRecursively(storageFile);
            storageFile.mkdirs();

            ethClient = new CoreDemoEthereumClient(BREthereumNetwork.mainnet,
                    storageFile.getAbsolutePath(),
                    "boring head harsh green empty clip fatal typical found crane dinner timber");

            ethClient.ewm.announceToken("0x722dd3f80bac40c951b51bdd28dd19d435762180",
                    "BRD",
                    "BRD Token",
                    "",
                    18,
                    "92000",
                    "1000000000",
                    0);
            ethClient.ewm.connect();

            System.gc();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_navigation);
    }
}
