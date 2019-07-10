package com.chengshi.train.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 9:45
 */
@Data
public class Pagination  implements Serializable {

    //一页显示的记录数
    private int numPerPage;
    //记录总数
    private int totalRows;
    //总页数
    private int totalPages;
    //当前页码
    private int currentPage;
    //起始行数
    private int startIndex;
    //结束行数
    private int lastIndex;

    public Pagination(){
        this.numPerPage=10;
        this.currentPage=1;
        this.startIndex=(currentPage - 1) * numPerPage;
        this.lastIndex=10;
    }

    public Pagination( int currentPage,int numPerPage) {
        this.numPerPage = numPerPage;
        this.currentPage = currentPage;
    }

    //计算开始时候的索引
    public void setStartIndex() {
        this.startIndex = (currentPage - 1) * numPerPage;
    }

    //计算结束时候的索引
    public void setLastIndex() {
        if (totalRows < numPerPage) {
            this.lastIndex = totalRows;
        } else if ((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)) {
            this.lastIndex = currentPage * numPerPage;
        } else if (totalRows % numPerPage != 0 && currentPage == totalPages) {//最后一页
            this.lastIndex = totalRows;
        }
    }

    //计算总页数
    public void setTotalPages() {
        if (totalRows % numPerPage == 0) {
            this.totalPages = totalRows / numPerPage;
        } else {
            this.totalPages = (totalRows / numPerPage) + 1;
        }
    }

}
