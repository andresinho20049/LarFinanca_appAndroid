package com.andre.larfinancas.fragments;

import android.animation.Animator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.andre.larfinancas.MainActivity;
import com.andre.larfinancas.NavigationHost;
import com.andre.larfinancas.R;
import com.andre.larfinancas.adapter.ExtratoRecyclerViewAdapter;
import com.andre.larfinancas.viewmodel.PagamentoViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ExtratoFragment extends Fragment {

    private PagamentoViewModel financeiroViewModel;
    private FloatingActionButton fab, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    private boolean isFABOpen = false;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tela, container, false);

        // Set up the toolbar
        ((MainActivity)getActivity()).setUpToolbar(view, R.id.product_grid, R.id.app_bar);

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final ExtratoRecyclerViewAdapter adapter = new ExtratoRecyclerViewAdapter(new ExtratoRecyclerViewAdapter.FinanceiroDiff());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        financeiroViewModel = new ViewModelProvider(this).get(PagamentoViewModel.class);
        financeiroViewModel.getAllPagamentos().observe(((MainActivity) getActivity()), pagamento -> {
            adapter.submitList(pagamento);
        });

        MaterialButton painelButton = view.findViewById(R.id.backdrop_painel);
        painelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new DashFragment(), false);
            }
        });

        fabLayout1 = (LinearLayout) view.findViewById(R.id.fabLayout1);
        fabLayout2 = (LinearLayout) view.findViewById(R.id.fabLayout2);
        fabLayout3 = (LinearLayout) view.findViewById(R.id.fabLayout3);
        fabBGLayout = view.findViewById(R.id.fabBGLayout);
        fab = view.findViewById(R.id.fab);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new DespesaFragment(), false);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new ReceitaFragment(), false);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                ((MainActivity)getActivity()).signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFABMenu(){
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-150);
        fabLayout2.animate().translationY(-300);
        fabLayout3.animate().translationY(-450);
    }

    private void closeFABMenu(){
        isFABOpen = false;
        fabBGLayout.setVisibility(View.GONE);
        fab.animate().rotation(0);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0);
        fabLayout3.animate().translationY(0);
        fabLayout3.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
                    fabLayout3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

}