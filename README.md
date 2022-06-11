# Best-3350-Group-Project

# Contents
[Team 10](#group-members)
- [Github Repository](#repo-url)
- [Dev Log](#dev-log)
- [Feature List](#feature-list)
- [Testing Environments](#environments-used-in-testing)

[Packages](#packages)
- [Business](#business)
- [Objects](#objects)
- [Persistence](#persistence)
- [Presentation](#presentation)

# Team 10
## Group members
Dane Wanke - wanked@myumanitoba.ca  
Gurdit Singh- singhg78@myumanitoba.ca  
Joseffus Santos - umsant28@myumanitoba.ca  
Manraj Singh – singhm53@myumanitoba.ca  
Zhihou Zhou- zhouz2@myumanitoba.ca  

# Repo URL
https://github.com/DaneHarrison/Eat-Dix

# Dev Log
The team dev log is kept as an md file in the github repository  
# Packages
## Business
Business classes handle program logic and calculations related to their activities.  
### Meal Diary Ops
The meal diary ops class handles logic and operations related to the main app activity which is the logging of a users meals. This class handles the retrieval of meal logs from a database based on the date the user wants to view. This class also handles the addition of new entries into the daily log and the database.  
  
### Recipe Book Ops
This class handles operations related to the app recipe book. This class handles the retrieval of food, drink, and meal recipe lists from the database. This class also handles the addition of new food, drink, and meal recipes added by users.  

### Unit Converter
This class handles unit conversions for meal diary entries.  

## Objects  
Object classes are objects that are shared by Business, Presentation, and Persistence classes.    
### ListItem
This class is an interface of thing required for objects to be displayed in the different app screens. The app uses views that rely on lists of items.

### Edible
This is an Abstract class at the top of the heirarchy of food items. This class defines essential attributes and methods that all fod items should have.

### 
## Persistence
Desc  
### DataAccessStub
Desc  
### SharedDB
Desc  
## Presentation
The presentat 

### Activity Meal Diary
Desc  

### Activity Recipe Book
Desc  

### FragToMealDiary
Desc  

### FragToRecipeBook
Desc  

### RVAMealDiary
Desc  

### RVARecipeBook
Desc  

### MealDiaryLiveData
Desc  

# Feature List
The main page of the Meal Planner application display's the food items that the user plans to consume on a specific Day. The bar depicts the Calorie goal for the day, the number of calories consumed via Food, calories burnt via Exercise, and the net calories that can make. The user can set the Calorie goal for the day, and calories burnt in the exercise routine and will be able to track the progress made. The user can edit the list of Food items that need to be consumed. They can add Food items, Meals, and Drinks to the Food list along with the amount that they are consuming for each item. Also, we can remove the items on the list to cut off the unnecessary calorie intake. The user can set the Calorie goal for the day and will be able to track the number of calories that can still be consumed to reach the target. Moreover, users are able to change the amount of each item they did intake. The users can also add new food recipes. They can 

# Environments Used in Testing

Our team used Android Studio Chipmunk 2021.2.1 Patch 1 for development. We tested our code both on a Nexus 7 device and several Android virtual devices created in Android studio. Android virtual devices used, had SDK 23 and SDK 29 for debugging.  


Write a readme.txt or readme.md file that describes the contents of your electronic submission. Include the project title and names of all team members. Identify the packages and major source code files. Include a URL to your Github private repo. Describe where/how your log is kept. Provide an overview of the major implemented features and where to find them in the GUI.  
In the readme, clearly describe which Android systems your app was tested on, including emulator and hardware. Describe exactly the environment(s) used, to ensure the markers can replicate an environment and avoid unexpected results. Remember that regardless of the hardware, it must run on the emulator with system image 6.0 (Marshmallow, API level 23).  
Sketch out the overall architecture of your system broadly. Include a copy of the sketch with your submission. You can either draw it on paper and include a scan (or a very readable photo), or draw in software using a tablet or drawing tools. Submit it as a png, jpg, or pdf.  
The developer log will contain entries that belong to the entire team, and those made by individual team members. It will contain information about the entire project, such as developer tasks and their assignments, and records of team meetings and participation. It will also contain individual entries by team members of their work sessions, detailing the amount of time actually spent on tasks. Note that while these log entries may correspond to events in the version control history, the log will still be kept separately from that record.  
It will also describe outstanding bugs and their fixes, and design decisions and rationale. You may choose to use a tool for keeping your log, or a document in the version control system, or a separate shared document (like a Google doc). Whichever tool you choose, you must export it to a text file log.txt and submit it as part of your iteration.  
