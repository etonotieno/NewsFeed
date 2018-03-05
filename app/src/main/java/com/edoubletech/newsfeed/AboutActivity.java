/*
 * Copyright (C) 2018 Eton Otieno Oboch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.edoubletech.newsfeed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        
        TextView attributionTextView = findViewById(R.id.attribution_text_view);
        
        attributionTextView.setOnClickListener(v -> {
            Uri apiUri = Uri.parse("https://newsapi.org");
            startActivity(new Intent(Intent.ACTION_VIEW, apiUri));
            
        });
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
              
                Intent upIntent = new Intent(this, MainActivity.class);
                
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    
                    TaskStackBuilder.create(this)
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
                
        }
        return super.onOptionsItemSelected(item);
    }
}
