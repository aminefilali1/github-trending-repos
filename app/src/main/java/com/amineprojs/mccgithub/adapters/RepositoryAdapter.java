package com.amineprojs.mccgithub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amineprojs.mccgithub.R;
import com.amineprojs.mccgithub.models.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepositoryAdapter extends BaseAdapter {

    private List<Repository> repositories;
    private LayoutInflater lf;
    private Context context;

    public RepositoryAdapter(Context context, List<Repository> albums) {
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.repositories = albums;
        this.context = context;
    }

    @Override
    public int getCount() {
        return repositories.size();
    }

    @Override
    public Object getItem(int position) {
        return repositories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = lf.inflate(R.layout.repo_item, null);
        }
        TextView textViewRepoName = (TextView) convertView.findViewById(R.id.repoName);
        TextView textViewRepoDescr = (TextView) convertView.findViewById(R.id.repoDescr);
        TextView textViewRepoStars = (TextView) convertView.findViewById(R.id.repoStars);
        TextView textViewOwnerName = (TextView) convertView.findViewById(R.id.ownerName);
        ImageView imageViewOwnerPhoto = (ImageView) convertView.findViewById(R.id.ownerPhoto);

        textViewRepoName.setText(repositories.get(position).getName());
        textViewRepoDescr.setText(repositories.get(position).getDescription());
        String formattedStars = formatStarsNumber(repositories.get(position).getStars());
        textViewRepoStars.setText(formattedStars);
        textViewOwnerName.setText(repositories.get(position).getOwnerName());

        Picasso.with(context).load(repositories.get(position).getOwnerPhoto()).into(imageViewOwnerPhoto);

        return convertView;
    }

    private String formatStarsNumber(long count) {
        if (count < 1000) return String.valueOf(count);
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c", count / Math.pow(1000, exp), "kMGTPE".charAt(exp-1));
    }
}