/*
	Program: CustomerQueue ~ Driver Class
	Student: Suzana Nesic
	Date: September 29, 2016
	Summary: Creates a customer object that is placed into a queue with a random service time assigned to the object. The program runs for 60 iterations, each iteration having a chance for a new customer to join the queue and the service time decreasing for the object in front. 
*/

import java.util.Random;

public class Driver {
   public static void main (String[] args) {
      int currentSize = 0; //variable holding the size of the customer line
      int maxSize = 0; //variable holding the maximum size of the customer line at any point during the 60 minutes
      int totalServiced = 0; //variable holding the number of customers serviced during the 60 minutes
      
      LinkedList queue = new LinkedList(); //creates the customer line (queue)
      Customer c = null; //modifies c to get access to getServiceTime and decServiceTime
      
      for (int i = 0; i < 60; i++) {
         int rand = new Random().nextInt(4); //sets up the 25% chance of a new customer coming in the line
            if(rand == 0) //if the random number generator was able to generate a zero, a new customer is added to the line (queue) and receives a service time
            {
               c = queue.addCustomer(new Customer()); //allows the object to be the customer added to the queue while having a service time assigned to it
               c.getServiceTime();
               System.out.println("Service time of customer in front is " + c.getServiceTime()); //obtains service time for the object in front of the queue
               currentSize++; //size of the queue increases
               System.out.println("New customer added! Queue length is now " + queue.getSize()); //displays queue length
               c.decServiceTime(); //service time of the object in front of the queue is decreased by 1
               if(currentSize > maxSize) //if the current size of the queue is bigger than the value assigned to the max size of the queue...
               {
                  maxSize = currentSize; //assign that value to the maxSize variable
               }
                  if(c.getServiceTime() == 0) //if the object in front of the queue is out of service time...
                  {
                     queue.removeCustomer(); //...remove the object from the front of the queue...
                     currentSize--; //...decrease the size of the queue...
                     System.out.println("Customer serviced and removed from the queue. Queue length is now " + queue.getSize()); //...let the user know of the current queue length size...
                     totalServiced++; //...and add to the total customers serviced counter
                  }
            }
      
            System.out.println("---------------------------------------------"); //printed for clarity and neatness when the code is compiled
      
      }
      System.out.println("Max size of queue reached this session was " + maxSize); //allows the user to see how big the line was
      System.out.println("Total number of customers served this session was " + totalServiced); //allows the user to see the total number of customers serviced
         
   }
}