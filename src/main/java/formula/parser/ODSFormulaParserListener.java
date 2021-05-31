package formula.parser;// Generated from ODSFormulaParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ODSFormulaParserParser}.
 */
public interface ODSFormulaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(ODSFormulaParserParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(ODSFormulaParserParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#intro}.
	 * @param ctx the parse tree
	 */
	void enterIntro(ODSFormulaParserParser.IntroContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#intro}.
	 * @param ctx the parse tree
	 */
	void exitIntro(ODSFormulaParserParser.IntroContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#forceRecalc}.
	 * @param ctx the parse tree
	 */
	void enterForceRecalc(ODSFormulaParserParser.ForceRecalcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#forceRecalc}.
	 * @param ctx the parse tree
	 */
	void exitForceRecalc(ODSFormulaParserParser.ForceRecalcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ODSFormulaParserParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ODSFormulaParserParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#prefixOp}.
	 * @param ctx the parse tree
	 */
	void enterPrefixOp(ODSFormulaParserParser.PrefixOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#prefixOp}.
	 * @param ctx the parse tree
	 */
	void exitPrefixOp(ODSFormulaParserParser.PrefixOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#postfixOp}.
	 * @param ctx the parse tree
	 */
	void enterPostfixOp(ODSFormulaParserParser.PostfixOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#postfixOp}.
	 * @param ctx the parse tree
	 */
	void exitPostfixOp(ODSFormulaParserParser.PostfixOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#infixOp}.
	 * @param ctx the parse tree
	 */
	void enterInfixOp(ODSFormulaParserParser.InfixOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#infixOp}.
	 * @param ctx the parse tree
	 */
	void exitInfixOp(ODSFormulaParserParser.InfixOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#arithmeticOp}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOp(ODSFormulaParserParser.ArithmeticOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#arithmeticOp}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOp(ODSFormulaParserParser.ArithmeticOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#additiveOp}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveOp(ODSFormulaParserParser.AdditiveOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#additiveOp}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveOp(ODSFormulaParserParser.AdditiveOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#mulplicativeOP}.
	 * @param ctx the parse tree
	 */
	void enterMulplicativeOP(ODSFormulaParserParser.MulplicativeOPContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#mulplicativeOP}.
	 * @param ctx the parse tree
	 */
	void exitMulplicativeOP(ODSFormulaParserParser.MulplicativeOPContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOp(ODSFormulaParserParser.ComparisonOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOp(ODSFormulaParserParser.ComparisonOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#referenceOp}.
	 * @param ctx the parse tree
	 */
	void enterReferenceOp(ODSFormulaParserParser.ReferenceOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#referenceOp}.
	 * @param ctx the parse tree
	 */
	void exitReferenceOp(ODSFormulaParserParser.ReferenceOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(ODSFormulaParserParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(ODSFormulaParserParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(ODSFormulaParserParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(ODSFormulaParserParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#emptyOrParameter}.
	 * @param ctx the parse tree
	 */
	void enterEmptyOrParameter(ODSFormulaParserParser.EmptyOrParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#emptyOrParameter}.
	 * @param ctx the parse tree
	 */
	void exitEmptyOrParameter(ODSFormulaParserParser.EmptyOrParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(ODSFormulaParserParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(ODSFormulaParserParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(ODSFormulaParserParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(ODSFormulaParserParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#rangeAddress}.
	 * @param ctx the parse tree
	 */
	void enterRangeAddress(ODSFormulaParserParser.RangeAddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#rangeAddress}.
	 * @param ctx the parse tree
	 */
	void exitRangeAddress(ODSFormulaParserParser.RangeAddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#sheetLocatorOrEmpty}.
	 * @param ctx the parse tree
	 */
	void enterSheetLocatorOrEmpty(ODSFormulaParserParser.SheetLocatorOrEmptyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#sheetLocatorOrEmpty}.
	 * @param ctx the parse tree
	 */
	void exitSheetLocatorOrEmpty(ODSFormulaParserParser.SheetLocatorOrEmptyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#sheetLocator}.
	 * @param ctx the parse tree
	 */
	void enterSheetLocator(ODSFormulaParserParser.SheetLocatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#sheetLocator}.
	 * @param ctx the parse tree
	 */
	void exitSheetLocator(ODSFormulaParserParser.SheetLocatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#subtableCell}.
	 * @param ctx the parse tree
	 */
	void enterSubtableCell(ODSFormulaParserParser.SubtableCellContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#subtableCell}.
	 * @param ctx the parse tree
	 */
	void exitSubtableCell(ODSFormulaParserParser.SubtableCellContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(ODSFormulaParserParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(ODSFormulaParserParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#cellAddress}.
	 * @param ctx the parse tree
	 */
	void enterCellAddress(ODSFormulaParserParser.CellAddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#cellAddress}.
	 * @param ctx the parse tree
	 */
	void exitCellAddress(ODSFormulaParserParser.CellAddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#referenceList}.
	 * @param ctx the parse tree
	 */
	void enterReferenceList(ODSFormulaParserParser.ReferenceListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#referenceList}.
	 * @param ctx the parse tree
	 */
	void exitReferenceList(ODSFormulaParserParser.ReferenceListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#namedExpression}.
	 * @param ctx the parse tree
	 */
	void enterNamedExpression(ODSFormulaParserParser.NamedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#namedExpression}.
	 * @param ctx the parse tree
	 */
	void exitNamedExpression(ODSFormulaParserParser.NamedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#simpleNamedExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleNamedExpression(ODSFormulaParserParser.SimpleNamedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#simpleNamedExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleNamedExpression(ODSFormulaParserParser.SimpleNamedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#sheetLocalNamedExpression}.
	 * @param ctx the parse tree
	 */
	void enterSheetLocalNamedExpression(ODSFormulaParserParser.SheetLocalNamedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#sheetLocalNamedExpression}.
	 * @param ctx the parse tree
	 */
	void exitSheetLocalNamedExpression(ODSFormulaParserParser.SheetLocalNamedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#externalNamedExpression}.
	 * @param ctx the parse tree
	 */
	void enterExternalNamedExpression(ODSFormulaParserParser.ExternalNamedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#externalNamedExpression}.
	 * @param ctx the parse tree
	 */
	void exitExternalNamedExpression(ODSFormulaParserParser.ExternalNamedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(ODSFormulaParserParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(ODSFormulaParserParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link ODSFormulaParserParser#matrixRow}.
	 * @param ctx the parse tree
	 */
	void enterMatrixRow(ODSFormulaParserParser.MatrixRowContext ctx);
	/**
	 * Exit a parse tree produced by {@link ODSFormulaParserParser#matrixRow}.
	 * @param ctx the parse tree
	 */
	void exitMatrixRow(ODSFormulaParserParser.MatrixRowContext ctx);
}