import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AccountManagerImpl implements AccountManager {


    private Person person;

    @Override
    public void registerNewAccount(String email, String password, Person person) throws DuplicateAccountException {
        String csvFile = "C:/Users/anast/IdeaProjects/Lesson4/src/database/db.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] accounts = {email, password, person.getData(), person.getName()};
        try {
            String account = "";
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] a = line.split(cvsSplitBy);

                if (a[0].equals(email)) {
                    throw new DuplicateAccountException("Account Already exists");

                }
                account += a[0] + "," + a[1] + "," + a[2] + "," + a[3] + "\n";

            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(account + accounts[0] + "," + accounts[1] + "," + accounts[2] + "," + accounts[3]);
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException {
        String csvFile = "C:/Users/anast/IdeaProjects/Lesson4/src/database/db.csv";
        BufferedReader br = null;
        BufferedWriter writer = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            List<String> persons = new ArrayList<String>();
            String line = "";
            while ((line = br.readLine()) != null) {
                persons.add(line);
            }
            String lineToRemove = null;
            for (String i:persons) {
                if (i.split(",")[0].equals(email) & i.split(",")[1].equals(password)) {
                    lineToRemove = i;
                }
            }
            persons.remove(lineToRemove);
            writer = new BufferedWriter(new FileWriter("C:/Users/anast/IdeaProjects/Lesson4/src/database/db.csv"));
            for(String j:persons){
                writer.write(j);
                writer.newLine();

            }
            if(lineToRemove==null){
                throw new WrongCredentialsException("Wrong password or email");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    public boolean hasAccount(String email) {
        String csvFile = "C:/Users/anast/IdeaProjects/Lesson4/src/database/db.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] a = line.split(cvsSplitBy);
                if (a[0].equals(email)) {
                    return true;
                }
            }

            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException,WrongCredentialsException {
        AttemptCounter attempts = AttemptCounter.getInstance();
        String csvFile = "C:/Users/anast/IdeaProjects/Lesson4/src/database/db.csv";
        BufferedReader br = null;
        String line = "";
        Person person = null;
        try{
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(line.split(",")[0].equals(email) & line.split(",")[1].equals(password)){
                    person = new Person(line.split(",")[3],line.split(",")[2]);
                    attempts.count();

                }
            }
            if(person == null){
                attempts.count();
                throw new WrongCredentialsException("Wrong password or email");
            }
        } catch (IOException|WrongCredentialsException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return person;
    }

    @Override
    public int numOfAccounts() {
        String csvFile = "C:/Users/anast/IdeaProjects/Lesson4/src/database/db.csv";
        BufferedReader br;
        String line="";
        int lineNumber=0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                lineNumber++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return lineNumber;
    }
}







