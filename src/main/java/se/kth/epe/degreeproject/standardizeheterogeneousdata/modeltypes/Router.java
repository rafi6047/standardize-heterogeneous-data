package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Router extends AbstractModel {
    private boolean staticARPTables;

    public boolean isStaticARPTables() {
        return staticARPTables;
    }

    public void setStaticARPTables(boolean staticARPTables) {
        this.staticARPTables = staticARPTables;
    }
}
