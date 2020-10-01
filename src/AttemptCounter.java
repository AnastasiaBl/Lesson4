public class AttemptCounter {
    private static AttemptCounter instance;
    private int count =0;
    public static synchronized AttemptCounter getInstance() {
        if (instance == null) {
            instance = new AttemptCounter();
        }
        return instance;
    }
    void count() throws TooManyLoginAttemptsException{
        if (count == 5) {
            throw new TooManyLoginAttemptsException("Too many login attempts");
        }
        count+=1;
    }

}
