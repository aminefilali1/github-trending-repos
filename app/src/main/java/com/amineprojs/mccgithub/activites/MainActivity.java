package com.amineprojs.mccgithub.activites;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.amineprojs.mccgithub.R;
import com.amineprojs.mccgithub.adapters.RepositoryAdapter;
import com.amineprojs.mccgithub.models.Repository;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewRepos;
    private List<Repository> repositoryList;

    ProgressDialog progressDialog;

    private static int PAGE_NUMBER = 1;
    private String endpoint = "https://api.github.com/search/repositories?q=created:%3E2017-12-16&sort=stars&order=desc&per_page=100&page=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRepos = findViewById(R.id.listViewRepos);
        repositoryList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        showProgressDialog();
        getTrendingRepos(PAGE_NUMBER);
    }

    private void getTrendingRepos(int pageNumber) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET, endpoint + pageNumber, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");
                            JSONObject item = null;
                            JSONObject owner = null;
                            Repository repository;
                            for(int i=0;i<=items.length()-1;i++) {
                                if (items.getJSONObject(i) != null) {
                                    item = items.getJSONObject(i);
                                    int repoId = item.getInt("id");
                                    String repoName = item.getString("name");
                                    String repoDescription = item.getString("description");
                                    int repoStars = item.getInt("stargazers_count");
                                    owner = item.getJSONObject("owner");
                                    String ownerName = owner.getString("login");
                                    String ownerPhoto = owner.getString("avatar_url");
                                    repository = new Repository(repoId, repoName, repoDescription, repoStars, ownerName, ownerPhoto);
                                    repositoryList.add(repository);
                                }
                            }
                            if (PAGE_NUMBER < 10) {
                                getTrendingRepos(PAGE_NUMBER);
                                PAGE_NUMBER++;
                            } else {
                                hideProgressDialog();
                                RepositoryAdapter repositoryAdapter = new RepositoryAdapter(MainActivity.this, repositoryList);
                                listViewRepos.setAdapter(repositoryAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq);
    }

    private void showProgressDialog() {
        progressDialog.setTitle(getString(R.string.fetching_repos));
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }
}
