package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class OperatingSystem extends AbstractModel {
    private boolean aSLR;
    private boolean dEP;
    private boolean staticARPTables;
    private boolean patched;
    private boolean antiMalware;
    private boolean hostFirewall;

    public OperatingSystem() {
        this.setModelType(this.getClass().getSimpleName());
    }

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

    @Override
    public String toString() {
        return super.toString() + "OperatingSystem{" +
                "aSLR=" + aSLR +
                ", dEP=" + dEP +
                ", staticARPTables=" + staticARPTables +
                ", patched=" + patched +
                ", antiMalware=" + antiMalware +
                ", hostFirewall=" + hostFirewall +
                "} ";
    }
}
