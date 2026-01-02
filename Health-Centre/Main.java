import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        StaffMember member = new Doctor("johny","john","12/12/2012","0123456789",
                "054321","202012","cardiologist",10);
        member = new Receptionist("kamal","vimal","12/13/2013",
                "0987654321","012345",14,9);
        StaffMember[] array = new StaffMember[5];


        //fixed in size
        //there is no underlying operation.(there is no method to add,remove item etc.)
        // it is dynamic in size as it can grow and shrink better alternatives is linkedlists 
        //provides underlying operations 
        //list maintains the insertion order (dont want duplicates)
        //Queue first in, first out FiFo or LIFO
        //set doesnt allow dulplicates and doesnt matina orders 
        //map is dictionary
        List<StaffMember> list = new ArrayList<StaffMember>();



        //Dynamic in size
        //there are underlying operation to support adding and removing items.
        list.add(member);
        list.remove(0);



    }


}