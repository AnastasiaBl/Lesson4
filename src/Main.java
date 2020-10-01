public class Main {
    public static void main(String[] args){
        AccountManagerImpl acc = new AccountManagerImpl();
        Person person = new Person("nastya","11.12");
        Person pers = new Person("xenia","14.01");
        System.out.println(acc.hasAccount("a.bliznyak@g.ru"));
        System.out.println(acc.numOfAccounts());








    }


}