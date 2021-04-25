package com.betul.bas.fragment;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

public class FragmentModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    String veri;
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return input+"";
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
        veri = String.valueOf(index);
    }

    public String getVeri(){
        return veri;
    }
    public LiveData<String> getText() {
        return mText;
    }
}