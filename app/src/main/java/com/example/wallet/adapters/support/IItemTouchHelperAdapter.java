package com.example.wallet.adapters.support;

public interface IItemTouchHelperAdapter {

    void onItemMove(int fromPosition,int toPosition);

    void onItemDismiss(int position);

    void onItemDismissFavorite(int position);
}
