# User Guide

## Table of Contents
* [Features](#features)
    * [Starting the program](#starting-the-program)
    * [Adding a task to track](#adding-a-task-to-track)
        * [Adding ToDo task: ](#adding-todo-task) `ToDo`
        * [Adding Event task: ](#adding-event-task) `Event`
        * [Adding Deadline task: ](#adding-deadline-task) `Deadline`
    * [Setting a task as done: ](#setting-a-task-as-done) `Done`
    * [Displaying task list: ](#displaying-task-list) `List`
    * [Deleting a task from the list](#deleting-a-task-from-the-list): `Delete`
    * [Finding keyword in the list](#finding-keyword-in-the-list): `Find`
    * [Exiting the program](#exiting-the-program): `Bye`
* [End of user guide](#end-of-user-guide)

## Features
Current version of Duke allows you to add 3 different types of tasks.
They are `Event`, `Deadline` and `ToDo`.
Other functions include `List`, `Find` and `Delete`.

### Starting the program
When the program _Duke_ start, it will attempt to locate a file called `savedtasklist.txt` and attempt to load previously saved tasks into your current task list.

Expected outcome:
```
Done! All tasks loaded!
===================================================
Hello! I'm Duke.
What can I do for you?
===================================================
```
### Adding a task to track
There are three types of task that you can add into your task list. They are `Event`, `Deadline` and `ToDo`. All three task requires you to provide a name for it. `Event` and `Deadline` requires you to specify a time of the task. 

#### Adding ToDo task
Duke will  attempt to add a task of type `Todo` with the name you provided into the task list.

Format of `Todo`:

`ToDo` {Task Name}

Example of usage: 
```
Todo gym excercise
```
Expected outcome:
```
New task added:
    [T][✗] CS2113 Tutorial
I'll keep track of it for you!
```

#### Adding Event task
Duke will  attempt to add a task of type `Event` with the name and time you provided into the task list.

Format of `Event`:

`Event` {Task Name} /at {Event Time}

**Note :** Event time should be of the format YYYY-MM-DD 

Example of usage: 
```
Event CS2101 meeting /at 2020-10-01
```
Expected outcome:
```
New task added:
    [E][✗] CS2101 meeting (at: Oct 1 2020)
I'll keep track of it for you!
```

#### Adding Deadline task
Duke will  attempt to add a task of type `Deadline` with the name and time you provided into the task list.

Format of `Deadline`:

`Deadline` {Task Name} /by {Deadline Time}

**Note :** Deadline time should be of the format YYYY-MM-DD 

Example of usage: 
```
Deadline IP V0.2 /by 2020-10-02
```
Expected outcome:
```
New task added:
    [D][✗] IP V0.2 (by: Oct 2 2020)
I'll keep track of it for you!
```

### Setting a task as done
Duke will  attempt to mark a task that is specify by you as done.

Format of `Done`:

`Done` {Task Number}

Example of usage: 
```
Done 1
```
Expected outcome:
```
Nice! I've marked this task as done:
   [T][✓] CS2113 Tutorial
```

### Displaying task list
To view your current task list, type `List` when prompted. 

Example of usage: 
```
List
```
Expected outcome:
```
Here are the tasks in your list:
1. [T][✓] CS2113 Tutorial
2. [E][✗] CS2101 meeting (at: Oct 1 2020)
3. [D][✗] IP V0.2 (by: Oct 2 2020)
----------------------------------------------------
```

### Deleting a task in the task list
To delete a task from the task list, type `delete` followed by the task number to delete.

Format of `Delete`:

`Delete` {Task Number}

Example of usage: 
```
Delete 1
```
Expected outcome:
```
Noted. I've removed this task:
    [T][✓] CS2113 Tutorial
```

### Finding a task in the task list
To find tasks with a keyword, type `Find` followed by the keyword.

Format of `Find`:

`Find` {Keyword}

Example of usage: 
```
Find IP
```
Expected outcome:
```
Here are the matching tasks in your list:
    [D][✗] IP V0.2 (by: Oct 2 2020)
```

### Exiting the program
To exit the program, type `Bye` when prompted.

While exiting the program, Duke will save a copy of your task list as `savedtasklist.txt` in the data folder.

Example of usage: 
```
bye
```
Expected outcome:
```
Done! All tasks saved!
===================================================
Good bye!
===================================================
===================================================
```

## End of user guide
