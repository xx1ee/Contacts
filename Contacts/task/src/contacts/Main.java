package contacts;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
interface Builder {
    void setName(String name);
    void setSurname(String surname);
    void setNumber(String number);
    void setOrganizationName(String organizationName);
    void setAddress(String address);
    void setBirthDay(String birthDay);
    void setGender(String gender);

    Contact getPerson();
    Contact getOrganization();
}
class PersonBuilder implements Builder {

    private String name;
    private String surname;
    private String number;
    private String birthDay;
    private String gender;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setNumber(String number) {
        if (check_number(number)) {
            this.number = number;
        } else {
            System.out.println("Bad number!");
            this.number = "[no data]";
        }
    }

    @Deprecated
    public void setOrganizationName(String organizationName) {}

    @Deprecated
    public void setAddress(String address) {}

    @Deprecated
    public Contact getOrganization() {return null;}

    @Override
    public void setBirthDay(String birthDay) {

        this.birthDay = birthDay;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Contact getPerson() {
        return new Person(name, surname, number, birthDay, gender);
    }
    public boolean check_number(String number) {
        boolean flag = false;
        String regex = "^\\+?[0-9a-zA-Z]{1,}(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        while (matcher.find()) {
            flag = true;
        }
        String regex1 = "^\\+?\\([0-9a-zA-Z]\\)(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})*$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(number);
        while (matcher1.find()) {
            flag = true;
        }
        String regex2 = "^\\+?[\\da-zA-Z]([ -]\\([\\da-zA-Z]{2,})?\\)([ -][\\da-zA-Z]{2,})*$";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(number);
        while (matcher2.find()) {
            flag = true;
        }
        return flag;
    }
}
abstract class Contact {
    public String name;
    public String surname;
    public String created;
    public String edit;
    public String number = "";
    public Contact(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.created = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));;
        this.edit = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));;
    }

    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
        this.created = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));;
        this.edit = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        if (check_number(number)) {
            this.number = number;
        } else {
            System.out.println("Bad number!");
            this.number = "[no data]";
        }
    }
    public void setName(String name) {
        this.name = name;
        edit = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        edit = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));;
    }
    public String getName() {
        return this.name;
    }
    public String getSurname() {
        return this.surname;
    }
    public void setLastEdittedTime(LocalDateTime lastEdittedTime) {
        this.edit = String.valueOf(lastEdittedTime.withSecond(0).withNano(0));
    }
    public boolean check_number(String number) {
        boolean flag = false;
        String regex = "^\\+?[0-9a-zA-Z]{1,}(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        while (matcher.find()) {
            flag = true;
        }
        String regex1 = "^\\+?\\([0-9a-zA-Z]\\)(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})*$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(number);
        while (matcher1.find()) {
            flag = true;
        }
        String regex2 = "^\\+?[\\da-zA-Z]([ -]\\([\\da-zA-Z]{2,})?\\)([ -][\\da-zA-Z]{2,})*$";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(number);
        while (matcher2.find()) {
            flag = true;
        }
        return flag;
    }
    abstract public void info(int check);
    abstract public void record();
    abstract public void print();
    abstract public String match();
}
class Org extends Contact {
    public String address = "";
    public Org(String name, String address, String number) {
        super(number, name);
        this.address = address;
    }

    public String getOrgname() {
        return super.name;
    }

    public void setOrgname(String name) {
        super.setName(name);
        super.setLastEdittedTime(LocalDateTime.now());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        super.setLastEdittedTime(LocalDateTime.now());
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }

    @Override
    public void setNumber(String number) {
        super.setNumber(number);
        super.setLastEdittedTime(LocalDateTime.now());
    }
    @Override
    public void info(int check) {
        System.out.println(check + ". " + getOrgname());
    }
    @Override
    public void record() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nSelect a field (address, number): ");
        String field = sc.next();
        System.out.print("Enter " + field + ": ");
        Scanner scanner = new Scanner(System.in);
        String field0 = scanner.nextLine();
        switch (field) {
            case "number":
                setNumber(field0);
                System.out.println("Saved\n");
                break;
            case "address":
                setAddress(field0);
                System.out.println("Saved\n");
                break;
        }
    }

    @Override
    public void print() {
        System.out.println("Organization name: " + getOrgname());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + created);
        System.out.println("Time last edit: " + edit);
    }

    @Override
    public String match() {
        return getOrgname().toLowerCase();
    }
}
class Person extends Contact {
    public String birth = "";
    public String gender = "";
    public Person(String name, String surname, String number, String  birthDay, String gender) {
        super(name, surname, number);
        this.birth = birthDay;
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        if (check_gender(gender)) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
        super.setLastEdittedTime(LocalDateTime.now());
    }
    public String getBirth() {
        return this.birth;
    }
    public void setBirth(String birth) {
        if (HasBirth()) {
            this.birth = birth;
        } else {
            System.out.println("Bad birth date!");
            this.birth = "[no data]";
        }
        super.setLastEdittedTime(LocalDateTime.now());
    }
    public String getName() {
        return super.name;
    }
    public void setSurname(String surname) {
        super.surname = surname;
        super.setLastEdittedTime(LocalDateTime.now());
    }
    public String getSurname() {
        return super.surname;
    }
    public void setName(String name) {
        super.name = name;
        super.setLastEdittedTime(LocalDateTime.now());
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }

