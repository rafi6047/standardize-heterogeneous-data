package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class WebApplicationFirewall extends AbstractModel {
    private boolean expertTuned;
    private boolean blackBoxTuned;
    private boolean monitored;
    private boolean tuningEffort;

    public boolean isExpertTuned() {
        return expertTuned;
    }

    public void setExpertTuned(boolean expertTuned) {
        this.expertTuned = expertTuned;
    }

    public boolean isBlackBoxTuned() {
        return blackBoxTuned;
    }

    public void setBlackBoxTuned(boolean blackBoxTuned) {
        this.blackBoxTuned = blackBoxTuned;
    }

    public boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(boolean monitored) {
        this.monitored = monitored;
    }

    public boolean isTuningEffort() {
        return tuningEffort;
    }

    public void setTuningEffort(boolean tuningEffort) {
        this.tuningEffort = tuningEffort;
    }
}
