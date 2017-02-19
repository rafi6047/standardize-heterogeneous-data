package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class ZoneManagement extends AbstractModel {
    private boolean patchManagement;
    private boolean hostHardening;
    private boolean antiMalwarePolicy;
    private boolean changeControl;
    private boolean hostFirewall;

    public boolean isPatchManagement() {
        return patchManagement;
    }

    public void setPatchManagement(boolean patchManagement) {
        this.patchManagement = patchManagement;
    }

    public boolean isHostHardening() {
        return hostHardening;
    }

    public void setHostHardening(boolean hostHardening) {
        this.hostHardening = hostHardening;
    }

    public boolean isAntiMalwarePolicy() {
        return antiMalwarePolicy;
    }

    public void setAntiMalwarePolicy(boolean antiMalwarePolicy) {
        this.antiMalwarePolicy = antiMalwarePolicy;
    }

    public boolean isChangeControl() {
        return changeControl;
    }

    public void setChangeControl(boolean changeControl) {
        this.changeControl = changeControl;
    }

    public boolean isHostFirewall() {
        return hostFirewall;
    }

    public void setHostFirewall(boolean hostFirewall) {
        this.hostFirewall = hostFirewall;
    }
}
