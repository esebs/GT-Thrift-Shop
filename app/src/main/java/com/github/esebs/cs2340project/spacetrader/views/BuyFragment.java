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
    private TextView narcoticsPrice;
    private TextView robotsPrice;

    //The item quantity shows how much of that resource a player wants to buy.
    private int waterQuanity = 0;
    private int fursQuanity = 0;
    private int foodQuanity = 0;
    private int oreQuanity = 0;
    private int gamesQuanity = 0;
    private int firearmsQuanity = 0;
    private int medicineQuanity = 0;
    private int machinesQuanity = 0;
    private int narcoticsQuanity = 0;
    private int robotsQuanity = 0;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private SeekBar seekBar;

    View view;

    @Nullable
    @Override
    /**
     *
     *
     * @param
     * @param
     * @param
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_fragment, container,false);
        final View dialog = inflater.inflate(R.layout.dialog, container,false);

        waterButton = view.findViewById(R.id.water_qty);
        waterPrice = view.findViewById(R.id.water_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.WATER) == -1) {
            //Room doesn't have the resources available, disable buying
            waterButton.setText("n/a");
            waterButton.setEnabled(false);
            waterPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            waterButton.setText("" + tradingViewModel.getBuyQuantity(Resource.WATER));
            waterPrice.setText("" + tradingViewModel.getBuyPrice(Resource.WATER) + " cr.");

        }

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.WATER);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Water.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(waterQuanity + " units");

                seekBar.setOnSeekBarChangeListener(waterSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
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
                        })
                        .create()
                        .show();
            }
        });



        fursButton = view.findViewById(R.id.furs_qty);
        fursPrice = view.findViewById(R.id.furs_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.FURS) == -1) {
            //Room doesn't have the resources available, disable buying
            fursButton.setText("n/a");
            fursButton.setEnabled(false);
            fursPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            fursButton.setText("" + tradingViewModel.getBuyQuantity(Resource.FURS));
            fursPrice.setText("" + tradingViewModel.getBuyPrice(Resource.FURS) + " cr.");
        }


        fursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.FURS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Furs.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(fursQuanity + " units");

                seekBar.setOnSeekBarChangeListener(fursSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.FURS, fursQuanity);

                                //Updates display
                                fursButton.setText("" + tradingViewModel.getBuyQuantity(Resource.FURS));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        foodButton = view.findViewById(R.id.food_qty);
        foodPrice = view.findViewById(R.id.food_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.FOOD) == -1) {
            //Room doesn't have the resources available, disable buying
            foodButton.setText("n/a");
            foodButton.setEnabled(false);
            foodPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            foodButton.setText("" + tradingViewModel.getBuyQuantity(Resource.FOOD));
            foodPrice.setText("" + tradingViewModel.getBuyPrice(Resource.FOOD) + " cr.");
        }

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.FOOD);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Food.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(foodQuanity + " units");

                seekBar.setOnSeekBarChangeListener(foodSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.FOOD, foodQuanity);

                                //Updates display
                                foodButton.setText("" + tradingViewModel.getBuyQuantity(Resource.FOOD));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });


        oreButton = view.findViewById(R.id.ore_qty);
        orePrice = view.findViewById(R.id.ore_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.ORE) == -1) {
            //Room doesn't have the resources available, disable buying
            oreButton.setText("n/a");
            oreButton.setEnabled(false);
            orePrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            oreButton.setText("" + tradingViewModel.getBuyQuantity(Resource.ORE));
            orePrice.setText("" + tradingViewModel.getBuyPrice(Resource.ORE) + " cr.");
        }

        oreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.ORE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Ore.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(oreQuanity + " units");

                seekBar.setOnSeekBarChangeListener(oreSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.ORE, oreQuanity);

                                //Updates display
                                oreButton.setText("" + tradingViewModel.getBuyQuantity(Resource.ORE));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        gamesButton = view.findViewById(R.id.games_qty);
        gamesPrice = view.findViewById(R.id.games_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.GAMES) == -1) {
            //Room doesn't have the resources available, disable buying
            gamesButton.setText("n/a");
            gamesButton.setEnabled(false);
            gamesPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            gamesButton.setText("" + tradingViewModel.getBuyQuantity(Resource.GAMES));
            gamesPrice.setText("" + tradingViewModel.getBuyPrice(Resource.GAMES) + " cr.");
        }

        gamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.GAMES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Games.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(gamesQuanity + " units");

                seekBar.setOnSeekBarChangeListener(gamesSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.GAMES, gamesQuanity);

                                //Updates display
                                gamesButton.setText("" + tradingViewModel.getBuyQuantity(Resource.GAMES));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        firearmsButton = view.findViewById(R.id.firearms_qty);
        firearmsPrice = view.findViewById(R.id.firearms_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.FIREARMS) == -1) {
            //Room doesn't have the resources available, disable buying
            firearmsButton.setText("n/a");
            firearmsButton.setEnabled(false);
            firearmsPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            firearmsButton.setText("" + tradingViewModel.getBuyQuantity(Resource.FIREARMS));
            firearmsPrice.setText(tradingViewModel.getBuyPrice(Resource.FIREARMS) + " cr.");
        }

        firearmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.FIREARMS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Firearms.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(firearmsQuanity + " units");

                seekBar.setOnSeekBarChangeListener(firearmsSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.FIREARMS, firearmsQuanity);

                                //Updates display
                                firearmsButton.setText("" + tradingViewModel.getBuyQuantity(Resource.FIREARMS));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        medicineButton = view.findViewById(R.id.medicine_qty);
        medicinePrice = view.findViewById(R.id.medicine_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.MEDICINE) == -1) {
            //Room doesn't have the resources available, disable buying
            medicineButton.setText("n/a");
            medicineButton.setEnabled(false);
            medicinePrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            medicineButton.setText("" + tradingViewModel.getBuyQuantity(Resource.MEDICINE));
            medicinePrice.setText(tradingViewModel.getBuyPrice(Resource.MEDICINE) + " cr.");
        }

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.MEDICINE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Medicine.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(medicineQuanity + " units");

                seekBar.setOnSeekBarChangeListener(medicineSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.MEDICINE, medicineQuanity);

                                //Updates display
                                medicineButton.setText("" + tradingViewModel.getBuyQuantity(Resource.MEDICINE));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        machinesButton = view.findViewById(R.id.machine_qty);
        machinesPrice = view.findViewById(R.id.machine_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.MACHINES) == -1) {
            //Room doesn't have the resources available, disable buying
            machinesButton.setText("n/a");
            machinesButton.setEnabled(false);
            machinesPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            machinesButton.setText("" + tradingViewModel.getBuyQuantity(Resource.MACHINES));
            machinesPrice.setText(tradingViewModel.getBuyPrice(Resource.MACHINES) + " cr.");
        }

        machinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.MACHINES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Machines.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(machinesQuanity + " units");

                seekBar.setOnSeekBarChangeListener(machinesSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.MACHINES, machinesQuanity);

                                //Updates display
                                machinesButton.setText("" + tradingViewModel.getBuyQuantity(Resource.MACHINES));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        narcoticsButton = view.findViewById(R.id.narcotics_qty);
        narcoticsPrice = view.findViewById(R.id.narcotics_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.NARCOTICS) == -1) {
            //Room doesn't have the resources available, disable buying
            narcoticsButton.setText("n/a");
            narcoticsButton.setEnabled(false);
            narcoticsPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            narcoticsButton.setText("" + tradingViewModel.getBuyQuantity(Resource.NARCOTICS));
            narcoticsPrice.setText(tradingViewModel.getBuyPrice(Resource.NARCOTICS) + " cr.");
        }

        narcoticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.NARCOTICS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Narcotics.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(narcoticsQuanity + " units");

                seekBar.setOnSeekBarChangeListener(narcoticsSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.NARCOTICS, narcoticsQuanity);

                                //Updates display
                                narcoticsButton.setText("" + tradingViewModel.getBuyQuantity(Resource.NARCOTICS));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        robotsButton = view.findViewById(R.id.robots_qty);
        robotsPrice = view.findViewById(R.id.robots_price);

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.ROBOTS) == -1) {
            //Room doesn't have the resources available, disable buying
            robotsButton.setText("n/a");
            robotsButton.setEnabled(false);
            robotsPrice.setText("--- cr.");

        } else {
            //Room has the resources available, display the quantity and the price
            robotsButton.setText("" + tradingViewModel.getBuyQuantity(Resource.ROBOTS));
            robotsPrice.setText(tradingViewModel.getBuyPrice(Resource.ROBOTS) + " cr.");
        }


        robotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.ROBOTS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can buy up to " + maxBuyable + " units of Robots.");

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(robotsQuanity + " units");

                seekBar.setOnSeekBarChangeListener(robotsSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.ROBOTS, robotsQuanity);

                                //Updates display
                                robotsButton.setText("" + tradingViewModel.getBuyQuantity(Resource.ROBOTS));
                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        return view;
    }


    //SeekBar handler for Buying Water
    SeekBar.OnSeekBarChangeListener waterSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes water quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy water seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            waterQuanity = seekBar.getProgress();
            textView3.setText(waterQuanity + " units");
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


    //SeekBar handler for Buying Furs
    SeekBar.OnSeekBarChangeListener fursSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes furs quantity to new value and updates seek bar counter
         *
         * @param seekBar  seekBar for Alert
         * @param progress current progress of the buy furs seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            fursQuanity = seekBar.getProgress();
            textView3.setText(fursQuanity + " units");
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


    //SeekBar handler for Buying Food
    SeekBar.OnSeekBarChangeListener foodSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes food quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy food seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            foodQuanity = seekBar.getProgress();
            textView3.setText(foodQuanity + " units");
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


    //SeekBar handler for Buying Ore
    SeekBar.OnSeekBarChangeListener oreSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes ore quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy ore seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            oreQuanity = seekBar.getProgress();
            textView3.setText(oreQuanity + " units");
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


    //SeekBar handler for Buying Games
    SeekBar.OnSeekBarChangeListener gamesSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes games quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy games seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            gamesQuanity = seekBar.getProgress();
            textView3.setText(gamesQuanity + " units");
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


    //SeekBar handler for Buying Firearms
    SeekBar.OnSeekBarChangeListener firearmsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes firearms quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy firearms seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            firearmsQuanity = seekBar.getProgress();
            textView3.setText(firearmsQuanity + " units");
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


    //SeekBar handler for Buying Medicine
    SeekBar.OnSeekBarChangeListener medicineSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes medicine quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy medicine seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            medicineQuanity = seekBar.getProgress();
            textView3.setText(medicineQuanity + " units");
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

    //SeekBar handler for Buying Machines
    SeekBar.OnSeekBarChangeListener machinesSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes machines quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy machines seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            machinesQuanity = seekBar.getProgress();
            textView3.setText(machinesQuanity + " units");
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


    //SeekBar handler for Buying Narcotics
    SeekBar.OnSeekBarChangeListener narcoticsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes narcotics quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy narcotics seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            narcoticsQuanity = seekBar.getProgress();
            textView3.setText(narcoticsQuanity + " units");
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


    //SeekBar handler for Buying Robots
    SeekBar.OnSeekBarChangeListener robotsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes robots quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy robots seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            robotsQuanity = seekBar.getProgress();
            textView3.setText(robotsQuanity + " units");
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
