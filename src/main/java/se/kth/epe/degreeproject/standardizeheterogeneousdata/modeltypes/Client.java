package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Client extends AbstractModel {
    private boolean patched;

    public boolean isPatched() {
        return patched;
    }

    public void setPatched(boolean patched) {
        this.patched = patched;
    }
}
