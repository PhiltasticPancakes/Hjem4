import java.util.*;
import java.io.*;
import java.lang.reflect.Field;
public class Test {
    private String[] testVal, expectedVal;
    private int testnr = 0;

    public void setupTest (String[] testval, String[] expectedVal) {
        this.testVal = testval;
        this.expectedVal = expectedVal;
    }

    // run a test for a class's toString() method and see if succeeds / fails
    // I.e. set vars in the class to something and see if toString() returns them as expected
    // is currently class specific (see logTest())
    public String runTest (Object testClass) {
        String expectedOutput = expectedVal[0];
        String[] classFields = getClassFields(testClass);
        String testlog;
        if (testVal.length != classFields.length) return "Not right amount of values provided in order to test this class!";
        for (int i = 1; i < expectedVal.length; i++) expectedOutput += ", " + expectedVal[i]; 
		testlog = (!testClass.toString().replace("\"", "").equals(expectedOutput)) ? "Test " + testnr + ": FAILURE. " : "Test " + testnr + ": SUCCESS. ";
        for (int i = 0; i < testVal.length; i++) testlog += classFields[i] + " = \"" + testVal[i] + "\"   "; 
        return testlog + "\nReceived forlag.toString(): \"" + testClass.toString() + "\"\n";
    }

    // return an array of fields/vars existing in a class
    private String[] getClassFields(Object obj) {
        Field[] field = obj.getClass().getDeclaredFields();
        String sfield;
        String[] sfieldArr = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            sfield = field[i].toString();
            sfieldArr[i] = sfield.substring(sfield.indexOf(obj.getClass().getName()), sfield.length());
            if (!field[i].getType().getName().startsWith("java.lang")) sfieldArr[i] = Arrays.toString(getInnerFields(field[i]));
        }
        return Arrays.toString(sfieldArr).replace("[", "").replace("]", "").split(", ");
    }

    // some classes has other classes as datatype/fields (which often contains more than one field)
    // this class returns those
    private String[] getInnerFields(Field obj) {
        Field[] field = obj.getType().getDeclaredFields();
        String sfield;
        String[] sfieldArr = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            sfield = field[i].toString();
            sfieldArr[i] = "";
            sfieldArr[i] += sfield.substring(sfield.indexOf(obj.getType().getName()), sfield.length());
            getInnerFields(field[i]);
        }
        return sfieldArr;
    }

    public static void main(String[] args) throws IOException{
        Test test = new Test();
        Tidsskrift tidsskrift = new Tidsskrift("");
        Forlag forlag = new Forlag("", "");

        logTest(tidsskrift, "tidsskrift-tests.txt", "tidsskrift-results.txt", "tidsskrift-log.txt");
        logTest(forlag, "forlag-tests.txt", "forlag-results.txt", "forlag-log.txt");
    }

    // log a test result to outputPath for an object obj using 
    // a testFilePath (lists of inputs to try) and a resultFilePath (list of outputs that are expected from test inputs)
    // currently only Tidsskrift and Forlag classes have defined test procedures
    public static void logTest(Object obj, String testFilePath, String resultFilePath, String outputPath) throws IOException {
        String[] t, r;
        Scanner testFile = new Scanner (new File(testFilePath));
        Scanner checkFile = new Scanner (new File(resultFilePath));
        Test test = new Test();
        test.testnr = 0;
        FileWriter writeLog = new FileWriter(outputPath);
        while(testFile.hasNextLine() && checkFile.hasNextLine()) {
            test.testnr++;
            t = testFile.nextLine().split(", ");
            r = checkFile.nextLine().split(", ");
            test.setupTest(t, r);
            if (obj instanceof Tidsskrift) {
                Tidsskrift tidsskrift = new Tidsskrift(t[0]);
                tidsskrift.setForlag(new Forlag(t[1], t[2]));
                tidsskrift.setIssn(t[3]);
                writeLog.append(test.runTest(tidsskrift)+ "\n");
            } else if (obj instanceof Forlag) {
                Forlag forlag = new Forlag(t[0], t[1]);
                writeLog.append(test.runTest(forlag)+ "\n");
            } else {
                System.out.println("No tests procedures defined for this class!");
            }
        }
        writeLog.close();
    }
}
