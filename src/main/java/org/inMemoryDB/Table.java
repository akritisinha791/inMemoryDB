package org.inMemoryDB;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//for db curd operations
//T is a type of record that table stores
public class Table<T extends Record> {

    private final Map<Long, T> records = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final String tableName;


    //constructor to initialize table with a name
    public Table(String tableName) {
        this.tableName = tableName;
    }

    //Insert a record into the table
    public T insert(T record){
        Long id = idGenerator.getAndIncrement();
        record.setId(id);
        records.put(id, record);
        return record;
    }

    //find by id
    public Optional<T> findById(Long id){
        return Optional.ofNullable(records.get(id));
    }

    //retrieves all records
    public List<T> findAll(){
        return new ArrayList<>(records.values());
    }

    //find with condition
    public List<T> findWhere(Predicate<T> condition){
        return records.values().stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    //update a record
    public boolean update(Long id, T updatedRecord){
        if(records.containsKey(id)){
            updatedRecord.setId(id);
            records.put(id, updatedRecord);
            return true;
        }
        else return false;
    }

    //Delete a record by id
    public boolean deleteById(Long id){
        if(records.containsKey(id)){
            records.remove(id);
            return true;
        }
        return false;
    }

    //Delete with condition
    public int deleteWhere(Predicate<T> condition){
        List<Long> toDelete = records.entrySet().stream()
                .filter(element ->condition.test(element.getValue()))
                        .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        toDelete.forEach(records::remove);
        return toDelete.size();
    }

    //record count
    public int count(){
        return records.size();
    }

    //clear all records
    public void clear(){
        records.clear();
        idGenerator.set(1);
    }

    //table names
    public String getTableName(){
        return tableName;
    }


}
