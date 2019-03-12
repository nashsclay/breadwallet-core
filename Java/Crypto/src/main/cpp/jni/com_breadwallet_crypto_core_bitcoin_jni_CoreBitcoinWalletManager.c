//  Created by Michael Carrara on 3/19/2019
//  Copyright (c) 2019 breadwallet LLC.
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.

#include <stdlib.h>
#include <assert.h>

#include "BRWalletManager.h"

#include "BRCryptoJni.h"
#include "com_breadwallet_crypto_core_bitcoin_jni_CoreBitcoinWalletManager.h"

// TODO: Add defensive checks on inputs
// TODO: Wire in callbacks to Java layer
// TODO: Re-write using personal coding style

static void
transactionEventCallback (BRWalletManager manager,
                          BRWallet *wallet,
                          BRTransaction *transaction,
                          BRTransactionEvent event);

static void
walletEventCallback (BRWalletManager manager,
                     BRWallet *wallet,
                     BRWalletEvent event);

static void
walletManagerEventCallback (BRWalletManager manager,
                            BRWalletManagerEvent event);

JNIEXPORT jlong JNICALL Java_com_breadwallet_crypto_core_bitcoin_jni_CoreBitcoinWalletManager_createBitcoinWalletManager (
        JNIEnv * env,
        jclass thisClass,
        jobject masterPublicKeyObject,
        jobject chainParamsObject,
        jint earliestKeyTime,
        jstring storagePathString)
{
    BRMasterPubKey *masterPubKey = (BRMasterPubKey *) getJNIReference(env, masterPublicKeyObject);
    BRChainParams *chainParams   = (BRChainParams *) getJNIReference(env, chainParamsObject);
    const char *storagePath = (*env)->GetStringUTFChars (env, storagePathString, 0);
    assert (earliestKeyTime >= 0);
    BRWalletManagerClient client = { transactionEventCallback, walletEventCallback, walletManagerEventCallback };

    BRWalletManager *walletManager = BRWalletManagerNew(client, *masterPubKey, chainParams, earliestKeyTime, storagePath);
    assert (walletManager);

    return (jlong) walletManager;
}

JNIEXPORT void JNICALL Java_com_breadwallet_crypto_core_bitcoin_jni_CoreBitcoinWalletManager_connect (
        JNIEnv * env,
        jobject thisObject)
{
    BRWalletManager *walletManager = (BRWalletManager *) getJNIReference(env, thisObject);
    BRWalletManagerConnect(walletManager);
}

JNIEXPORT void JNICALL Java_com_breadwallet_crypto_core_bitcoin_jni_CoreBitcoinWalletManager_disposeNative (
        JNIEnv * env,
        jobject thisObject)
{
    BRWalletManager *walletManager = (BRWalletManager *) getJNIReference(env, thisObject);
    if (NULL != walletManager) BRWalletManagerFree(walletManager);
}

static void
transactionEventCallback (BRWalletManager manager,
                          BRWallet *wallet,
                          BRTransaction *transaction,
                          BRTransactionEvent event) {
    printf ("TST: TransactionEvent: %d\n", event.type);
}

static void
walletEventCallback (BRWalletManager manager,
                     BRWallet *wallet,
                     BRWalletEvent event) {
    printf ("TST: WalletEvent: %d\n", event.type);
}

static void
walletManagerEventCallback (BRWalletManager manager,
                            BRWalletManagerEvent event) {
    printf ("TST: WalletManagerEvent: %d\n", event.type);
}
