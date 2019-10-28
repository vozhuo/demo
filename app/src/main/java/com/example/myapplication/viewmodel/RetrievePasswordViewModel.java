package com.example.myapplication.viewmodel;

import com.example.myapplication.entity.RetrievePasswordReq;
import com.example.myapplication.entity.RetrievePasswordResp;
import com.example.myapplication.event.RetrievePasswordEvent;
import com.example.myapplication.model.UserModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrievePasswordViewModel extends ViewModel {

    private MutableLiveData<RetrievePasswordResp> mObservableRetrievePasswordResp;

    public RetrievePasswordViewModel(){
        mObservableRetrievePasswordResp = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    public MutableLiveData<RetrievePasswordResp> getObservableRetrievePasswordResp() {
        return mObservableRetrievePasswordResp;
    }

    public void retrievePassword(RetrievePasswordReq retrievePasswordReq){
        UserModel.getInstance().retrievePassword(retrievePasswordReq);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRetrievePasswordRespEvent(RetrievePasswordEvent event){
        mObservableRetrievePasswordResp.setValue(event.getRetrievePasswordResp());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
