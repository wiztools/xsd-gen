package org.wiztools.xsdgen;

import java.util.regex.Pattern;

/**
 *
 * @author subWiz
 */
public final class TypeInferenceUtil {
    private TypeInferenceUtil() {}

    private static final Pattern P_URL = Pattern.compile("[a-z]+://.*");
    private static final String XSD_URL = ":anyURI";

    private static final Pattern P_BOOLEAN = Pattern.compile("(true|false)");
    private static final String XSD_BOOLEAN = ":boolean";

    private static final Pattern P_DATE = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final String XSD_DATE = ":date";

    private static final Pattern P_TIME = Pattern.compile("\\d{2}:\\d{2}:\\d{2}\\.\\d{2}");
    private static final String XSD_TIME = ":time";

    private static final Pattern P_DATE_TIME = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{2}(-|\\+)\\d{2}:\\d{2}");
    private static final String XSD_DATE_TIME = ":dateTime";

    private static final Pattern P_INT = Pattern.compile("-?\\d{1,9}");
    private static final String XSD_INT = ":int";

    private static final Pattern P_LONG = Pattern.compile("-?\\d+");
    private static final String XSD_LONG = ":long";

    private static final Pattern P_DECIMAL = Pattern.compile("-?\\d+\\.\\d+");
    private static final String XSD_DECIMAL = ":decimal";

    private static final Pattern P_NORMALIZED_STRING = Pattern.compile("[^\\s]+");
    private static final String XSD_NORMALIZED_STRING = ":normalizedString";

    private static final String XSD_STRING = ":string";

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
