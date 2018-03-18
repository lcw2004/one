<% if (table.isPageList == 1) { %>
import @{table.className}List from '../../views/@{moduleName}/@{table.restPath}/@{table.classNameNoEO}List.vue'
<% } %>
<% if (table.isPageForm == 1) { %>
import @{table.className}View from '../../views/@{moduleName}/@{table.restPath}/@{table.classNameNoEO}View.vue'
import @{table.className}Edit from '../../views/@{moduleName}/@{table.restPath}/@{table.classNameNoEO}Edit.vue'
<% } %>

export default [
<% if (table.isPageList == 1) { %>
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/list', component: @{table.className}List},
<% } %>
<% if (table.isPageForm == 1) { %>
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/:id/edit', component: @{table.className}Edit},
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/:id/view', component: @{table.className}View},
  {path: '/@{strategy.moduleSimpleName}/@{table.restPath}/add', component: @{table.className}Edit}
<% } %>
]
