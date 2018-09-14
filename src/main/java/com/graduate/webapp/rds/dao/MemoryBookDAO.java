package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.MemoryBook;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface MemoryBookDAO {
public void insert(MemoryBook memoryBook);
public void insert(List<MemoryBook> memoryBookList);
public void update(MemoryBook memoryBook,MemoryBook oldMemory_book);
public void delete(MemoryBook memoryBook);
public MemoryBook get(MemoryBook memoryBook);
public List<MemoryBook> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}
