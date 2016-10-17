package com.theironyard;

import com.github.javaparser.ParseException;
import net.doughughes.testifier.annotation.Testifier;
import net.doughughes.testifier.matcher.RegexMatcher;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.util.SourceCodeExtractor;
import net.doughughes.testifier.util.TestifierAnnotationReader;
import net.doughughes.testifier.watcher.NotifyingWatcher;
import net.doughughes.testifier.watcher.OutputWatcher;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

@Testifier(sourcePath = "./src/main/java/com/theironyard/BasicExpressions.java", clazz = BasicExpressions.class)
public class BasicExpressionsTest {

    @Rule
    public NotifyingWatcher notifyingWatcher = new NotifyingWatcher("https://tiy-testifier-webapp.herokuapp.com/notify");

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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
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
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
        assertThat("The value printed should be a String.",
                printed.get(0), instanceOf(String.class));
    }

    @Test
    @Testifier(method = "outputConcatenationOfTwoStrings", args = {})
    public void outputConcatenationOfTwoStringsCodeStructureTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputConcatenationOfTwoStrings();

        /* assert */
        // read this test's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputConcatenationOfTwoStringsCodeStructureTest").getAnnotation(Testifier.class)
        );

        // check the structure of the code
        String methodDescription = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());
        assertThat("The method should have two Strings concatenated together",
                methodDescription, RegexMatcher.matches("^.*?StringLiteralExpr plus StringLiteralExpr.*?$"));
    }

    @Test
    @Testifier(method = "outputConcatenationOfStringAndInteger", args = {})
    public void outputConcatenationOfStringAndIntegerTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputConcatenationOfStringAndInteger();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
        assertThat("The value printed should be a String.",
                printed.get(0), instanceOf(String.class));
    }

    @Test
    @Testifier(method = "outputConcatenationOfStringAndInteger", args = {})
    public void outputConcatenationOfStringAndIntegerCodeStructureTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputConcatenationOfStringAndInteger();

        /* assert */
        // read this test's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputConcatenationOfStringAndIntegerCodeStructureTest").getAnnotation(Testifier.class)
        );

        // check the structure of the code
        String methodDescription = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());
        assertThat("The method should concatenate a String and an integer together",
                methodDescription, RegexMatcher.matches("^.*?((StringLiteralExpr plus IntegerLiteralExpr)|(IntegerLiteralExpr plus StringLiteralExpr)).*?$"));

    }

    @Test
    @Testifier(method = "outputSumOfTwoIntegers", args = {})
    public void outputSumOfTwoIntegersTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfTwoIntegers();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
        assertThat("The value printed should be an integer.",
                printed.get(0), instanceOf(int.class));
    }

    @Test
    @Testifier(method = "outputSumOfTwoIntegers", args = {})
    public void outputSumOfTwoIntegersCodeStructureTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfTwoIntegers();

        /* assert */
        // read this test method's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputSumOfTwoIntegersCodeStructureTest").getAnnotation(Testifier.class)
        );

        // check the structure of the code
        String methodDescription = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());
        assertThat("The method should add two integers together",
                methodDescription, RegexMatcher.matches("^.*?IntegerLiteralExpr plus IntegerLiteralExpr.*?$"));

    }

    @Test
    @Testifier(method = "outputSumOfTwoDoubles", args = {})
    public void outputSumOfTwoDoublesTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfTwoDoubles();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
        assertThat("The value printed should be a double.",
                printed.get(0), instanceOf(double.class));
    }

    @Test
    @Testifier(method = "outputSumOfTwoDoubles", args = {})
    public void outputSumOfTwoDoublesCodeStructureTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfTwoDoubles();

        /* assert */
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputSumOfTwoDoublesCodeStructureTest").getAnnotation(Testifier.class)
        );

        // check the structure of the code
        String methodDescription = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());
        assertThat("The method should add two doubles together",
                methodDescription, RegexMatcher.matches("^.*?DoubleLiteralExpr plus DoubleLiteralExpr.*?$"));
    }

    @Test
    @Testifier(method = "outputSumOfADoubleAndAnInteger", args = {})
    public void outputSumOfADoubleAndAnIntegerTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfADoubleAndAnInteger();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
        assertThat("The value printed should be a double.",
                printed.get(0), instanceOf(double.class));
    }

    @Test
    @Testifier(method = "outputSumOfADoubleAndAnInteger", args = {})
    public void outputSumOfADoubleAndAnIntegerCodeStructureTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfADoubleAndAnInteger();

        /* assert */
        // read this test's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputSumOfADoubleAndAnIntegerCodeStructureTest").getAnnotation(Testifier.class)
        );

        // check the structure of the code
        String methodDescription = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());
        assertThat("The method should add an integer and double together",
                methodDescription, RegexMatcher.matches("^.*?((DoubleLiteralExpr plus IntegerLiteralExpr)|(IntegerLiteralExpr plus DoubleLiteralExpr)).*?$"));

    }

    @Test
    @Testifier(method = "outputSumOfThreeIntegers", args = {})
    public void outputSumOfThreeIntegersTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfThreeIntegers();

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The method should have printed some output.",
                printed.size(), greaterThan(0));
        assertThat("The value printed should be an integer.",
                printed.get(0), instanceOf(int.class));
    }

    @Test
    @Testifier(method = "outputSumOfThreeIntegers", args = {})
    public void outputSumOfThreeIntegersCodeStructureTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */
        BasicExpressions.outputSumOfThreeIntegers();

        /* assert */
        // read this test's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("outputSumOfThreeIntegersCodeStructureTest").getAnnotation(Testifier.class)
        );

        // check the structure of the code
        String methodDescription = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());
        assertThat("The method should add three integers together",
                methodDescription, RegexMatcher.matches("^.*?IntegerLiteralExpr plus IntegerLiteralExpr plus IntegerLiteralExpr.*?$"));

    }

}