    @Override
    public void setNumber(String number) {
        super.setNumber(number);
    }
    @Override
    public void info(int check) {
        System.out.println(check + ". " + getName() + " " + getSurname());
    }
    public boolean HasBirth() {
        if (!this.birth.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean check_gender(String gender) {
        boolean flag = false;
        String regex = "M|F";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gender);
        while (matcher.find()) {
            flag = true;
        }
        return flag;
    }
    public void record() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nSelect a field (name, surname, birth, gender, number): ");
        String field = sc.next();
        System.out.print("Enter " + field + ": ");
        Scanner scanner = new Scanner(System.in);
        String field0 = scanner.nextLine();
        switch (field) {
            case "number":
                setNumber(field0);
                break;
            case "name":
                setName(field0);
                break;
            case "surname":
                setSurname(field0);
                break;
            case "gender":
                setGender(field0);
                break;
        }
    }

    @Override
    public void print() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Birth date: " + getBirth());
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + created);
        System.out.println("Time last edit: " + edit);
    }

    @Override
    public String match() {
        return getName().toLowerCase() + " " + getSurname().toLowerCase();
    }
}
class Director {

    private Builder builder;

    Scanner scanner = new Scanner(System.in);

    public void buildContact(Builder builder) {
        System.out.print("Enter the name: ");
        builder.setName(scanner.nextLine());
        System.out.print("Enter the surname: ");
        builder.setSurname(scanner.nextLine());
        System.out.print("Enter the number: ");
        builder.setNumber(scanner.nextLine());
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void buildPerson(Builder builder) {
        System.out.print("Enter the name: ");
        builder.setName(scanner.nextLine());

        System.out.print("Enter the surname: ");
        builder.setSurname(scanner.nextLine());

        System.out.print("Enter the birth date: ");
        String date = scanner.nextLine();
        if (date.equals("")) {
            System.out.print("Bad birth date!\n");
            builder.setBirthDay("[no data]");
        } else {
            builder.setBirthDay(date);
        }

        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (gender.equals("")) {
            System.out.print("Bad gender!\n");
            builder.setGender("[no data]");
        } else {
            builder.setGender(gender);
        }

        System.out.print("Enter the number: ");
        builder.setNumber(scanner.nextLine());
    }

    public void buildOrganization(Builder builder) {
        System.out.print("Enter the organization name: ");
        builder.setOrganizationName(scanner.nextLine());
        System.out.print("Enter the address: ");
        builder.setAddress(scanner.nextLine());
        System.out.print("Enter the number: ");
        builder.setNumber(scanner.nextLine());
    }
}
class OrganizationBuilder implements Builder {

    private String number;
    private String organizationName;
    private String address;

    @Deprecated
    public void setName(String name) {}

    @Deprecated
    public void setSurname(String surname) {}

    @Deprecated
    public Contact getPerson() {return null;}

    @Override
    public void setNumber(String number) {
        if (check_number(number)) {
            this.number = number;
        } else {
            System.out.println("Bad number!");
            this.number = "[no data]";
        }
    }
    public boolean check_number(String number) {
        boolean flag = false;
        String regex = "^\\+?[0-9a-zA-Z]{1,}(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        while (matcher.find()) {
            flag = true;
        }
        String regex1 = "^\\+?\\([0-9a-zA-Z]\\)(( |-)[0-9a-zA-Z]{2,})?(( |-)[0-9a-zA-Z]{2,})*$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(number);
        while (matcher1.find()) {
            flag = true;
        }
        String regex2 = "^\\+?[\\da-zA-Z]([ -]\\([\\da-zA-Z]{2,})?\\)([ -][\\da-zA-Z]{2,})*$";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(number);
        while (matcher2.find()) {
            flag = true;
        }
        return flag;
    }
    @Override
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Deprecated
    public void setBirthDay(String birthDay) {}

