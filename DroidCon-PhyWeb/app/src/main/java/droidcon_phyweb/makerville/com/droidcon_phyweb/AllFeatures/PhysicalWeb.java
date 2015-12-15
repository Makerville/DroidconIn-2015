package droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import droidcon_phyweb.makerville.com.droidcon_phyweb.InformationAdapter;
import droidcon_phyweb.makerville.com.droidcon_phyweb.PhysicalWebAdapter;
import droidcon_phyweb.makerville.com.droidcon_phyweb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhysicalWeb extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public PhysicalWeb() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_physical_web, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv1);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PhysicalWebAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

}
