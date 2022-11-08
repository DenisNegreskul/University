package comparator;

import model.University;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

public enum UniversityComparatorType {

    ID(Comparator.comparing(University::getId, StringUtils::compare)),
    FULL_NAME(Comparator.comparing(University::getFullName, StringUtils::compare)),
    SHORT_NAME(Comparator.comparing(University::getShortName, StringUtils::compare)),
    PROFILE((o1, o2) -> {
        if (o1.getMainProfile() == null) {
            return 1;
        } else if (o2.getMainProfile() == null) {
            return -1;
        }
        return StringUtils.compare(o1.getMainProfile().name(), o2.getMainProfile().name());
    }),
    YEAR(Comparator.comparingInt(University::getYearOfFoundation));

    private final Comparator<University> comparator;

    UniversityComparatorType(Comparator<University> comparator) {
        this.comparator = comparator;
    }

    public Comparator<University> getComparator() {
        return comparator;
    }
}
