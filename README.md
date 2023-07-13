## Hibernate Assignments

1) Create Database Schema in MySQL - Bank-Management-System
2) Change username and password details in resources->application.properties file
3) Enter below APIs in Postman

#### REST APIS

1) To add details of account, bank, customers.  (POST)

```localhost:8080/api/v1/accounts```

```
    {
        "accountNumber" : "1111000" ,
        "balance" : 1033000 ,
        "bank" : {
            "bankName" : "HDFC"
        } , 
        "customer" : {
            "name" : "ashit"
        }   
    }
```

2) Get all details of accounts (GET)
   ```localhost:8080/api/v1/accounts```
   ``` localhost:8080/api/v1/customers ```
3) Get account by account number (GET)
   ```localhost:8080/api/v1/accounts/{Pass account number}```
4) Update account details by account number (PUT)
   ```localhost:8080/api/v1/accounts/{Pass account number}```
```
    {
        "accountNumber" : "1111000" ,
        "balance" : 1000 ,
        "bank" : {
            "bankName" : "HDFC"
        } , 
        "customer" : {
            "name" : "ashit"
        }   
    }
```
5) Delete account details by account number (DELETE)
   ``` localhost:8080/api/v1/accounts/{Pass account number} ```
