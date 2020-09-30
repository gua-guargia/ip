# Duke: IP Project

This is a my Java project Duke for CS2113T. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## User Guide

### 1.0 What is Duke? 

### 2.0 About this document


### 3.0 Basic commands

   #### List tasks: `<list>`
   This command display all the items stored in the task list, as well as each task's complete status, type (i.e. todo, event, deadline) and date/deadline if it's type of 'event' or 'deadline'.
   
   Example:
   list
      ____________________________________________________________
      1. [T][✓] borrow book
      2. [T][✓] read book
      3. [D][✓] return book (by: June 6th)
      4. [E][✓] project meeting (at: Aug 6th 2-4pm)
      5. [T][✘] join sports club
      ____________________________________________________________
   
   
   #### Delete task: `<delete>`
   This command allow the user delete items from task list by index. The index is corresponding to the index displayed by list command.
   
   Example:
   delete 2
      ____________________________________________________________
      Noted. I've removed this task: 
      [T][✓] read book
      Now you have 4 tasks in the list.
      ____________________________________________________________
  
   #### Add new task: `<todo>`, `<event>`, `<deadline>`
   
   #### Mark task as done: `<done>`
   
   #### Find task by description: `<find>`
   
   #### Exit the program: `<bye>`


## Developer Guide

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
