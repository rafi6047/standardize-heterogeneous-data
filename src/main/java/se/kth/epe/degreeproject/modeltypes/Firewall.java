package se.kth.epe.degreeproject.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Firewall {
    private boolean knownRuleSet;
    private boolean enabled;

    public boolean isKnownRuleSet() {
        return knownRuleSet;
    }

    public void setKnownRuleSet(boolean knownRuleSet) {
        this.knownRuleSet = knownRuleSet;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
