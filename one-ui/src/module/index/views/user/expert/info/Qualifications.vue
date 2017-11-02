<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <tbody>
          <tr>
            <th style="width: 10px">#</th>
            <th>资质类型</th>
            <th>证书编号</th>
            <th>颁发机构</th>
            <th>颁发日期</th>
            <th>有效日期</th>
            <th>影印件</th>
            <th>备注</th>
          </tr>
          <tr>
            <td>1</td>
            <td>营业执照</td>
            <td>410728000048416（2-8）</td>
            <td>长垣县工商行政管理局</td>
            <td>2017-01-01</td>
            <td>2027-01-01</td>
            <td>
              <a>查看</a>
            </td>
            <td></td>
          </tr>
          <tr>
            <td>2</td>
            <td>税务登记证（国税）</td>
            <td>410728317412046</td>
            <td>长垣县工商行政管理局</td>
            <td>2017-01-01</td>
            <td>2027-01-01</td>
            <td>
              <a>查看</a>
            </td>
            <td></td>
          </tr>
          <tr>
            <td>3</td>
            <td>组织机构代码证</td>
            <td>31741204-6</td>
            <td>长垣县工商行政管理局</td>
            <td>2017-01-01</td>
            <td>2027-01-01</td>
            <td>
              <a>查看</a>
            </td>
            <td></td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</template>

<script>
  export default {
    components: {},
    props: {
      obj: {
        type: Object,
        required: true
      }
    },
    data: () => {
      return {}
    }
  }
</script>
