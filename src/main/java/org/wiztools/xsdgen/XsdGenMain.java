package org.wiztools.xsdgen;

import java.io.File;
import java.io.IOException;
import nu.xom.ParsingException;

/**
 *
 * @author subWiz
 */
public class XsdGenMain {
    public static void main(String[] arg) throws ParsingException, IOException {
        if(arg.length != 1) {
            System.err.println("Accpets one parameter: XML file path.");
            System.exit(1);
        }

        final File file = new File(arg[0]);
        XsdUtil.parse(file);
    }
}
