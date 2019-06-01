package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView main_name;
    TextView also_knows_as;
    TextView Place_of_origin;
    TextView ingeridents;
    TextView discription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        main_name = findViewById(R.id.main_name);
        also_knows_as = findViewById(R.id.also_known_tv);
        Place_of_origin = findViewById(R.id.origin_tv);
        ingeridents = findViewById(R.id.ingredients_tv);
        discription = findViewById(R.id.description_tv);


        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage()).placeholder(R.mipmap.ic_launcher_round)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        main_name.setText(sandwich.getMainName());
        Place_of_origin.setText(sandwich.getPlaceOfOrigin());
        discription.setText(sandwich.getDescription());

        List<String> ing = sandwich.getIngredients();
        String ingerident=ing.get(0);
        for(int i=1;i<ing.size();i++)
        {
            ingerident=ingerident+ing.get(i);
        }


        List<String> aka = sandwich.getIngredients();
        String also = aka.get(0);
        for(int i=1;i<aka.size();i++)
        {
            also=also+aka.get(i);
        }
        also_knows_as.setText(also);
        ingeridents.setText(ingerident);


    }
}
