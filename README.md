# WizTools.org XSD Gen

Command-line tool written in Java for generating XSD from an XML. Can also be used as a library within your application.

## Install

Builds are available as [uber jar](https://github.com/wiztools/xsd-gen/releases).

Mac users can install via homebrew:

```
brew tap wiztools/repo
brew install xsd-gen
```

Once installed, `xsd-gen` is available in `PATH`.

## Running

To generate schema:

    $ java -jar xsd-gen-fat-VERSION.jar /path/to/xml.xml > /path/to/my.xsd

To view additional command-line options:

    $ java -jar xsd-gen-fat-VERSION.jar -h

## As Library

Latest versions of the library are available in the [jitpack repository](https://jitpack.io/#wiztools/xsd-gen/).

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
