package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.DataAccessStub;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
//import java.time.LocalDate;

public class MealDiaryOps {
    MealDiaryLiveData mealDiaryLiveData;
    Calendar dataDate;

    public MealDiaryOps(MealDiaryLiveData mealDiaryLiveData){
        this.mealDiaryLiveData = mealDiaryLiveData;
        this.dataDate = Calendar.getInstance();
    }

    public void init(){
        DataAccessStub db = new DataAccessStub();
        db.open("someDB");
        ArrayList<DiaryItem> dbFetch = db.getToday();

        mealDiaryLiveData.getActivityDate().setValue(dataDate);
    }

    public void getData(){

    }

    public void nextDate(){
        dataDate.add(Calendar.DAY_OF_YEAR, 1);
    }

    public void prevDate(){
        dataDate.add(Calendar.DAY_OF_YEAR, -1);
    }

    public void setDataDate(Calendar newDate){
        dataDate = newDate;
    }
}
