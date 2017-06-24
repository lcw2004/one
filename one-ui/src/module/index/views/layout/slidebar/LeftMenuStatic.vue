<template>
  <ul class="sidebar-menu">
    <li class="header">招标负责人</li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>待办事项</span>
        <span class="pull-right-container">
          <span class="label label-primary pull-right">4</span>
        </span>
      </a>
    </li>
    <li class="active treeview">
      <a href="#">
        <i class="fa fa-dashboard"></i>
        <span>招标项目管理</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li class="active"><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/of-manager'>我的招标项目</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/create'>招标项目发布</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/manage'>招标项目管理</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/fee-manage'>费金管理</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/floor-price'>设置拦标价</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/audit'>招标项目审核</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/expert-apply'>申请专家抽取</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/expert-extract'>专家抽取</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/time-manage'>开标时间调整</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/abolish'>招标项目流标</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/calendar'>项目日历视图</router-link>
        </a></li>
      </ul>
    </li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>供应商管理</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/user/supplier/manage'>供应商管理</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/user/supplier/level'>供应商等级管理</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/user/supplier/questionnaire'>供应商调查</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/user/supplier/audit'>供应商审核</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/user/supplier/purchase-audit'>供应商投标类别审核</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/user/supplier/blacklist'>供应商黑名单</router-link>
        </a></li>
      </ul>
    </li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>专家管理</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/user/expert/manage'>评标专家管理</router-link>
        </a></li>
      </ul>
    </li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>系统管理</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/system/purchase-type'>采购类别管理</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/system/profession-type'>专业类别管理</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/system/notice'>须知文件管理（投标、注册）</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/system/room'>评标室管理</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/system/setting'>银行账户配置</router-link>
        </a></li>
      </ul>
    </li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>门户管理</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>略</a></li>
      </ul>
    </li>

    <li class="header">专家</li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>招标项目评标</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/bidding-of-expert'>招投标项目评审</router-link>
        </a></li>
      </ul>
    </li>

    <li class="header">供应商</li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>个人中心</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/personal/user-info'>个人信息</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/personal/password'>修改密码</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/personal/supplier/office-info'>机构信息</router-link>
        </a></li>
      </ul>
    </li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>我的项目</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/bidding-of-supplier'>我的投标项目</router-link>
        </a></li>
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/bidding-can-join'>可参与项目</router-link>
        </a></li>
      </ul>
    </li>


    <li class="header">专家</li>
    <li class="treeview">
      <a href="#">
        <i class="fa fa-files-o"></i>
        <span>招标项目</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a><i class="fa fa-circle-o"></i>
          <router-link to='/bidding/bidding-of-supervisor'>招投标项目监督</router-link>
        </a></li>
      </ul>
    </li>
  </ul>
</template>

<script>
  export default {
    data: () => {
      return {
        topMenu: {}
      }
    },
    mounted () {
    },
    computed: {}
  }
</script>
