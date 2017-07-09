<html>

<!--------  This file has been coded by Jacques LEVY. ------------------->
<!--------  Usage is allowed  in terme of GNU General Public License ------------------->

<head> 
   <title> Crible d'Ératosthène </title> 

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    
   <style type="text/css">

table#Mytable {

     border:0px;
     border-spacing:4px; /* équivalent cellspacing (ne fonctionne pas sur IE) */
}



table#Mytable td {
   background-color:#ffc;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 1px solid #000;
   text-align:center;
   width:50px;
   height:50px;
   font-size:25px;
   color:blue;
   #opacity: 0.5;
   #filter: alpha(opacity=50);    
}


table#MyPrimeTab {

     border:0px;
     border-spacing:4px; /* équivalent cellspacing (ne fonctionne pas sur IE) */
}



table#MyPrimeTab td {
   background-color:#fff;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 0px solid #000;
   text-align:center;
   width:50px;
   height:50px;
   font-size:25px;
   color:blue;
   #opacity: 0.5;
   #filter: alpha(opacity=50);    
}


.croixR{
   background:#ccf;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 1px solid #000;
   text-align:center;
   width:50px;
   height:50px;
   font-size:25px;
   color:blue;
   background:url("../images/croix_coche_r.jpg") no-repeat left top;
   background-size: 50px 50px;
}


.croixV{
   background:#ffc;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 1px solid #000;
   text-align:center;
   width:50px;
   height:50px;
   font-size:25px;
   color:blue;
   background:url("../images/croix_coche_v.jpg") no-repeat left top;
   background-size: 50px 50px;
}


.croixB{
   background:#ffc;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 1px solid #000;
   text-align:center;
   width:50px;
   height:50px;
   font-size:25px;
   color:blue;
   background:url("../images/croix_coche_b.jpg") no-repeat left top;
   background-size: 50px 50px;
}


.croixN{
   background:#ffc;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 1px solid #000;
   text-align:center;
   width:50px;
   height:50px;
   font-size:25px;
   color:blue;
   background:url("../images/croix_coche_n.jpg") no-repeat left top;
   background-size: 50px 50px;
}

.croixJ{
   background:#ffc;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 1px solid #000;
   text-align:center;
   width:50px;
   height:50px;
   font-size:25px;
   color:blue;
   background:url("../images/croix_coche_j.jpg") no-repeat left top;
   background-size: 50px 50px;
}



.circle {
    background: #f00;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    opacity: 0.5;
}

.texte0{
   background:#fff;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 0px solid #000;
   text-align:center;
   font-size:25px;
   color:blue;  
}

.texte{
   background:#ccf;
   padding:2px; /* équivalent cellpadding */
   spacing: 3px;
   overflow:auto; /* si ça dépasse on affiche la cell2bar */ 
   border: 1px solid #000;
   text-align:center;
   font-size:25px;
   color:blue;  
}

.button
{
	position: absolute;
	background-color: #FFC;
	border: 1px solid #000;
        text-align:center;
        color:blue;
        font-size:16px;
}

h1
{
  color:blue;
}

.directive
{
  color:magenta;
  font-size:130%;
}

   </style>


<script type="text/javascript">

var NB_ELEM=10;
var PauseTimerCrux=200;

var tmp="";
var tempNb=0;
var defaultColor="rgb(255, 255, 255)";
var SelectedColor="rgb(180, 180, 255)";;

function trim (myString)
{
   //return myString.replace(/^\s+/g,'').replace(/\s+$/g,'')
  return myString.replace(/ /g,'');
} 


var Queue = (function(){

    function Queue() {};

    Queue.prototype.running = false;

    Queue.prototype.queue = [];

    Queue.prototype.add_function = function(callback) { 
        var _this = this;
        //add callback to the queue
        this.queue.push(function(){
            var finished = callback();
            if(typeof finished === "undefined" || finished) {
               //  if callback returns `false`, then you have to 
               //  call `next` somewhere in the callback
               _this.next();
            }
        });

        if(!this.running) {
            // if nothing is running, then start the engines!
            this.next();
        }

        return this; // for chaining fun!
    }

    Queue.prototype.next = function(){
        this.running = false;
        //get the first element off the queue
        var shift = this.queue.shift(); 
        if(shift) { 
            this.running = true;
            shift(); 
        }
    }

    return Queue;

})();





