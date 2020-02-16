package com.demo.xebia.assignment.datasource.response.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result
{
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("adx_keywords")
    @Expose
    private String adxKeywords;
    @SerializedName("column")
    @Expose
    private Object column;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String abstract_data;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("asset_id")
    @Expose
    private Long assetId;
    @SerializedName("views")
    @Expose
    private Integer views;
    private List<String> desFacet = null;
    private List<String> orgFacet;
    private List<String> perFacet;
    private List<String> geoFacet;
    private List<Medium> media = null;



    @SerializedName("uri")
    @Expose
    private String uri;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getAdxKeywords()
    {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords)
    {
        this.adxKeywords = adxKeywords;
    }

    public Object getColumn()
    {
        return column;
    }

    public void setColumn(Object column)
    {
        this.column = column;
    }

    public String getSection()
    {
        return section;
    }

    public void setSection(String section)
    {
        this.section = section;
    }

    public String getByline()
    {
        return byline;
    }

    public void setByline(String byline)
    {
        this.byline = byline;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAbstract()
    {
        return abstract_data;
    }

    public void setAbstract(String abstract_data)
    {
        this.abstract_data = abstract_data;
    }

    public String getPublishedDate()
    {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate)
    {
        this.publishedDate = publishedDate;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getAssetId()
    {
        return assetId;
    }

    public void setAssetId(Long assetId)
    {
        this.assetId = assetId;
    }

    public Integer getViews()
    {
        return views;
    }

    public void setViews(Integer views)
    {
        this.views = views;
    }

    public List<String> getDesFacet()
    {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet)
    {
        this.desFacet = desFacet;
    }

    public List<String> getOrgFacet()
    {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet)
    {
        this.orgFacet = orgFacet;
    }

    public List<String> getPerFacet()
    {
        return perFacet;
    }

    public void setPerFacet(List<String> perFacet)
    {
        this.perFacet = perFacet;
    }

    public List<String> getGeoFacet()
    {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet)
    {
        this.geoFacet = geoFacet;
    }

    public List<Medium> getMedia()
    {
        return media;
    }

    public void setMedia(List<Medium> media)
    {
        this.media = media;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }
}