package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Photo;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface PhotoDAO {
public void insert(Photo photo);
public void insert(List<Photo> photoList);
public void update(Photo photo,Photo oldPhoto);
public void delete(Photo photo);
public Photo get(Photo photo);
public List<Photo> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}
