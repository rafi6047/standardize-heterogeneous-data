package se.kth.epe.degreeproject.standardizeheterogeneousdata.dao;

import com.arangodb.ArangoConfigure;
import com.arangodb.ArangoDriver;

/**
 * Created by Rafi on 2017-04-26.
 */
public class ArangoDB {

    private static ArangoDB instance = null;
    private static ArangoDriver  arangoDriver = null;

    protected ArangoDB() {

    }

    public static ArangoDB getInstance() {
        if(instance == null) {
            instance = new ArangoDB();
        }
        return instance;
    }

    public static ArangoDriver getArangoDriver() {
        if (arangoDriver == null) {
            ArangoConfigure configure = new ArangoConfigure();
            configure.init();
            ArangoDriver arangoDriver = new ArangoDriver(configure);
        }
        return arangoDriver;
    }
}