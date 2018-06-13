/*
 *  Copyright (C) 2018 Eton Otieno Oboch
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.edoubletech.newsfeed.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.edoubletech.newsfeed.data.Repository;
import com.edoubletech.newsfeed.data.model.News;

import java.util.List;

public class MainViewModel extends ViewModel {
    
    private MutableLiveData<String> categoryName;
    
    public MainViewModel() {
        categoryName = new MutableLiveData<>();
    }
    
    public void search(String categoryName) {
        if (categoryName != null)
            this.categoryName.setValue(categoryName);
    }
    
    public LiveData<List<News>> getNewsList() {
        return Transformations.switchMap(this.categoryName, input -> {
            Repository repository = new Repository();
            return repository.getNewsList(input);
        });
    }
}