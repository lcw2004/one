import ArticleForm from '../../views/cms/article/ArticleForm.vue'
import ArticleList from '../../views/cms/article/ArticleList.vue'
import GroupForm from '../../views/cms/group/GroupForm.vue'
import GroupList from '../../views/cms/group/GroupList.vue'

export default [
  {path: '/cms/article', component: ArticleList},
  {path: '/cms/article/:id/form', component: ArticleForm},
  {path: '/cms/article/add', component: ArticleForm},
  {path: '/cms/group', component: GroupList},
  {path: '/cms/group/:id/form', component: GroupForm},
  {path: '/cms/group/add', component: GroupForm}
]
