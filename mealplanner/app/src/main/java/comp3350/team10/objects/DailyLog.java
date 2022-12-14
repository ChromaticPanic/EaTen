package comp3350.team10.objects;

import java.util.ArrayList;
import java.util.Calendar;

public class DailyLog {
    private final static double MAX_PROGRESS = 100;     //Scales the progress bar (percentage)
    private final static double MAX_EXCESS = 100;       //How many calories a user can exceed their goal by
    private Calendar date;                              //The date of this particular log
    private ArrayList<Edible> edibleLog;                //The edibles present in the given log for a given date
    private double calorieGoal;                         //The calorie goal for a given date
    private double exerciseGoal;                        //The calorie goal for exercising for a given date
    private double exerciseActual;                      //The calories burnt while exercising for a given date
    private double edibleCalories;                      //The calorie total for a list of edibles for a given date
    private double progressExcess;                      //Represents the overflow of calories on the progress bar
    private double progressBar;                         //Represents the calories on the progress bar
    private double calorieNet;                          //Represents the consumed calories - exercise calories burnt

    public DailyLog() {
        this.date = Calendar.getInstance();
        this.edibleLog = null;
        this.calorieGoal = -1;
        this.exerciseGoal = -1;
        this.exerciseActual = 0;
        this.edibleCalories = 0;
        this.progressExcess = -1;
        this.progressBar = -1;
        this.calorieNet = -1;
    }


    public DailyLog init(Calendar date, ArrayList<Edible> log, double calGoal, double excGoal, double excActual) throws IllegalArgumentException, NullPointerException {
        try {
            this.setDate(date);
            this.setEdibleList(log);
            this.setCalorieGoal(calGoal);
            this.setExerciseGoal(excGoal);
            this.setExerciseActual(excActual);
            this.updateProgress();
        } catch (Exception e) {
            throw e;
        }
        return this;
    }

    public DailyLog clone() {
        DailyLog copy = new DailyLog();
        copy.init(this.date, getEdibleList(), this.calorieGoal, this.exerciseGoal, this.getExerciseActual());

        return copy;
    }

    private void setDate(Calendar date) throws NullPointerException {
        if (date != null) {
            this.date = date;
        } else {
            throw new NullPointerException("DailyLog setDate cannot be null: DailyLog - setDate");
        }
    }

    private void setEdibleList(ArrayList<Edible> newLog) throws IllegalArgumentException {
        Validator.validArrayListNoNull(newLog, "DailyLog - setEdibleList");
        this.edibleLog = newLog;
        this.updateProgress();
    }

    public void setExerciseActual(double newExerciseActual) throws IllegalArgumentException {
        Validator.atLeastZero(newExerciseActual, "DailyLog - setExerciseActual");
        this.exerciseActual = newExerciseActual;
        this.updateProgress();
    }

    public void setCalorieGoal(double newCalorieGoal) throws IllegalArgumentException {
        Validator.atLeastZero(newCalorieGoal, "DailyLog - setCalorieGoal");
        this.calorieGoal = newCalorieGoal;
        this.updateProgress();
    }

    public void setExerciseGoal(double newExerciseGoal) throws IllegalArgumentException {
        Validator.atLeastZero(newExerciseGoal, "DailyLog - setExerciseGoal");
        this.exerciseGoal = newExerciseGoal;
    }

    public void addEdibleToLog(int pos, Edible newEdible) throws NullPointerException {
        if (newEdible != null) {
            if (pos >= 0 && pos < edibleLog.size()) {
                this.edibleLog.add(pos, newEdible);
            } else {
                this.edibleLog.add(newEdible);
            }

            this.updateProgress();
        } else {
            throw new NullPointerException("DailyLog AddEdibleToLog requires an initialized Edible object: DailyLog - addEdibleToLog");
        }
    }

    private void calculateEdibleCalories() {
        double calculatedCalories = 0;

        for (int i = 0; i < this.edibleLog.size(); i++) {
            calculatedCalories += this.edibleLog.get(i).getCalories();
        }

        this.edibleCalories = calculatedCalories;

    }

    public void updateProgress() {
        this.calculateEdibleCalories();
        this.netCalories();
        this.calcProgress();
    }

    private void calcProgress() {
        if (this.calorieNet > 0) {
            this.progressExcess = 0;
            this.setNormalProgress();
        } else {
            this.progressBar = MAX_PROGRESS;
            this.setExcessProgress();
        }
        if (this.progressBar < 0) {
            this.progressBar = 0;
        }
    }

    private void setNormalProgress() {
        if (this.calorieGoal > 0) {
            this.progressBar = (this.calorieGoal - this.calorieNet) * MAX_PROGRESS / this.calorieGoal;
        } else {
            this.progressBar = 0;
        }

    }

    private void setExcessProgress() {
        if (this.calorieGoal > 0) {
            this.progressExcess = -this.calorieNet * MAX_PROGRESS / this.calorieGoal;
        } else {
            this.progressExcess = MAX_EXCESS;
        }

        if (this.progressExcess > MAX_EXCESS) {
            this.progressExcess = MAX_EXCESS;
        }
    }

    private void netCalories() {
        this.calorieNet = this.calorieGoal - (this.edibleCalories - this.exerciseActual);
    }

    public void removeItem(int pos) throws IllegalArgumentException {
        if (pos >= 0 && pos < this.edibleLog.size()) {
            this.edibleLog.remove(pos);
            this.updateProgress();
        } else {
            throw new IllegalArgumentException("DailyLog removeItem position specified is out of bounds: DailyLog - removeItem");
        }
    }

    public ArrayList<Edible> getEdibleList() {
        ArrayList<Edible> arrayCopy = null;
        Edible copy = null;
        if (this.edibleLog != null) {
            arrayCopy = new ArrayList<Edible>();
            for (int i = 0; i < this.edibleLog.size(); i++) {
                copy = this.edibleLog.get(i).clone();
                arrayCopy.add(copy);
            }
        }
        return arrayCopy;

    }

    public double getExerciseActual() {
        return this.exerciseActual;
    }

    public double getExerciseGoal() {
        return this.exerciseGoal;
    }

    public double getCalorieGoal() {
        return this.calorieGoal;
    }

    public double getEdibleCalories() {
        return this.edibleCalories;
    }

    public Calendar getDate() {
        return this.date;
    }

    public double getCalorieNet() {
        return this.calorieNet;
    }

    public double getProgressBar() {
        return this.progressBar;
    }

    public double getProgressExcess() {
        return this.progressExcess;
    }
}