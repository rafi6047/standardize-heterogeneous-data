package se.kth.epe.degreeproject.standardizeheterogeneousdata.modelcreation;

import se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes.AbstractModel;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes.OperatingSystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rafi on 2017-02-18.
 */
public class Models {

    private Map<String, AbstractModel> modelMap;

    public Models() {
        if (modelMap == null) {
            modelMap = createModels();
        }
    }

    private Map<String, AbstractModel> createModels() {
        Map<String, AbstractModel> modelMap = new HashMap<>();

        OperatingSystem windows10 = new OperatingSystem();
        windows10.setModelName("windows10");
        windows10.setAntiMalware(true);
        windows10.setaSLR(false);
        windows10.setdEP(true);
        windows10.setHostFirewall(false);
        windows10.setPatched(true);
        windows10.setStaticARPTables(false);

        modelMap.put("windows10", windows10);

        return modelMap;
    }

    public Map<String, AbstractModel> getModels() {
        return modelMap;
    }

    public AbstractModel getModel(String keyword) {
        if (modelMap.containsKey(keyword)) {
            return modelMap.get(keyword);
        }

        return null;
    }

}
