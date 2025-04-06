# CS180Project
Item Class Description
	This class represents an item in our ecommerce store. It implements ItemsInterface and overrides its interfaces. Items contains 4 main        fields: name, description, quantity, and user, storing respectively an item’s name, description, quantity, and the user that purchases /      sells it. Items also includes the standard accessor and mutator methods for each field, which can help retrieve and modify each               respective field. 
	We tested Items using the ItemsTest class, which verifies that all the getter and setter methods in Items work correctly. ItemsTest sets 	values for each field in Items and then checks that they are each returned correctly. A valid User object is then created and linked to 	the item to ensure the relationship is correctly established. 
	The Items class is an integral part of our project – it directly relates to the user class, as both buyers and sellers interact on our 		ecommerce store over the items they are buying / selling, and connects to the bank class, which holds the functions to buy / sell items. 

