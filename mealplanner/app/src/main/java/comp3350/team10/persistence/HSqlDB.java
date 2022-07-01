package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class HSqlDB  implements LogDBInterface, RecipeDBInterface, UserDBInterface {
    private static final String SHUTDOWN_CMD = "shutdown compact";
    private Connection currConn;
    private Statement reqHandler;
    private String dbPath; //		url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
    private String dbName;
    private String dbType = "HSQL";
    
    public HSqlDB() {
        this.reqHandler = currConn.createStatement();
        this.open();
    }


    public void open() {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();

		currConn = DriverManager.getConnection(dbPath, "user", "pass");
        System.out.println("Opened " + this.dbType +" database named " + this.dbName + " @dbPath " + this.dbPath);
    }

    public String getDBType() {
        return this.dbType;
    }

    public void close() {
        if(currConn != null) {
		    reqHandler.executeQuery(SHUTDOWN_CMD);
			currConn.close();
        }
    }


    public ArrayList<Edible> getFoodList(Calendar date) {
        PreparedStatement getFoodList = currConn.prepareStatement("GET * FROM Food");
        PreparedStatement getCustomFoodList = currConn.prepareStatement("GET * FROM CustomFood");
        ArrayList<Edible> foodList = new ArrayList<Edible>();
        ResultSet results = getFoodList.executeQuery();
        boolean ediblesRemain = results.next();
        
        while(ediblesRemain) {
            foodList.add(readEdible(results, false));
            ediblesRemain = results.next();
		}

        results.close();

        results = getCustomFoodList.executeQuery();
        ediblesRemain = results.next();

        while(ediblesRemain) {
            foodList.add(readEdible(results, true));
            ediblesRemain = results.next();
		}

        results.close();


        return foodList;
    }

    private Edible readEdible(ResultSet results, boolean isCustom) {
        Edible currEdible = new Edible();

        String name, description, unit;
        int id, quantity, calories, protein, carbs, fat;
        bytes[] photo;
        boolean isAlcoholic, isSpicy, isVegan, isVegetarian, isGlutenFree;
        
        id = results.getInt("EdibleID");
        name = results.getString("Name");
        description = results.getString("Description");
        quantity = results.getInt("Quantity");
        unit = results.getString("Unit");
        calories = results.getInt("Calories");
        protein = results.getInt("Protein");
        carbs = results.getInt("Carbs");
        fat = results.getInt("Fat");
        isAlcoholic = results.getBoolean("IsAlcoholic");
        isSpicy = results.getBoolean("IsSpicy");
        isVegan = results.getBoolean("IsVegan");
        isVegetarian = results.getBoolean("IsVegetarian");
        isGlutenFree = results.getBoolean("IsGlutenFree");
        photo = results.getBytes("Photo");

        currEdible.initDetails(id, name, description, quantity, this.findEnum(unit));
        currEdible.initNutrition(calories, protein, carbs, fat);
        currEdible.initCategories(isAlcoholic, isSpicy, isVegan, isVegetarian, isGlutenFree);
        currEdible.initMetadata(isCustom, photo);

        return currEdible;
    }

    private Edible.Unit findEnum(String enumName) {
        Edible.Unit currUnit = null;

        for (Edible.Unit unit : Edible.Unit.values()) { 
            if(unit.toString().equals(enumName)) {
                currUnit = unit;
                break;
            } 
        }

        return currUnit;
    }

    public ArrayList<Edible> getMealList(Calendar date) {
        PreparedStatement getMealList = currConn.prepareStatement("GET * FROM Meal");   //join ingredients
        PreparedStatement getCustomMealList = currConn.prepareStatement("GET * FROM CustomMeal");
        ArrayList<Edible> mealList = new ArrayList<Edible>();
        ResultSet results = getMealList.executeQuery();
        boolean ediblesRemain = results.next();
        Meal currEdible;
        
        while(ediblesRemain) {
            currEdible = readEdible(results, false);
            currEdible.setInstructions(results.getString("Instructions"));
            currEdible.setIngredients(findIngredients("Meal", false));
            mealList.add(currEdible);
            ediblesRemain = results.next();
		}

        results.close();

        // results = getCustomFoodList.executeQuery();
        // ediblesRemain = results.next();

        // while(ediblesRemain) {
        //     foodList.add(readEdible(results, true));
        //     ediblesRemain = results.next();
		// }

        // results.close();


        return foodList;
    }

    public ArrayList<Ingredient> findIngredients(String edibleType, boolean custom) {
        PreparedStatement getMealList = currConn.prepareStatement("GET * FROM Meal");   //join ingredients
        PreparedStatement getCustomMealList = currConn.prepareStatement("GET * FROM CustomMeal");
        //PreparedStatement getMealList = currConn.prepareStatement("GET * FROM Meal");   //join ingredients
        //PreparedStatement getCustomMealList = currConn.prepareStatement("GET * FROM CustomMeal");
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Edible currIngredient;

        if(edibleType.equals("meal")) {

        }
        else if(edibleType.equals("drink")) {

        }

        return ingredients;
    }

    public ArrayList<Edible> getDrinkList(Calendar date) {
        return new ArrayList<Edible>();
    }

    public int getNextKey() {
        return 1;
    }

    public void addFoodToRecipeBook(Edible newFood) {
        PreparedStatement addFood = currConn.prepareStatement("INSERT INTO Food (?)");
        PreparedStatement addCustomFood = currConn.prepareStatement("INSERT INTO CustomFood (?)");
        boolean isCustom = newFood.isCustom();
        int edibleID;

        if(isCustom) {
            edibleID = this.addEdible(newFood, true);
            addFood.setInt(1, edibleID);
            addFood.executeQuery();
        }
        else {
            edibleID = this.addEdible(newFood, false);
            addCustomFood.setInt(1, edibleID);
            addCustomFood.executeQuery();
        }
    }

    private int addEdible(Edible newEdible, boolean isCustom) {
        PreparedStatement addEdible = currConn.prepareStatement("INSERT INTO Edible (Name, Description, Quantity, " +
            "Unit, Calories, Protein, Carbs, Fat, Photo, Alcoholic, Spicy, Vegan, Vegetarian, GluteFree) VALUES (" + 
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement addCustomEdible = currConn.prepareStatement("INSERT INTO CustomEdible (Name, Description, " +
            "Quantity, Unit, Calories, Protein, Carbs, Fat, Photo, Alcoholic, Spicy, Vegan, Vegetarian, GluteFree) VALUES (" + 
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ResultSet results;
        int edibleID;

        if(isCustom) {
            addCustomEdible.setString(1, newEdible.getName());
            addCustomEdible.setString(2, newEdible.getDescription());
            addCustomEdible.setInt(3, newEdible.getQuantity());
            addCustomEdible.setString(4, newEdible.getUnit());
            addCustomEdible.setInt(5, newEdible.getCalories());
            addCustomEdible.setInt(6, newEdible.getProtein());
            addCustomEdible.setInt(7, newEdible.getCarbs());
            addCustomEdible.setInt(8, newEdible.getFat());
            addCustomEdible.setBytes(9, newEdible.getPhotoBytes());
            addCustomEdible.setBoolean(10, newEdible.getIsAlcoholic());
            addCustomEdible.setBoolean(11, newEdible.getIsSpicy());
            addCustomEdible.setBoolean(12, newEdible.getIsVegan());
            addCustomEdible.setBoolean(13, newEdible.getIsVegetarian());
            addCustomEdible.setBoolean(14, newEdible.getIsGlutenFree());
            addCustomEdible.executeQuery();
            results.getRowId("CustomEdibleID");
        }
        else {
            addEdible.setString(1, newEdible.getName());
            addEdible.setString(2, newEdible.getDescription());
            addEdible.setInt(3, newEdible.getQuantity());
            addEdible.setString(4, newEdible.getUnit());
            addEdible.setInt(5, newEdible.getCalories());
            addEdible.setInt(6, newEdible.getProtein());
            addEdible.setInt(7, newEdible.getCarbs());
            addEdible.setInt(8, newEdible.getFat());
            addEdible.setBytes(9, newEdible.getPhotoBytes());
            addEdible.setBoolean(10, newEdible.getIsAlcoholic());
            addEdible.setBoolean(11, newEdible.getIsSpicy());
            addEdible.setBoolean(12, newEdible.getIsVegan());
            addEdible.setBoolean(13, newEdible.getIsVegetarian());
            addEdible.setBoolean(14, newEdible.getIsGlutenFree());
            addEdible.executeQuery();
            results.getRowId("EdibleID");
        }

        results.close();

        return edibleID;
    }

    public void addMealToRecipeBook(Meal newMeal) {
        PreparedStatement addMeal = currConn.prepareStatement("INSERT INTO Meal VALUES (?, ?)");
        PreparedStatement addCustomMeal = currConn.prepareStatement("INSERT INTO CustomMeal VALUES (?, ?)");
        PreparedStatement addIngredient = currConn.prepareStatement("INSERT INTO MealIngredient (PreparedID, " +
            "EdibleID, Quantity, Unit), VALUES (?, ?, ?, ?)");
        PreparedStatement addCustomIngredientToMeal = currConn.prepareStatement("INSERT INTO MealIngredient " +
            "(PreparedID, CustomEdibleID, Quantity, Unit), VALUES (?, ?, ?, ?)");
        PreparedStatement addCustomIngredientToCustomMeal = currConn.prepareStatement("INSERT INTO MealIngredient " +
            "(CustomPreparedID, CustomEdibleID, Quantity, Unit), VALUES (?, ?, ?, ?)");

        Meal currMeal;
        Ingredients currIngredients;
        boolean isCustom = newMeal.isCustom();
        boolean edibleID = this.addEdible(newMeal, isCustom);

        if(isCustom) {
            addCustomMeal.setInt(1, edibleID);
            addCustomMeal.setString(2, newMeal.getInstructions());
            addCustomMeal.executeQuery();

            currIngredients = newMeal.getIngredients();
            for(int i = 0; i < currIngredients.size(); i++) {
                currMeal = currIngredients.get(i);

                if(!currMeal.isCustom()) {
                    addCustomIngredientToMeal.setInt(1, edibleID);
                    addCustomIngredientToMeal.setInt(2, currMeal.getDBKey());
                    addCustomIngredientToMeal.setInt(3, currMeal.getQuantity());
                    addCustomIngredientToMeal.setString(4, currMeal.getUnit());
                    addCustomIngredientToMeal.executeQuery();
                }
                else {
                    addCustomIngredientToCustomMeal.setInt(1, edibleID);
                    addCustomIngredientToCustomMeal.setInt(2, currMeal.getDBKey());
                    addCustomIngredientToCustomMeal.setInt(3, currMeal.getQuantity());
                    addCustomIngredientToCustomMeal.setString(4, currMeal.getUnit());
                    addCustomIngredientToCustomMeal.executeQuery();
                }
            }
        }
        else {
            addMeal.setInt(1, edibleID);
            addMeal.setString(2, newMeal.getInstructions());
            addMeal.executeQuery();

            currIngredients = newMeal.getIngredients();
            for(int i = 0; i < currIngredients.size(); i++) {
                currMeal = currIngredients.get(i);

                addIngredient.setInt(1, edibleID);
                addIngredient.setInt(2, currMeal.getDBKey());
                addIngredient.setInt(3, currMeal.getQuantity());
                addIngredient.setString(4, currMeal.getUnit());
                addIngredient.executeQuery();
            }
        }
    }

    public void addDrinkToRecipeBook(Drink newDrink) {
        PreparedStatement addDrink = currConn.prepareStatement("INSERT INTO Drink VALUES (?, ?)");
        PreparedStatement addCustomDrink = currConn.prepareStatement("INSERT INTO CustomDrink VALUES (?, ?)");
        PreparedStatement addIngredient = currConn.prepareStatement("INSERT INTO DrinkIngredient (PreparedID, " +
            "EdibleID, Quantity, Unit, Substitute), VALUES (?, ?, ?, ?, ?)");
        PreparedStatement addCustomIngredientToDrink = currConn.prepareStatement("INSERT INTO DrinkIngredient " +
            "(PreparedID, CustomEdibleID, Quantity, Unit, Substitute), VALUES (?, ?, ?, ?, ?)");
        PreparedStatement addCustomIngredientToCustomDrink = currConn.prepareStatement("INSERT INTO DrinkIngredient " +
            "(CustomPreparedID, CustomEdibleID, Quantity, Unit, Substitute), VALUES (?, ?, ?, ?, ?)");

        Drink currDrink;
        Ingredients currIngredients;
        boolean isCustom = currMeal.isCustom();
        boolean edibleID = this.addEdible(currDrink, isCustom);

        if(isCustom) {
            addCustomDrink.setInt(1, edibleID);
            addCustomDrink.setString(2, newDrink.getInstructions());
            addCustomDrink.executeQuery();

            currIngredients = newDrink.getIngredients();
            for(int i = 0; i < currIngredients.size(); i++) {
                currDrink = currIngredients.get(i);

                if(!currDrink.isCustom()) {
                    addCustomIngredientToDrink.setInt(1, edibleID);
                    addCustomIngredientToDrink.setInt(2, currDrink.getDBKey());
                    addCustomIngredientToDrink.setInt(3, currDrink.getQuantity());
                    addCustomIngredientToDrink.setString(4, currDrink.getUnit());
                    addCustomIngredientToDrink.setString(5, currDrink.getIsSubstitute());
                    addCustomIngredientToDrink.executeQuery();
                }
                else {
                    addCustomIngredientToCustomDrink.setInt(1, edibleID);
                    addCustomIngredientToCustomDrink.setInt(2, currDrink.getDBKey());
                    addCustomIngredientToCustomDrink.setInt(3, currDrink.getQuantity());
                    addCustomIngredientToCustomDrink.setString(4, currDrink.getUnit());
                    addCustomIngredientToCustomDrink.setString(5, currDrink.getIsSubstitute());
                    addCustomIngredientToCustomDrink.executeQuery();
                }
            }
        }
        else {
            addDrink.setInt(1, edibleID);
            addDrink.setString(2, newDrink.getInstructions());
            addDrink.executeQuery();

            currIngredients = newDrink.getIngredients();
            for(int i = 0; i < currIngredients.size(); i++) {
                currDrink = currIngredients.get(i);

                addIngredient.setInt(1, edibleID);
                addIngredient.setInt(2, currDrink.getDBKey());
                addIngredient.setInt(3, currDrink.getQuantity());
                addIngredient.setString(4, currDrink.getUnit());
                addIngredient.setString(5, currDrink.getIsSubstitute());
                addIngredient.executeQuery();
            }
        }
    }

    public DailyLog searchFoodLogByDate(int userID, Calendar date) {
        PreparedStatement findLog = currConn.prepareStatement("GET * FROM History, WHERE UserID = ? AND Date = ?");
        ResultSet results;
        DailyLog log = null;
        int exerciseActual;
        ArrayList<Edible> edibleLog;

        findLog.setInt(1, userID);
        findLog.setDate(2, date);
        results = findLog.executeQuery();

        if(results.next()) {
            edibleLog = this.getEdibleLog(results.getInt("HistoryID"));
            exerciseActual = this.getExerciseActual(results.getInt("HistoryID"));
            log = new DailyLog().init(date, edibleLog, results.getInt("CalorieGoal"), userID, results.getInt("ExerciseGoal"), 
                exerciseActual);
        }

        return log;
    }

    private ArrayList<Edible> getEdibleLog(int historyID) {
        PreparedStatement findLog = currConn.prepareStatement("GET * FROM EdibleHistory, HistoryID = ?");
        ResultSet results;
        ArrayList<Edible> edibleLog = new ArrayList<Edible>();

        findLog.setInt(1, historyID);
        results = findLog.executeQuery();

        while(results.next()) {
            //do a thing to pass foods/drinks/meals
        }

        return edibleLog;
    }

    private int getExerciseActual(int HistoryID) {
        PreparedStatement getExerciseActual = currConn.prepareStatement("GET * FROM WorkoutHistory, WHERE HistoryID = ?");
        int exerciseActual = 0;
        ResultSet results;

        getExerciseActual.setInt(1, HistoryID);
        results = getExerciseActual.executeQuery();

        if(results.next()) {
            exerciseActual = results.getInt("ExerciseActual");
        }
        results.close();

        return exerciseActual;
    }

    public Edible findEdibleByKey(int dbkey, boolean isCustom) {
        PreparedStatement addCustomEdibleHistory = currConn.prepareStatement("INSERT INTO EdibleHistory (HistoryID, " +
        "CustomEdibleID, Quantity, Unit) VALUES (?, ?, ?, ?)");
//look for in drink, meal, food and custom stuff
    Edible currEdible;
    ResultSet results;
        return null;
    }

    public void addLog(DailyLog newLog) {
        PreparedStatement addHistory = currConn.prepareStatement("INSERT INTO History (UserID, Date, CalorieGoal, " +
            "CalorieActual) VALUES (?, ?, ?, 0)");
        PreparedStatement addEdibleHistory = currConn.prepareStatement("INSERT INTO EdibleHistory (HistoryID, " +
            "EdibleID, Quantity, Unit) VALUES (?, ?, ?, ?)");
        PreparedStatement addCustomEdibleHistory = currConn.prepareStatement("INSERT INTO EdibleHistory (HistoryID, " +
            "CustomEdibleID, Quantity, Unit) VALUES (?, ?, ?, ?)");

        ArrayList<Edible> edibles = newLog.getEdibleList();
        Edible currEdible;
        ResultSet results;
        int historyID;

        addHistory.setInt(1, currLog.getUserID());
        addHistory.setDate(2, currLog.getDate());
        addHistory.setInt(3, currLog.getCalorieGoal());
        results = addHistory.executeQuery();
        historyID = results.getRowId("HistoryID");
        results.close();
        
        for(int i = 0; i < edibles.size(); i++) {
            currEdible = edibles.get(i);

            if(currEdible.isCustom()) {
                addCustomEdibleHistory.setInt(historyID);
                addCustomEdibleHistory.setInt(currEdible.getDBKey());
                addCustomEdibleHistory.setInt(currEdible.getQuantity());
                addCustomEdibleHistory.setString(currEdible.getUnit());
                addCustomEdibleHistory.executeQuery();
            }
            else {
                addEdibleHistory.setInt(historyID);
                addEdibleHistory.setInt(currEdible.getDBKey());
                addEdibleHistory.setInt(currEdible.getQuantity());
                addEdibleHistory.setInt(currEdible.getUnit());
                addEdibleHistory.executeQuery();
            }
        }

        if(newLog.getExerciseActual() != 0) {
            this.setExerciseActual(newLog, newLog.getExerciseActual());
        }
    }

    public void deleteLog(DailyLog delLog) {
        PreparedStatement setExerciseActual = currConn.prepareStatement("DELETE History WHERE Date = ? AND UserID = ?");

        setExerciseActual.setDate(1, currLog.getDate());
        setExerciseActual.setInt(1, currLog.getUserID());
        setExerciseActual.executeQuery();
    }

    public void setExerciseActual(DailyLog currLog, int newExercise) {
        int logID = getHistoryID(currLog);
        PreparedStatement setExerciseActual = currConn.prepareStatement("CASE " +
            "WHEN COUNT(SELECT * FROM WorkoutHistory WHERE HistoryID = ?) = 0 THEN INSERT INTO WorkoutHistory VALUES (?, ?) " +
            "ELSE UPDATE WorkoutHistory SET ExerciseActual = ?, WHERE HistoryID = ?"); 

        setExerciseActual.setInt(1, logID);
        setExerciseActual.setInt(2, logID);
        setExerciseActual.setInt(3, newExercise);
        setExerciseActual.setInt(4, newExercise);
        setExerciseActual.setInt(5, logID);
        setExerciseActual.executeQuery();
    }

    private int getHistoryID(DailyLog currLog) {
        PreparedStatement getHistoryID = currConn.prepareStatement("GET HistoryID FROM History WHERE UserID = ? AND Date = ?");
        ResultSet results;

        setExerciseActual.setDate(1, currLog.getDate());
        setExerciseActual.setInt(1, currLog.getUserID());
        results = setExerciseActual.executeQuery();
        results.next();

        return results.getInt("HistoryID");
    }


    public void addUser(String name, int height, int weight) {
        PreparedStatement addUser = currConn.prepareStatement("INSERT * INTO User (Name, Height, Weight) VALUES (?, ?, ?)");

        addUser.setString(1, name);
        addUser.setInt(1, height);
        addUser.setInt(1, weight);
        addUser.executeQuery();
    }

    public User getUser() {
        PreparedStatement getUser = currConn.prepareStatement("GET * FROM User");
        ResultSet results = getUser.executeQuery();
        User currUser;

        int userID, height, weight, calorieGoal, exerciseGoal;
        String name;

        results.next();
        currUser = new User(results.getInt("UserID"), results.getString("Name"), results.getInt("Height"),  
        results.getInt("Weight"), results.getInt("CalorieGoal"), results.getInt("ExerciseGoal"));


        return currUser;
    }

    public void setHeight(int userID, int newHeight) {
        PreparedStatement setHeight = currConn.prepareStatement("Update User SET Height = ?, WHERE UserID = ?");
        
        setHeight.setInt(1, newHeight);
        setHeight.setInt(2, userID);
        setHeight.executeQuery();
    }

    public void setWeight(int userID, int newWeight) {
        PreparedStatement setWeight = currConn.prepareStatement("Update User SET Weight = ?, WHERE UserID = ?");
        
        setWeight.setInt(1, newWeight);
        setWeight.setInt(2, userID);
        setWeight.executeQuery();
    }

    public void setCalorieGoal(int userID, int goal, Calendar date) {
        PreparedStatement setCalorieGoal = currConn.prepareStatement("UPDATE History SET CalorieGoal = ? " +
            "WHERE UserID = ? AND Date >= ?");
            
        setCalorieGoal.setInt(1, goal);
        setCalorieGoal.setInt(2, userID);
        setCalorieGoal.setDate(2, date);
        setCalorieGoal.executeQuery();
    }

    public void setExerciseGoal(int userID, int goal, Calendar date) {
        PreparedStatement setExerciseGoal = currConn.prepareStatement("UPDATE History SET ExerciseGoal = ? " +
            "WHERE UserID = ? AND Date >= ?");

        setExerciseGoal.setInt(1, goal);
        setExerciseGoal.setInt(2, userID);
        setExerciseGoal.setDate(2, date);
        setExerciseGoal.executeQuery();
    }

    private void seedDB() { //just do this easy in 
        SQLiteDatabase db = this.getReadableDatabase();
        
        //Used to add data to each respective table
        ContentValues edible = new ContentValues();
        ContentValues customEdible = new ContentValues();
        ContentValues user = new ContentValues();
        ContentValues history = new ContentValues();
        ContentValues workoutHistory = new ContentValues();
        ContentValues edibleHistory = new ContentValues();
        ContentValues food = new ContentValues();
        ContentValues meal = new ContentValues();
        ContentValues drink = new ContentValues();
        ContentValues customFood = new ContentValues();
        ContentValues customMeal = new ContentValues();
        ContentValues ingredient = new ContentValues();
        ContentValues drinkIngredient = new ContentValues();

        //The saved ID's of seeded entries (for later use as foreign keys)
        long pineappleID, mushroomID, eggID, walnutID, milkID, onionID, wannabePinaColadaID, scrambledEggsID;
        long pickleID, scrambledEggsWithPickleID;
        long userID;
        long firstDayHistoryID, secondDayHistoryID;
        

        //creates new edible entries then add them to the DB
        edible.put("Name", "Pineapple");
        edible.put("Quantity", 79);
        edible.put("Unit", "oz");
        edible.put("Calories", 10);
        edible.put("Protein", 20);
        edible.put("Carbs", 30);
        edible.put("Fat", 30);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", true);
        edible.put("IsVegetarian", true);
        edible.put("IsGlutenFree", true);
        pineappleID = db.insert("Edible", null, edible);

        edible.put("Name", "Mushroom");
        edible.put("Quantity", 20);
        edible.put("Unit", "ml");
        edible.put("Calories", 5);
        edible.put("Protein", 200);
        edible.put("Carbs", 31);
        edible.put("Fat", 30);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", true);
        mushroomID = db.insert("Edible", null, edible);

        edible.put("Name", "Egg");
        edible.put("Quantity", 9);
        edible.put("Unit", "oz");
        edible.put("Calories", 15);
        edible.put("Protein", 10);
        edible.put("Carbs", 10);
        edible.put("Fat", 10);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        eggID = db.insert("Edible", null, edible);

        edible.put("Name", "Walnut");
        edible.put("Quantity", 93);
        edible.put("Unit", "serving");
        edible.put("Calories", 43);
        edible.put("Protein", 2);
        edible.put("Carbs", 2);
        edible.put("Fat", 3);
        edible.put("IsAlcoholic", true);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        walnutID = db.insert("Edible", null, edible);

        edible.put("Name", "Milk");
        edible.put("Quantity", 10);
        edible.put("Unit", "cups");
        edible.put("Calories", 40);
        edible.put("Protein", 100);
        edible.put("Carbs", 2);
        edible.put("Fat", 3);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        milkID = db.insert("Edible", null, edible);

        edible.put("Name", "Onion");
        edible.put("Quantity", 10);
        edible.put("Unit", "g");
        edible.put("Calories", 2);
        edible.put("Protein", 1);
        edible.put("Carbs", 1);
        edible.put("Fat", 1);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        onionID = db.insert("Edible", null, edible);

        edible.put("Name", "Wannabe Pina-Colada");
        edible.put("Quantity", 10);
        edible.put("Unit", "g");
        edible.put("Calories", 2);
        edible.put("Protein", 1);
        edible.put("Carbs", 1);
        edible.put("Fat", 1);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        wannabePinaColadaID = db.insert("Edible", null, edible);

        edible.put("Name", "Scrambled Eggs");
        edible.put("Quantity", 10);
        edible.put("Unit", "g");
        edible.put("Calories", 20);
        edible.put("Protein", 60);
        edible.put("Carbs", 1);
        edible.put("Fat", 1);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        scrambledEggsID = db.insert("Edible", null, edible);


        //creates customEdibles then adds them to the DB
        customEdible.put("Name", "Pickle");
        customEdible.put("Quantity", 11);
        customEdible.put("Unit", "g");
        customEdible.put("Calories", 5);
        customEdible.put("Protein", 5);
        customEdible.put("Carbs", 5);
        customEdible.put("Fat", 5);
        customEdible.put("IsAlcoholic", false);
        customEdible.put("IsSpicy", false);
        customEdible.put("IsVegan", false);
        customEdible.put("IsVegetarian", true);
        customEdible.put("IsGlutenFree", true);
        pickleID = db.insert("CustomEdible", null, customEdible);

        customEdible.put("Name", "Scrambled Eggs With A Pickle");
        customEdible.put("Quantity", 10);
        customEdible.put("Unit", "g");
        customEdible.put("Calories", 25);
        customEdible.put("Protein", 65);
        customEdible.put("Carbs", 1);
        customEdible.put("Fat", 1);
        customEdible.put("IsAlcoholic", false);
        customEdible.put("IsSpicy", false);
        customEdible.put("IsVegan", false);
        customEdible.put("IsVegetarian", false);
        customEdible.put("IsGlutenFree", false);
        scrambledEggsWithPickleID = db.insert("CustomEdible", null, customEdible);
        
        
        //creates new user then adds the user to the database
        user.put("Name", "BestUser");
        user.put("Height", 190);  //cm
        user.put("Weight", 160);  //pounds
        user.put("CalorieGoal", 4000);
        user.put("ExerciseGoal", 100);
        userID = db.insert("User", null, user);


        //creates history for the newly added user and adds it to the database
        history.put("UserID", userID);
        history.put("Date", Calendar.getInstance().toString());
        history.put("CalorieGoal", 5000);
        history.put("CalorieActual", 6000);
        firstDayHistoryID = db.insert("History", null, history);
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1); //incrase the day by 1

        history.put("UserID", userID);
        history.put("Date", calendar.getTime().toString());
        history.put("CalorieGoal", 4000);
        history.put("CalorieActual", -1000);
        secondDayHistoryID = db.insert("History", null, history);


        //creates workout history for the newly added user and adds it to the database
        workoutHistory.put("HistoryID", firstDayHistoryID);
        workoutHistory.put("ExerciseActual", 100);
        db.insert("WorkoutHistory", null, workoutHistory);

        workoutHistory.put("HistoryID", secondDayHistoryID);
        workoutHistory.put("ExerciseActual", 1000);
        db.insert("WorkoutHistory", null, workoutHistory);


        //creates edible history for the newly added user and adds it to the database
        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", pineappleID);
        edibleHistory.put("Quantity", 1);
        edibleHistory.put("Unit", "cups");
        db.insert("EdibleHistory", null, edibleHistory);

        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", walnutID);
        edibleHistory.put("Quantity", 2);
        edibleHistory.put("Unit", "serving");
        db.insert("EdibleHistory", null, edibleHistory);
        
        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", wannabePinaColadaID);
        edibleHistory.put("Quantity", 3);
        edibleHistory.put("Unit", "serving");
        db.insert("EdibleHistory", null, edibleHistory);

        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", scrambledEggsWithPickleID);
        edibleHistory.put("Quantity", 10);
        edibleHistory.put("Unit", "g");
        db.insert("EdibleHistory", null, edibleHistory);


        //fills food table out with already created edibles
        food.put("EdibleID", pineappleID);
        db.insert("Food", null, food);

        food.put("EdibleID", mushroomID);
        db.insert("Food", null, food);

        food.put("EdibleID", eggID);
        db.insert("Food", null, food);

        food.put("EdibleID", walnutID);
        db.insert("Food", null, food);

        food.put("EdibleID", onionID);
        db.insert("Food", null, food);


        //fills meal table out with already created edibles
        meal.put("EdibleID", scrambledEggsID); 
        meal.put("Instructions", "git guud");
        db.insert("Meal", null, meal);


        //fills drink table out with already created edibles
        drink.put("EdibleID", wannabePinaColadaID); 
        drink.put("Instructions", "git guud");
        db.insert("Drink", null, drink);


        //fills custom food table out with already created edibles
        customFood.put("EdibleID", pickleID); 
        db.insert("CustomFood", null, customFood);


        //fills custom food table out with already created edibles
        customMeal.put("EdibleID", scrambledEggsWithPickleID); 
        drink.put("Instructions", "this one is different!");
        db.insert("CustomFood", null, customMeal);


        //fills ingredient table out with already created edibles   
        ingredient.put("PreparedID", scrambledEggsID);
        ingredient.put("EdibleID", eggID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient.put("PreparedID", scrambledEggsID);
        ingredient.put("EdibleID", onionID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient = new ContentValues();
        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
        ingredient.put("EdibleID", eggID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient = new ContentValues();
        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
        ingredient.put("EdibleID", onionID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient = new ContentValues();
        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
        ingredient.put("CustomEdibleID", pickleID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        
        //fills ingredient table out with already created edibles   
        drinkIngredient.put("PreparedID", wannabePinaColadaID);
        drinkIngredient.put("EdibleID", milkID);
        drinkIngredient.put("Quantity", 5);
        drinkIngredient.put("Unit", "ml");
        db.insert("DrinkIngredient", null, drinkIngredient);

        drinkIngredient.put("PreparedID", wannabePinaColadaID);
        drinkIngredient.put("EdibleID", pineappleID);
        drinkIngredient.put("Quantity", 10);
        drinkIngredient.put("Unit", "ml");
        db.insert("DrinkIngredient", null, drinkIngredient);
    }
}