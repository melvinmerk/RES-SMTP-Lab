package ch.heigvd.res.smtplab.model.prank;

import ch.heigvd.res.smtplab.model.mail.Group;
import ch.heigvd.res.smtplab.model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrankGenerator {

    private int numberOfGroups;
    private List<String> messages;
    private List<Person> victims;
    private List<Person> witnesses;

    public PrankGenerator(int numberOfGroups, List<String> messages, List<Person> victims, List<Person> witnesses) {
        this.numberOfGroups = numberOfGroups;
        this.messages = messages;
        this.victims = victims;
        this.witnesses = witnesses;
    }

    public List<Prank> genPranks()  {
        List<Prank> prankList = new ArrayList<>();
        List<Group> groups = genGroups();

        Random rand = new Random();


        // generate prank for each group with random message
        for(Group group : groups) {
            List<Person> victims = group.getMembers();
            String message = messages.get(rand.nextInt(messages.size()));
            prankList.add(new Prank(victims.get(0), victims.subList(1, victims.size()), witnesses, message));
        }

        return prankList;
    }

    private List<Group> genGroups() {
        int groupSize = victims.size() / numberOfGroups;
        if(groupSize < 3) {
            throw new RuntimeException("Not enough victims to have " + numberOfGroups + " groups.");
        }

        List<Group> groups = new ArrayList<>();

        for(int i = 0; i < numberOfGroups; i++) {
            groups.add(new Group());
        }

        int j = 0;
        for(int i = 0; i < victims.size(); i++) {

            if(i != 0 && (i % groupSize) == 0 && (j+1) < numberOfGroups)
                j++;

            groups.get(j).addMember(victims.get(i));
        }

        return groups;

    }


}
