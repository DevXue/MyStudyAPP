package xue.myapp.home.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import xue.myapp.Constants;
import xue.myapp.R;
import xue.myapp.common.ui.CommonFragment;

public class SimpleFragment3 extends CommonFragment {
    @Bind(R.id.textView)
    TextView textView;

    public static SimpleFragment3 newInstance(String info) {
        SimpleFragment3 fragment = new SimpleFragment3();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, info);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, null);
        textView= (TextView) view.findViewById(R.id.textView);
        textView.setText(getArguments().getString(Constants.ARG_PARAM1));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "哈喽我的", Snackbar.LENGTH_SHORT).show();
            }
        });
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        Log.e("SimpleFragment3","执行003");
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
