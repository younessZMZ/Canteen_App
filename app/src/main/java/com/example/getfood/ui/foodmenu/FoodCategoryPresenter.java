package com.example.getfood.ui.foodmenu;

import com.example.getfood.ui.base.BasePresenter;
import com.fazemeright.canteen_app_models.models.CartItem;
import com.fazemeright.canteen_app_models.models.FoodItem;
import com.fazemeright.firebase_api__library.listeners.DBValueEventListener;

import java.util.ArrayList;

public class FoodCategoryPresenter<V extends FoodCategoryMvpView> extends BasePresenter<V> implements FoodCategoryMvpPresenter<V> {

    public FoodCategoryPresenter() {
    }


    @Override
    public void fetchFoodList(final String category) {

        apiManager.foodMenuListener(category, new DBValueEventListener<ArrayList<FoodItem>>() {
            @Override
            public void onDataChange(ArrayList<FoodItem> data) {
                getMvpView().bindFoodListAdapter(data);
            }

            @Override
            public void onCancelled(Error error) {

            }
        });
    }

    @Override
    public ArrayList<CartItem> getCartItems() {
        return dataManager.getCartItems();
    }

    @Override
    public void addFoodItemToCart(CartItem cartItem) {
        dataManager.getCartItems().add(cartItem);
    }
}
