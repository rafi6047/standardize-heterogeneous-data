package se.kth.epe.degreeproject.standardizeheterogeneousdata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Rafi on 2017-02-22.
 */
@RestController
@RequestMapping("/file")
public class FileRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileRestController.class);

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
    public Map<String, Object> getApplicationOrServiceInfo(@PathVariable String fileType, @RequestBody String requestBody) {

        FileType fileTypeEnum = FileType.contains(fileType);
        if (fileTypeEnum == null) {
            LOGGER.info("File type not supported: " + fileType);
            return new HashMap<>();
        }

        LOGGER.info("Received request for file type: " + fileType);
        LOGGER.debug("File content: \n" + requestBody);

        return adapter.parseFile(requestBody, fileTypeEnum);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.POST, value = "/autoReadAndWrite")
    public Response autoGenerateOutputFileFromInputFile() throws IOException {
        final String inputFilesLocation = "/Users/Rafi/IntellijProjects/standardize-heterogeneous-data/src/main/resources/inputfiles/";
        final String outputFilesLocation = "/Users/Rafi/IntellijProjects/standardize-heterogeneous-data/src/main/resources/outputfiles";

        final File inputFolder = new File(inputFilesLocation);
        final File outputFolder = new File(outputFilesLocation);

        deleteFilesInOutputDirectory(outputFolder);

        listFilesinFolder(inputFolder, outputFolder);

        return Response.ok().build();
    }

    private void deleteFilesInOutputDirectory(File outputFolder) throws IOException {
        FileUtils.deleteDirectory(outputFolder);

    }

    private void listFilesinFolder(final File inputFolder, final File outputFolder) throws IOException {
        for (final File fileEntry : inputFolder.listFiles()) {
            if (fileEntry.isHidden()) {
                continue;
            }
            if (fileEntry.isDirectory()) {
                readAndWriteFiles(fileEntry, outputFolder);
            }
        }
    }

    private void readAndWriteFiles(final File directory, final File outputDirectory) throws IOException {
        for (final File file : directory.listFiles()) {
            if (file.isHidden()) {
                continue;
            }
            // read
            Map<String, Object> applicationInfo;
            String applicationInfoString;
            String baseName;
            String extension;
            File finalOutputDirectory;

            String fileType = directory.getName();
            Charset charset = Charset.defaultCharset();

            String[] directoryCharsetArray = directory.getName().split("_");
            if (directoryCharsetArray.length > 1) {
                charset = Charset.forName(directoryCharsetArray[1]);
                fileType = directoryCharsetArray[0];
            }


            String content = "";
            try {
                content = String.join("\n", Files.readAllLines((Paths.get(file.getAbsolutePath())), charset));
            } catch (MalformedInputException mie) {
                LOGGER.info("MalformedInputException for file: " + directory.getName() + "/" + file.getName());
            }
            applicationInfo = getApplicationOrServiceInfo(fileType, content);
            applicationInfoString = objectMapper.writeValueAsString(applicationInfo);

            // write
            baseName = FilenameUtils.getBaseName(file.getName());
            extension = FilenameUtils.getExtension(file.getName());
            finalOutputDirectory = new File(outputDirectory + "/" + directory.getName());
            finalOutputDirectory.mkdirs();


            Files.write(Paths.get(finalOutputDirectory + "/" + baseName + "_output." + extension), applicationInfoString.getBytes(), StandardOpenOption.CREATE);
        }
    }
}