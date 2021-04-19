# aliexpress-parser

This application parses 100 products from the [AliExpress site](https://flashdeals.aliexpress.com/en.htm) and writes them to CSV file. 

# Used technologies: 
* Java 1.8
* [Maven](http://maven.apache.org) (for building)
* [Maven wrapper](https://github.com/takari/maven-wrapper)
* [Jackson](https://github.com/FasterXML/jackson)
* [Apache HttpComponents](https://hc.apache.org)


# Build Instructions

To run this application from the command line perform next steps:
1. Navigate to the projects root repository
2. Run command:

 **for the Unix system:**
* ./mvnw clean install

 **for Windows:**
* ./mvnw.cmd clean install

3. Navigate to **target** folder (cd target/)

4. Run **java -jar aliexpress_parser-1.0-SNAPSHOT.jar**

Output file: target/products.csv

