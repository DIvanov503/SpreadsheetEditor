grammar ODSFormulaParser;

options {
	tokenVocab = ODSFormulaLexer;
}

formula
    : intro? expression[0]
    ;

intro
    : Eq forceRecalc?
    ;

forceRecalc
    : Eq
    ;

expression[int pr]
    : ( Number |
        String |
        array |
        prefixOp expression[7] |
        ParenLeft expression[0] ParenRight |
        functionName ParenLeft parameterList ParenRight |
        reference |
        QuotedLabel |
        AutomaticIntersection |
        namedExpression |
        Error
        ) ( {6 >= $pr}? postfixOp |
            {9 >= $pr}? RangeOp expression[10] |
            {8 >= $pr}? IntersectionOp expression[9] |
            {7 >= $pr}? ReferenceConcatenationOp expression[8] |
            {5 >= $pr}? Power expression[6] |
            {4 >= $pr}? mulplicativeOP expression[5] |
            {3 >= $pr}? additiveOp expression[4] |
            {2 >= $pr}? StringOp expression[3] |
            {1 >= $pr}? comparisonOp expression[2] )*
    ;

prefixOp
    : Plus
    | Minus
    ;

postfixOp
    : Percent
    ;

infixOp
    : arithmeticOp
    | comparisonOp
    | StringOp
    | referenceOp
    ;

arithmeticOp
    : additiveOp
    | mulplicativeOP
    | Power
    ;

additiveOp
    : Plus
    | Minus
    ;

mulplicativeOP
    : Mul
    | Div
    ;

comparisonOp
    : Eq
    | NEq
    | LT
    | GT
    | LE
    | GE
    ;

referenceOp
    : IntersectionOp
    | ReferenceConcatenationOp
    | RangeOp
    ;

functionName
    : Identifier
    ;

parameterList
    : /* empty */
    | parameter ( Separator emptyOrParameter )*
    | Separator emptyOrParameter
    | ( Separator emptyOrParameter )*
    ;

emptyOrParameter
    :
    | parameter
    ;

parameter
    : expression[0]
    ;

reference
    : '[' ( source? rangeAddress | ReferenceError ) ']'
    ;


rangeAddress
    : sheetLocatorOrEmpty '.' Column Row ( RangeOp '.' Column Row )?
    | sheetLocatorOrEmpty '.' Column
    ;

sheetLocatorOrEmpty
    : sheetLocator
    | /* empty */
    ;

sheetLocator
    : SheetName ( '.' subtableCell )*
    ;

subtableCell
    : ( Column Row )
    | QuotedSheetName
    ;

source
    : '\'' IRI '\'' '#'
    ;

IRI
    : [^\\]
    ;

cellAddress
    : sheetLocatorOrEmpty '.' Column Row
    ;

referenceList
    : reference ( ReferenceConcatenationOp reference )*
    ;

namedExpression
    : simpleNamedExpression
    | sheetLocalNamedExpression
    | externalNamedExpression
    ;

simpleNamedExpression
    : Identifier
    | '$$' ( Identifier | SingleQuoted )
    ;

sheetLocalNamedExpression
    : QuotedSheetName '.' simpleNamedExpression
    ;

externalNamedExpression
    : source ( simpleNamedExpression | sheetLocalNamedExpression )
    ;

array
    : '{' matrixRow ( RowSeparator matrixRow )* '}'
    ;

matrixRow
    : expression[0] ( Separator expression[0] )*
    ;

