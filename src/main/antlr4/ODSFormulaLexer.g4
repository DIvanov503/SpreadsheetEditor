lexer grammar ODSFormulaLexer;

Whitespace
    : [ \t\r\n]+ -> skip
    ;

SingleQuoted
    : '\'' ( ~'\'' | '\'\'' )+ '\''
    ;

Number
    : StandardNumber
    | PointNumber
    ;

StandardNumber
    : [0-9]+ ( '.' [0-9]+ )? ( [eE] [-+]? [0-9]+ )?
    ;

PointNumber
    : '.' [0-9]+ ( [eE] [-+]? [0-9]+ )?
    ;

String
    : '"' ( ~'"' | '""' )* '"'
    ;

Plus
    : '+'
    ;

Minus
    : '-'
    ;

Percent
    : '%'
    ;

Mul
    : '*'
    ;

Div
    : '/'
    ;

Power
    : '^'
    ;

Eq
    : '='
    ;

NEq
    : '<>'
    ;

LT
    : '<'
    ;

GT
    : '>'
    ;

LE
    : '<='
    ;

GE
    : '>='
    ;

StringOp
    : '&'
    ;



IntersectionOp
    : '!'
    ;

ReferenceConcatenationOp
    : '~'
    ;

RangeOp
    : ':'
    ;

Separator
    : ';'
    ;

SheetName
    : QuotedSheetName
    | '$'? [^\]. #$']+
    ;

QuotedSheetName
    : '$'? SingleQuoted
    ;

ReferenceError
    : '#REF!'
    ;

Column
    : '$'? [A-Z]+
    ;

Row
    : '$'? [1-9] [0-9]*
    ;

QuotedLabel
    : SingleQuoted
    ;

AutomaticIntersection
    : QuotedLabel '!!' QuotedLabel
    ;

Identifier
    : [A-Za-z] ( [A-Za-z] | [0-9] | '_' | '.' )*
    ;

Error
    : '#' [A-Z0-9]+ ( [!?] | ([A-Z] | ( [0-9] [!?] ) ) )
    ;

RowSeparator
    : '|'
    ;

ParenLeft
    : '('
    ;

ParenRight
    : ')'
    ;