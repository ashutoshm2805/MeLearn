package me.lr;


import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "AddUserFragment";
    private PersonDatabase personDatabase;
    //FragmentAddUserBinding addPersonDataBinding;

    public AddUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        final EditText firstname, lastname, email;
        fab.setVisibility(View.GONE);
        View rootView = inflater.inflate(R.layout.fragment_add_user, container, false);
        //addPersonDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user, container, false);
        firstname = rootView.findViewById(R.id.firstname);
        lastname = rootView.findViewById(R.id.lastname);
        email = rootView.findViewById(R.id.email);
        FloatingActionButton adduser = rootView.findViewById(R.id.adduser);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                personDatabase = Room.databaseBuilder(getContext(), PersonDatabase.class, "Person")
                        .allowMainThreadQueries()
                        .build();
            }
        };
        new Thread(r).run();

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        personDatabase.personDao().insertAll(new
                                Person(firstname.getText().toString(), lastname.getText().toString(),email.getText().toString()));
                    }
                };
                Thread thread = new Thread(r);
                thread.run();
            }
        });
        return rootView;
    }

}
