/*
 * Copyright 2015 subwiz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wiztools.xsdgen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author subwiz
 */
public class TypeInferenceUtilTest {
    
    public TypeInferenceUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTypeOfContent method, of class TypeInferenceUtil.
     */
    @Test
    public void testGetTypeOfContentDateTime() {
        System.out.println("getTypeOfContent");
        String content = "2000-01-01T12:34:56Z";
        String expResult = TypeInferenceUtil.XSD_DATE_TIME;
        String result = TypeInferenceUtil.getTypeOfContent(content);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTypeOfContent method, of class TypeInferenceUtil.
     * Reported in Issue #12 ( https://github.com/wiztools/xsd-gen/issues/12 ).
     */
    @Test
    public void testGetTypeOfContentDateTimeInvalid() {
        System.out.println("getTypeOfContentDateTimeInvalid");
        String content = "12121212";
        String expResult = TypeInferenceUtil.XSD_INT;
        String result = TypeInferenceUtil.getTypeOfContent(content);
        assertEquals(expResult, result);
    }

    /**
     * Reported in issue #14 ( https://github.com/wiztools/xsd-gen/issues/14 )
     */
    @Test
    public void testFourDgtAsTime() {
        System.out.println("testFourDgtAsTime");
        String content = "2012";
        String expResult = TypeInferenceUtil.XSD_INT;
        String result = TypeInferenceUtil.getTypeOfContent(content);
        assertEquals(expResult, result);
    }
}
