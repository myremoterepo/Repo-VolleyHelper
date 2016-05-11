package com.example.fanzf.repo_volleyhelper.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fanzf.repo_volleyhelper.R;
import com.example.fanzf.repo_volleyhelper.tool.NetworkUtil;

/**
 * Created by fanzf on 2016/5/10.
 */
public class ResponseFragment extends Fragment implements RequestContract.View {
    private RelativeLayout progress;
    private TextView content;
    private Button stringReq;
    private Button gsonReq;

    private RequestContract.Presenter mPresenter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_response, container, false);
        progress = (RelativeLayout)root.findViewById(R.id.progress);
        content = (TextView) root.findViewById(R.id.content);
        stringReq = (Button)root.findViewById(R.id.stringRequest);
        gsonReq = (Button)root.findViewById(R.id.gsonRequest);
        stringReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtil.getNetWork(ResponseFragment.this.getActivity())){
                    mPresenter.stringRequest();
                } else {
                    Toast.makeText(ResponseFragment.this.getActivity(), "没有可用网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gsonReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtil.getNetWork(ResponseFragment.this.getActivity())){
                    mPresenter.gsonRequest();
                } else {
                    Toast.makeText(ResponseFragment.this.getActivity(), "没有可用网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(RequestContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void setContent(String content) {
        this.content.setText(content);
    }
}
