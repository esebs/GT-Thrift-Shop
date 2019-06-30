package com.github.esebs.cs2340project.spacetrader.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.TradingViewModel;

public class BuyFragment extends Fragment {
    private Model model = Model.getModelInstance();
    private TradingViewModel tradingViewModel = new TradingViewModel();

    private Button waterButton;
    private Button fursButton;
    private Button foodButton;
    private Button oreButton;
    private Button gamesButton;
    private Button firearmsButton;
    private Button medicineButton;
    private Button machinesButton;
    private Button narcoticsButton;
    private Button robotsButton;

    private TextView waterPrice;
    private TextView fursPrice;
    private TextView foodPrice;
    private TextView orePrice;
    private TextView gamesPrice;
    private TextView firearmsPrice;
    private TextView medicinePrice;
    private TextView machinesPrice;
    private TextView nacorticsPrice;
    private TextView robotsPrice;

    private int waterQuanity;
    private int fursQuanity;
    private int foodQuanity;
    private int oreQuanity;
    private int gamesQuanity;
    private int firearmsQuanity;
    private int medicineQuanity;
    private int machinesQuanity;
    private int nacorticsQuanity;
    private int robotsQuanity;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private SeekBar seekBar;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_fragment, container,false);
        final View dialog = inflater.inflate(R.layout.dialog, container,false);

        waterButton = view.findViewById(R.id.water_qty);
        waterButton.setText("" + tradingViewModel.getBuyQuantity(Resource.WATER));
        waterPrice = view.findViewById(R.id.water_price);
        waterPrice.setText("" + tradingViewModel.getBuyPrice(Resource.WATER) + " cr.");

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.WATER);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " bayes of Water.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);

                waterQuanity = seekBar.getProgress();

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(waterQuanity + " bayes");

                seekBar.setOnSeekBarChangeListener(seekerListener);


                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.buyResources(Resource.WATER, waterQuanity);
                                waterButton.setText("" + tradingViewModel.getBuyQuantity(Resource.WATER));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });


        fursButton = view.findViewById(R.id.furs_qty);
        fursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Furs.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        foodButton = view.findViewById(R.id.food_qty);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Food.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        oreButton = view.findViewById(R.id.ore_qty);
        oreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTON", "Pressed");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Ore.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        gamesButton = view.findViewById(R.id.games_qty);
        gamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Games.");
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        firearmsButton = view.findViewById(R.id.firearms_qty);
        firearmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Firearms.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        medicineButton = view.findViewById(R.id.medicine_qty);
        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Medicine.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        machinesButton = view.findViewById(R.id.machine_qty);
        machinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Machines.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        narcoticsButton = view.findViewById(R.id.narcotics_qty);
        narcoticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Narcotics.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        robotsButton = view.findViewById(R.id.robots_qty);
        robotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                //viewmodel getPlayerGetRoom
                textView1.setText("You can buy up to " + 2 + " bayes of Robots.");

                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });

        return view;
    }


    //SeekBar handler for Buying Water
    SeekBar.OnSeekBarChangeListener seekerListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes pilot points to new value and updates Points Remaining
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy water seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            waterQuanity = seekBar.getProgress();
            textView3.setText(waterQuanity + " bayes");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

}
