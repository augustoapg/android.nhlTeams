package sheridan.araujope.nhlinfo;

import androidx.appcompat.app.AppCompatActivity;
import sheridan.araujope.nhlinfo.beans.Team;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NHLDataActivity extends AppCompatActivity {

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
                                String teamConference = teamJSON.getJSONObject("conference")
                                        .getString("name");

                                Team team = new Team(teamName, teamLocation, teamConference);
                                teamList.add(team);
                                Log.i("NHLData", team.toString());
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
        Log.i("NHLData", "Populating list view");
        // add list to listView
        ArrayAdapter<Team> arrayAdapter =
                new ArrayAdapter<Team>(this, R.layout.list_item, teamList);
        mTeamList.setAdapter(arrayAdapter);
    }
}
