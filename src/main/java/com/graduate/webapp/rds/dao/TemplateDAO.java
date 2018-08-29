package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Template;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface TemplateDAO {
public void insert(Template template);
public void insert(List<Template> templateList);
public void update(Template template,Template oldTemplate);
public void delete(Template template);
public Template get(Template template);
public List<Template> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}
