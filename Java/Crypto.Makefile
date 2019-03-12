DIR=$(shell pwd)

#
# Java
#

JAVA_DIR=${JAVA_HOME}

JAVA_LIB=

JNI_LIB=libCrypto.jnilib

JNI_SDIR=Crypto/src/main/cpp/jni

JNI_SRCS=

JNI_OBJS=$(JNI_SRCS:.c=.o)

JNI_HDRS=$(JNI_SRCS:.c=.h)

JAVA_SDIR=Crypto/src/main/java/com/breadwallet/crypto

JAVA_SRCS=$(JAVA_SDIR)/api/bitcoin/BitcoinBackendClient.java \
	$(JAVA_SDIR)/api/bitcoin/BitcoinChainParams.java \
	$(JAVA_SDIR)/api/bitcoin/BitcoinMasterPubKey.java \
	$(JAVA_SDIR)/api/bitcoin/BitcoinPersistenceClient.java \
	$(JAVA_SDIR)/api/bitcoin/BitcoinWalletManagerListener.java \
	$(JAVA_SDIR)/api/event/TransferEvent.java \
	$(JAVA_SDIR)/api/event/WalletEvent.java \
	$(JAVA_SDIR)/api/event/WalletEventBalanceUpdated.java \
	$(JAVA_SDIR)/api/event/WalletEventCreated.java \
	$(JAVA_SDIR)/api/event/WalletEventDeleted.java \
	$(JAVA_SDIR)/api/event/WalletManagerEvent.java \
	$(JAVA_SDIR)/api/event/WalletManagerEventConnected.java \
	$(JAVA_SDIR)/api/event/WalletManagerEventDisconnected.java \
	$(JAVA_SDIR)/api/event/WalletManagerEventSyncStarted.java \
	$(JAVA_SDIR)/api/event/WalletManagerEventSyncStopped.java \
	$(JAVA_SDIR)/api/Account.java \
	$(JAVA_SDIR)/api/Network.java \
	$(JAVA_SDIR)/api/WalletManager.java \
	$(JAVA_SDIR)/api/WalletManagerBackendClient.java \
	$(JAVA_SDIR)/api/WalletManagerListener.java \
	$(JAVA_SDIR)/api/WalletManagerPersistenceClient.java \
	$(JAVA_SDIR)/core/bitcoin/jni/CoreBitcoinChainParams.java \
	$(JAVA_SDIR)/core/bitcoin/jni/CoreBitcoinMasterPubKey.java \
	$(JAVA_SDIR)/core/bitcoin/jni/CoreBitcoinWalletManager.java \
	$(JAVA_SDIR)/core/bitcoin/jni/CoreBitcoinWalletManagerClient.java \
	$(JAVA_SDIR)/core/bitcoin/BitcoinChainParams.java \
	$(JAVA_SDIR)/core/bitcoin/BitcoinChainParamsAdapter.java \
	$(JAVA_SDIR)/core/bitcoin/BitcoinMasterPubKey.java \
	$(JAVA_SDIR)/core/bitcoin/BitcoinMasterPubKeyAdapter.java \
	$(JAVA_SDIR)/core/bitcoin/BitcoinNetworks.java \
	$(JAVA_SDIR)/core/bitcoin/BitcoinWalletManager.java \
	$(JAVA_SDIR)/core/common/jni/JniReference.java \
	$(JAVA_SDIR)/core/common/CommonWalletManager.java \
	$(JAVA_SDIR)/core/jni/Bip39.java \
	$(JAVA_SDIR)/core/Account.java \
	$(JAVA_SDIR)/core/CoreCrypto.java

JAVA_OBJS=$(JAVA_SRCS:.java=.class)

#
# Core
#

C_SDIR=Crypto/src/main/cpp/core

CORE_SRCS=

CORE_OBJS=$(CORE_SRCS:.c=.o)

ETH_SRCS=

ETH_OBJS=$(ETH_SRCS:.c=.o)

#
# Targets
#

compile: $(JNI_LIB) java_comp

$(JNI_LIB): $(JNI_OBJS) $(CORE_OBJS)
	cc -dynamiclib -o $(JNI_LIB) $(JNI_OBJS) $(CORE_OBJS)

java_comp:	FORCE
	@mkdir -p build
	@echo "Crypto Java"
	@javac -d build $(JAVA_SRCS)

jni_hdr_crypto:	FORCE
	@echo Crypto JNI Headers
	@(cd build/com/breadwallet/crypto/core/bitcoin/jni; \
	  for class in *.class; do \
	      javah -jni -d $(DIR)/$(JNI_SDIR) -classpath $(DIR)/build com.breadwallet.crypto.core.bitcoin.jni.$${class%%.class}; \
	  done)
	@(cd build/com/breadwallet/crypto/core/common/jni; \
	  for class in *.class; do \
	      javah -jni -d $(DIR)/$(JNI_SDIR) -classpath $(DIR)/build com.breadwallet.crypto.core.common.jni.$${class%%.class}; \
	  done)
	@(cd build/com/breadwallet/crypto/core/jni; \
	  for class in *.class; do \
	      javah -jni -d $(DIR)/$(JNI_SDIR) -classpath $(DIR)/build com.breadwallet.crypto.core.jni.$${class%%.class}; \
	  done)


jni_hdr: java_comp jni_hdr_crypto

clean:
	rm -rf build $(JNI_OBJS) $(CORE_OBJS) $(JAVA_OBJS) $(JNI_LIB)

FORCE:

# test: $(JNI_LIB) java_comp
# 	java -Xss1m -Dwallet.test -classpath build -Djava.library.path=. \
# 		 com.breadwallet.core.test.BRWalletManager $(ARGS) # -D.

# debug: $(JNI_LIB) java_comp
# 	java -Xss1m -Xdebug -Xrunjdwp:transport=dt_socket,address=8008,server=y,suspend=n \
# 		 -Dwallet.test -classpath build -Djava.library.path=. \
# 		 com.breadwallet.core.test.BRWalletManager $(ARGS) # -D.
