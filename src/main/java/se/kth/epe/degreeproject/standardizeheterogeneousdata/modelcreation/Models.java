package se.kth.epe.degreeproject.standardizeheterogeneousdata.modelcreation;

import se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes.OperatingSystem;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Models {

    public static void main(String[] args) {
        Models models = new Models();
        models.createModels();
    }

    private void createModels() {

        OperatingSystem windows10 = new OperatingSystem();
        windows10.setModelName("windows10");
        windows10.setAntiMalware(true);
        windows10.setaSLR(false);
        windows10.setdEP(true);
        windows10.setHostFirewall(false);
        windows10.setPatched(true);
        windows10.setStaticARPTables(false);

        System.out.println(windows10);

    }

}