function BuildGrid()
{
   var nb=0;	
   var table = document.getElementById("Mytable");
   
   for (i=0; i<NB_ELEM; i++)
   {
      tr = table.insertRow(i);
      tr.id = "tr_"+i+1+""; 
      for (j=0; j<NB_ELEM; j++)
      {
      	 nb = 10*i+ j+1;
         td= tr.insertCell(j);
         td.innerHTML += nb;
         td.id = "td_"+nb+"";  
      }
        
   }   
}

function BuildPrime()
{
   var nb=0;	
   var table = document.getElementById("MyPrimeTab");
   
   for (i=0; i<NB_ELEM; i++)
   {
      tr = table.insertRow(i);
      tr.id = "tr_prime_"+i+1+""; 
      for (j=0; j<NB_ELEM; j++)
      {
      	 nb = 10*i+ j+1;
         td= tr.insertCell(j);
         td.id = "td_prime_"+nb+"";  
      }
        
   }   
}


function isPrimeNumber( number)
{
  nb_divisor=0;
  is_prime= true;	
  for (i = 1; i<=number; i++)
  {
     if (0 == number % i)
     {
     	nb_divisor++;
     } 
  }
  
  if (nb_divisor > 2)
   {
      is_prime = false;
   }
  return (is_prime);	
}



function circleNumber(nb)
{ 
   // Stop sieve	
   if ( nb > NB_ELEM)
   {
      return;	
   }  
   	
   // Change the style	
   document.getElementById("td_"+nb+"").className = "circle";
   
   if (localStorage.getItem("drapLang") != "eng" )
   {
       document.getElementById("message_text").innerHTML = "Supression des multiples de "+nb+" ";
   }
   else
   {
       document.getElementById("message_text").innerHTML = "Removing multiples of "+nb+" ";
   } 
   document.getElementById("message_text").className = "texte"; 
   
   var index_found=0;
   
   for (j=0; j<NB_ELEM; j++)
   {
      for (i=0; i<NB_ELEM; i++)
      {
      	 k = (i+1) + 10*j;
      	 
      	 if ( (0 == k % nb) && (k > nb) )
      	 {
      	    index_found++;
      	    
      	    console.log("k: "+k+", nb: "+nb+" ");

      	    // use a closure to remove multiples
            setTimeout((function(k,index_found) {
                            return function() 
                            {
                               RemoveMultiple(k,index_found);
                            };
                         })(k,index_found), index_found*PauseTimerCrux);        	    
      	    	
      	 }
      }	
   	
   }
   
   if (localStorage.getItem("drapLang") != "eng" )
   {
       document.getElementById("message_text_prime").innerHTML = "Nombres premiers: ";
   }
   else
   {
       document.getElementById("message_text_prime").innerHTML = "Primes numbers: ";
   }
   document.getElementById("message_text_prime").className = "texte"; 
   document.getElementById("td_prime_"+nb+"").innerHTML += nb;
   
   
}


function RemoveMultiple(nb, i)
{
   // Change the style	
   if ( ( 0 == (nb %2) ) && (nb > 2) )
   {
      document.getElementById("td_"+nb+"").className = "croixR";
   }
   else if  ( ( 0 == (nb %3) ) && (nb > 3) )
   {
     document.getElementById("td_"+nb+"").className = "croixV";	
   }
   else if  ( ( 0 == (nb %5) ) && (nb > 5) )
   {
     document.getElementById("td_"+nb+"").className = "croixB";	
   }   
   else if  ( ( 0 == (nb %7) ) && (nb > 7) )
   {
     document.getElementById("td_"+nb+"").className = "croixJ";	
     
      
     // Sieve is over => Complete prime number tables 
     setTimeout(DisplayFullPrimeNumbers, 3000)
       
   }
	
   else // Should not be here
   {
     document.getElementById("td_"+nb+"").className = "croixR";	
   }
}

function DisplayFullPrimeNumbers()
{
   for (j=0; j<NB_ELEM; j++)
   {
      for (l=1; l<=NB_ELEM; l++)	
      {
     	 check_nb = 10*j+ (l+1);
     	 
     	 if ( ( true == isPrimeNumber(check_nb) ) && (check_nb> 7)  && (check_nb <= NB_ELEM*NB_ELEM) )
     	 {     
             document.getElementById("td_prime_"+check_nb+"").innerHTML = check_nb;
         }
      }
   } 	
}

