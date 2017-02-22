package se.kth.epe.degreeproject.standardizeheterogeneousdata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.modelcreation.Models;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes.AbstractModel;

import javax.annotation.PostConstruct;

/**
 * Created by Rafi on 2017-02-22.
 */
@RestController
public class ModelRestController {

    @Autowired
    private Models models;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setup() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @RequestMapping("/getmodel/{modelname}")
    public AbstractModel getModel(@PathVariable("modelname") String modelName) {
        ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return models.getModel(modelName);
    }
}