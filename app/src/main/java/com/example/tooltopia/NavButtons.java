package com.example.tooltopia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class NavButtons {

    public static void createButton(int buttonID, final Class<?> target, final Context context) {
        Button button = ((Activity) context).findViewById(buttonID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, target);
                context.startActivity(intent);
            }
        });
    }

}