function Eratosthene()
{
  var nb=0, i=0, j=0;    
  var queue = new Queue;
  var index_prime_number=0;
  var time_to_wait = 0;
   	
   for (j=0; j<NB_ELEM; j++)
   {
     for (i=1; i<=NB_ELEM; i++)	
     {
     	 nb = 10*j+ (i+1);
     	 
     	 if ( true == isPrimeNumber(nb) )
     	 {     	 	
     	    	
     	    // Circle the number  
            //setTimeout(circleNumber, PauseTimer)
          
           
            if (2 == nb) 
            {
            	time_to_wait = 12000;
            }
            else if (nb <= 7)
            {
               time_to_wait = 10000;
            }
            else
            {
               time_to_wait= 7000;
            }
           	
          
            
            // Use a closure to manage callback parameter
            setTimeout((function(nb) {
                            return function() 
                            {
                               circleNumber(nb);
                            };
                         })(nb), index_prime_number*time_to_wait);            
            
           index_prime_number++;

     	    
     	    // Remove multiples	
     	 }
     }
   	
   }		
}	



function Restart()
{
   var i 
   
   var X = [ 50  ]; 
   var Y = [ 500  ];   
   //changeText('');
   //changeText2('');
   


   if (localStorage.getItem("drapLang") == null )
   {
     localStorage.setItem("drapLang", "fr");
   }
      
   DisplayDirectives();   


   
 
}


function DisplayDirectives()
{
   /* Manage lang */ 
   var myH1="";
   var myInstr="";
   var myDrap="";
   
  
   if (localStorage.getItem("drapLang") != "eng" )
   {
      myH1="Crible d'Ératosthène";      
      myInstr="Détermination des nombres premiers par la méthode du crible d'Ératosthène"; 	
      document.getElementById('BtValid').value="Lancer";
      document.getElementById('BtRestart').value="Recommencer"; 
      myDrap="../images/version-eng.gif";
      
     
   }
   else
   {
      myH1="Sieve of Eratosthene";      
      myInstr="Determination of prime numbers by the Eratosthenes screen method"; 	
      document.getElementById('BtValid').value="Launch";
      document.getElementById('BtRestart').value="Restart"; 
      myDrap="../images/version-fra.gif";
     
   } 
   
    
   
   document.getElementById("myH1").innerHTML = "<u>"+myH1+"</u>" +"&nbsp; &nbsp;<img src=\""+myDrap+"\" onClick=\"ManageLang()\" >"; 
   document.getElementById("directive").innerHTML = ""+myInstr+"" +"&nbsp;"; 
   
   document.getElementById("message_text").innerHTML = "";
   document.getElementById("message_text_prime").innerHTML = "";
   
   BuildGrid();
   BuildPrime();

   

   
   	
}


function ManageLang()
{
	
  if (localStorage.getItem("drapLang") == "fr" )
  {
    localStorage.setItem("drapLang", "eng");	
  }
  else if (localStorage.getItem("drapLang") == "eng" ) 
  {
    localStorage.setItem("drapLang", "fr");		
  }

  
  // Reload the url
  location.reload();
}

function Refresh()
{
  location.reload();	
}



</script>

</head> 


 <body onload="Restart()"> 



<center> 
 <h1 id="myH1"> </h1>
</center>

 <br>

<div class="directive" id="directive"> <br></div>


<br>

 
<table>
<tr> 
  <td> <div id="message_text" class="texte0"></div> <table id="Mytable">  </table> </td> 
  <td> <div id="message_text_prime" class="texte0"></div> <table id="MyPrimeTab">  </table> </td> 
</tr>
</table>    

 
 <br><br> <br><br> <br>
 

<form id="theForm">
  <input type="button" id="BtValid"  onClick="Eratosthene()"  class="button">  &nbsp;   &nbsp;  &nbsp;  &nbsp;   &nbsp;   &nbsp;  &nbsp;  &nbsp;   &nbsp;   &nbsp;  &nbsp;  &nbsp; 
  <input type="button" id="BtRestart" onClick="Refresh()"  class="button">
  
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
</form>

<br>


@Jacques LEVY



</body>

</html>
