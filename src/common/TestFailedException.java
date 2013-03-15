package common;

/**
 * The class <code>TestFailedException</code> and its subclasses are used for errors,
 * appeared during test run.
 *
 * @author
 */
public class TestFailedException extends Exception {

    private static final long serialVersionUID = 1L;
    private String url;
    private String message;
    private String exceptionType;
    //Reporting reporting;


    //	used for known exceptions with specified message
    public TestFailedException(String message, String exceptionType){ //LoggingSelenium selenium ,

        super(message + " | url: '" + "'");                      //getLocation(selenium) +
        this.message=message;
        this.exceptionType=exceptionType;
        //reporting=new Reporting(selenium);

        //url = getLocation(selenium);

        //	if exception is thrown manually then LoggingSelenium doesn't log error automatically
        //selenium.logAssertion(exceptionType, message + " | url:" + url, createStackTrace( this ));
        //selenium.logAssertion(exceptionType, message + " | url:" + url, toString());
        //Reporting.LOG("Error was:"+message);
        //Reporting.LOG("Expected:"+Reporting.getErrorMessage());
        //Reporting.reportlog(exceptionType,message);
        //selenium.logAssertion(exceptionType, message, toString());

    }


    //	used for unknown exceptions that are catched from script
    public TestFailedException(Exception exception){                                      //LoggingSelenium selenium ,

        super(exception.getMessage() + " | url: '" + "'" + createStackTrace(exception));  //getLocation(selenium) +
        //url = getLocation(selenium);
    }

    /**
     * It is getter which will return the url
     * @return String  - url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the location of the error while running
     * //@param selenium
     * @return location
     */
    private static String getLocation(  ){                       //LoggingSelenium selenium

        String location = "unknown";

        try{
            //location = selenium.getLocation();
        } catch (Exception e) {
            location = "error in selenium.getLocation()";
        }

        return location;

    }
    /**
     * Create a stack trace of the error
     * @param e
     * @return stackTrace
     */
    private static String createStackTrace( Exception e ){

        String stackTrace = "";

        for( int i=0; i<e.getStackTrace().length; i++ )
            stackTrace = stackTrace + "\n           " + e.getStackTrace()[i].toString();

        return stackTrace;
    }


    public String toString(){
        return  exceptionType+"|"+message;
    }

}

