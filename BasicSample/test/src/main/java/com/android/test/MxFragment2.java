package com.android.test;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.test.databinding.ListFragment2Binding;
import com.android.test.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;
//import com.android.test.databinding.ListFragmentBinding;

//import com.android.test.databinding.ListFragmentBinding;

/**
 * Created by lijunjie on 23/08/2017.
 */

public class MxFragment2 extends LifecycleFragment {

    public static final String TAG=MxFragment2.class.getSimpleName();
    ListFragment2Binding mBinding;
    RecyclerView mListView;
    TextAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment2, container, false);
        mBinding.setIsLoading(false);
        mBinding.getRoot().setBackgroundColor(Color.RED);



        mListView = (RecyclerView)mBinding.getRoot().findViewById(R.id.products_list);
        return mBinding.getRoot();
//        FrameLayout f=new FrameLayout(getContext());
//        f.setBackgroundColor(Color.BLUE);
//        return f;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListViewModel model= ViewModelProviders.of(this).get(ListViewModel.class);
        mAdapter=new TextAdapter(new ArrayList<String>());
        model.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                mAdapter.mList=strings;
                mAdapter.notifyDataSetChanged();
            }
        });

        mListView.setAdapter(mAdapter);
    }

    private void initView() {


    }


    class TextAdapter extends  RecyclerView.Adapter<TextViewHolder> {

        List<String> mList;
        public TextAdapter(List<String> list) {
           mList=list;
        }

        @Override
        public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView t= new TextView(getActivity());
            t.setText("test");

            return new TextViewHolder(t);

        }

        @Override
        public void onBindViewHolder(TextViewHolder holder, int position) {
            holder.setText("position:"+position);
        }


        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        TextView mT;

        public TextViewHolder(View itemView) {
            super(itemView);
            if(itemView instanceof TextView) {
                mT=(TextView)itemView;
            }
        }

        public void setText(String text) {
            if(mT!=null) {
                mT.setText(text);
            }
        }

    }
}
