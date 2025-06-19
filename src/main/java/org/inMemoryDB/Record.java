package org.inMemoryDB;
//all db entries must have an id as the primary key
public interface Record {

    Long getId();
    void setId(Long id);

}
