<% if (table.isPageList == 1) { %>
import @{table.classNameNoEO}List from '../../views/@{moduleName}/@{table.restPath}/@{table.classNameNoEO}List.vue'
<% } %>
<% if (table.isPageForm == 1) { %>
import @{table.classNameNoEO}View from '../../views/@{moduleName}/@{table.restPath}/@{table.classNameNoEO}View.vue'
import @{table.classNameNoEO}Edit from '../../views/@{moduleName}/@{table.restPath}/@{table.classNameNoEO}Edit.vue'
<% } %>

export default [
<% if (table.isPageList == 1) { %>
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/list', component: @{table.classNameNoEO}List},
<% } %>
<% if (table.isPageForm == 1) { %>
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/:id/edit', component: @{table.classNameNoEO}Edit},
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/:id/view', component: @{table.classNameNoEO}View},
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/add', component: @{table.classNameNoEO}Edit}
<% } %>
]
