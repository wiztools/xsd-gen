package org.wiztools.xsdgen;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;

/**
 *
 * @author subWiz
 */
final class XsdUtil {
    private XsdUtil() {}

    private static final String XSD_NS_URI = "http://www.w3.org/2001/XMLSchema";

    private static void recurseGen(Element parent, Element parentOutElement) {
        // Adding complexType element:
        Element complexType = new Element("xsd:complexType", XSD_NS_URI);
        Element sequence = new Element("xsd:sequence", XSD_NS_URI);
        complexType.appendChild(sequence);
        parentOutElement.appendChild(complexType);

        Elements childs = parent.getChildElements();
        final Set<String> elementNamesProcessed = new HashSet<String>();
        for(int i=0; i<childs.size(); i++) {
            Element e = childs.get(i);
            final String localName = e.getLocalName();
            final String nsURI = e.getNamespaceURI();
            final String nsPrefix = e.getNamespacePrefix();
            final String nsName = (nsPrefix!=null?nsPrefix + ":": "") + localName;

            if(e.getChildElements().size() > 0) {
                Element element = new Element("xsd:element", XSD_NS_URI);
                element.addAttribute(new Attribute("name", localName));
                recurseGen(e, element);
            }
            else {
                final String cnt = e.getValue();
                final String eValue = cnt==null? null: cnt.trim();

                final String type = TypeInferenceUtil.getTypeOfContent(eValue);

                if(!elementNamesProcessed.contains(nsName)) { // process an element first time only
                    Element element = new Element("xsd:element", XSD_NS_URI);
                    element.addAttribute(new Attribute("name", localName));
                    element.addAttribute(new Attribute("type", type));
                    if(parent.getChildElements(localName, nsURI).size() > 1){
                        element.addAttribute(new Attribute("maxOccurs", "unbounded"));
                    }
                    else {
                        element.addAttribute(new Attribute("minOccurs", "0"));
                        element.addAttribute(new Attribute("maxOccurs", "1"));
                    }

                    sequence.appendChild(element);
                }
            }
            elementNamesProcessed.add(nsName);
        }
    }

    static void parse(File file) throws ParsingException, IOException {
        Builder parser = new Builder();
        Document doc = parser.build(file);
        final Element rootElement = doc.getRootElement();

        // Output Document
        Element outRoot = new Element("xsd:schema", XSD_NS_URI);
        Document outDoc = new Document(outRoot);

        // Setting targetNamespace:
        {
            final String nsPrefix = rootElement.getNamespacePrefix();
            if(nsPrefix != null) {
                outRoot.addAttribute(new Attribute("targetNamespace", rootElement.getNamespaceURI()));
                outRoot.addAttribute(new Attribute("elementFormDefault", "qualified"));
            }
        }

        // Adding all other namespace attributes:
        {
            for(int i=0; i<rootElement.getNamespaceDeclarationCount(); i++) {
                final String nsPrefix = rootElement.getNamespacePrefix(i);
                final String nsURI = rootElement.getNamespaceURI(nsPrefix);
                outRoot.addNamespaceDeclaration(nsPrefix, nsURI);
            }
        }

        // Adding the root element:
        Element rootElementXsd = new Element("xsd:element", XSD_NS_URI);
        {
            rootElementXsd.addAttribute(new Attribute("name", rootElement.getLocalName()));
            outRoot.appendChild(rootElementXsd);
        }

        recurseGen(rootElement, rootElementXsd);

        // Display output:
        Serializer serializer = new Serializer(System.out, "UTF-8");
        serializer.setIndent(2);
        serializer.write(outDoc);
    }
}
