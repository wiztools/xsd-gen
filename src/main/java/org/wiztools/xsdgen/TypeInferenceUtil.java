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

    private static final Pattern P_DATE_TIME = Pattern.compile("^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$");
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
