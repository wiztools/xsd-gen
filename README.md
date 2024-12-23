# WizTools.org XSD Gen

Command-line tool written in Java for generating XSD from an XML. Can also be used as a library within your application.

## Install

Builds are available as [uber jar](https://github.com/wiztools/xsd-gen/releases).

## Running

To generate schema:

    $ java -jar xsd-gen-VERSION-fat.jar /path/to/xml.xml > /path/to/my.xsd

To view additional command-line options:

    $ java -jar xsd-gen-VERSION-fat.jar -h

## As Library

Latest versions of the library are available in the [GitHub Packages](https://github.com/wiztools/xsd-gen/packages/2343174).

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
