package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Memory_book;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface Memory_bookDAO {
public void insert(Memory_book memory_book);
public void insert(List<Memory_book> memory_bookList);
public void update(Memory_book memory_book,Memory_book oldMemory_book);
public void delete(Memory_book memory_book);
public Memory_book get(Memory_book memory_book);
public List<Memory_book> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}
