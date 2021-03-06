package theunderjackets.com.rottentechmatoes;

import java.util.Arrays;
import java.util.List;

/**
 * Enum to restrict what possible majors there are.
 * Created by Andrew Suh on 2/12/2016.
 */
public enum Major {

    UNDECIDED("<Undecided>"),
    AEROSPACE_ENGINEERING("Aerospace Engineering"),
    APPLIED_LANGUAGE_AND_INTERCULTURAL_STUDIES("Applied Language and Intercultural Studies"),
    APPLIED_MATHEMATICS("Applied Mathematics"),
    APPLIED_PHYSICS("Applied Physics"),
    ARCHITECTURE("Architecture"),
    BIOCHEMISTRY("Biochemistry"),
    BIOLOGY("Biology"),
    BIOMEDICAL_ENGINEERING("Biomedical Engineering"),
    BUILDING_CONSTRUCTION("Building Construction"),
    BUSINESS_ADMINISTRATION("Business Administration"),
    CHEMICAL_AND_BIOMOLECULAR_ENGINEERING("Chemical and Biomolecular Engineering"),
    CHEMISTRY("Chemistry"),
    CIVIL_ENGINEERING("Civil Engineering"),
    COMPUTATIONAL_MEDIA("Computational Media"),
    COMPUTER_ENGINEERING("Computer Engineering"),
    COMPUTER_SCIENCE("Computer Science"),
    DISCRETE_MATHEMATICS("Discrete Mathematics"),
    EARTH_AND_ATMOSPHERIC_SCIENCES("Earth and Atmospheric Sciences"),
    ECONOMICS("Economics"),
    ECONOMICS_AND_INTERNATIONAL_AFFAIRS("Economics and International Affairs"),
    ELECTRICAL_ENGINEERING("Electrical Engineering"),
    ENVIRONMENTAL_ENGINEERING("Environmental Engineering"),
    GLOBAL_ECONOMICS_AND_MODERN_LANGUAGES("Global Economics and Modern Languages"),
    HISTORY_TECHNOLOGY_SOCIETY("History, Technology, Society"),
    INDUSTRIAL_ENGINEERING("Industrial Engineering"),
    INDUSTRIAL_DESIGN("Industrial Design"),
    INTERNATIONAL_AFFAIRS("International Affairs"),
    INTERNATIONAL_AFFAIRS_AND_MODERN_LANGUAGE("International Affairs and Modern Languages"),
    LITERATURE_MEDIA_AND_COMMUNICATION("Literature, Media, and Communication"),
    MATERIALS_SCIENCE_AND_ENGINEERING("Materials Science and Engineering"),
    MECHANICAL_ENGINEERING("Mechanical Engineering"),
    NUCLEAR_AND_RADIOLOGICAL_ENGINEERING("Nuclear and Radiological Engineering"),
    PHYSICS("Physics"),
    PSYCHOLOGY("Psychology"),
    PUBLIC_POLICY("Public Policy");

    private final String majorName;

    /**
     * Constructor to create the major enum.
     * @param majorName name of the major
     */
    Major(String majorName) {
        this.majorName = majorName;
    }

    /**
     * Creates a list of all possible majors and returns it in sorted order.
     *
     * @return Sorted list of all possible majors.
     */
    public static List<Major> getMajorList() {
        return Arrays.asList(Major.values());
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Getter method for the name of the major. May need to be public for later use, functionality.
     *
     * @return the name of the major
     */
    public String getName() {
        return this.majorName;
    }
}
