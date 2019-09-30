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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    private Button mButtonGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonGetData = findViewById(R.id.btnViewData);
        mButtonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NHLDataActivity.class);
                startActivity(intent);
            }
        });
    }
}
