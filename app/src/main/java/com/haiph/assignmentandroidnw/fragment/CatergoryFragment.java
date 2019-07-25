package com.haiph.assignmentandroidnw.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haiph.assignmentandroidnw.R;
import com.haiph.assignmentandroidnw.Retrofit;
import com.haiph.assignmentandroidnw.adapter.CategoryAdapter;
import com.haiph.assignmentandroidnw.model.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatergoryFragment extends Fragment {
    Context context;
    int currentPage, lastItemVisible, totalItemCount;
    private RecyclerView rcView;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        rcView = view.findViewById(R.id.rcCategory);

        rcView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ArrayList<Example> examplesList = new ArrayList<>();
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        categoryAdapter = new CategoryAdapter(examplesList, context, new CategoryAdapter.AdapterListener() {
            @Override
            public void itemOnclick(int position) {
                final Example example = examplesList.get(position);
                PostInCateFragment fragment = new PostInCateFragment();
                Bundle b = new Bundle();
                b.putString("id", example.getId().toString());
                b.putString("title", example.getTitle().getRendered());
                b.putString("sticky", example.getSticky().toString());
                b.putString("status", example.getStatus());
                b.putString("date", example.getDate());
                b.putString("date_gmt", example.getDateGmt());
                b.putString("featured_media", example.getFeaturedMedia().toString());
                b.putString("type", example.getType());
                b.putString("author", example.getAuthor().toString());
                b.putString("image",example.getContent().getRendered());

                b.putInt("POSITION", position);



                fragment.setArguments(b);
                fragmentTransaction.replace(R.id.frameLayout, fragment);

                fragmentTransaction.commit();

            }
        });

        rcView.setAdapter(categoryAdapter);
        rcView.setItemAnimator(new DefaultItemAnimator());

        getData(currentPage);

        final LinearLayoutManager linearLayout = (LinearLayoutManager) rcView.getLayoutManager();
        rcView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (linearLayout != null) {
                    lastItemVisible = linearLayout.findLastVisibleItemPosition();

                }
                totalItemCount = Integer.parseInt(String.valueOf(rcView.getAdapter().getItemCount()));
                if (!rcView.canScrollVertically(1) && newState == recyclerView.SCROLL_STATE_IDLE) {
                    currentPage++;
                    getData(currentPage);
                }

            }
        });

        return view;


    }

    public void getData(int currentPage) {

        Retrofit.getInstance().getExampleList("18", "10", String.valueOf(currentPage)).enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (response.code() == 200) {
                    Log.e("data", response.body() + "");
                    categoryAdapter.UpdateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.e("fail", t.getMessage());
            }
        });


    }
}
