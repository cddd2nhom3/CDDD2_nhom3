package com.example.admin.myapplication.Object;

public class Link {
    String  Link;

    public Link(String link) {
        super();
        Link = link;
    }
    //tao thuoc tinh get set
    @Override
    public String toString() {
        return   Link ;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public void add(Link link2) {
        // TODO Auto-generated method stub

    }

    public String size() {
        // TODO Auto-generated method stub
        return null;
    }
}
