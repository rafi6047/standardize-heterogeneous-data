package se.kth.epe.degreeproject.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Network {
    private boolean portSecurity;
    private boolean dNSSec;

    public boolean isPortSecurity() {
        return portSecurity;
    }

    public void setPortSecurity(boolean portSecurity) {
        this.portSecurity = portSecurity;
    }

    public boolean isdNSSec() {
        return dNSSec;
    }

    public void setdNSSec(boolean dNSSec) {
        this.dNSSec = dNSSec;
    }
}
