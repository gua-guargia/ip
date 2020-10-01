# Duke: IP Project

This is a Java project Duke for CS2113T. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## User Guide

### 1.0 What is Duke? 
This is a Java project Duke for CS2113T. It's named after the Java mascot _Duke_. Duke is an task manager which allows the user to add, delete, modify and check the tasks.

### 2.0 About this document
This document shows you six basic features in Duke, the `<list>`, `<todo>`, `<event>`, `<deadline>`, `<delete>`, `<find>`, `<bye>` and `<done>` commands.

Note the following symbols and formatting used in this document:
format and symbols | meanings
--------- | ------------
`<list>` | A grey highlight (called a mark-up) indicates that this is a command that can be typed into the command line and executed by the application
Welcome to Duke | A box of content splited by two lines indicates that this is a output that computed by Duke after interpreting the command line.

### 3.0 Basic commands

   #### List tasks: `<list>`
   This command displays all the items stored in the task list, as well as each task's complete status, type (i.e. todo, event, deadline) and date/deadline if it's type of 'event' or 'deadline'.
   
   Example of usage:
   > list
   
   Expected outcome:
   >   ____________________________________________________________
   >   1. [T][✓] borrow book
   >   2. [T][✓] read book
   >   3. [D][✓] return book (by: June 6th)
   >   4. [E][✓] project meeting (at: Aug 6th 2-4pm)
   >   5. [T][✘] join sports club
   >   ____________________________________________________________
   
   
   #### Delete task: `<delete>`
   This command allows the user delete items from task list by index. The index is corresponding to the index displayed by list command.
   
   Example of usage:
   > delete 2
   
   Expected outcome:
   >   ____________________________________________________________
   >     Noted. I've removed this task:
   >     [T][✓] read book
   >     Now you have 4 tasks in the list.
   >   ____________________________________________________________
  
  
   #### Add new task: `<todo>`, `<event>`, `<deadline>`
   This command allows the user to add new tasks. The user can create 3 different type of tasks: Todo, Deadline and Event.
   
   **todo** task requires a task description.
   
   Example of usage:
   > todo read book
   
   Expected outcome:
   >   ____________________________________________________________
   >     Got it. I've added this task:
   >     [T][✘] read book
   >     Now you have 5 tasks in the list.
   >   ____________________________________________________________
   
   **event** task requires a task description and a date of the event.
   
   Example of usage:
   > event visit parents /at Sunday afternoon
   
   Expected outcome:
   >   ____________________________________________________________
   >     Got it. I've added this task:
   >     [E][✘] visit parents(at: Sunday afternoon)
   >     Now you have 6 tasks in the list.
   >   ____________________________________________________________
   
   **deadline** task requires a task description and a deadline date or time.
   
   Example of usage:
   > deadline ip publish /by Friday 11:59 p.m.
   
   Expected outcome:
   >   ____________________________________________________________
   >     Got it. I've added this task:
   >     [D][✘] ip publish(by: Friday 11:59 p.m.)
   >     Now you have 7 tasks in the list.
   >   ____________________________________________________________
   
   
   #### Mark task as done: `<done>`
   This command mark task as done by index in the task list. Users requires to enter an index between 1 to maximum amount of items in the list. If there is no element in the list, a error message will be displayed.
   
   Example of usage:
   > list
   > done 2
   
   Expected outcome:
   >   ____________________________________________________________
   >   1. [T][✓] read book
   >   2. [E][✘] project meeting(at: Aug 6th 2-4pm)
   >   3. [T][✓] join sports club
   >   4. [T][✓] finish landry
   >   5. [T][✘] finish ip
   >   6. [E][✓] date(at: friday)
   >   7. [T][✘] work
   >   ____________________________________________________________
   
   >   ____________________________________________________________
   >     Nice! I've marked this task as done:
   >     [E][✓] project meeting(at: Aug 6th 2-4pm)
   >   ____________________________________________________________
   
   
   #### Find task by description: `<find>`
   This command allows the user to find task that contains the key word in its description. The key word is used as filter word for searching.
   
   Example of usage:
   > find finish sports
   
   Expected outcome:
   >   ____________________________________________________________
   >   Here are the matching tasks in your list:
   >   1. [T][✓] join sports club
   >   2. [T][✓] finish landry
   >   3. [T][✘] finish ip
   >   ____________________________________________________________
   
   #### Exit the program: `<bye>`
   This command exist the user from program and update the user list in the file.
   
   Example of usage:
   > bye
   
   Expected outcome:
   >   ____________________________________________________________
   >     Your file has been saved in data/duke.txt
   >   ____________________________________________________________
   >   ____________________________________________________________
   >     Bye. Hope to see you again soon!
   >   ____________________________________________________________


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

