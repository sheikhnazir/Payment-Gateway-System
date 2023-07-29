# Payment-Gateway-System
Create a payment gateway,
 User =  UserId, Name, Email, AccountNumber, List<Transaction>
 Transaction = TransactionId, UserId, Amount, Status, AmountDeducted, Time                                                
 Refund = Amount, UserId,  TransactionId                                               
 
Description: A user can do multiple transactions. A transaction can be success or failed. Money can be deducted in case of failed transaction as well.
 
POST API - Add a User
POST API - Add a Transaction for a given amount
 
GET API - Find the total amount over successful transactions for a user
DELETE API - Delete all transactions that are failed
POST API - Create a refund object only when a transaction is failed but money is deducted.
