/*
    Program: CustomerQueue ~ LinkedList Class
    Student: Suzana Nesic
    Date: September 29, 2016
    Summary: Creates a customer line class with methods to be used to hold or remove a place for the customer object. Holds the position in line for each customer. 
    Functionality: 
        Constructor: Initial size of the line = 0
                     Initial first and last spot in the line = 0
        Public Methods: addCustomer, removeCustomer, getSize
*/

public class LinkedList
{
   private Customer first, last; //sets up the placement of the object (customer) in the queue
   private int size; //sets up the size of the customer line (queue)


public LinkedList() //default copy constructor
{
   size = 0;
   first = last = null;
}

public Customer addCustomer(Customer c) //adds a customer to the back of the line
{
   if(first==null) //if the line is initially empty then have it update to the new object
      first = c;
   else //if the list is not empty then update the last object as the new object
   {
      last.setNext(c);
   }
   last = c; //updates last to reference the new object
   size++; //increases the size of the customer line
   return first;
}

public Customer removeCustomer()
{
   Customer temp = first.getNext(); //temporary reference to the first object so it can be returned
   if(first==null) //if the line is already empty
      return null;

   first = first.getNext(); //updates first so it can refer to the second customer in line
   if(first==null) //if the list is empty after removing the first object(customer) in line
   {
      last = null;
      return temp;
   }
      size--; //decrease the size of the line
      return temp;
   }


public int getSize()
{
   return size; //returns the size of the customer line
}
}