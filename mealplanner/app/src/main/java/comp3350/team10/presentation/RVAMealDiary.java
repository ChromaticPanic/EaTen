package comp3350.team10.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;


public class RVAMealDiary extends RecyclerViewAdapter {
    private FragToMealDiary sendToMealDiary; // interface to pass data to mealdiary
    private Context context;

    public RVAMealDiary(ArrayList<Edible> dataSet) {
        super(dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        ViewHolder viewHolder = null;
        this.context = viewGroup.getContext();

        if (viewType == FragmentType.diaryModify.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.fragment_diary_card_context, viewGroup, false);
        } else if (viewType == FragmentType.diaryAdd.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.fragment_diary_add_log, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card,
                    viewGroup, false);
        }

        context = view.getContext();
        if (context instanceof FragToMealDiary) {
            this.sendToMealDiary = (FragToMealDiary) context;
        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (super.getViewType() == FragmentType.diaryModify.ordinal()) {
            setDiaryContextListeners(viewHolder);
        } else if (super.getViewType() == FragmentType.diaryAdd.ordinal()) {
            setDiaryAddListeners(viewHolder);
        } else {
            setDiaryEntryData(viewHolder, position);
            setDiaryEntryListeners(viewHolder);
        }
    }


    private void setDiaryEntryData(ViewHolder viewHolder, final int position) {
        TextView itemName = viewHolder.getView().findViewById(R.id.itemNameBox);
        TextView itemQty = viewHolder.getView().findViewById(R.id.itemQtyBox);
        TextView itemUnit = viewHolder.getView().findViewById(R.id.itemUnitBox);
        TextView itemCals = viewHolder.getView().findViewById(R.id.itemCalsBox);
        ImageView itemImage = viewHolder.getView().findViewById(R.id.itemImage);
        Edible currentItem = super.getDataSet().get(position);
        Bitmap image = null;

        itemName.setText(currentItem.getName());
        itemQty.setText(String.format("%3.2f", currentItem.getQuantity()));
        itemUnit.setText(currentItem.getUnit().toString());
        itemCals.setText(String.format("%3d", (int) currentItem.getCalories()));
        image = super.getBitmapFromFile(this.context, currentItem.getPhoto());

        if (image != null) {
            itemImage.setImageBitmap(image);
        } else {
            itemImage.setImageResource(R.drawable.ic_eggplant);
        }
    }

    private void setDiaryEntryListeners(ViewHolder viewHolder) {
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToMealDiary != null) {
                    sendToMealDiary.showContextUI(position);
                }
            }
        });
    }

    private void setDiaryContextListeners(ViewHolder viewHolder) {
        viewHolder.getView().findViewById(R.id.btnBackMealLog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToMealDiary != null) {
                            sendToMealDiary.showContextUI(position);
                        }
                    }
                });

        viewHolder.getView().findViewById(R.id.btnDeleteMeal)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToMealDiary != null) {
                            sendToMealDiary.removeItem(position);
                        }
                    }
                });

        viewHolder.getView().findViewById(R.id.btnModifyMeal)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (sendToMealDiary != null) {
                            sendToMealDiary.editItem();
                        }
                    }
                });
    }

    private void setDiaryAddListeners(ViewHolder viewHolder) {
        viewHolder.getView().findViewById(R.id.btnAddMeal)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToMealDiary != null) {
                            sendToMealDiary.addEntry(position);
                        }
                    }
                });
    }
}
