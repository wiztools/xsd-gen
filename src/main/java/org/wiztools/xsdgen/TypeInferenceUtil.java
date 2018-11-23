package org.wiztools.xsdgen;

import java.util.regex.Pattern;

/**
 *
 * @author subWiz
 */
public final class TypeInferenceUtil {
    private TypeInferenceUtil() {}

    private static final Pattern P_URL = Pattern.compile("[a-z]+://.*");
    public static final String XSD_URL = ":anyURI";

    private static final Pattern P_BOOLEAN = Pattern.compile("(true|false)");
    public static final String XSD_BOOLEAN = ":boolean";

    private static final Pattern P_DATE = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    public static final String XSD_DATE = ":date";

    private static final Pattern P_TIME = Pattern.compile("\\d{2}:\\d{2}:\\d{2}\\.\\d{2}");
    public static final String XSD_TIME = ":time";

    // Pattern from: https://stackoverflow.com/questions/3143070/javascript-regex-iso-datetime
    // Customized for #14: https://github.com/wiztools/xsd-gen/issues/14
    private static final Pattern P_DATE_TIME = Pattern.compile("^[+-]?\\d{4}-[01]\\d-[0-3]\\d(T[0-2]\\d:[0-5]\\d:?([0-5]\\d(\\.\\d+)?)?([+-][0-2]\\d:[0-5]\\d)?Z?)?$");
    public static final String XSD_DATE_TIME = ":dateTime";

    private static final Pattern P_INT = Pattern.compile("-?\\d{1,9}");
    public static final String XSD_INT = ":int";

    private static final Pattern P_LONG = Pattern.compile("-?\\d+");
    public static final String XSD_LONG = ":long";

    private static final Pattern P_DECIMAL = Pattern.compile("-?\\d+\\.\\d+");
    public static final String XSD_DECIMAL = ":decimal";

    private static final Pattern P_NORMALIZED_STRING = Pattern.compile("[^\\s]+");
    public static final String XSD_NORMALIZED_STRING = ":normalizedString";

    public static final String XSD_STRING = ":string";

    static String getTypeOfContent(final String content) {
        if(content == null) {
            return XSD_STRING;
        }

        if(P_URL.matcher(content).matches()) {
            return XSD_URL;
        }
        else if(P_BOOLEAN.matcher(content).matches()) {
            return XSD_BOOLEAN;
        }
        else if(P_DATE.matcher(content).matches()) {
            return XSD_DATE;
        }
        else if(P_TIME.matcher(content).matches()) {
            return XSD_TIME;
        }
        else if(P_DATE_TIME.matcher(content).matches()) {
            return XSD_DATE_TIME;
        }
        else if(P_INT.matcher(content).matches()) {
            return XSD_INT;
        }
        else if(P_LONG.matcher(content).matches()) {
            return XSD_LONG;
        }
        else if(P_DECIMAL.matcher(content).matches()) {
            return XSD_DECIMAL;
        }
        else if(P_NORMALIZED_STRING.matcher(content).matches()) {
            return XSD_NORMALIZED_STRING;
        }
        return XSD_STRING;
    }
}
