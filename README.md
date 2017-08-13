# WizTools.org XSD Gen

Command-line tool written in Java for generating XSD from an XML. Can also be used as a library within your application.

## Running

To generate schema:

    $ java -jar xsd-gen-fat-VERSION.jar /path/to/xml.xml > /path/to/my.xsd

To view additional command-line options:

    $ java -jar xsd-gen-fat-VERSION.jar -h

## As Library

To add as dependency in your Maven project:

```xml
<dependency>
  <groupId>org.wiztools</groupId>
  <artifactId>xsd-gen</artifactId>
  <version>VERSION</version>
</dependency>
```

Usage:

```java
import org.wiztools.xsdgen.XsdGen;
import java.io.File;
import java.io.FileOutputStream;

...

XsdGen gen = new XsdGen();
gen.parse(new File("in.xml"));
File out = new File("out.xsd");
gen.write(new FileOutputStream(out));
```
