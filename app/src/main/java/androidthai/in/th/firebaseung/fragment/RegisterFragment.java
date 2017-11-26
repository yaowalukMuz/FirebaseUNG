package androidthai.in.th.firebaseung.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidthai.in.th.firebaseung.MainActivity;
import androidthai.in.th.firebaseung.R;
import androidthai.in.th.firebaseung.utility.MyAlertDialog;

/**
 * Created by Yaowaluk on 25/11/2017.
 */

public class RegisterFragment extends Fragment {

    //    Explicit

    //valiable of Firebase Auth
    private String tag = "25NovV1";
    private String nameString, emailString, passwordString;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    //valiable of Firebase Database
    private FirebaseUser firebaseUser;
    private FirebaseDatabase databaseReference;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Set up Firebase
        firebaseAuth = FirebaseAuth.getInstance();

//        Create Toolbar
        createToolbar();

//        Create Menu Icon right
        setHasOptionsMenu(true);




    }//Main Method

    //    Method for action item Menu ( onSelect save Menue )
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemSave) {
            checkSpace();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkSpace() {
        Log.d(tag, "CheckSpace Work");
//        Initial View

        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText emailEditText = getView().findViewById(R.id.edtEmail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//        Get Value From EditText
        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();


//        Check Space --26/11/17 --start

        if (nameString.isEmpty()|| emailString.isEmpty()||passwordString.isEmpty()) {
//            Have space
            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
            myAlertDialog.myNormalDialog("Have Space", getString(R.string.sub_register));

        }else{
//            No space
            updateFirebase();

        }

    } // Check Space

    private void updateFirebase() {

//        Setup ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please waiting!!!!");
        progressDialog.show();



        //create authen in firebase by email and password --> .addOnCompleteListener ถ้าสร้างสำเร็จ/ไม่สำเร็จ ส่งค่ากลับมา
        firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //SaveNameDisplay To  Filebase
                            saveNameDisplayToFirebase();


                            // success
                            Toast.makeText(getActivity(),"Update Firebase Success",Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().popBackStack();

                        } else {
                            //Non Success--->fire base return result ::::task.getException().getMessage()
                            //format email missmat
                            //pass less then 6 digit

                            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                            myAlertDialog.myNormalDialog("Cannot Update Firebase",task.getException().getMessage());

                        }

                    }//--end--- method onComplete
                }); //---end ---OnCompleteListener


    } //updateFirebase

    private void saveNameDisplayToFirebase() {
//        Get UID of Firebase

      firebaseUser = firebaseAuth.getCurrentUser();

      showLog();

    }   //Save nameDisplay to Firebase

    private void showLog() {
        String tag = "26NovV1";

        Log.d(tag,"UID---->"+firebaseUser.getUid());
        Log.d(tag, "Email--->" + firebaseUser.getEmail());
    }

    //  Method Create menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void createToolbar() {

//        Config toolbar
        Toolbar toolbar = getView().findViewById(R.id.tbRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

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
