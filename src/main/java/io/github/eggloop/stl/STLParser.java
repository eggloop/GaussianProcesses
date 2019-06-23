package io.github.eggloop.stl;// Generated from STL.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class STLParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            PARAMETERS = 1, SERIES = 2, LPAR = 3, RPAR = 4, COMMA = 5, LBRAT = 6, RBRAT = 7, U = 8,
            F = 9, G = 10, TRUE = 11, FALSE = 12, PLUS = 13, MINUS = 14, MULT = 15, DIV = 16, AND = 17,
            OR = 18, NOT = 19, EQ = 20, NEQ = 21, GT = 22, GE = 23, LT = 24, LE = 25, E = 26, NUMBER = 27,
            ID = 28, NEWLINE = 29, COMMENT = 30, WS = 31;
    public static final int
            RULE_prog = 0, RULE_stat = 1, RULE_formula = 2, RULE_expr = 3, RULE_interval = 4;

    private static String[] makeRuleNames() {
        return new String[]{
                "prog", "stat", "formula", "expr", "interval"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, null, null, "'('", "')'", "','", "'['", "']'", "'U_'", "'F_'",
                "'G_'", "'True'", "'False'", "'+'", "'-'", "'*'", "'/'", "'&'", "'|'",
                "'!'", "'='", "'!='", "'>'", "'>='", "'<'", "'<='", "'=='"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, "PARAMETERS", "SERIES", "LPAR", "RPAR", "COMMA", "LBRAT", "RBRAT",
                "U", "F", "G", "TRUE", "FALSE", "PLUS", "MINUS", "MULT", "DIV", "AND",
                "OR", "NOT", "EQ", "NEQ", "GT", "GE", "LT", "LE", "E", "NUMBER", "ID",
                "NEWLINE", "COMMENT", "WS"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "STL.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public STLParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class ProgContext extends ParserRuleContext {
        public List<StatContext> stat() {
            return getRuleContexts(StatContext.class);
        }

        public StatContext stat(int i) {
            return getRuleContext(StatContext.class, i);
        }

        public ProgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_prog;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitProg(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ProgContext prog() throws RecognitionException {
        ProgContext _localctx = new ProgContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_prog);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(11);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(10);
                            stat();
                        }
                    }
                    setState(13);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARAMETERS) | (1L << SERIES) | (1L << LPAR) | (1L << F) | (1L << G) | (1L << TRUE) | (1L << FALSE) | (1L << MINUS) | (1L << NOT) | (1L << NUMBER) | (1L << NEWLINE))) != 0));
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StatContext extends ParserRuleContext {
        public StatContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stat;
        }

        public StatContext() {
        }

        public void copyFrom(StatContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class BlankContext extends StatContext {
        public TerminalNode NEWLINE() {
            return getToken(STLParser.NEWLINE, 0);
        }

        public BlankContext(StatContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitBlank(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class TextformulaContext extends StatContext {
        public FormulaContext formula() {
            return getRuleContext(FormulaContext.class, 0);
        }

        public TerminalNode NEWLINE() {
            return getToken(STLParser.NEWLINE, 0);
        }

        public TextformulaContext(StatContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitTextformula(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class AssignContext extends StatContext {
        public TerminalNode PARAMETERS() {
            return getToken(STLParser.PARAMETERS, 0);
        }

        public TerminalNode EQ() {
            return getToken(STLParser.EQ, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode NEWLINE() {
            return getToken(STLParser.NEWLINE, 0);
        }

        public AssignContext(StatContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitAssign(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StatContext stat() throws RecognitionException {
        StatContext _localctx = new StatContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_stat);
        try {
            setState(24);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
                case 1:
                    _localctx = new TextformulaContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(15);
                    formula(0);
                    setState(16);
                    match(NEWLINE);
                }
                break;
                case 2:
                    _localctx = new AssignContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(18);
                    match(PARAMETERS);
                    setState(19);
                    match(EQ);
                    setState(20);
                    expr(0);
                    setState(21);
                    match(NEWLINE);
                }
                break;
                case 3:
                    _localctx = new BlankContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(23);
                    match(NEWLINE);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FormulaContext extends ParserRuleContext {
        public FormulaContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formula;
        }

        public FormulaContext() {
        }

        public void copyFrom(FormulaContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class NotContext extends FormulaContext {
        public TerminalNode NOT() {
            return getToken(STLParser.NOT, 0);
        }

        public FormulaContext formula() {
            return getRuleContext(FormulaContext.class, 0);
        }

        public NotContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitNot(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class UContext extends FormulaContext {
        public List<FormulaContext> formula() {
            return getRuleContexts(FormulaContext.class);
        }

        public FormulaContext formula(int i) {
            return getRuleContext(FormulaContext.class, i);
        }

        public TerminalNode U() {
            return getToken(STLParser.U, 0);
        }

        public IntervalContext interval() {
            return getRuleContext(IntervalContext.class, 0);
        }

        public UContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitU(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class FContext extends FormulaContext {
        public TerminalNode F() {
            return getToken(STLParser.F, 0);
        }

        public IntervalContext interval() {
            return getRuleContext(IntervalContext.class, 0);
        }

        public FormulaContext formula() {
            return getRuleContext(FormulaContext.class, 0);
        }

        public FContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitF(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ParensFormulaContext extends FormulaContext {
        public TerminalNode LPAR() {
            return getToken(STLParser.LPAR, 0);
        }

        public FormulaContext formula() {
            return getRuleContext(FormulaContext.class, 0);
        }

        public TerminalNode RPAR() {
            return getToken(STLParser.RPAR, 0);
        }

        public ParensFormulaContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitParensFormula(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class TrueFalseContext extends FormulaContext {
        public Token op;

        public TerminalNode TRUE() {
            return getToken(STLParser.TRUE, 0);
        }

        public TerminalNode FALSE() {
            return getToken(STLParser.FALSE, 0);
        }

        public TrueFalseContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitTrueFalse(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class GContext extends FormulaContext {
        public TerminalNode G() {
            return getToken(STLParser.G, 0);
        }

        public IntervalContext interval() {
            return getRuleContext(IntervalContext.class, 0);
        }

        public FormulaContext formula() {
            return getRuleContext(FormulaContext.class, 0);
        }

        public GContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitG(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class AndOrContext extends FormulaContext {
        public Token op;

        public List<FormulaContext> formula() {
            return getRuleContexts(FormulaContext.class);
        }

        public FormulaContext formula(int i) {
            return getRuleContext(FormulaContext.class, i);
        }

        public TerminalNode AND() {
            return getToken(STLParser.AND, 0);
        }

        public TerminalNode OR() {
            return getToken(STLParser.OR, 0);
        }

        public AndOrContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitAndOr(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class AtomContext extends FormulaContext {
        public Token op;

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public TerminalNode GT() {
            return getToken(STLParser.GT, 0);
        }

        public TerminalNode GE() {
            return getToken(STLParser.GE, 0);
        }

        public TerminalNode LT() {
            return getToken(STLParser.LT, 0);
        }

        public TerminalNode LE() {
            return getToken(STLParser.LE, 0);
        }

        public TerminalNode E() {
            return getToken(STLParser.E, 0);
        }

        public AtomContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitAtom(this);
            else return visitor.visitChildren(this);
        }
    }

    public final FormulaContext formula() throws RecognitionException {
        return formula(0);
    }

    private FormulaContext formula(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
        FormulaContext _prevctx = _localctx;
        int _startState = 4;
        enterRecursionRule(_localctx, 4, RULE_formula, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(46);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
                    case 1: {
                        _localctx = new ParensFormulaContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(27);
                        match(LPAR);
                        setState(28);
                        formula(0);
                        setState(29);
                        match(RPAR);
                    }
                    break;
                    case 2: {
                        _localctx = new NotContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(31);
                        match(NOT);
                        setState(32);
                        formula(6);
                    }
                    break;
                    case 3: {
                        _localctx = new AtomContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(33);
                        expr(0);
                        setState(34);
                        ((AtomContext) _localctx).op = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GE) | (1L << LT) | (1L << LE) | (1L << E))) != 0))) {
                            ((AtomContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(35);
                        expr(0);
                    }
                    break;
                    case 4: {
                        _localctx = new TrueFalseContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(37);
                        ((TrueFalseContext) _localctx).op = _input.LT(1);
                        _la = _input.LA(1);
                        if (!(_la == TRUE || _la == FALSE)) {
                            ((TrueFalseContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                    }
                    break;
                    case 5: {
                        _localctx = new FContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(38);
                        match(F);
                        setState(39);
                        interval();
                        setState(40);
                        formula(2);
                    }
                    break;
                    case 6: {
                        _localctx = new GContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(42);
                        match(G);
                        setState(43);
                        interval();
                        setState(44);
                        formula(1);
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(58);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(56);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
                                case 1: {
                                    _localctx = new AndOrContext(new FormulaContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_formula);
                                    setState(48);
                                    if (!(precpred(_ctx, 7)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                                    setState(49);
                                    ((AndOrContext) _localctx).op = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == AND || _la == OR)) {
                                        ((AndOrContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(50);
                                    formula(8);
                                }
                                break;
                                case 2: {
                                    _localctx = new UContext(new FormulaContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_formula);
                                    setState(51);
                                    if (!(precpred(_ctx, 3)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(52);
                                    match(U);
                                    setState(53);
                                    interval();
                                    setState(54);
                                    formula(4);
                                }
                                break;
                            }
                        }
                    }
                    setState(60);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class ExprContext extends ParserRuleContext {
        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expr;
        }

        public ExprContext() {
        }

        public void copyFrom(ExprContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class NumberContext extends ExprContext {
        public TerminalNode NUMBER() {
            return getToken(STLParser.NUMBER, 0);
        }

        public NumberContext(ExprContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitNumber(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class NegNumberContext extends ExprContext {
        public TerminalNode MINUS() {
            return getToken(STLParser.MINUS, 0);
        }

        public TerminalNode NUMBER() {
            return getToken(STLParser.NUMBER, 0);
        }

        public NegNumberContext(ExprContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitNegNumber(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class IdContext extends ExprContext {
        public Token op;

        public TerminalNode PARAMETERS() {
            return getToken(STLParser.PARAMETERS, 0);
        }

        public TerminalNode SERIES() {
            return getToken(STLParser.SERIES, 0);
        }

        public IdContext(ExprContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitId(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ParensExprContext extends ExprContext {
        public TerminalNode LPAR() {
            return getToken(STLParser.LPAR, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode RPAR() {
            return getToken(STLParser.RPAR, 0);
        }

        public ParensExprContext(ExprContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitParensExpr(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class AlgOpContext extends ExprContext {
        public Token op;

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public TerminalNode MULT() {
            return getToken(STLParser.MULT, 0);
        }

        public TerminalNode DIV() {
            return getToken(STLParser.DIV, 0);
        }

        public TerminalNode PLUS() {
            return getToken(STLParser.PLUS, 0);
        }

        public TerminalNode MINUS() {
            return getToken(STLParser.MINUS, 0);
        }

        public AlgOpContext(ExprContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitAlgOp(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ExprContext expr() throws RecognitionException {
        return expr(0);
    }

    private ExprContext expr(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExprContext _localctx = new ExprContext(_ctx, _parentState);
        ExprContext _prevctx = _localctx;
        int _startState = 6;
        enterRecursionRule(_localctx, 6, RULE_expr, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(70);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case NUMBER: {
                        _localctx = new NumberContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(62);
                        match(NUMBER);
                    }
                    break;
                    case MINUS: {
                        _localctx = new NegNumberContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(63);
                        match(MINUS);
                        setState(64);
                        match(NUMBER);
                    }
                    break;
                    case PARAMETERS:
                    case SERIES: {
                        _localctx = new IdContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(65);
                        ((IdContext) _localctx).op = _input.LT(1);
                        _la = _input.LA(1);
                        if (!(_la == PARAMETERS || _la == SERIES)) {
                            ((IdContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                    }
                    break;
                    case LPAR: {
                        _localctx = new ParensExprContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(66);
                        match(LPAR);
                        setState(67);
                        expr(0);
                        setState(68);
                        match(RPAR);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(77);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new AlgOpContext(new ExprContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                setState(72);
                                if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                setState(73);
                                ((AlgOpContext) _localctx).op = _input.LT(1);
                                _la = _input.LA(1);
                                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << MULT) | (1L << DIV))) != 0))) {
                                    ((AlgOpContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                                } else {
                                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                    _errHandler.reportMatch(this);
                                    consume();
                                }
                                setState(74);
                                expr(6);
                            }
                        }
                    }
                    setState(79);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class IntervalContext extends ParserRuleContext {
        public TerminalNode LBRAT() {
            return getToken(STLParser.LBRAT, 0);
        }

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public TerminalNode COMMA() {
            return getToken(STLParser.COMMA, 0);
        }

        public TerminalNode RBRAT() {
            return getToken(STLParser.RBRAT, 0);
        }

        public IntervalContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interval;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof STLVisitor) return ((STLVisitor<? extends T>) visitor).visitInterval(this);
            else return visitor.visitChildren(this);
        }
    }

    public final IntervalContext interval() throws RecognitionException {
        IntervalContext _localctx = new IntervalContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_interval);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(80);
                match(LBRAT);
                setState(81);
                expr(0);
                setState(82);
                match(COMMA);
                setState(83);
                expr(0);
                setState(84);
                match(RBRAT);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 2:
                return formula_sempred((FormulaContext) _localctx, predIndex);
            case 3:
                return expr_sempred((ExprContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 7);
            case 1:
                return precpred(_ctx, 3);
        }
        return true;
    }

    private boolean expr_sempred(ExprContext _localctx, int predIndex) {
        switch (predIndex) {
            case 2:
                return precpred(_ctx, 5);
        }
        return true;
    }

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!Y\4\2\t\2\4\3\t\3" +
                    "\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\16\n\2\r\2\16\2\17\3\3\3\3\3\3\3\3\3" +
                    "\3\3\3\3\3\3\3\3\3\5\3\33\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4" +
                    "\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\61\n\4\3\4\3\4\3\4\3\4\3" +
                    "\4\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5" +
                    "\3\5\5\5I\n\5\3\5\3\5\3\5\7\5N\n\5\f\5\16\5Q\13\5\3\6\3\6\3\6\3\6\3\6" +
                    "\3\6\3\6\2\4\6\b\7\2\4\6\b\n\2\7\3\2\30\34\3\2\r\16\3\2\23\24\3\2\3\4" +
                    "\3\2\17\22\2a\2\r\3\2\2\2\4\32\3\2\2\2\6\60\3\2\2\2\bH\3\2\2\2\nR\3\2" +
                    "\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\17\3\2\2\2\17\r\3\2\2\2\17\20\3\2\2\2" +
                    "\20\3\3\2\2\2\21\22\5\6\4\2\22\23\7\37\2\2\23\33\3\2\2\2\24\25\7\3\2\2" +
                    "\25\26\7\26\2\2\26\27\5\b\5\2\27\30\7\37\2\2\30\33\3\2\2\2\31\33\7\37" +
                    "\2\2\32\21\3\2\2\2\32\24\3\2\2\2\32\31\3\2\2\2\33\5\3\2\2\2\34\35\b\4" +
                    "\1\2\35\36\7\5\2\2\36\37\5\6\4\2\37 \7\6\2\2 \61\3\2\2\2!\"\7\25\2\2\"" +
                    "\61\5\6\4\b#$\5\b\5\2$%\t\2\2\2%&\5\b\5\2&\61\3\2\2\2\'\61\t\3\2\2()\7" +
                    "\13\2\2)*\5\n\6\2*+\5\6\4\4+\61\3\2\2\2,-\7\f\2\2-.\5\n\6\2./\5\6\4\3" +
                    "/\61\3\2\2\2\60\34\3\2\2\2\60!\3\2\2\2\60#\3\2\2\2\60\'\3\2\2\2\60(\3" +
                    "\2\2\2\60,\3\2\2\2\61<\3\2\2\2\62\63\f\t\2\2\63\64\t\4\2\2\64;\5\6\4\n" +
                    "\65\66\f\5\2\2\66\67\7\n\2\2\678\5\n\6\289\5\6\4\69;\3\2\2\2:\62\3\2\2" +
                    "\2:\65\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\7\3\2\2\2><\3\2\2\2?@\b" +
                    "\5\1\2@I\7\35\2\2AB\7\20\2\2BI\7\35\2\2CI\t\5\2\2DE\7\5\2\2EF\5\b\5\2" +
                    "FG\7\6\2\2GI\3\2\2\2H?\3\2\2\2HA\3\2\2\2HC\3\2\2\2HD\3\2\2\2IO\3\2\2\2" +
                    "JK\f\7\2\2KL\t\6\2\2LN\5\b\5\bMJ\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2" +
                    "P\t\3\2\2\2QO\3\2\2\2RS\7\b\2\2ST\5\b\5\2TU\7\7\2\2UV\5\b\5\2VW\7\t\2" +
                    "\2W\13\3\2\2\2\t\17\32\60:<HO";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}