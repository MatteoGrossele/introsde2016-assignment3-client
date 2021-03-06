package introsde.assignment.soap.client;

import introsde.assignment.soap.ws.Person;
import introsde.assignment.soap.ws.Measure;
import introsde.assignment.soap.ws.People;
import introsde.assignment.soap.ws.PeopleService;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import java.io.*;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class PeopleClient{

    static String LOG_FILE= "client_log.txt";

    public static void main(String[] args) throws Exception {
        Person p;
        Measure m;
        List<Person> persons;
        List<Measure> measures;
        int result;

        //Open the log file for the output of the client
        BufferedWriter writer_log = new BufferedWriter(new FileWriter(new File(LOG_FILE)));
        //Start client connection to the server
        PeopleService service = new PeopleService();
        People people = service.getPeopleImplPort();

        //Client Start
        System.out.print("-------------------- IntroSDE Assignment 3 Client --------------------\n\n");
        writer_log.write("-------------------- IntroSDE Assignment 3 Client --------------------\n\n");
        //Step 1 : Print server WSDL url
        System.out.print("Server WSDL url : "+service.getWSDLDocumentLocation() + "\n\n");
        writer_log.write("Server WSDL url : "+service.getWSDLDocumentLocation() + "\n\n");

        //REQUEST #1

        System.out.print("-------------------- Request #1 --------------------\n");
        writer_log.write("-------------------- Request #1 --------------------\n");
        System.out.print("readPersonList() => List :\n");
        writer_log.write("readPersonList() => List :\n");
        System.out.print("List of the people stored :\n\n");
        writer_log.write("List of the people stored :\n\n");
        persons = people.getPeopleList();
        for(Person per: persons) {
            System.out.print(per.getIdPerson() + " : " + per.getFirstname() + " "+per.getLastname() + " " + per.getBirthdate() + "\n");
            writer_log.write(per.getIdPerson() + " : " + per.getFirstname() + " "+per.getLastname() + " " + per.getBirthdate() + "\n");
        }
        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #2

        System.out.print("-------------------- Request #2 --------------------\n");
        writer_log.write("-------------------- Request #2 --------------------\n");
        System.out.print("readPerson(Long id) => Person :\n");
        writer_log.write("readPerson(Long id) => Person :\n");
        System.out.print("Person with id 1 :\n\n");
        writer_log.write("Person with id 1 :\n\n");

        p = people.readPerson(1);

        System.out.print(p.getIdPerson() + " : " + p.getFirstname() + " "+p.getLastname() + " " + p.getBirthdate() + "\n");
        writer_log.write(p.getIdPerson() + " : " + p.getFirstname() + " "+p.getLastname() + " " + p.getBirthdate() + "\n");

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #3

        System.out.print("-------------------- Request #3 --------------------\n");
        writer_log.write("-------------------- Request #3 --------------------\n");
        System.out.print("updatePerson(Person p) => Person :\n");
        writer_log.write("updatePerson(Person p) => Person :\n");
        System.out.print("Change lastname of person with id 2 with 'ChangedSurname' :\n\n");
        writer_log.write("Change lastname of person with id 2 with 'ChangedSurname' :\n\n");

        p = people.readPerson(2);
        System.out.print("Person 2 ");
        writer_log.write("Person 2 ");

        System.out.print(p.getIdPerson() + " : " + p.getFirstname() + " "+p.getLastname() + " " + p.getBirthdate() + "\n");
        writer_log.write(p.getIdPerson() + " : " + p.getFirstname() + " "+p.getLastname() + " " + p.getBirthdate() + "\n");

        p.setLastname("ChangedSurname");

        result = people.updatePerson(p);

        p = people.readPerson(2);

        System.out.print("Person 2 edited ");
        writer_log.write("Person 2 edited ");
        System.out.print(p.getIdPerson() + " : " + p.getFirstname() + " "+p.getLastname() + " " + p.getBirthdate() + "\n");
        writer_log.write(p.getIdPerson() + " : " + p.getFirstname() + " "+p.getLastname() + " " + p.getBirthdate() + "\n");

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #4

        System.out.print("-------------------- Request #4 --------------------\n");
        writer_log.write("-------------------- Request #4 --------------------\n");
        System.out.print("createPerson(Person p) => Person :\n");
        writer_log.write("createPerson(Person p) => Person :\n");
        System.out.print("Create a new person :\n\n");
        writer_log.write("Create a new person :\n\n");

        p = new Person();
        p.setIdPerson(20);
        p.setFirstname("Matteo");
        p.setLastname("Grossele");
        p.setBirthdate("24-06-1993");

        result = people.createPerson(p);

        p = people.readPerson(result);

        System.out.print(p.getIdPerson() + " : " + p.getFirstname() + " " + p.getLastname() + " " + p.getBirthdate() + "\n");
        writer_log.write(p.getIdPerson() + " : " + p.getFirstname() + " " + p.getLastname() + " " + p.getBirthdate() + "\n");

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #5

        System.out.print("-------------------- Request #5 --------------------\n");
        writer_log.write("-------------------- Request #5 --------------------\n");
        System.out.print("deletePerson(Long id) :\n");
        writer_log.write("deletePerson(Long id) :\n");
        System.out.print("Delete the person we just created :\n\n");
        writer_log.write("Delete the person we just created :\n\n");        

        result = people.deletePerson(20);

        System.out.print("Result : " + result + "\n");
        writer_log.write("Result : " + result + "\n");

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #6

        System.out.print("-------------------- Request #6 --------------------\n");
        writer_log.write("-------------------- Request #6 --------------------\n");
        System.out.print("readPersonHistory(Long id, String measureType) => List :\n");
        writer_log.write("readPersonHistory(Long id, String measureType) => List :\n");
        System.out.print("History of the measures 'height' of the person with id 1 :\n\n");
        writer_log.write("History of the measures 'height' of the person with id 1 :\n\n");

        measures = people.readPersonHistory(1, "height");
        
        for(Measure meas: measures) {
            System.out.print(meas.getMid() + " : " + meas.getValue() + " " + meas.getType() +"\n");
            writer_log.write(meas.getMid() + " : " + meas.getValue() + " " + meas.getType() +"\n");
        }

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #7

        System.out.print("-------------------- Request #7 --------------------\n");
        writer_log.write("-------------------- Request #7 --------------------\n");
        System.out.print("readMeasureTypes() => List :\n");
        writer_log.write("readMeasureTypes() => List :\n");
        System.out.print("List of all the measures stored :\n\n");
        writer_log.write("List of all the measures stored :\n\n");

        measures = people.readMeasureTypes();
        
        for(Measure meas: measures) {
            System.out.print(meas.getMid() + " : " + meas.getValue() + " " + meas.getType() + " Person-" + meas.getIdPerson() + "\n");
            writer_log.write(meas.getMid() + " : " + meas.getValue() + " " + meas.getType() + " Person-" + meas.getIdPerson() +"\n");
        }

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #8

        System.out.print("-------------------- Request #8 --------------------\n");
        writer_log.write("-------------------- Request #8 --------------------\n");
        System.out.print("readPersonMeasure(Long id, String measureType, Long mid) => Measure :\n");
        writer_log.write("readPersonMeasure(Long id, String measureType, Long mid) => Measure :\n");
        System.out.print("Measure of type 'height' for the person with id 1 and mid 2 :\n\n");
        writer_log.write("Measure of type 'height' for the person with id 1 and mid 2 :\n\n");

        m = people.readPersonMeasure(1, "height", 2).get(0);
        
        System.out.print(m.getMid() + " : " + m.getValue() + " " + m.getType() +"\n");
        writer_log.write(m.getMid() + " : " + m.getValue() + " " + m.getType() +"\n");

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #9

        System.out.print("-------------------- Request #9 --------------------\n");
        writer_log.write("-------------------- Request #9 --------------------\n");
        System.out.print("savePersonMeasure(Long id, Measure m) :\n");
        writer_log.write("savePersonMeasure(Long id, Measure m) :\n");
        System.out.print("Create a measure of type 'height' for the person with id 1, mid 4 and value 9.999 :\n\n");
        writer_log.write("Create a measure of type 'height' for the person with id 1, mid 4 and value 9.999 :\n\n");

        m = new Measure();
        m.setIdMeasure(99);
        m.setValue("9.999");
        m.setDate("12-12-2016");
        m.setType("height");
        m.setIdPerson(1);
        m.setMid(4);

        result = people.savePersonMeasure(1, m);
        
        System.out.print(m.getMid() + " : " + m.getValue() + " " + m.getType() +"\n");
        writer_log.write(m.getMid() + " : " + m.getValue() + " " + m.getType() +"\n");

        System.out.print("\n");
        writer_log.write("\n");

        //REQUEST #10

        System.out.print("-------------------- Request #10 --------------------\n");
        writer_log.write("-------------------- Request #10 --------------------\n");
        System.out.print("updatePersonMeasure(Long id, Measure m) => Measure :\n");
        writer_log.write("updatePersonMeasure(Long id, Measure m) => Measure :\n");
        System.out.print("Update the created measure with the value 1.77 :\n\n");
        writer_log.write("Update the created measure with the value 1.77 :\n\n");

        m = people.readPersonMeasure(1, "height", 4).get(0);
        System.out.print(m.getMid() + " before : " + m.getValue() + " " + m.getType() +"\n");
        writer_log.write(m.getMid() + " before : " + m.getValue() + " " + m.getType() +"\n");

        m.setValue("1.77");
        result = people.updatePersonMeasure(1, m);

        m = people.readPersonMeasure(1, "height", 4).get(0);
        
        System.out.print(m.getMid() + " after : " + m.getValue() + " " + m.getType() +"\n");
        writer_log.write(m.getMid() + " after : " + m.getValue() + " " + m.getType() +"\n");

        System.out.print("\n");
        writer_log.write("\n");

        //Remove created measure for future executions
        people.deleteMeasure(99);
        //Restore value of changed surname for future executions
        p = people.readPerson(2);
        p.setLastname("Norris");
        result = people.updatePerson(p);


        writer_log.flush();
    }
}