    @Deprecated
    public void setGender(String gender) {}

    public Org getOrganization() {
        return new Org(organizationName, address, number);
    }
}
public class Main {
    public static List<Contact> persons = new LinkedList<>();
    static Director director = new Director();
    public static void main(String[] args) {
        boolean fl = true;
        while (fl) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            Scanner sc = new Scanner(System.in);
            String act = sc.nextLine();
            switch (act) {
                case "add":
                    System.out.print("Enter the type (person, organization): ");
                    String type = sc.nextLine();
                    switch (type) {
                        case "person":
                            PersonBuilder builder = new PersonBuilder();
                            director.buildPerson(builder);
                            persons.add(builder.getPerson());
                            System.out.println("The record added.\n");
                            break;
                        case "organization":
                            OrganizationBuilder organizationBuilder = new OrganizationBuilder();
                            director.buildOrganization(organizationBuilder);
                            persons.add(organizationBuilder.getOrganization());
                            System.out.println("The record added.\n");
                            break;
                    }
                    break;
                case "info":
                    int c0 = 1;
                    for (Contact per: persons) {
                        per.info(c0);
                        c0++;
                    }
                    System.out.print("Enter index to show info: ");
                    int record = sc.nextInt();
                    Contact contact = persons.get(record - 1);
                    contact.print();
                    System.out.println();
                    break;
                case "exit":
                    fl = false;
                    break;
                case "count":
                    System.out.println(persons.size() + " records\n");
                    break;
                case "list":
                    c0 = 1;
                    for (Contact per: persons) {
                        per.info(c0);
                        c0++;
                    }
                    while (act != "back") {
                        System.out.print("\n[list] Enter action ([number], back):");
                        act = sc.nextLine();
                        switch (act) {
                            case "back":
                                break;
                            default:
                               act = info(Integer.parseInt(act));
                        }
                    }
                    break;
                case "search":
                    String query = "";
                    search();
                    while (query != "back") {
                        System.out.println("[search] Enter action ([number], back, again):");
                        query = sc.nextLine();
                        switch (query) {
                            case "again":
                                search();
                                break;
                            case "back":
                                break;
                            default:
                                try {
                                    int number_query = Integer.parseInt(query);
                                    contact = persons.get(number_query - 1);
                                    contact.print();
                                    System.out.println();
                                } catch (NumberFormatException e) {
                                    System.out.println("Input String cannot be parsed to Integer.");
                                }
                                break;
                        }
                        break;
                    }
            }
        }
    }
    public static String info(int record) {
        Scanner sc = new Scanner(System.in);
        Contact contact = persons.get(record - 1);
        contact.print();
        System.out.println();
        System.out.print("[record] Enter action (edit, delete, menu):");
        String act = sc.nextLine();
        System.out.println();
        switch (act) {
            case "edit":
                if (persons.isEmpty()) {
                    System.out.println("No records to edit");
                } else {
                    int c0 = 1;
                    for (Contact per: persons) {
                        per.info(c0);
                        c0++;
                    }
                    Contact contact0 = persons.get(record - 1);
                    contact0.record();
                contact0.print();
                }
                return "back";
            case "delete":
                if (persons.isEmpty()) {
                    System.out.println("No records to remove!");
                } else {
                    int c = 1;
                    for (Contact per: persons) {
                        per.info(c);
                        c++;
                    }
                    System.out.print("Select a record: ");
                    record = sc.nextInt();
                    persons.remove(record - 1);
                    System.out.println("The record removed!\n");
                }
                return "back";
        }
        return "back";
    }
    public static void search() {
        Scanner sc = new Scanner(System.in);
        int coincidence = 0;
        String name = "";
        List<String> coincidences = new LinkedList();
        System.out.print("Enter search query:");
        String query = sc.nextLine();
        String regex1 = ".*" + query + ".*";
        Pattern pattern1 = Pattern.compile(regex1);
        for (Contact per : persons) {
            Matcher matcher1;
            matcher1 = pattern1.matcher(per.match());
            name = per.getName() + " " + per.getSurname();
            while (matcher1.find()) {
                coincidence++;
                coincidences.add(name);
            }
            matcher1 = pattern1.matcher(per.getNumber());
            String number = per.getNumber();
            while (matcher1.find()) {
                coincidence++;
                coincidences.add(number);
            }
        }
        System.out.println("Found " + coincidence + " results");
        for (int i = 0; i < coincidences.size(); i++) {
            System.out.println(i + 1 + ". " + coincidences.get(i));
        }
    }
}
