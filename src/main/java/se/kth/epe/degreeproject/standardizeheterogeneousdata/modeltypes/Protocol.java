package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Protocol extends AbstractModel {
    private boolean nonce;
    private boolean encrypted;
    private boolean authenticated;

    public boolean isNonce() {
        return nonce;
    }

    public void setNonce(boolean nonce) {
        this.nonce = nonce;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
