package com.edoubletech.newsfeed;

import com.edoubletech.newsfeed.data.GuardianMapper;
import com.edoubletech.newsfeed.data.guardian.GuardianMain;
import com.edoubletech.newsfeed.data.guardian.GuardianResponse;
import com.edoubletech.newsfeed.data.guardian.GuardianResult;
import com.edoubletech.newsfeed.data.model.News;

import java.util.ArrayList;
import java.util.List;

public class Mapper implements GuardianMapper {

    @Override
    public List<News> mapGuardianToNews(GuardianMain main) {
        GuardianResponse response = main.getResponse();
        List<GuardianResult> results = response.getResults();
        List<News> articles = new ArrayList<>();
        for (GuardianResult result : results) {
            articles.add(new News(
                    result.getId(),
                    result.getFields().getThumbnail(), /* Thumbnail for the news */
                    result.getWebUrl(), /* Website url*/
                    result.getSectionName(), /* Section name*/
                    result.getWebTitle(), /* Web Title of Article*/
                    result.getFields().getTrailText(), /* Trail Text*/
                    result.getFields().getBodyText(), /* Description */
                    result.getWebPublicationDate())); /* Publication Date*/
        }
        return articles;
    }
}