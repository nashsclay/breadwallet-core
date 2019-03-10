package com.breadwallet.crypto.core.common.jni;

public class JniReference {
    static {
        try { System.loadLibrary("core"); }
        catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            System.err.println ("Native code library failed to load.\\n\" + " + e);
        }
    }

    protected static boolean SHOW_FINALIZE = false;
    /**
     * C Pointer (as a Java long) to the underlying Breadwallet Core entity allocated from the
     * C heap memory.  The referenced Core entity is used to implement native functions that
     * call Core functions (and thus expect a Core entity).
     *
     * The address must be determined in a subclass specific way and thus must be provided in the
     * subclasses constructor.
     */
    protected long jniReferenceAddress;

    protected JniReference (long jniReferenceAddress)
    {
        this.jniReferenceAddress = jniReferenceAddress;
    }

    protected JniReference ()
    {
        this.jniReferenceAddress = 0;
    }

    //
    //
    //
    protected void finalize() throws Throwable {
        if (SHOW_FINALIZE) System.err.println("Finalize: " + toString());
        dispose();
    }

    public void dispose() {
        disposeNative();
    }

    public native void disposeNative();

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode()) + " JNI=" + Long.toHexString(jniReferenceAddress);
    }
}
