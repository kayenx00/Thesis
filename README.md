# PATIENTS MANAGEMENT SYSTEM
///////////////////////////////// How to install and launch the “Patients System Management” website ////////////////////////

What you need to have: 
- IntelliJ IDEA (Community or Ultimate).
- Visual Studio Code.
- MySQL Workbench. 
- Ensure your computer has Java (17 or higher) and NodeJS.
- Install Java JDK version 17 or higher. 

Installment: 
- Go to this link to install the website’s back-end: https://github.com/kayenx00/Thesis
- Go to this link to install the website’s front-end: https://github.com/kayenx00/covid-19-patient-management-front-end 
- Use IntelliJ IDEA and Visual Studio Code to open the back-end‚Äôs folder and front-end‚Äôs folder, respectively.
- Please follow the guidelines of the following link to install and launch MySQL on your local machine: 
https://www.simplilearn.com/tutorials/mysql-tutorial/mysql-workbench-installation (please remember your root password when installing).
 
Launching: 
- First, open MySQL Workbench and choose the root.
 
- Now open your back-end folder with IntelliJ IDEA. (open IntelliJ IDEA -> File -> Open). 
- Open Project (File -> Project Structure -> Project Settings -> Project).
 
- In your Project window, select SDK, then choose the Java JDK version you installed. 
- Close the Project window.
- After adding JDK to the project, you need to Build the project (Build -> Build Project).
- Then go to the “resources” folder, and access the application.properties file.  
 
- In the file, you must change line 4 to your root password(the password you set when installing the MySQL Workbench).
 
(Replace ‚”Nguyenlong09102014” with your password). 
- Now click run to launch the back-end. (Run -> Run ‘ThesisApplication’)
- Then go back to MySQL Workbench again. 
- Open the query tab (File -> New Query Tab), then enter the following scripts:
```
 USE `test`
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`1`, `ROLE_ADMIN`);
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`2`‚ `ROLE_PATIENT`);
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`3`‚ `ROLE_DOCTOR`);
 INSERT INTO `test`.`roles` (`id`, `name`) VALUES (`4`, `ROLE_NURSE`);
 INSERT INTO `test`.`users` (`email`, `password`, `username`, `enabled`) VALUES (`nguyenhlong09@gmail.com`‚ `$2a$10$jM9VC9O8yzqRghpN35Qct.R040oFNoWFaNqe04aT7FhVbdirxB.XO`, `Kayen`, 1);
 INSERT INTO `test`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');

```
- Now open your front-end folder with your Visual Studio Code. (File -> Open Folder).
- After opening the folder, open the Terminal tab (Terminal -> New Terminal).
- On your Terminal tab, enter the following script `npm i` to initiate all packages. 
 
- After waiting for the initiating, enter the next script `npm start` to launch the front-end.
 
- After waiting for the front-end to start, Visual Studio Code will redirect you to the Website’s homepage. Now you successfully launch the websites. 

