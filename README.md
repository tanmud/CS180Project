# CS180Project
<u><h3>Item Class</u></h3>
	This class represents an item in our ecommerce store. It 
implements ItemsInterface and overrides its methods. Items 
contains 4 main fields: name, description, quantity, 
and user, storing respectively an item’s name, description, 
quantity, and the user that purchases / sells it. Items also 
includes the standard accessor and mutator methods for each 
field, which can help retrieve and modify each 
respective field. 
	
We tested Items using the ItemsTest class, which verifies 
that all the getter and setter methods in Items work correctly.
ItemsTest sets 	values for each field in Items and then checks 
that they are each returned correctly. A valid User object is
then created and linked to 	the item to ensure the relationship
is correctly established. 
	The Items class is an integral part of our project – 
it directly relates to the user class, as both buyers and 
sellers interact on our ecommerce store over the
items they are buying / selling, and connects to the 
bank class, which holds the functions to buy / sell items. 

<u><h3>Database Class</u></h3>
This class represents the store system in our ecommerce 
platform. It implements DatabaseInterace and overrides 
its methods. Databae contains 4 main fields: userFile, 
conversationFile, userList, and conversations, which store
the fileNames for saved data and the lists of site users
and conversations, respectively. Database also includes 
the nessesary methods to start up and shut down the system, 
create users, check if a user exists, and perform item 
searches based off an item name. 

We tested Database using 
the DatabaseTest class, which created temporary users, 
storing and retrieving their data, and performed search 
operations to verify the Database operated correctly. The 
Database class is an integral part of our project - it 
connects closely with the Items, User, and Bank classes, 
supporting the creation, function, and storage of our store 
items, and the function of our program.

<u><h3>User Class</h3></u>

This class represents the people (buyers or sellers) in the 
ecommerce platform. It implements UserInterface, overriding
relevant methods, and allows you to create a User object, 
which contains information like their name, username, 
password, bank, and chat history. It is also possible to delete
their account and test if a user is equal to another. Relevant
accessor and mutator methods for each field are available.

We tested User using the UserTest class, which had preset 
temporary users to test the functionality of the constructor,
accessor methods, mutator methods, deletion, and equals methods.
This would ensure that relevant information would not be modified 
incorrectly. The User class is very important because buyers
and sellers are part of the premise of the platform, which,
in basic terms, is a place were people (users) can buy or sell
items.

<u><h3>Message Class</u></h3>

This class represents messages, which can be sent from user to 
user to communicate for selling or buying items. It implements 
MessageInterface and allows you to create a Message 
object, which contains information like the message’s sender,
recipient, content, date, and time the message is sent. Relevant
accessor and mutator methods for each field are available.

We tested Message using the MessageTest class, which had preset
User and Message objects to test the functionality of the
constructor, accessor methods, mutator methods, and toString 
methods for the date and time. These tests ensure relevant
information is not modified correctly, or shown in a 
non-human-readable format. The Message class is essential because
it facilitates communication between buyers and sellers.