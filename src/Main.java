public class Main {
    public static void main(String[] args) throws TooManyLoginAttemptsException {
        AccountManagerImpl acc = new AccountManagerImpl();
        Person person = new Person("nastya","11.12");
        Person pers = new Person("xenia","14.01");
        System.out.println(acc.hasAccount("a.bliznyak@g.ru"));
        System.out.println(acc.numOfAccounts());
        System.out.println(acc.getPerson("a.bliznyak@g.ru","123"));
        AttemptCounter counter = new AttemptCounter();
        try{
            System.out.println(acc.getPerson("a.bliznyak@g.ru","1235"));
            System.out.println("hu");
            System.out.println(acc.getPerson("a.bliznyak@g.ru","fkmd"));
            System.out.println(acc.getPerson("a.bliznyak@g.ru","fkmd"));
            System.out.println(acc.getPerson("a.bliznyak@g.ru","fkmd"));
            System.out.println(acc.getPerson("a.bliznyak@g.ru","fkmd"));
        }catch(TooManyLoginAttemptsException e){
            e.printStackTrace();
        }










    }


}