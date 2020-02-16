package com.demo.xebia.assignment.datasource.tools;

import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class ToStringConverterFactory extends Converter.Factory
{
  static final MediaType MEDIA_TYPE = MediaType.get("text/plain");

  @Override public @Nullable
  Converter<ResponseBody, String> responseBodyConverter(
      Type type, Annotation[] annotations, Retrofit retrofit)
  {
    if (String.class.equals(type)) {
      return ResponseBody::string;
    }
    return null;
  }

  @Override public @Nullable Converter<String, RequestBody> requestBodyConverter(Type type,
      Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit)
  {
    if (String.class.equals(type)) {
      return value -> RequestBody.create(MEDIA_TYPE, value);
    }
    return null;
  }
}