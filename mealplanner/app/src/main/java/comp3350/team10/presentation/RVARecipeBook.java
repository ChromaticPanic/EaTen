package comp3350.team10.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;


public class RVARecipeBook extends RecyclerViewAdapter {
    private FragToRecipeBook sendToRecipeBook;          // interface to pass data to recipebook

    public RVARecipeBook(ArrayList<Edible> dataSet) {
        super(dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewHolder viewHolder = null;
        Context context = null;
        View view = null;

        if (viewType == FragmentType.recipeModify.ordinal())
        { 
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card_context, viewGroup, false);
        } else {
            
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card, viewGroup, false);
                
        }

        context = view.getContext();
        if (context instanceof FragToRecipeBook) {
            this.sendToRecipeBook = (FragToRecipeBook) context;
        }

        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        if (super.getViewType() == FragmentType.recipeModify.ordinal())
        { 
                setCardSelectionListeners(viewHolder, position);
            } else {
                setRecipeData(viewHolder, position);
                setCardListeners(viewHolder, position);
                
        }
    }


    private void setCardListeners(ViewHolder viewHolder, int position) {
        viewHolder.getView().findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendToRecipeBook != null) {
                    sendToRecipeBook.showContextUI(position);
                }
            }
        });
    }

    private void setRecipeData(ViewHolder viewHolder, int position) {
        //ImageView itemImage = viewHolder.getView().findViewById(R.id.mealImage);
        TextView textDesc = viewHolder.getView().findViewById(R.id.mealDesc);
        TextView mealCalories = viewHolder.getView().findViewById(R.id.mealCals);

        Edible currentItem = getDataSet().get(position);
        Edible currentFood;

        if(currentItem instanceof Edible) {
            currentFood = (Edible)currentItem;
            //itemImage.setImageResource(currentFood.getIconPath());
            textDesc.setText(currentFood.getName());
            mealCalories.setText(Integer.toString(currentFood.getCalories()));
        }
    }

    private void setCardSelectionListeners(ViewHolder viewHolder, int position) {
        Button viewButton = viewHolder.getView().findViewById(R.id.viewBtn2);
        Button backButton = viewHolder.getView().findViewById(R.id.btnBackRecipe);
        Button addButton = viewHolder.getView().findViewById(R.id.addToPlannerBtn2);
        ImageView addIcon = viewHolder.getView().findViewById(R.id.addIcon);
        String launcher = "";

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendToRecipeBook != null) {
                    sendToRecipeBook.addToMealDiary();
                }
            }
        });

        if (this.sendToRecipeBook != null) {

            launcher = this.sendToRecipeBook.getIntentExtra("Source");
            if (launcher != null && launcher.equals("NAV")) {
                viewHolder.getView().findViewById(R.id.addToPlannerBtn2).setVisibility(View.GONE);
                viewHolder.getView().findViewById(R.id.addIcon).setVisibility(View.GONE);
            }
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToRecipeBook != null) {
                    sendToRecipeBook.showContextUI(position);
                }
            }
        });
    }
}