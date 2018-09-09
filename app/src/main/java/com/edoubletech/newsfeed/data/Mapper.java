package com.edoubletech.newsfeed.data;

public interface Mapper<Input, Output>{

    Output mapToModel(Input input);
}
