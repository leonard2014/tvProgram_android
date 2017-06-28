package com.leonard.www.tvprog.contract;

/**
 * Created by leoxw on 06/24/2017.
 */

public interface IBaseView<T> {
    void showEmpty();
    void showProgress();
    void hideProgress();
    void showError();
    void showContent(T content);
}
