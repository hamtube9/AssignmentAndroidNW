package com.haiph.assignmentandroidnw.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.haiph.assignmentandroidnw.R;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostInCateFragment extends Fragment {
    private int position;
    private TextView tvstt, tvTitle, tvdate, tvdategmt, tvAuthor, tvmedia, tvsticky, tvType;
    private ImageView imgPostInCase;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_in_category, container, false);

        imgPostInCase = view.findViewById(R.id.imgPostInCategory);


        tvAuthor = view.findViewById(R.id.tvAuthor);
        tvdate = view.findViewById(R.id.tvdate);
        tvdategmt = view.findViewById(R.id.tvdategmt);
        tvsticky = view.findViewById(R.id.tvsticky);
        tvmedia = view.findViewById(R.id.tvmedia);
        tvstt = view.findViewById(R.id.tvstt);
        tvType = view.findViewById(R.id.tvType);

        tvTitle = view.findViewById(R.id.tvTitle);


        Bundle bundle = getArguments();

        if (bundle != null) {

            String url;


            url = bundle.getString("image");

            Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]");
            Matcher m = p.matcher(url);

            List<String> urls = new ArrayList<>();
            while (m.find()) {
                String post_url = m.group();
                urls.add(post_url);
            }

            Glide
                    .with(getActivity())
                    .load(url)
                    .centerCrop()
                    .override(150, 200)
                    .into(imgPostInCase);


            position = bundle.getInt("POSITION");
            tvAuthor.setText(bundle.getString("author"));
            tvdate.setText(bundle.getString("date"));
            tvdategmt.setText(bundle.getString("date_gmt"));
            tvmedia.setText(bundle.getString("featured_media"));
            tvsticky.setText(bundle.getString("sticky"));
            tvstt.setText(bundle.getString("status"));
            tvType.setText(bundle.getString("type"));
            tvTitle.setText(bundle.getString("title"));
        }


        return view;
    }

}
