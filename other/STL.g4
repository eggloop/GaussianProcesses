grammar STL;

prog	:	stat+
	    ;

stat:   formula NEWLINE                 # textformula
    |   PARAMETERS EQ expr NEWLINE      # assign
    |   NEWLINE                         # blank
    ;

formula: LPAR formula RPAR                                 # parensFormula
	|    formula op=(AND|OR) formula  # AndOr
	|	 NOT formula                            # Not
	|    expr op=(GT|GE|LT|LE|E) expr                      # atom
	|    op =(TRUE|FALSE)                                  # trueFalse
	|	 formula U  interval formula   # U
	|    F  interval formula                     # F
	|	 G  interval formula                     # G
	;

expr:   expr op=(MULT|DIV|PLUS|MINUS) expr  # algOp
    |   NUMBER                              # number
    |   MINUS NUMBER                        # negNumber
    |   op=(PARAMETERS|SERIES)              # id
    |   LPAR expr RPAR                      # parensExpr
    |   SERIES  LPAR op=(PLUS|MINUS) PARAMETERS RPAR  # atomicTranslation
    ;


interval : LBRAT  expr COMMA  expr RBRAT;


PARAMETERS:     [a-z]+ ;
SERIES  :       [A-Z] ([A-Z] | [0-9])* ;

LPAR    :       '(';
RPAR    :       ')';
COMMA   :       ',';
LBRAT   :       '[';
RBRAT   :       ']';
U       :	'U_';
F       :	'F_';
G       :	'G_';

TRUE	:	'True';
FALSE	:	'False';

PLUS	:	'+';
MINUS	:	'-';
MULT	:	'*';
DIV	:	'/';

AND	:	'&';
OR	:	'|';
NOT	:	'!';

EQ	:	'=';
NEQ	:	'!=';
GT	:	'>';
GE	:	'>=';
LT	:	'<';
LE	:	'<=';
E	:	'==';


NUMBER
	:   ('0'..'9')+ ('.')* ('0'..'9')* EXPONENT?
	|   '.' ('0'..'9')+ EXPONENT?
	|   ('0'..'9')+ EXPONENT
	;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;



ID	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
	;

NEWLINE	:	( '\r'| '\n' )
	;

// $>


// $<White space

COMMENT
    :   ('//' ~('\n'|'\r')*)->channel(HIDDEN)
    ;

/* Ignore white space characters, except from newline */
WS
    :   (' ' | '\t' | NEWLINE )->channel(HIDDEN)
    ;
// $>