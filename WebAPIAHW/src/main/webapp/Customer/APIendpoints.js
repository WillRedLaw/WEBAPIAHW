$(document).ready(function () {
    var customerEmail;
    var password;
    var customerId;
    var accountType;
    var accountTypeSelect;
    var accountBalanceType;
    
    //1. CUSTOMER REGISTRATION (EMAIL AND PASSWORD INPUT) USING POST
    $("#createCustomer").click(function() {
        $("#panel0").hide();
        $("#panel1").show();
        customerEmail = $("#customerEmail").val();
        password = $("#password").val();
        
        let body = {customerEmail:customerEmail, password:password};
        console.log(body);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:49000/api/customer',
            data: JSON.stringify({ "customerEmail": customerEmail,"password": password}),
            success: function (result, status, xhr) {
            },
            error: function (xhr, status, error) {
            alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
            }).done(function( data ) {
               console.log(data);
               customerId = data.customerId;
               console.log("id is "+customerId);
         });
    });
    
   //2. REGISTRATION CONTINUE (NAME AND ADDRESS) USING PUT
    $("#updateCustomerDetails").click(function() {
        $("#panel2").show();
        $("#panel3").show();
        $("#panel1").hide();
        var customerName = $("#customerName").val();
        var customerAddress = $("#customerAddress").val();
        $.ajax({
            type: "PUT",
            url: "http://localhost:49000/api/customer/"+customerId+"/update?customerName="+customerName+"&customerAddress="+customerAddress,
            dataType: 'json',
            success: function (result, status, xhr) {
            },
            error: function (xhr, status, error) {
                alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
        }).done(function( data ) {
            
           // 2.A. DISPLAY OF CUSTOMER DETAILS
           $.ajax({
           type:"GET",
           dataType:"json",
           contentType:"application/json",
           url:"http://localhost:49000/api/customer/getAC/"+customerId,
           success: function (result, status, xhr) {
           },error: function (xhr, status, error) {
           alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
           }
           }).then(function(data) {
           $('.resp-customerId').append(data.customerId);
           $('.resp-customerName').append(data.customerName);
           $('.resp-customerAddress').append(data.customerAddress);
           $('.resp-customerEmail').append(data.customerEmail);
           $('.resp-password').append(data.password);
           $('.resp-customerCreated').append(data.customerCreated);
           });
        });
    });
    
    //3. ACCOUNT CREATION USING POST
    $("#createAccount").click(function() {
        $("#panel4").show();
        accountType = $("#accounts").val();
        let body = {"accountType":accountType};
         $.ajax({
            type: 'POST',
            url:"http://localhost:49000/api/customer/"+customerId+"/Account",
            dataType: 'json',
            data: JSON.stringify({"accountType": accountType}),
            success: function (result, status, xhr) {
            },
            error: function (xhr, status, error) {
                alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
        }).done(function( data ) {
        });       
    });
    
    //4. ACCOUNTS DISPLAY, USING GET
    $("#listAllAccount").click(function() {
        $("#panel5").show();
        $("#panel6").show();
        $("#panel7").show();
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
               
               $('#myTable').empty();
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
                        $('#myTable').append(row);
                    }
               });
    });
    
    //5. BALANCE DISPLAY BY ACCOUNT TYPE USING GET
    $("#displayBalance").click(function() {
        accountBalanceType = $("#accBalance").val();
        
        $.ajax({
               type:"GET",
               dataType:"json",
               contentType:"application/json",
               url:"http://localhost:49000/api/customer/"+customerId+"/account/"+accountBalanceType+"/balance",
               success: function (result, status, xhr) {
               },error: function (xhr, status, error) {
               alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
               }

               }).then(function(data) {
               data = JSON.stringify(data);
               alert("Balance for account: "+accountBalanceType+ ", is "+data);
        });
        
    });
    
    //6. DISPLAY OF TRANSACTION DIVS
     $("#CreateTransaction").click(function() {
        $("#panel8").show();
        $("#panel10").show();
        $("#panel12").show();
        $("#panel14").show();
        $("#panel16").show();
     });
     
     //7. WITHDRAWAL TRANSACTION CREATION USING POST
     $("#Withdrawal").click(function() {
         $("#CreateTransaction").hide();
         $("#panel9").show();
         var withdrawalAccType = $("#accountOutW").val();
         var accNumberOut = $("#accountNumberWOut").val();
         var amountWithdrawal = $("#wAmount").val();
         
         let body = {"amount":amountWithdrawal};
         $.ajax({
            type: 'POST',
            url:"http://localhost:49000/api/customer/"+customerId+"/transaction/"+withdrawalAccType+"/wid/"+accNumberOut+"/create",
            dataType: 'json',
            data: JSON.stringify({"amount": amountWithdrawal}),
            success: function (result, status, xhr) {
            },
            error: function (xhr, status, error) {
                alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
        }).done(function( data ) {
          alert(data);
             
                
             //7.A. DISPLAY OF ACCOUNT DETAILS ON WHICH WAS WITHDRAWAL EXECUTED USING GET
               $.ajax({
               type:"GET",
               dataType:"json",
               contentType:"application/json",
               url:"http://localhost:49000/api/customer/"+customerId+"/getUA/"+withdrawalAccType,
               success: function (result, status, xhr) {
               },error: function (xhr, status, error) {
               alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
               }

               }).then(function(data) {
               console.log(data);
                $('#myTableWithdrawal').empty(); 
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
                        $('#myTableWithdrawal').append(row);
                    }
               });
            });
        });
    
     //8.LODGEMENT TRANSACTION CREATION USING POST
     $("#Lodgement").click(function() {
         $("#panel11").show();
         var lodgementAccType = $("#accountInL").val();
         var accNumberIn = $("#accountNumberLIn").val();
         var amountLodgement = $("#lAmount").val();
         
         let body = {"amount":amountLodgement};
         
         $.ajax({
            type: 'POST',
            url:"http://localhost:49000/api/customer/"+customerId+"/transaction/"+lodgementAccType+"/lodg/"+accNumberIn+"/create",
            dataType: 'json',
            data: JSON.stringify({"amount": amountLodgement}),
            success: function (result, status, xhr) {
            },
            error: function (xhr, status, error) {
                alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
            }).done(function( data ) {
            alert(data);
               
               //8.A. DISPLAY OF ACCOUNT DETAILS ON WHICH WAS LODGEMENT EXECUTED, USING GET
               $.ajax({
               type:"GET",
               dataType:"json",
               contentType:"application/json",
               url:"http://localhost:49000/api/customer/"+customerId+"/getUA/"+lodgementAccType,
               success: function (result, status, xhr) {

               },error: function (xhr, status, error) {
               alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
               }

               }).then(function(data) {
               console.log(data);
               $('#myTableLodgement').empty();
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
                        $('#myTableLodgement').append(row);
                    }
               });
            });
     });
     
     //9.TRANSFER TRANSACTION BETWEEN OWN ACCOUNTS CREATION USING POST
     $("#TransferBetweenAccounts").click(function() {
         $("#panel13").show();
         var accountTypeTOut = $("#accountTypeTOut").val();
         var accNumberTOut = $("#accNumberTOut").val();
         var accountTypeTIn = $("#accountTypeTIn").val();
         var accNumberTIn = $("#accNumberTIn").val();
         var amountTransfer = $("#amountTransfer").val();
         
         let body = {"amount":amountTransfer};
         
         $.ajax({
            type: 'POST',
            url:"http://localhost:49000/api/customer/"+customerId+"/transaction/"+accountTypeTOut+"/wid/"+accNumberTOut+
                          "/betweenAccounts/"+accountTypeTIn+"/lodg/"+accNumberTIn+"/create",
            dataType: 'json',
            data: JSON.stringify({"amount": amountTransfer}),
            success: function (result, status, xhr) {
            },
            error: function (xhr, status, error) {
                alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
            }).done(function( data ) {
            alert(data);
               
               //9.A. DISPLAY OF ACCOUNT DETAILS ON WHICH WAS TRANSACTION BETWEEN OWN ACCOUNTS EXECUTED 
               //- SENDER ACCOUNT, USING GET
               $.ajax({
               type:"GET",
               dataType:"json",
               contentType:"application/json",
               url:"http://localhost:49000/api/customer/"+customerId+"/getUA/"+accountTypeTOut,
               success: function (result, status, xhr) {

               },error: function (xhr, status, error) {
               alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
               }

               }).then(function(data) {
               console.log(data);
               
               $('#myTableTransfer').empty();
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
                        $('#myTableTransfer').append(row);
                    }

               });
           });
     });   
        
    
    //10.TRANSFER TRANSACTION BETWEEN DIFFERENT CUSTOMERS ACCOUNT CREATION USING POST
    $("#TransferBetweenCustomers").click(function() {
         $("#panel15").show();
         
         var accountTypeTCOut = $("#accountTypeTCOut").val();
         var accNumberTCOut = $("#accNumberTCOut").val();
         var receiversName = $("#receiversName").val();
         var accNumberRec = $("#accNumberRec").val();
         var amountTrCus = $("#amountTrCus").val();
         
         let body = {"amount":amountTrCus};
         
         $.ajax({
            type: 'POST',
            url:"http://localhost:49000/api/customer/"+customerId+"/account/"+accountTypeTCOut+"/wid/"+
                       accNumberTCOut+"/betweenCustomers/"+receiversName+"/account/"+accNumberRec+"/create",
            dataType: 'json',
            data: JSON.stringify({"amount": amountTrCus}),
            success: function (result, status, xhr) {
            },
            error: function (xhr, status, error) {
                alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
            }
            }).done(function( data ) {
            alert(data);
               
               //10.A. DISPLAY OF ACCOUNT DETAILS ON WHICH WAS TRANSACTION TO ANOTHER CUSTOMER EXECUTED 
               //- SENDER ACCOUNT, USING GET
               $.ajax({
               type:"GET",
               dataType:"json",
               contentType:"application/json",
               //api for getting specific user account by accountType
               url:"http://localhost:49000/api/customer/"+customerId+"/getUA/"+accountTypeTCOut,
               success: function (result, status, xhr) {
               },error: function (xhr, status, error) {
               alert("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
               }

               }).then(function(data) {
               console.log(data);
               $('#myTableTransferCustomers').empty();
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
                        $('#myTableTransferCustomers').append(row);
                    }
               });
            });
    });
    
    
    // 11. DISPLAY OF TRANSACTIONS FOR A PARTICULAR ACCOUNT BY ACCOUNT TYPE, USING GET           
    $("#displayTransactions").click(function() {
        $("#panel17").show();
        accountTypeSelect = $("#accountsTransactions").val();
         console.log(accountTypeSelect);
         
         $.ajax({
               type:"GET",
               dataType:"json",
               contentType:"application/json",
               url:"http://localhost:49000/api/customer/"+customerId+"/getUA/"+accountTypeSelect+"/trans",
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
