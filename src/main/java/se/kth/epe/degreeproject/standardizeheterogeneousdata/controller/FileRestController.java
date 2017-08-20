package se.kth.epe.degreeproject.standardizeheterogeneousdata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter.Adapter;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter.FileType;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.modelcreation.Models;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rafi on 2017-02-22.
 */
@RestController
@RequestMapping("/file")
public class FileRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileRestController.class);

    @Autowired
    private Models models;

    @Autowired
    private Adapter adapter;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setup() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.POST, value = "/{fileType}")
    public Map<String, Object> getApplicationTypeForMSPowerShellFile(@PathVariable String fileType, @RequestBody String requestBody) {

        FileType fileTypeEnum = FileType.contains(fileType);
        if (fileTypeEnum == null) {
            LOGGER.info("File type not supported: " + fileType);
            return new HashMap<>();
        }

        LOGGER.info("Received request for file type: " + fileType + ". Content: \n" + requestBody);

        return adapter.parseFile(requestBody, fileTypeEnum);
    }
}