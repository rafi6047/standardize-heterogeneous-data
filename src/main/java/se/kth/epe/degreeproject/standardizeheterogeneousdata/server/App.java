package se.kth.epe.degreeproject.standardizeheterogeneousdata.server;

import com.google.gson.GsonBuilder;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.modelcreation.Models;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes.OperatingSystem;
import com.google.gson.Gson;

import static spark.Spark.get;

/**
 * Created by Rafi on 2017-02-20.
 */
public class App {

    private static Models models = new Models();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        models.getModel("windows10");
        get("/getmodel/:modelName", "application/json", (request, response) -> (OperatingSystem)models.getModel(request.params(":modelName")), gson::toJson);

    }
}
