# <img src="https://portalsam.net/wp-content/uploads/2021/04/currencyconvertericon.png" width="64" height="64" />CurrencyConverter


Small accessability tool for converting currency values from and to eachother.

Currently supports: 
*United States Dollar* (**USD**), *Canadian Dollar* (**CAD**), *Australian Dollar* (**AUD**), *Great British Pound* (**GBP**), *Euro* (**EUR**), *Japanese Yen* (**JPY**)

Currency information is grabbed from https://free.currencyconverterapi.com/, CurrencyConverter updates this information every *2 hours*.

# Commands

* **/convertcurrency**

  • When supplied one argument of a 3 letter currency type, */convertcurrency* displays the value of it compared to *US Dollar*.
  
  *Example:*
  ```
  /convertcurrency cad
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/04/currencyconverter1.png" width="700" height="40" />
  
  • When supplied two arguments of a number and a 3 letter currency type together and another currency type, it will show its value in that requested currency. This command can convert between all currencies supported.
  
  *Example:*
  ```
  /convertcurrency 20usd jpy
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/04/currencyconverter2.png" width="556" height="25" />
  
* **/updatecurrency**
 
  • When supplied with no arguments */updatecurrency* will stop the current 2 hour update cycle, grab new data from the API, then restart the cycle.
  
  *Example:*
  ```
  /updatecurrency
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/04/currencyconverter3.png" width="706" height="24" />
  
  • When supplied with the argument "disable" the command will cancel the 2 hour update cycle until you run */updatecurrency* again.
  
  *Example:*
  ```
  /updatecurrency disable
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/04/currencyconverter4.png" width="658" height="45" />
  
  # Help
  
  * **Replacing the default APIKey**
  
    • The API key this plugin uses can be found in the config.yml file under the *CurrencyConverter* folder in plugins. It is **Highly** reccomended that this be changed from the default so the plugin doesn't run out of queries when trying to get new information. The plugin will still function without a new API Key or if it hits the query limit, but the information given back may not be accurate. (*Currency Information embedded is from Saturday, April 17, 2021*)
    
  * **Default help**
  
    • The Default help information that will be reported by Paper.
  
    <img src="https://portalsam.net/wp-content/uploads/2021/04/currencyconverterhelp.png" width="732" height="160" />
  
