package org.wiztools.xsdgen;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author subWiz
 */
public class XsdUtilTest {

    public XsdUtilTest() {
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
        XsdUtil.parse(file);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    @Test
    public void testParseRepeat() throws Exception {
        System.out.println("parse");
        File file = new File("src/test/resources/bathroom.xml");
        XsdUtil.parse(file);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    @Test
    public void testParseAttributes() throws Exception {
        System.out.println("parse");
        File file = new File("src/test/resources/attr.xml");
        XsdUtil.parse(file);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
}