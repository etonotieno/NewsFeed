package com.edoubletech.newsfeed.data;

import com.edoubletech.newsfeed.data.guardian.GuardianMain;
import com.edoubletech.newsfeed.data.model.News;

import java.util.List;

public interface GuardianMapper {
    List<News> mapGuardianToNews(GuardianMain main);
}
