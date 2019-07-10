package com.chengshi.train.util.page;

import com.chengshi.train.util.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 10:47
 */
public class Page<T> extends Pagination {
    Logger logger=LoggerFactory.getLogger(Pagination.class);
    private JdbcTemplate jdbcTemplate;
    private String sql;
    private List records;

    public Page( int currentPage,int numPerPage){
        super(numPerPage,currentPage);
    }


    /**
     * 每页显示10条记录的构造函数,使用该函数必须先给Pagination设置currentPage，jTemplate初值
     *
     * @param sql Oracle语句
     */
    public Page(String sql) {
        if (jdbcTemplate == null) {
            throw new IllegalArgumentException("com.chengshi.train.util.Pagination.jTemplate is null,please initial it first. ");
        } else if (sql.equals("")) {
            throw new IllegalArgumentException("com.chengshi.train.util.Pagination.sql is empty,please initial it first. ");
        }
        int currentPage=getCurrentPage();
        int numPerPage=getNumPerPage();
        new Page(sql, currentPage, numPerPage,jdbcTemplate);
    }

    /**
     * 分页构造函数
     *
     * @param sql         根据传入的sql语句得到一些基本分页信息
     * @param currentPage 当前页
     * @param numPerPage  每页记录数
     */
    public Page(String sql, int currentPage, int numPerPage,JdbcTemplate jdbcTemplate) {
        super(numPerPage,currentPage);
        //计算总记录数
        StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
        totalSQL.append(sql);
        totalSQL.append(" ) totalTable ");
        //总记录数
        setTotalRows(jdbcTemplate.queryForObject(totalSQL.toString(), Integer.class));
        //计算总页数
        setTotalPages();
        //计算起始行数
        setStartIndex();
        //计算结束行数
        setLastIndex();
        int startIndex=getStartIndex();
        //装入结果集
        setSql(sql,startIndex, numPerPage);
        jdbcTemplate.queryForList(getSql());
        setRecords(jdbcTemplate.queryForList(getSql()));
    }

    /**
     * 构造MySQL数据分页SQL
     *
     * @param queryString
     * @param startIndex
     * @param pageSize
     * @return
     */
    public void setSql(String queryString, Integer startIndex, Integer pageSize) {
        String result = "";
        if (null != startIndex && null != pageSize) {
            result = queryString + " limit " + startIndex + "," + pageSize;
        } else if (null != startIndex && null == pageSize) {
            result = queryString + " limit " + startIndex;
        } else {
            result = queryString;
        }
        this.sql= result;
    }

    public String getSql() {
        return sql;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }
}
