package edu.elte.spring.loris.model.test;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class SendEmailEvent {
  private String emailAddress;
  private String subject;
  private String content;
}
