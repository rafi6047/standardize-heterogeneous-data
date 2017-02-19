package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Datastore extends AbstractModel {
    private boolean encrypted;

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }
}
