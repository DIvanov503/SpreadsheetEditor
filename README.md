# Spreadsheet Editor

This is a small spreadsheet editor supporting basic functions.

## Description

The current version supports basic display and editor features:

- open and display .ods files
- editing cell values
- saving changes as .ods files
- extensible worksheet
- creating new sheets within spreadsheets
- parsing formulas as per OASIS Standard (http://docs.oasis-open.org/office/v1.2/OpenDocument-v1.2-part2.pdf)

## Build

### Dependencies

This project depends on

- jOpenDocument
- antlr4

These dependencies are included into `pom.xml`.

### Compilation

Run

`mvn package`

or import the project to IntelliJ IDEA and build artifacts. Depending on your preferred build method, the executable jar file will be located in either

`target/SpreadsheetEditor-1.0-SNAPSHOT-jar-with-dependencies.jar`

or

`out\artifacts\SpreadsheetEditor_jar\SpreadsheetEditor.jar`

### Run

The application can take up to one command line argument, which should be the initial spreadsheet file.

## Future work

- supporting formula insertion and calculation
- autofill hints for formula editor
- resizable rows
- drag and drop interface for formula extrapolation
- implementation of undo/redo interface
- add support to other file formats (`.xsl`)
- add locale switching support
- unit testing