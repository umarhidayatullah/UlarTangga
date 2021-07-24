package com.example.ulartangga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    MaterialCardView[][] tiles = new MaterialCardView[5][5];
    ImageView[][] player = new ImageView[5][5];
    Button btnUp, btnDown, btnLeft, btnRight;
    int[][] arr = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
    int[] pointer = {0, 0}; //x, y
    int[] temp = {0,0}; //tempX, tempY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnUp = findViewById(R.id.btn_up);
        btnDown = findViewById(R.id.btn_bot);
        btnLeft = findViewById(R.id.btn_left);
        btnRight = findViewById(R.id.btn_right);
        varInit();
        gameStart();
        btnUp.setOnClickListener(v -> {
            if (pointer[1] > 0) {
                System.arraycopy(pointer, 0, temp, 0, pointer.length);
                pointer[1]--;
                setArray();
                setColor();
                SetGambar(temp, pointer);
            }
        });

        btnDown.setOnClickListener(v -> {
            if (pointer[1] < 4) {
                System.arraycopy(pointer, 0, temp, 0, pointer.length);
                pointer[1]++;
                setArray();
                setColor();
                SetGambar(temp, pointer);
            }
        });

        btnLeft.setOnClickListener(v -> {
            if (pointer[0] > 0) {
                System.arraycopy(pointer, 0, temp, 0, pointer.length);
                pointer[0]--;
                setArray();
                setColor();
                SetGambar(temp, pointer);
            }
        });

        btnRight.setOnClickListener(v -> {
            if (pointer[0] < 4) {
                System.arraycopy(pointer, 0, temp, 0, pointer.length);
                pointer[0]++;
                setArray();
                setColor();
                SetGambar(temp, pointer);
            }
        });


    }

    private void SetGambar(int[] temp, int[] pointer) {
        player[temp[1]][temp[0]].setVisibility(View.GONE);
        player[pointer[1]][pointer[0]].setVisibility(View.VISIBLE);
    }

    private void setArray() {
        if (arr[pointer[1]][pointer[0]] == 0) arr[pointer[1]][pointer[0]]++;
        else if (arr[pointer[1]][pointer[0]] == 1) arr[pointer[1]][pointer[0]]--;
    }

    private void gameStart() {
        System.arraycopy(pointer, 0, temp, 0, pointer.length);
        setArray();
        setColor();
        SetGambar(temp, pointer);

        String message = "Game Start! \n Start at " + pointer[0] + " " + pointer[1];
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setColor() {
        if (arr[pointer[1]][pointer[0]] == 1)
            tiles[pointer[1]][pointer[0]].setCardBackgroundColor(getResources().getColor(R.color.black));
        else if (arr[pointer[1]][pointer[0]] == 0)
            tiles[pointer[1]][pointer[0]].setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    private void varInit() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String tilesId = "cd_tiles_" + i + j;
                tiles[i][j] = findViewById(getResources().getIdentifier(tilesId,
                        "id", getPackageName()));
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String playerId = "player_" + i + j;
                player[i][j] = findViewById(getResources().getIdentifier(playerId,
                        "id", getPackageName()));
            }
        }
    }
}