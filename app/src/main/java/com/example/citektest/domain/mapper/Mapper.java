package com.example.citektest.domain.mapper;

public interface Mapper<In, Out> {

    public Out map(In data);
}
