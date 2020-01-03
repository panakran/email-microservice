package com.pkran.mailservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "EmailDTO")
public class EmailDTO {

    @ApiModelProperty(value = "Email title", required = true)
    private String title;
    @ApiModelProperty(value = "Email body", required = true)
    private String body;
    @ApiModelProperty(value = "Email sender", required = true)
    private String sender;
    @ApiModelProperty(value = "Email recipients", required = true)
    private List<String> recipients;

    public EmailDTO() {
    }

    public EmailDTO(String title, String body, String sender, List<String> recipients) {
        this.title = title;
        this.body = body;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", sender='" + sender + '\'' +
                ", recipients=" + recipients +
                '}';
    }
}
