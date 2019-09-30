package sheridan.araujope.nhlinfo;

import androidx.appcompat.app.AppCompatActivity;
import sheridan.araujope.nhlinfo.beans.Team;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NHLDataActivity extends AppCompatActivity implements Serializable {

    private static final int GET_MESSAGE = 1;
    protected List<Team> teamList = new ArrayList<Team>();
    private ListView mTeamList;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhldata);

        mTeamList = findViewById(R.id.teamList);
        mQueue = Volley.newRequestQueue(this);
        jsonParse();
    }

    private void jsonParse() {
        String url = getString(R.string.httphost);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("teams");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject teamJSON = jsonArray.getJSONObject(i);
                                String teamName = teamJSON.getString("teamName");
                                String teamLocation = teamJSON.getString("locationName");
                                String teamVenue = teamJSON.getJSONObject("venue")
                                        .getString("name");
                                String teamFirstYear = teamJSON.getString("firstYearOfPlay");
                                String teamDivision = teamJSON.getJSONObject("division")
                                        .getString("name");
                                String teamConference = teamJSON.getJSONObject("conference")
                                        .getString("name");

                                Team team = new Team(teamName, teamLocation, teamVenue,
                                        teamFirstYear, teamConference, teamDivision);
                                teamList.add(team);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        populateListView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    private void populateListView() {
        final Context ctx = this;
        Log.i("NHLData", "Populating list view");
        // add list to listView
        ArrayAdapter<Team> arrayAdapter =
                new ArrayAdapter<Team>(this, R.layout.list_item, teamList);
        mTeamList.setAdapter(arrayAdapter);
        mTeamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openTeamDetails(teamList.get(position));
            }
        });
    }

    private void openTeamDetails(Team team) {
        Intent myIntent = new Intent(this, TeamDetails.class);
        Log.i("NHLData", team.toString());
        myIntent.putExtra("Team", team);
        startActivity(myIntent);
    }
}
