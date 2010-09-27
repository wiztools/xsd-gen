package org.wiztools.xsdgen;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author subWiz
 */
public class XsdGenMain {
    public static void main(String[] arg) throws ParseException, IOException {
        if(arg.length != 1) {
            System.err.println("Accpets one parameter: XML file path.");
            System.exit(1);
        }

        final File file = new File(arg[0]);
        new XsdUtil().parse(file);
    }
}
