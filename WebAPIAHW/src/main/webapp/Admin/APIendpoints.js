$(document).ready(function () {
    var customerNewId;
    var accountTypeSelect;
    
    //1. TO CREATE MOCK UP CUSTOMERS
    $("#createMockUpCustomer").click(function() {
        $.ajax({
            type:"GET",
            dataType:"json",
            contentType:"application/json",
            url:"http://localhost:49000/api/customer/createMockCustomers",
            success: function (result, status, xhr) {
            },error: function (xhr, status, error) {
            alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
            }).then(function(data) {
                data = JSON.stringify(data);
                alert("Customers are created!");
                });
    });
    
    // 2. TO DISPLAY CUSTOMERS
    $("#displayMockUpCustomer").click(function() {
        $.ajax({
            type:"GET",
            dataType:"json",
            contentType:"application/json",
            url:"http://localhost:49000/api/customer/getAllC",
            success: function (result, status, xhr) {
            },error: function (xhr, status, error) {
            alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
            }).then(function(data) {
            $('#mockUpCustomers').empty();
                for (var i=0; i<data.length; i++) {
                    var row = $('<tr><td>Customer ID</td><td>Customer Name</td>'+
                            '<td>Customer Address</td><td>Customer Email</td>'+
                            '<td>Password</td><td>Date Customer Created</td></tr>'+
                            '<tr><td>' + data[i].customerId+ 
                            '</td><td>' + data[i].customerName +
                            '</td><td>' + data[i].customerAddress + 
                            '</td><td>' + data[i].customerEmail + 
                            '</td><td>'+ data[i].password + 
                            '</td><td>' + data[i].customerCreated + 
                            '</td></tr>');
                $('#mockUpCustomers').append(row);
            }
       });
    });
    
    // 3. TO DISPLAY CUSTOMERS ACCOUNTS BY CUSTOMER ID
    $("#displayAccounts").click(function() {
        var customerId = $("#customerId").val();
            $.ajax({
               type:"GET",
               dataType:"json",
               contentType:"application/json",
               url:"http://localhost:49000/api/customer/"+customerId+"/getAllAccounts",
               success: function (result, status, xhr) {
               },error: function (xhr, status, error) {
               alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
               }
               }).then(function(data) {
                   console.log(data);
               $('#myTableDisplayAccounts').empty();
               for (var i=0; i<data.length; i++) {
                    var row = $('<tr><td>Sort Code</td><td>Account Id</td>'+
                                '<td>Account Type</td><td>Account Number</td>'+
                                '<td>Balance</td><td>Account Created</td></tr>'+
                                '<tr><td>' + data[i].sortCode+ 
                                '</td><td>' + data[i].accountId +
                                '</td><td>' + data[i].accountType + 
                                '</td><td>' + data[i].accNumber + 
                                '</td><td>'+ data[i].balance + 
                                '</td><td>' + data[i].accountCreated + 
                                '</td></tr>');
                    $('#myTableDisplayAccounts').append(row);
                    }
               }); 
    });
    
    // 4. TO DISPLAY CUSTOMER TRANSACTIONS BY CUSTOMER ID AND ACCOUNT TYPE
    $("#displayTransactions").click(function() {
        accountTypeSelect = $("#accountsTransactions").val();
        customerNewId = $("#customerIdNew").val();
         
         $.ajax({
            type:"GET",
            dataType:"json",
            contentType:"application/json",
            url:"http://localhost:49000/api/customer/"+customerNewId+"/getUA/"+accountTypeSelect+"/trans",
            success: function (result, status, xhr) {
            },error: function (xhr, status, error) {
            alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
            }).then(function(data) {
                console.log(data);
            $('#myTableTransactions').empty();
                for (var i=0; i<data.length; i++) {
                    var row = $('<tr><td>Transaction Number</td><td>Transaction Type</td>'+
                                 '<td>Description</td><td>Post Balance</td>'+
                                 '<td>Amount</td><td>Transaction Created</td></tr>'+
                                 '<tr><td>' + data[i].transactionNumber+ 
                                 '</td><td>' + data[i].transactionType +
                                 '</td><td>' + data[i].description + 
                                 '</td><td>' + data[i].postBalance + 
                                 '</td><td>' + data[i].amount + 
                                 '</td><td>'+ data[i].transactionCreated +
                                 '</td></tr>');
                     $('#myTableTransactions').append(row);
                }
            });
     });   
});
