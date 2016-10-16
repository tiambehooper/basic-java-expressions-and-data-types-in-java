package com.theironyard;

import com.github.javaparser.ParseException;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import net.doughughes.testifier.annotation.Testifier;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.util.SourceCodeExtractor;
import net.doughughes.testifier.util.TestifierAnnotationReader;
import net.doughughes.testifier.watcher.NotifyingWatcher;
import net.doughughes.testifier.watcher.OutputWatcher;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

@Testifier(sourcePath = "./src/main/java/com/theironyard/BasicExpressions.java", clazz = BasicExpressions.class)
public class BasicExpressionsTest {

    @Rule
    public NotifyingWatcher notifyingWatcher = new NotifyingWatcher("http://testifier.doughughes.net/notify");

    @Rule
    public OutputWatcher outputWatcher = new OutputWatcher();

    @Test
    @Testifier(method = "outputBoolean", args = {})
    public void outputBooleanTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputBoolean();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a boolean keyword.",
                printed.get(0), isOneOf(true, false));
    }

    @Test
    @Testifier(method = "outputTrue", args = {})
    public void outputTrueTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputTrue();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a boolean keyword for true.",
                printed.get(0), equalTo(true));
    }

    @Test
    @Testifier(method = "outputFalse", args = {})
    public void outputFalseTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputFalse();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a boolean keyword for false.",
                printed.get(0), equalTo(false));
    }

    @Test
    @Testifier(method = "outputPositiveInteger", args = {})
    public void outputPositiveIntegerTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputPositiveInteger();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a positive integer.",
                (int) printed.get(0), greaterThanOrEqualTo(0));
    }

    @Test
    @Testifier(method = "outputNegativeInteger", args = {})
    public void outputNegativeIntegerTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputNegativeInteger();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a negative integer.",
                (int) printed.get(0), lessThan(0));
    }

    @Test
    @Testifier(method = "outputPositiveDouble", args = {})
    public void outputPositiveDoubleTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputPositiveDouble();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a positive double.",
                (double) printed.get(0), greaterThanOrEqualTo(0.0));
    }

    @Test
    @Testifier(method = "outputNegativeDouble", args = {})
    public void outputNegativeDoubleTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputNegativeDouble();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a negative double.",
                (double) printed.get(0), lessThan(0.0));
    }

    @Test
    @Testifier(method = "outputHelloWorld", args = {})
    public void outputHelloWorldTest() {
        /* arrange */

        /* act */
        BasicExpressions.outputHelloWorld();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be \"Hello World!\"",
                printed.get(0), equalTo("Hello World!"));
    }

    @Test
    @Testifier(method = "outputConcatenationOfTwoStrings", args = {})
    public void outputConcatenationOfTwoStringsTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputConcatenationOfTwoStrings();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a String.",
                printed.get(0), instanceOf(String.class));

        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputConcatenationOfTwoStringsTest").getAnnotation(Testifier.class)
        );

        SourceCodeExtractor extractor = new SourceCodeExtractor(reader.getSourcePath());
        List<Node> exprs = extractor.filterFlattenedNodes(BinaryExpr.class, reader.getMethod(), reader.getArgs());

        boolean found = false;
        for (Node node : exprs) {
            BinaryExpr expr = (BinaryExpr) node;
            if (expr.getLeft().getClass().equals(StringLiteralExpr.class) &&
                    expr.getOperator().name().equals("plus") &&
                    expr.getRight().getClass().equals(StringLiteralExpr.class)) {
                found = true;
                break;
            }
        }

        assertThat("The method should have two Strings concatenated together",
                found, is(true));
    }

    @Test
    @Testifier(method = "outputConcatenationOfStringAndInteger", args = {})
    public void outputConcatenationOfStringAndIntegerTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputConcatenationOfStringAndInteger();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a String.",
                printed.get(0), instanceOf(String.class));

        // read this test's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputConcatenationOfStringAndIntegerTest").getAnnotation(Testifier.class)
        );

        // get the method's binary expressions
        SourceCodeExtractor extractor = new SourceCodeExtractor(reader.getSourcePath());
        List<Node> exprs = extractor.filterFlattenedNodes(BinaryExpr.class, reader.getMethod(), reader.getArgs());

        boolean found = false;
        for (Node node : exprs) {
            BinaryExpr expr = (BinaryExpr) node;
            if (    (expr.getLeft().getClass().equals(StringLiteralExpr.class) &&
                            expr.getOperator().name().equals("plus") &&
                            expr.getRight().getClass().equals(IntegerLiteralExpr.class)
                    )
                            ||
                    (expr.getLeft().getClass().equals(IntegerLiteralExpr.class) &&
                            expr.getOperator().name().equals("plus") &&
                            expr.getRight().getClass().equals(StringLiteralExpr.class)
                    )   ) {
                found = true;
                break;
            }
        }

        assertThat("The method should concatenate a String and an integer together",
                found, is(true));
    }

    @Test
    @Testifier(method = "outputSumOfTwoIntegers", args = {})
    public void outputSumOfTwoIntegersTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfTwoIntegers();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be an integer.",
                printed.get(0), instanceOf(int.class));

        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputSumOfTwoIntegersTest").getAnnotation(Testifier.class)
        );

        SourceCodeExtractor extractor = new SourceCodeExtractor(reader.getSourcePath());
        List<Node> exprs = extractor.filterFlattenedNodes(BinaryExpr.class, reader.getMethod(), reader.getArgs());

        boolean found = false;
        for (Node node : exprs) {
            BinaryExpr expr = (BinaryExpr) node;
            if (expr.getLeft().getClass().equals(IntegerLiteralExpr.class) &&
                    expr.getOperator().name().equals("plus") &&
                    expr.getRight().getClass().equals(IntegerLiteralExpr.class)) {
                found = true;
                break;
            }
        }

        assertThat("The method should add two integers together",
                found, is(true));
    }


    @Test
    @Testifier(method = "outputSumOfTwoDoubles", args = {})
    public void outputSumOfTwoDoublesTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfTwoDoubles();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a double.",
                printed.get(0), instanceOf(double.class));

        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputSumOfTwoDoublesTest").getAnnotation(Testifier.class)
        );

        SourceCodeExtractor extractor = new SourceCodeExtractor(reader.getSourcePath());
        List<Node> exprs = extractor.filterFlattenedNodes(BinaryExpr.class, reader.getMethod(), reader.getArgs());

        boolean found = false;
        for (Node node : exprs) {
            BinaryExpr expr = (BinaryExpr) node;
            if (expr.getLeft().getClass().equals(DoubleLiteralExpr.class) &&
                    expr.getOperator().name().equals("plus") &&
                    expr.getRight().getClass().equals(DoubleLiteralExpr.class)) {
                found = true;
                break;
            }
        }

        assertThat("The method should add two doubles together",
                found, is(true));
    }

    @Test
    @Testifier(method = "outputSumOfADoubleAndAnInteger", args = {})
    public void outputSumOfADoubleAndAnIntegerTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfADoubleAndAnInteger();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The value printed should be a double.",
                printed.get(0), instanceOf(double.class));

        // read this test's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputSumOfADoubleAndAnIntegerTest").getAnnotation(Testifier.class)
        );

        // get the method's binary expressions
        SourceCodeExtractor extractor = new SourceCodeExtractor(reader.getSourcePath());
        List<Node> exprs = extractor.filterFlattenedNodes(BinaryExpr.class, reader.getMethod(), reader.getArgs());

        boolean found = false;
        for (Node node : exprs) {
            BinaryExpr expr = (BinaryExpr) node;
            if (    (expr.getLeft().getClass().equals(DoubleLiteralExpr.class) &&
                    expr.getOperator().name().equals("plus") &&
                    expr.getRight().getClass().equals(IntegerLiteralExpr.class)
            )
                    ||
                    (expr.getLeft().getClass().equals(IntegerLiteralExpr.class) &&
                            expr.getOperator().name().equals("plus") &&
                            expr.getRight().getClass().equals(DoubleLiteralExpr.class)
                    )   ) {
                found = true;
                break;
            }
        }

        assertThat("The method should add an integer and double together",
                found, is(true));
    }

}