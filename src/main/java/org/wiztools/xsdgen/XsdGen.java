package org.wiztools.xsdgen;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import org.wiztools.commons.StringUtil;

/**
 *
 * @author subWiz
 */
public final class XsdGen {

    private static final String XSD_NS_URI = "http://www.w3.org/2001/XMLSchema";
    private final String XSD_PREFIX;

    private Document doc = null; // populated after the parse method is called!

    XsdGen(final String prefix) {
        XSD_PREFIX = prefix;
    }

    XsdGen() {
        XSD_PREFIX = "xsd"; // Default XSD Prefix
    }

    private void processAttributes(final Element inElement, final Element outElement) {
        for(int i=0; i<inElement.getAttributeCount(); i++) {
            final Attribute attr = inElement.getAttribute(i);

            final String name = attr.getLocalName();

            final String nsPrefix = attr.getNamespacePrefix();
            final String nsURI = attr.getNamespaceURI();
            final String nsName = (nsPrefix==null? "": nsPrefix + ":") + name;
            
            final String value = attr.getValue();

            Element attrElement = new Element(XSD_PREFIX + ":attribute", XSD_NS_URI);
            attrElement.addAttribute(new Attribute("name", name));
            attrElement.addAttribute(new Attribute("type", TypeInferenceUtil.getTypeOfContent(value)));
            attrElement.addAttribute(new Attribute("use", "required"));

            outElement.appendChild(attrElement);
        }
    }

    private void recurseGen(Element parent, Element parentOutElement) {
        // Adding complexType element:
        Element complexType = new Element(XSD_PREFIX + ":complexType", XSD_NS_URI);
        Element sequence = new Element(XSD_PREFIX + ":sequence", XSD_NS_URI);
        complexType.appendChild(sequence);
        processAttributes(parent, complexType);
        parentOutElement.appendChild(complexType);

        Elements childs = parent.getChildElements();
        final Set<String> elementNamesProcessed = new HashSet<String>();
        for(int i=0; i<childs.size(); i++) {
            Element e = childs.get(i);
            final String localName = e.getLocalName();
            final String nsURI = e.getNamespaceURI();
            final String nsPrefix = e.getNamespacePrefix();
            final String nsName = (nsPrefix!=null?nsPrefix + ":": "") + localName;

            if(!elementNamesProcessed.contains(nsName)) { // process an element first time only
                if(e.getChildElements().size() > 0) { // Is complex type with children!
                    Element element = new Element(XSD_PREFIX + ":element", XSD_NS_URI);
                    element.addAttribute(new Attribute("name", localName));

                    // Add occurance parameters:
                    if(parent.getChildElements(localName, nsURI).size() > 1){
                        element.addAttribute(new Attribute("maxOccurs", "unbounded"));
                    }
                    else {
                        element.addAttribute(new Attribute("minOccurs", "0"));
                        element.addAttribute(new Attribute("maxOccurs", "1"));
                    }

                    // Recurse into childrens:
                    recurseGen(e, element);

                    sequence.appendChild(element);
                }
                else {
                    final String cnt = e.getValue();
                    final String eValue = cnt==null? null: cnt.trim();

                    final String type = TypeInferenceUtil.getTypeOfContent(eValue);

                    Element element = new Element(XSD_PREFIX + ":element", XSD_NS_URI);
                    element.addAttribute(new Attribute("name", localName));

                    if(parent.getChildElements(localName, nsURI).size() > 1){
                        element.addAttribute(new Attribute("maxOccurs", "unbounded"));
                    }
                    else {
                        element.addAttribute(new Attribute("minOccurs", "0"));
                        element.addAttribute(new Attribute("maxOccurs", "1"));
                    }
                    // Attributes
                    final int attrCount = e.getAttributeCount();
                    if(attrCount > 0) { // has attributes: complex type without sequence!
                        Element complexTypeCurrent = new Element(XSD_PREFIX + ":complexType", XSD_NS_URI);
                        Element simpleContent = new Element(XSD_PREFIX + ":simpleContent", XSD_NS_URI);
                        Element extension = new Element(XSD_PREFIX + ":extension", XSD_NS_URI);
                        extension.addAttribute(new Attribute("base", type));
                        processAttributes(e, extension);
                        simpleContent.appendChild(extension);
                        complexTypeCurrent.appendChild(simpleContent);

                        element.appendChild(complexTypeCurrent);
                    }
                    else { // if no attributes, just put the type:
                        element.addAttribute(new Attribute("type", type));
                    }
                    sequence.appendChild(element);
                }
            }
            elementNamesProcessed.add(nsName);
        }
    }

    private Document getDocument(File file) throws ParsingException, IOException {
        Builder parser = new Builder();
        Document doc = parser.build(file);
        final Element rootElement = doc.getRootElement();

        // Output Document
        Element outRoot = new Element(XSD_PREFIX + ":schema", XSD_NS_URI);
        Document outDoc = new Document(outRoot);

        // Setting targetNamespace:
        {
            final String nsPrefix = rootElement.getNamespacePrefix();
            if(!StringUtil.isStrEmpty(nsPrefix)) {
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
        Element rootElementXsd = new Element(XSD_PREFIX + ":element", XSD_NS_URI);
        {
            rootElementXsd.addAttribute(new Attribute("name", rootElement.getLocalName()));
            outRoot.appendChild(rootElementXsd);
        }

        recurseGen(rootElement, rootElementXsd);

        // Root element attributes
        processAttributes(rootElement, rootElementXsd);

        return outDoc;
    }

    public XsdGen parse(File file) throws IOException, ParseException {
        try{
            doc = getDocument(file);
            return this;
        }
        catch(ParsingException ex) {
            throw new ParseException(ex);
        }
    }

    public void write(final OutputStream os) throws IOException {
        if(doc == null) throw new IllegalStateException("Call parse() before calling this method!");
        write(os, Charset.defaultCharset());
    }

    public void write(final OutputStream os, final Charset charset) throws IOException {
        if(doc == null) throw new IllegalStateException("Call parse() before calling this method!");
        // Display output:
        Serializer serializer = new Serializer(os, charset.name());
        serializer.setIndent(2);
        serializer.write(doc);
    }

    @Override
    public String toString() {
        final String xml = doc.toXML();
        return xml==null?"[Not yet initialized!]" :xml;
    }
}
