package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class User extends AbstractModel {
    private boolean securityAware;

    public boolean isSecurityAware() {
        return securityAware;
    }

    public void setSecurityAware(boolean securityAware) {
        this.securityAware = securityAware;
    }
}
