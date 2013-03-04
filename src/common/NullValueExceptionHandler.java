package common;

/**
 * The class <code>NullValueExceptionHandler</code> is used for null value verifications,
 * appeared during test run.
 *
 * @author
 */
public class NullValueExceptionHandler extends TestFailedException {
    private static final long serialVersionUID = 1L;
    private String nullValue;

    public NullValueExceptionHandler(LoggingSelenium selenium, String nullValue){

        super( selenium, nullValue, "nullValueFoundException" );
        this.nullValue = nullValue;
    }


    public String getElement(){
        return nullValue;
    }


}
