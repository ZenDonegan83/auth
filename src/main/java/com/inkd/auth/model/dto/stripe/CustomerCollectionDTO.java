package com.inkd.auth.model.dto.stripe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerCollectionDTO implements Serializable {

    @JsonProperty("object")
    private String object;

    @JsonProperty("data")
    private List<CustomerCollectionDataDTO> data;

    @JsonProperty("has_more")
    private String hasMore;

    @JsonProperty("url")
    private String url;

    @JsonProperty("request_params")
    private CustomerCollectionRQPDTO requestParams;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<CustomerCollectionDataDTO> getData() {
        return data;
    }

    public void setData(List<CustomerCollectionDataDTO> data) {
        this.data = data;
    }

    public String getHasMore() {
        return hasMore;
    }

    public void setHasMore(String hasMore) {
        this.hasMore = hasMore;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CustomerCollectionRQPDTO getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(CustomerCollectionRQPDTO requestParams) {
        this.requestParams = requestParams;
    }
}
