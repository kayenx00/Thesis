# PATIENTS MANAGEMENT SYSTEM
///////////////////////////////// How to install and launch the “Patients System Management” back-end ////////////////////////

What you need to have: 
- IntelliJ IDEA (Community or Ultimate).
- MySQL Workbench. 
- Ensure your computer has Java (17 or higher).
- Install Java JDK version 17 or higher. 

Installment: 
- Go to this link to install the website’s back-end: https://github.com/kayenx00/Thesis
- Please follow the guidelines of the following link to install and launch MySQL on your local machine: 
https://www.simplilearn.com/tutorials/mysql-tutorial/mysql-workbench-installation (please remember your root password when installing).

![image](https://user-images.githubusercontent.com/53591019/234654664-f1f52e2e-04ac-409f-9bdc-93b940097fe2.png)

Launching: 
- First, open MySQL Workbench and choose the root.

![image](https://user-images.githubusercontent.com/53591019/234654706-26995eba-fe56-4442-86d4-88122e6cdbdf.png) 

- Run the following script to create database name `test`:
```
 CREATE Database `test`;
```
- Now open your back-end folder with IntelliJ IDEA. (open IntelliJ IDEA -> File -> Open). 
- Open Project (File -> Project Structure -> Project Settings -> Project).

![image](https://user-images.githubusercontent.com/53591019/234654741-3704e0bf-f72f-4352-8ddc-6025915adc5a.png)

- In your Project window, select SDK, then choose the Java JDK version you installed. 
- Close the Project window.
- After adding JDK to the project, you need to Build the project (Build -> Build Project).
- Then go to the “resources” folder, and access the application.properties file.  

![image](https://user-images.githubusercontent.com/53591019/234654789-866ea932-bc74-4900-a3a0-9c679ddeadfc.png)

- In the file, you must change line 4 to your root password(the password you set when installing the MySQL Workbench).

![image](https://user-images.githubusercontent.com/53591019/234654841-15670e9d-e45b-4b08-9b09-86379a90dbd7.png)

(Replace ‚”Nguyenlong09102014” with your password). 
- Now click run to launch the back-end. (Run -> Run ‘ThesisApplication’)
- Then go back to MySQL Workbench again. 
- Open the query tab (File -> New Query Tab), then enter the following scripts to create Admin's account (Or Medical Staff's account). Note that 
the account's username is: "Kayen" and account password is: "654321":
```
 USE `test`
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`1`, `ROLE_ADMIN`);
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`2`‚ `ROLE_PATIENT`);
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`3`‚ `ROLE_DOCTOR`);
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`4`, `ROLE_NURSE`);
 INSERT INTO `test`.`users` (`email`, `password`, `username`, `enabled`) VALUES (`nguyenhlong09@gmail.com`‚ `$2a$10$jM9VC9O8yzqRghpN35Qct.R040oFNoWFaNqe04aT7FhVbdirxB.XO`, `Kayen`, 1);
 INSERT INTO `test`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');

```
 Now you successfully launch the websites's back-end. 

