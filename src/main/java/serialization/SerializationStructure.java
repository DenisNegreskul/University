package serialization;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Student;
import model.University;
import statistics.Statistics;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@XmlRootElement(name = "root")
public class SerializationStructure {

    @XmlElementWrapper(name = "studentsInfo")
    @XmlAnyElement
    @SerializedName("Студенты")
    private Collection<Student> students;

    @XmlElementWrapper(name = "universitiesInfo")
    @XmlAnyElement
    @SerializedName("Университеты")
    private Collection<University> universities;

    @XmlElementWrapper(name = "statisticalInfo")
    @XmlAnyElement
    @SerializedName("Статистика")
    private Collection<Statistics> statistics;

    @XmlElement
    @SerializedName("Создано в")
    private final String processedAt = java.time.ZonedDateTime.now().truncatedTo(ChronoUnit.MILLIS)
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    private SerializationStructure() {
    }

    public SerializationStructure(Collection<Student> students, Collection<University> universities, Collection<Statistics> statistics) {
        this.students = students;
        this.universities = universities;
        this.statistics = statistics;
    }
}
