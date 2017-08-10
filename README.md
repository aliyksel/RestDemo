# RestDemo
SpringBoot , REST, MongoDb example

This Example includes some REST methods. This example uses MongoDB as database.

Data model is look like this;
{
        "_id" : ObjectId("5983954bbb13061a88ca2925"),
        "_class" : "com.example.demo.models.Customer",
        "firstName" : "Alice",
        "lastName" : "Smith",
        "totalSpend" : 470,
        "bills" : [
                {
                        "billId" : NumberLong(2),
                        "totalSpend" : 470,
                        "items" : [
                                {
                                        "barcode" : NumberLong(113),
                                        "name" : "bread 2",
                                        "price" : 120
                                },
                                {
                                        "barcode" : NumberLong(112),
                                        "name" : "pice 2",
                                        "price" : 350
                                }
                        ]
                }
        ]
}
{
        "_id" : ObjectId("598cb22b8f81d71e2487900d"),
        "_class" : "com.example.demo.models.Customer",
        "firstName" : "Bob",
        "lastName" : "Smith",
        "totalSpend" : 0
}

test caseses;
HEAD : control service header.   http://localhost:8080/customer
GET  : get all customers        http://localhost:8080/customer
GET  : get a customer           http://localhost:8080/customer/"+customerId
OPTION : control options to service.
POST : create a customer        http://localhost:8080/customer
PUT  : uptdate an item from bills  http://localhost:8080/customer/"+ customerId +"/bill/"+billId+"/barCode/"+barcode 
DELETE : delete an item from bills  http://localhost:8080/customer/"+ customerId +"/bill/"+billId+"/barCode/"+barcode 
DELETE : delete a bill from customer http://localhost:8080/customer/"+ customerId +"/bill/"+billId 

