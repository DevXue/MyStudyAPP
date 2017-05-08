package xue.myapp.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import xue.myapp.R;
import xue.myapp.common.ui.CommonFragment;

public class SimpleFragment extends CommonFragment {
    private static final String ARG_PARAM1 = "info";
    @Bind(R.id.textView)
    TextView textView;

    public static SimpleFragment newInstance(String info) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, info);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, null);
        textView= (TextView) view.findViewById(R.id.textView);
        textView.setText(getArguments().getString("info"));
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    protected void loadData() {
        super.loadData();
        if (!isVisible) {
            return;
        }

        Log.e("SimpleFragment","执行");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
