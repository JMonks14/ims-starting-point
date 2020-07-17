Inventory management system 
written by James Monks

This application allows the user to view and make changes to a database containing customers, orders and items. They may view all customers in the system as well as adding new ones or removing or updating existing ones.
Likewise they may view, create, update and delete items as well as orders. They may create orders, assign them to a customer, view all orders, update which customer they belong to, view the items in an order; add, delete or update
the quantity of an item in an an order.

## Getting Started


All source code and documentation for this project can be found at https://github.com/JMonks14/ims-starting-point
It is recommended to fork this repository and then clone a copy to your local machine.


### Prerequisites

Please note: to run this application you will need to have Java installed on your computer.

See https://www.java.com/en/download/help/windows_manual_download.xml#download for details of how 
to install java if you do not have it already installed.

You will also need to install maven if you have not already done so, see https://maven.apache.org/install.html for details of how to do so.

you will need to also need to install Mysql, see https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html#:~:text=The%20simplest%20and%20recommended%20method,%2Finstaller%2F%20and%20execute%20it.
for details of how to do so if you have not already done so.

### Installing

To open a development environment use an IDE such as eclipse. Go to file > Open Projects from File System. In the new window click directory, then navigate to where you should have cloned the
repository described above to and select the folder 'ims-starting-point'. When you return to the previous window click 'Finish'. This will allow you to navigate the project using the IDE.

You will need to create a database for the application to interact with. You can do this in your local instance of Mysql or using a web instance created using a cloud computing service if you prefer.
In your instance of Mysql create the tables your database will interact with. You can do this by running the commands found in Customers create.txt, Items create.txt, Orders create.txt and
Order_details create.txt in the ims-starting-point repo.

then run the following commands in mysql:
INSERT INTO Customers (first_name, last_name, username, password)
VALUES('nolongerexists','nolongerexists','nolongerexists','nolongerexists');

UPDATE Customers set cust_ID=0;

INSERT INTO Orders (fk_cust_ID, total_cost) VALUES(0,0);

UPDATE Orders set order_ID=0;

INSERT INTO Items (item_name, quant_in_stock, price) VALUES('nolongerexists',0,0);

Update Items set item_ID=0;

this will set up the nolongerexists options which deleted orders and orderlines are automatically assigned to.

Finally in your IDE open com.qa.ims.utils/Utils.java and set the value of the attribute public static final String MYSQL_URL to the contact address of your data base.
This will be "jdbc:mysql://localhost:3306/" For a cloud instance you will need to find the IP address of your instance and use that. 
You will also need to open sql schema in src/main/resources and set the database name after 'create database if not exists' to match the name of your database.

You may now run the application in the IDE by opening runner and clicking the run button. The application will first prompt you to enter the username and password for your sql instance.
Once you have entered these you will be prompted to enter a domain and then an action. The options will be explained but for example, to add a new customer to the database:
When the program displayed 'Info - Please enter a domain' you would enter 'customer', then 'create' when 'what would you like to do with customer' was displayed. You would then be prompted 
to enter a first name, last name, username and password successively. Your entry would then be added to the database and could be viewed by selecting 'customer' folowed by 'read'




### Unit Tests 

If you navigate to src/test/java in your IDE you fill find a number of classes containing junit tests corresponding to the classes in src/main. These can be run individually by right-clicking
in a test class and clicking coverage as > 1 junit test. These will indicate if the code is working as intended.

for example if you do this in CustomerDaoMysqlTest the methods in the test class will create a database in your local instance of mysql called 'ims_test', add a customer table then create,
read, update and delete a number of entries from it beofre deleting the table. any code that is executed successfully in either the test class or the original class being tested will be highlighted
in green. To run all tests simultaneously you can right click on the project folder in the IDE navigation and click coverage as > 2 junit test. A coverage tab should appear on the bottom panel
which will indicate how much of the code in the project is being successfully covered by the tests.


## Deployment
To deploy the application navigate to the project folder in command prompt and enter: mvn package

This will create 2 new files in the folder:
JamesMonks-20Junsoftware1.jar
JamesMonks-20Junsoftware1-jar-with-dependencies.jar

To run the application use first ensure that the project directory is open in Command prompt and enter the command java -jar target/JamesMonks-20Junsoftware1-jar-with-dependencies.jar

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **James Monks** - *Application development* - (https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Vinesh Ghela was taught me how to use all of the technologies I used to build the application
* Alan Davies provided some valuable assistance, particularly in regards to junit testing

