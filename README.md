# CS180Project
<u><h3>Item Class Description</u></h3>
	This class represents an item in our ecommerce store. It implements ItemsInterface and overrides its interfaces. Items contains 4 main        fields: name, description, quantity, and user, storing respectively an item’s name, description, quantity, and the user that purchases /      sells it. Items also includes the standard accessor and mutator methods for each field, which can help retrieve and modify each               respective field. 
	We tested Items using the ItemsTest class, which verifies that all the getter and setter methods in Items work correctly. ItemsTest sets 	values for each field in Items and then checks that they are each returned correctly. A valid User object is then created and linked to 	the item to ensure the relationship is correctly established. 
	The Items class is an integral part of our project – it directly relates to the user class, as both buyers and sellers interact on our 		ecommerce store over the items they are buying / selling, and connects to the bank class, which holds the functions to buy / sell items. 


<u><h3>User Class</h3></u>

Allows you to create a User object, which contains information like their name, username, password, bank, and chat history. It is also possible to send messages (objects of the Message class) to a specified recipient as well as pull up chat history with that person. Relevant accessor and mutator methods for each attribute are available.

<h4>UserTest</h4>

A document with the JUnit tests for User.java, testing the sendMessage and getChats methods.

<u><h3>Message Class</u></h3>

Allows you to create a Message object, which contains information like the message’s sender, recipient, content, date, and time the message is sent. Relevant accessor and mutator methods for each attribute are available.

<h4>MessageTest</h4>

A document with the JUnit tests for Message.java, testing the dateToString and timeToString methods.