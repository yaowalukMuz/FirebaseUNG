package androidthai.in.th.firebaseung.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidthai.in.th.firebaseung.MainActivity;
import androidthai.in.th.firebaseung.R;

/**
 * Created by Yaowaluk on 25/11/2017.
 */

public class RegisterFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();


    }//Main Method

    private void createToolbar() {

//        Config toolbar
        Toolbar toolbar = getView().findViewById(R.id.tbRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.sub_register));

//        Back Icon
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//      Set onClick event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                popBackStack มาจากหน้าไหนกลับไปหน้านั้น ==` finish
                ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
        }
        });

//        IMgOnToolbar


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;

    }
}//Main Class
