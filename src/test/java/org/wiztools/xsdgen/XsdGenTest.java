package org.wiztools.xsdgen;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author subWiz
 */
public class XsdGenTest {

    public XsdGenTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class XsdUtil.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        File file = new File("src/test/resources/tv.xml");
        new XsdGen().parse(file).write(System.out);
    }

    @Test
    public void testParseRepeat() throws Exception {
        System.out.println("parse");
        File file = new File("src/test/resources/bathroom.xml");
        new XsdGen().parse(file).write(System.out);
    }

    @Test
    public void testParseAttributes() throws Exception {
        System.out.println("parse");
        File file = new File("src/test/resources/attr.xml");
        new XsdGen().parse(file).write(System.out);
    }

    @Test
    public void testParseNoNamespace() throws Exception {
        System.out.println("parse");
        File file = new File("src/test/resources/no-namespace.xml");
        new XsdGen().parse(file).write(System.out);
    }
}