package com.lcw.one.util.http;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页类
 *
 * @param <T>
 * @author ThinkGem
 * @version 2013-7-2
 */
public class PageInfo<T> {

    // 当前页码
    private Integer pageNo = 1;

    // 每页显示数据条数
    private Integer pageSize = 10;

    // 总记录数
    private Long count;

    // 总页数
    private Long pageCount;

    // 数据
    private List<T> list = new ArrayList<T>();

    // 预留，用于传输分页时候需要传输的数据
    private Map<String, Object> ext = new HashMap<>();

    // 排序
    private String orderBy = "";

    public PageInfo() {
        this.pageSize = -1;
    }

    /**
     * 构造方法
     *
     * @param request 传递 repage 参数，来记住页码
     */
    public PageInfo(HttpServletRequest request) {
        // 设置页码参数（传递repage参数，来记住页码）
        String no = request.getParameter("pageNo");
        if (StringUtils.isEmpty(no)) {
            this.setPageNo(1);
        } else if (StringUtils.isNumeric(no)) {
            this.setPageNo(Integer.parseInt(no));
        }

        // 设置页面大小参数（传递repage参数，来记住页码大小）
        String size = request.getParameter("pageSize");
        if (StringUtils.isEmpty(size)) {
            this.setPageSize(10);
        }
        if (StringUtils.isNumeric(size)) {
            this.setPageSize(Integer.parseInt(size));
        }

        // 设置排序参数
        String orderBy = request.getParameter("orderBy");
        if (StringUtils.isNotBlank(orderBy)) {
            this.setOrderBy(orderBy);
        }
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     */
    public PageInfo(Integer pageNo, Integer pageSize) {
        this(pageNo, pageSize, 0L);
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     */
    public PageInfo(Integer pageNo, Integer pageSize, Long count) {
        this(pageNo, pageSize, count, new ArrayList<T>());
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     * @param list     本页数据对象列表
     */
    public PageInfo(Integer pageNo, Integer pageSize, Long count, List<T> list) {
        if (pageNo == null) {
            pageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        this.setCount(count);
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.setList(list);
    }

    /**
     * 获取设置总数
     *
     * @return
     */
    public Long getCount() {
        return count;
    }

    /**
     * 设置数据总数
     *
     * @param count
     */
    public void setCount(Long count) {
        this.count = count;
        if (pageSize >= count) {
            pageNo = 1;
        }
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码
     *
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取页面大小
     *
     * @return
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面大小（最大500）
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;// > 500 ? 500 : pageSize;
    }

    /**
     * 获取本页数据对象列表
     *
     * @return List<T>
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置本页数据对象列表
     *
     * @param list
     */
    public PageInfo<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    /**
     * 获取查询排序字符串
     *
     * @return
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置查询排序，标准查询有效， 实例： updatedate desc, name asc
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public Long getPageCount() {
        if (count % pageSize != 0) {
            pageCount = count / pageSize + 1;
        } else {
            pageCount = count / pageSize;
        }
        if (pageCount < 1) {
            pageCount = 1L;
        }
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }
}
