package com.demo.xebia.assignment.datasource.response.news;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class OptionsDeserilizer implements JsonDeserializer<ArrayList<Result>> {

        @Override
        public ArrayList<Result> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            Type resultListType = new TypeToken<ArrayList<Result>>(){}.getType();
            ArrayList<Result> options = new Gson().fromJson(json, resultListType);

            for (int i = 0; i < json.getAsJsonArray().size(); i++)
            {
                JsonObject jsonObject = json.getAsJsonArray().get(i).getAsJsonObject();

                if (jsonObject.has("media"))
                {
                    Object elem = jsonObject.get("media");
                        if (elem instanceof JsonArray || elem instanceof JSONArray)
                        {
                            List<Medium> values = new Gson().fromJson(elem.toString(), new TypeToken<ArrayList<Medium>>() {}.getType());
                            options.get(i).setMedia(values);
                        }
                        else
                        {
                            options.get(i).setMedia(null);
                        }
                }

                if (jsonObject.has("des_facet"))
                {
                    Object elem = jsonObject.get("des_facet");
                    if (elem instanceof JsonArray || elem instanceof JSONArray)
                    {
                            String[] values = new Gson().fromJson(elem.toString(), (Type) String[].class);
                            options.get(i).setDesFacet( Arrays.asList( values ));
                    }
                    else
                    {
                        options.get(i).setDesFacet(null);
                    }
                }

                if (jsonObject.has("per_facet"))
                {
                    Object elem = jsonObject.get("per_facet");
                    if (elem instanceof JsonArray || elem instanceof JSONArray)
                    {
                        String[] values = new Gson().fromJson(elem.toString(), (Type) String[].class);
                        options.get(i).setPerFacet( Arrays.asList( values ));
                    }
                    else
                    {
                        options.get(i).setPerFacet(null);
                    }

                }

                if (jsonObject.has("org_facet"))
                {
                    Object elem = jsonObject.get("org_facet");
                    if (elem instanceof JsonArray || elem instanceof JSONArray)
                    {
                        String[] values = new Gson().fromJson(elem.toString(), (Type) String[].class);
                        options.get(i).setOrgFacet( Arrays.asList( values ));
                    }
                    else
                    {
                        options.get(i).setOrgFacet(null);
                    }
                }

                if (jsonObject.has("geo_facet"))
                {
                    Object elem = jsonObject.get("geo_facet");
                    if (elem instanceof JsonArray || elem instanceof JSONArray)
                    {
                        String[] values = new Gson().fromJson(elem.toString(), (Type) String[].class);
                        options.get(i).setGeoFacet( Arrays.asList( values ));
                    }
                    else
                    {
                        options.get(i).setGeoFacet(null);
                    }
                }
            }
            return options ;
        }
    }