package serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import model.Student;
import model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serialization.SerializationStructure;
import statistics.Statistics;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collection;

public class XMLConverter {

    private static final Logger logger = LoggerFactory.getLogger(XMLConverter.class);

    private XMLConverter() {
    }

    public static void serializeToXML(SerializationStructure structure, Path filePath) {
        String file = "" + filePath + java.time.LocalDate.now() + ".xml";
        logger.info("Serialization to " + file + " started");
        try (OutputStream os = new FileOutputStream(file)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(SerializationStructure.class, Student.class,
                                                              University.class, Statistics.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(structure, os);
        } catch (Exception e) {
            logger.error("Serialization to " + file + " failed", e);
            throw new RuntimeException(e);
        }
        logger.info("Serialization to " + file + " ended successfully");
    }
}

