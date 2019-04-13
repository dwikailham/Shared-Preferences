package com.dwikailham.notesapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dwikailham.notesapp.R;
import com.dwikailham.notesapp.adapters.NoteAdapter;
import com.dwikailham.notesapp.models.Constant;
import com.dwikailham.notesapp.models.Data;
import com.dwikailham.notesapp.models.Session;
import com.dwikailham.notesapp.models.Settings;


public class NoteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    public Session session;
    public Settings settings;

    private OnNoteFragmentListener listener;

    public NoteFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public interface OnNoteFragmentListener {
        void onLogoutClick();
    }

    public void setListener(OnNoteFragmentListener listener) {
        this.listener = listener;
    }

    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_note, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settings = new Settings(getContext());
        session = new Session(settings);
//        Toast.makeText(getActivity(), Integer.toString(session.getLayout()),
//                Toast.LENGTH_LONG).show();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = view.findViewById(R.id.rv_notes);

        adapter = new NoteAdapter(getContext(), Data.getNotes());
        recyclerView.setAdapter(adapter);
        if(session.getLayout() == Constant.LAYOUT_MODE_LIST)
            displayAsList();
        else
            displayAsGrid();

        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    private void displayAsList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.setLayout(Constant.LAYOUT_MODE_LIST);
    }

    private void displayAsGrid() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setLayout(Constant.LAYOUT_MODE_GRID);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_list:
                displayAsList();
                session.setLayout(Constant.LAYOUT_MODE_LIST);
                Toast.makeText(getActivity(), "Layout session Set",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_show_grid:
                displayAsGrid();
                session.setLayout(Constant.LAYOUT_MODE_GRID);
                Toast.makeText(getActivity(), "Layout session Set",
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_logout:
                listener.onLogoutClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
