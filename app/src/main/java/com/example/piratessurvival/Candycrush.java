package com.example.piratessurvival;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Candycrush extends AppCompatActivity {

    int[] candies = {
            R.drawable.bluecandy,
            R.drawable.greencandy,
            R.drawable.redcandy,
            R.drawable.orangecandy,
            R.drawable.yellowcandy,
            R.drawable.purplecandy
    };

    int widthOfBlock, noOfBlocks = 8, widthOfScreen;
    ArrayList<ImageView> candy = new ArrayList<>();
    int candyToBeDragged, candyToBeReplased;
    int notCandy = R.drawable.transparent;
    Handler mHandler;
    int interval = 100;
    TextView scoreResult;
    int score = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candy_crush);
        scoreResult = findViewById(R.id.score);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthOfBlock = widthOfScreen / noOfBlocks;
        createBoard();
        for (final ImageView imageView : candy) {
            imageView.setOnTouchListener(new OnSwipeListener(this) {
                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplased = candyToBeDragged - 1;
                    candyInterChange();
                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplased = candyToBeDragged + 1;
                    candyInterChange();
                }

                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplased = candyToBeDragged - noOfBlocks;
                    candyInterChange();
                }

                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplased = candyToBeDragged + noOfBlocks;
                    candyInterChange();
                }
            });
        }
        mHandler = new Handler();
        startRepeat();
    }

    private void checkRowForTree() {
        for (int i = 0; i < 62; i++) {
            int chosedCandy = (int) candy.get(i).getTag();
            boolean isBlank = (int) candy.get(i).getTag() == notCandy;
            Integer[] notValid = {6, 7, 14, 15, 22, 23, 30, 31, 38, 39, 46, 47, 54, 55};
            List<Integer> list = Arrays.asList(notValid);
            if (!list.contains(i)) {
                int x = i;
                if ((int) candy.get(x++).getTag() == chosedCandy && !isBlank &&
                        (int) candy.get(x++).getTag() == chosedCandy &&
                        (int) candy.get(x).getTag() == chosedCandy) {
                    score = score + 3;
                    scoreResult.setText(String.valueOf(score));
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);
                    x--;
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);
                    x--;
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);

                    // Check the score
                    if (score == 100) {
                        // Show the congratulations dialog
                        showCongratulationsDialog();
                    }
                }
            }
        }
        moveDownCandies();
    }

    private void checkColumnForTree() {
        for (int i = 0; i < 47; i++) {
            int chosedCandy = (int) candy.get(i).getTag();
            boolean isBlank = (int) candy.get(i).getTag() == notCandy;

            int x = i;
            if ((int) candy.get(x).getTag() == chosedCandy && !isBlank &&
                    (int) candy.get(x + noOfBlocks).getTag() == chosedCandy &&
                    (int) candy.get(x + 2 * noOfBlocks).getTag() == chosedCandy) {
                score = score + 3;
                scoreResult.setText(String.valueOf(score));
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);
                x = x + noOfBlocks;
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);
                x = x + noOfBlocks;
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);

                // Check the score
                if (score >= 20) {
                    // Show the congratulations dialog
                    showCongratulationsDialog();
                }
            }
        }
    }

    private void moveDownCandies() {
        Integer[] firstRow = {0, 1, 2, 3, 4, 5, 6, 7};
        List<Integer> list = Arrays.asList(firstRow);
        for (int i = 55; i >= 0; i--) {
            if ((int) candy.get(i + noOfBlocks).getTag() == notCandy) {
                candy.get(i + noOfBlocks).setImageResource((int) candy.get(i).getTag());
                candy.get(i + noOfBlocks).setTag(candy.get(i).getTag());
                candy.get(i).setImageResource(notCandy);
                candy.get(i).setTag(notCandy);

                if (list.contains(i) && (int) candy.get(i).getTag() == notCandy) {
                    int randomColor = (int) Math.floor(Math.random() * candies.length);
                    candy.get(i).setImageResource(candies[randomColor]);
                    candy.get(i).setTag(candies[randomColor]);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            if ((int) candy.get(i).getTag() == notCandy) {
                int randomColor = (int) Math.floor(Math.random() * candies.length);
                candy.get(i).setImageResource(candies[randomColor]);
                candy.get(i).setTag(candies[randomColor]);
            }
        }
    }

    Runnable repeatChecker = new Runnable() {
        @Override
        public void run() {
            try {
                checkRowForTree();
                checkColumnForTree();
                moveDownCandies();
            } finally {
                mHandler.postDelayed(repeatChecker, interval);
            }
        }
    };

    void startRepeat() {
        repeatChecker.run();
    }

    private void candyInterChange() {
        int background = (int) candy.get(candyToBeReplased).getTag();
        int background1 = (int) candy.get(candyToBeDragged).getTag();
        candy.get(candyToBeDragged).setImageResource(background);
        candy.get(candyToBeReplased).setImageResource(background1);
        candy.get(candyToBeDragged).setTag(background);
        candy.get(candyToBeReplased).setTag(background1);
    }

    private void createBoard() {
        GridLayout gridLayout = findViewById(R.id.board);
        gridLayout.setRowCount(noOfBlocks);
        gridLayout.setColumnCount(noOfBlocks);
        //since we want square.
        gridLayout.getLayoutParams().width = widthOfScreen;
        gridLayout.getLayoutParams().height = widthOfScreen;
        for (int i = 0; i < noOfBlocks * noOfBlocks; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new
                    android.view.ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
            imageView.setMaxHeight(widthOfBlock);
            imageView.setMaxWidth(widthOfBlock);
            int randomCandy = (int) Math.floor(Math.random() * candies.length);// this generates
            // randomIndex from candies array
            imageView.setImageResource(candies[randomCandy]);
            imageView.setTag(candies[randomCandy]);
            candy.add(imageView);
            gridLayout.addView(imageView);
        }
    }

    // Method to show the congratulations dialog
    private void showCongratulationsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Congratulations! You earned 100 points.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Go to Category activity
                Intent intent = new Intent(Candycrush.this, Category.class);
                intent.putExtra("water_checkbox_visible", true); // Pass the extra parameter
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

