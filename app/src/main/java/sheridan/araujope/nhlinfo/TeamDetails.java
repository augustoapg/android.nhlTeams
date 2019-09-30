/**
 * Project: NHL Teams Information
 * Author: Augusto A P Goncalez
 * Date: Sept. 30, 2019
 *
 * Project Description:
 * This app gets information from an API to display a list of all the teams in the NHL.
 * The list contains basic information, but when clicking on a team, a new Activity page opens with
 * additional information.
 */

package sheridan.araujope.nhlinfo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import sheridan.araujope.nhlinfo.beans.Team;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class TeamDetails extends AppCompatActivity implements Serializable {

    private TextView mTeamName;
    private TextView mTeamLocation;
    private TextView mTeamVenue;
    private TextView mTeamFirstYear;
    private TextView mTeamConference;
    private TextView mTeamDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get team from intent
        Intent i = getIntent();
        Team team = (Team)i.getSerializableExtra("Team");

        // get elements
        mTeamName = findViewById(R.id.txtTeamTitle);
        mTeamLocation = findViewById(R.id.txtLocation);
        mTeamVenue = findViewById(R.id.txtVenue);
        mTeamFirstYear = findViewById(R.id.txtFirstYear);
        mTeamConference = findViewById(R.id.txtConference);
        mTeamDivision = findViewById(R.id.txtDivision);

        // set text
        mTeamName.setText(team.getName());
        mTeamLocation.setText(team.getLocation());
        mTeamVenue.setText(team.getVenue());
        mTeamFirstYear.setText(team.getFirstYear());
        mTeamConference.setText(team.getConference());
        mTeamDivision.setText(team.getDivision());
    }

}
