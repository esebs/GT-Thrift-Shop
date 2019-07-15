package com.github.esebs.cs2340project.spacetrader.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.viewmodels.TradingViewModel;

/**
 * This class will generate and display the buy fragment
 * when the 'Buy' tab is pressed
 *
 * @version 1.0
 * @author Elio Gerges
 */
public class BuyFragment extends Fragment {
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

    //The item quantity shows how much of that resource a player wants to buy.
    private int waterQuantity;
    private int fursQuantity;
    private int foodQuantity;
    private int oreQuantity;
    private int gamesQuantity;
    private int firearmsQuantity;
    private int medicineQuantity;
    private int machinesQuantity;
    private int narcoticsQuantity;
    private int robotsQuantity;

    private TextView textView1;
    private TextView textView3;
    private SeekBar seekBar;


    /**
     * When the 'Buy' tab is pressed, onCreateView method is executed
     * and the contents of the 'Buy' tab are displayed. The items,
     * number of items purchasable and the item cost are displayed.
     * When the user presses the button with the item quantity, they
     * are prompted with an alert asking the user how many items they
     * would like to buy. The user can then select a number then press
     * buy to make that transaction. The Quantity buttons are then updated
     * accordingly.
     *
     * @param inflater is the inflater for the layout
     * @param container is the container for all items in the fragment
     * @param savedInstanceState is the saved instance of the fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView waterPrice;
        TextView fursPrice;
        TextView foodPrice;
        TextView orePrice;
        TextView gamesPrice;
        TextView firearmsPrice;
        TextView medicinePrice;
        TextView machinesPrice;
        TextView narcoticsPrice;
        TextView robotsPrice;
        View view = inflater.inflate(R.layout.buy_fragment, container,false);
        final View dialog = inflater.inflate(R.layout.trade_dialog, container,false);

        waterButton = view.findViewById(R.id.water_qty);
        waterPrice = view.findViewById(R.id.water_price);
        waterQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.WATER) == -1) {
            //Room doesn't have the resources available, disable buying
            waterButton.setText(getString(R.string.quantity_not_available));
            waterButton.setEnabled(false);
            waterPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            waterButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.WATER)));
            waterPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.WATER)));
        }

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.WATER);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "water"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);


                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, waterQuantity));

                seekBar.setOnSeekBarChangeListener(waterSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.buyResources(Resource.WATER, waterQuantity);
                                waterButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.WATER)));
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
        fursQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.FURS) == -1) {
            //Room doesn't have the resources available, disable buying
            fursButton.setText(getString(R.string.quantity_not_available));
            fursButton.setEnabled(false);
            fursPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            fursButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.FURS)));
            fursPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.FURS)));
        }


        fursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.FURS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "fur"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, fursQuantity));

                seekBar.setOnSeekBarChangeListener(fursSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.FURS, fursQuantity);

                                //Updates display
                                fursButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.FURS)));

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
        foodQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.FOOD) == -1) {
            //Room doesn't have the resources available, disable buying
            foodButton.setText(getString(R.string.quantity_not_available));
            foodButton.setEnabled(false);
            foodPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            foodButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.FOOD)));
            foodPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.FOOD)));
        }

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.FOOD);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "food"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, foodQuantity));

                seekBar.setOnSeekBarChangeListener(foodSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.FOOD, foodQuantity);

                                //Updates display
                                foodButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.FOOD)));
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
        oreQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.ORE) == -1) {
            //Room doesn't have the resources available, disable buying
            oreButton.setText(getString(R.string.quantity_not_available));
            oreButton.setEnabled(false);
            orePrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            oreButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.ORE)));
            orePrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.ORE)));
        }

        oreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.ORE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "ore"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, oreQuantity));

                seekBar.setOnSeekBarChangeListener(oreSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.ORE, oreQuantity);

                                //Updates display
                                oreButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.ORE)));
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
        gamesQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.GAMES) == -1) {
            //Room doesn't have the resources available, disable buying
            gamesButton.setText(getString(R.string.quantity_not_available));
            gamesButton.setEnabled(false);
            gamesPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            gamesButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.GAMES)));
            gamesPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.GAMES)));
        }

        gamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.GAMES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "games"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, gamesQuantity));

                seekBar.setOnSeekBarChangeListener(gamesSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.GAMES, gamesQuantity);

                                //Updates display
                                gamesButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.GAMES)));

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
        firearmsQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.FIREARMS) == -1) {
            //Room doesn't have the resources available, disable buying
            firearmsButton.setText(getString(R.string.quantity_not_available));
            firearmsButton.setEnabled(false);
            firearmsPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            firearmsButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.FIREARMS)));
            firearmsPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.FIREARMS)));
        }

        firearmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.FIREARMS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "firearms"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, firearmsQuantity));

                seekBar.setOnSeekBarChangeListener(firearmsSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.FIREARMS, firearmsQuantity);

                                //Updates display
                                firearmsButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.FIREARMS)));

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
        medicineQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.MEDICINE) == -1) {
            //Room doesn't have the resources available, disable buying
            medicineButton.setText(getString(R.string.quantity_not_available));
            medicineButton.setEnabled(false);
            medicinePrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            medicineButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.MEDICINE)));
            medicinePrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.MEDICINE)));
        }

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.MEDICINE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "medicine"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, medicineQuantity));

                seekBar.setOnSeekBarChangeListener(medicineSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.MEDICINE, medicineQuantity);

                                //Updates display
                                medicineButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.MEDICINE)));

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
        machinesQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.MACHINES) == -1) {
            //Room doesn't have the resources available, disable buying
            machinesButton.setText(getString(R.string.quantity_not_available));
            machinesButton.setEnabled(false);
            machinesPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            machinesButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.MACHINES)));
            machinesPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.MACHINES)));
        }

        machinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.MACHINES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "machines"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, machinesQuantity));

                seekBar.setOnSeekBarChangeListener(machinesSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.MACHINES, machinesQuantity);

                                //Updates display
                                machinesButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.MACHINES)));
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
        narcoticsQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.NARCOTICS) == -1) {
            //Room doesn't have the resources available, disable buying
            narcoticsButton.setText(getString(R.string.quantity_not_available));
            narcoticsButton.setEnabled(false);
            narcoticsPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            narcoticsButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.NARCOTICS)));
            narcoticsPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.NARCOTICS)));
        }

        narcoticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.NARCOTICS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "narcotics"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, narcoticsQuantity));

                seekBar.setOnSeekBarChangeListener(narcoticsSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.NARCOTICS, narcoticsQuantity);

                                //Updates display
                                narcoticsButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.NARCOTICS)));

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
        robotsQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getBuyQuantity(Resource.ROBOTS) == -1) {
            //Room doesn't have the resources available, disable buying
            robotsButton.setText(getString(R.string.quantity_not_available));
            robotsButton.setEnabled(false);
            robotsPrice.setText(getString(R.string.price_not_available));

        } else {
            //Room has the resources available, display the quantity and the price
            robotsButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.ROBOTS)));
            robotsPrice.setText(getString(R.string.buy_resource_price, tradingViewModel.getBuyPrice(Resource.ROBOTS)));
        }


        robotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxBuyable = tradingViewModel.calculateMaxBuyable(Resource.ROBOTS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.buy_text_1, maxBuyable, "robots"));

                seekBar = dialog.findViewById(R.id.seek_bar);
                seekBar.setMax(maxBuyable);
                seekBar.setProgress(0);

                textView3 = dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, robotsQuantity));

                seekBar.setOnSeekBarChangeListener(robotsSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Buys the resource
                                tradingViewModel.buyResources(Resource.ROBOTS, robotsQuantity);

                                //Updates display
                                robotsButton.setText(getString(R.string.buy_resource_quantity, tradingViewModel.getBuyQuantity(Resource.ROBOTS)));

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

    /**
     * This method will refresh the tabs each time the users revisits a tab
     *
     * @param isVisibleToUser is a boolean representing if a fragment is visible to the user
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && getFragmentManager() != null) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


    //SeekBar handler for Buying Water
    private SeekBar.OnSeekBarChangeListener waterSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes water quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy water seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            waterQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, waterQuantity));
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
    private SeekBar.OnSeekBarChangeListener fursSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes furs quantity to new value and updates seek bar counter
         *
         * @param seekBar  seekBar for Alert
         * @param progress current progress of the buy furs seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            fursQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, fursQuantity));
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
    private SeekBar.OnSeekBarChangeListener foodSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes food quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy food seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            foodQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, foodQuantity));
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
    private SeekBar.OnSeekBarChangeListener oreSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes ore quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy ore seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            oreQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, oreQuantity));
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
    private SeekBar.OnSeekBarChangeListener gamesSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes games quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy games seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            gamesQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, gamesQuantity));
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
    private SeekBar.OnSeekBarChangeListener firearmsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes firearms quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy firearms seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            firearmsQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, firearmsQuantity));
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
    private SeekBar.OnSeekBarChangeListener medicineSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes medicine quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy medicine seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            medicineQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, medicineQuantity));
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
    private SeekBar.OnSeekBarChangeListener machinesSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes machines quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy machines seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            machinesQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, machinesQuantity));
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
    private SeekBar.OnSeekBarChangeListener narcoticsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes narcotics quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy narcotics seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            narcoticsQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, narcoticsQuantity));
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
    private SeekBar.OnSeekBarChangeListener robotsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes robots quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy robots seek bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            robotsQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, robotsQuantity));
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
