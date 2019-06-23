package io.github.eggloop.stl;// Generated from STL.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link STLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface STLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link STLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(STLParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textformula}
	 * labeled alternative in {@link STLParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextformula(STLParser.TextformulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link STLParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(STLParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link STLParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank(STLParser.BlankContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(STLParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code U}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitU(STLParser.UContext ctx);
	/**
	 * Visit a parse tree produced by the {@code F}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF(STLParser.FContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parensFormula}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensFormula(STLParser.ParensFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueFalse}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueFalse(STLParser.TrueFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code G}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitG(STLParser.GContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndOr}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOr(STLParser.AndOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link STLParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(STLParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code number}
	 * labeled alternative in {@link STLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(STLParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegNumber}
	 * labeled alternative in {@link STLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegNumber(STLParser.NegNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link STLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(STLParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parensExpr}
	 * labeled alternative in {@link STLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpr(STLParser.ParensExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AlgOp}
	 * labeled alternative in {@link STLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlgOp(STLParser.AlgOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(STLParser.IntervalContext ctx);
}