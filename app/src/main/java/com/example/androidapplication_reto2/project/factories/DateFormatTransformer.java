package com.example.androidapplication_reto2.project.factories;

import org.simpleframework.xml.transform.Transform;

import java.util.Date;
import java.text.DateFormat;

public class DateFormatTransformer implements Transform<Date>
{
    private DateFormat dateFormat;


    public DateFormatTransformer(DateFormat dateFormat)
    {
        this.dateFormat = dateFormat;
    }



    @Override
    public Date read(String value) throws Exception
    {
        return dateFormat.parse(value);
    }


    @Override
    public String write(Date value) throws Exception
    {
        return dateFormat.format(value);
    }

}
