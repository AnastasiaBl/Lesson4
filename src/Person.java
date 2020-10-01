public class Person {
    private String name;
    private String data;

    public Person(String name,String data){
        this.name = name;
        this.data = data;
    }
    public void setName(String n){
        name = n;
    }
    public void setData(String d){
        data = d;
    }

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}

