package se.kth.epe.degreeproject.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class OperatingSystem {
    private boolean aSLR;
    private boolean dEP;
    private boolean staticARPTables;
    private boolean patched;
    private boolean antiMalware;
    private boolean hostFirewall;

    public boolean isaSLR() {
        return aSLR;
    }

    public void setaSLR(boolean aSLR) {
        this.aSLR = aSLR;
    }

    public boolean isdEP() {
        return dEP;
    }

    public void setdEP(boolean dEP) {
        this.dEP = dEP;
    }

    public boolean isStaticARPTables() {
        return staticARPTables;
    }

    public void setStaticARPTables(boolean staticARPTables) {
        this.staticARPTables = staticARPTables;
    }

    public boolean isPatched() {
        return patched;
    }

    public void setPatched(boolean patched) {
        this.patched = patched;
    }

    public boolean isAntiMalware() {
        return antiMalware;
    }

    public void setAntiMalware(boolean antiMalware) {
        this.antiMalware = antiMalware;
    }

    public boolean isHostFirewall() {
        return hostFirewall;
    }

    public void setHostFirewall(boolean hostFirewall) {
        this.hostFirewall = hostFirewall;
    }
}
