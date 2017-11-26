package androidthai.in.th.firebaseung.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidthai.in.th.firebaseung.R;
import androidthai.in.th.firebaseung.utility.MyAlertDialog;

/**
 * Created by masterung on 25/11/2017 AD.
 */

public class MainFragment extends Fragment{

    //Explicit
    private String emailString,passwordString;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register Controller
        registerController();


//        Login Conterller
        loginConterller();



    }   //Main method run after onCreateView

    private void loginConterller() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Initial View
                EditText emailEditText = getView().findViewById(R.id.edtEmail1);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword1);

//            Get Value From  Edit
                emailString = emailEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();


//                Check Space
                if (emailString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myNormalDialog("Have Space",getString(R.string.sub_register));

                } else {
//                    No Space //cast to MainFragment --over mainMethod
                    checkEmailAndPass();
                }
            }

        });

    }

    private void checkEmailAndPass() {
        FirebaseAuth firebaseAut = FirebaseAuth.getInstance();

        // sent value check value on firebase
        firebaseAut.signInWithEmailAndPassword(emailString,passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(),"Welcome",Toast.LENGTH_SHORT).show();

                } else {
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myNormalDialog("Authen False",task.getException().getMessage());

                }


            }
        });

    }//CheckEmailAndPass


    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null).commit();
            }   //Onclick
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}   // Main Class
