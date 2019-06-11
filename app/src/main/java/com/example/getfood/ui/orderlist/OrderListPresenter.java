package com.example.getfood.ui.orderlist;

import android.support.annotation.NonNull;

import com.example.getfood.models.FullOrder;
import com.example.getfood.ui.base.BasePresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderListPresenter<V extends OrderListMvpView> extends BasePresenter<V> implements OrderListMvpPresenter<V> {
    private ArrayList<FullOrder> orderListItems;

    public OrderListPresenter() {
    }

    @Override
    public void fetchOrderList(String rollNo) {
        orderListItems = new ArrayList<>();

        apiManager.orderListListener(rollNo, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    orderListItems.clear();
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        orderListItems.add(dsp.getValue(FullOrder.class));
                    }
                    getMvpView().bindListAdapter(orderListItems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
