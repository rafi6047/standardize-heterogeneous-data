package se.kth.epe.degreeproject.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class AccessControl {

    private boolean noDefaultPasswords;
    private boolean passwordPolicyEnforcement;
    private boolean salting;
    private boolean enabled;
    private boolean backoff;
    private boolean hashedPasswordRepository;

    public boolean isNoDefaultPasswords() {
        return noDefaultPasswords;
    }

    public void setNoDefaultPasswords(boolean noDefaultPasswords) {
        this.noDefaultPasswords = noDefaultPasswords;
    }

    public boolean isPasswordPolicyEnforcement() {
        return passwordPolicyEnforcement;
    }

    public void setPasswordPolicyEnforcement(boolean passwordPolicyEnforcement) {
        this.passwordPolicyEnforcement = passwordPolicyEnforcement;
    }

    public boolean isSalting() {
        return salting;
    }

    public void setSalting(boolean salting) {
        this.salting = salting;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isBackoff() {
        return backoff;
    }

    public void setBackoff(boolean backoff) {
        this.backoff = backoff;
    }

    public boolean isHashedPasswordRepository() {
        return hashedPasswordRepository;
    }

    public void setHashedPasswordRepository(boolean hashedPasswordRepository) {
        this.hashedPasswordRepository = hashedPasswordRepository;
    }
}
