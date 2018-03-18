package com.lcw.one.codegen.entity;

import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;

@Entity
@Table(name = "code_gen_table")

public class CodeGenTableEO {

    
    @Id
    @Column(name = "table_id")
    private String tableId;

    
    @Basic
    @Column(name = "table_name")
    private String tableName;

    
    @Basic
    @Column(name = "parent_table")
    private String parentTable;

    
    @Basic
    @Column(name = "parent_table_fk")
    private String parentTableFk;

    
    @Basic
    @Column(name = "is_bean")
    private Integer isBean;

    
    @Basic
    @Column(name = "is_rest")
    private Integer isRest;

    
    @Basic
    @Column(name = "is_page_list")
    private Integer isPageList;

    
    @Basic
    @Column(name = "is_page_form")
    private Integer isPageForm;

    
    @Basic
    @Column(name = "is_page_modal")
    private Integer isPageModal;

    
    @Basic
    @Column(name = "is_dao")
    private Integer isDao;

    
    @Basic
    @Column(name = "is_service")
    private Integer isService;

    
    @Basic
    @Column(name = "rest_path")
    private String restPath;


    public String getTableId () {
        return this.tableId;
    }

    public void setTableId (String tableId) {
        this.tableId = tableId;
    }

    public String getTableName () {
        return this.tableName;
    }

    public void setTableName (String tableName) {
        this.tableName = tableName;
    }

    public String getParentTable () {
        return this.parentTable;
    }

    public void setParentTable (String parentTable) {
        this.parentTable = parentTable;
    }

    public String getParentTableFk () {
        return this.parentTableFk;
    }

    public void setParentTableFk (String parentTableFk) {
        this.parentTableFk = parentTableFk;
    }

    public Integer getIsBean () {
        return this.isBean;
    }

    public void setIsBean (Integer isBean) {
        this.isBean = isBean;
    }

    public Integer getIsRest () {
        return this.isRest;
    }

    public void setIsRest (Integer isRest) {
        this.isRest = isRest;
    }

    public Integer getIsPageList () {
        return this.isPageList;
    }

    public void setIsPageList (Integer isPageList) {
        this.isPageList = isPageList;
    }

    public Integer getIsPageForm () {
        return this.isPageForm;
    }

    public void setIsPageForm (Integer isPageForm) {
        this.isPageForm = isPageForm;
    }

    public Integer getIsPageModal () {
        return this.isPageModal;
    }

    public void setIsPageModal (Integer isPageModal) {
        this.isPageModal = isPageModal;
    }

    public Integer getIsDao () {
        return this.isDao;
    }

    public void setIsDao (Integer isDao) {
        this.isDao = isDao;
    }

    public Integer getIsService () {
        return this.isService;
    }

    public void setIsService (Integer isService) {
        this.isService = isService;
    }

    public String getRestPath () {
        return this.restPath;
    }

    public void setRestPath (String restPath) {
        this.restPath = restPath;
    }

}




