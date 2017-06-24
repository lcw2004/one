<template>
  <li class="dropdown messages-menu" :class="{ open: isOpen }">
    <a class="dropdown-toggle" data-toggle="dropdown" @click="toggole()">
      <i class="fa fa-envelope-o"></i>
      <span class="label label-success">4</span>
    </a>
    <ul class="dropdown-menu">
      <li class="header">You have 4 messages</li>
      <li>
        <!-- inner menu: contains the actual data -->
        <ul class="menu">
          <li><!-- start message -->
            <a href="#">
              <div class="pull-left">
                <img src="static/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">
              </div>
              <h4>
                Support Team
                <small><i class="fa fa-clock-o"></i> 5 mins</small>
              </h4>
              <p>Why not buy a new awesome theme?</p>
            </a>
          </li>
          <!-- end message -->
          <li>
            <a href="#">
              <div class="pull-left">
                <img src="static/adminlte/img/user3-128x128.jpg" class="img-circle" alt="User Image">
              </div>
              <h4>
                AdminLTE Design Team
                <small><i class="fa fa-clock-o"></i> 2 hours</small>
              </h4>
              <p>Why not buy a new awesome theme?</p>
            </a>
          </li>
          <li>
            <a href="#">
              <div class="pull-left">
                <img src="static/adminlte/img/user4-128x128.jpg" class="img-circle" alt="User Image">
              </div>
              <h4>
                Developers
                <small><i class="fa fa-clock-o"></i> Today</small>
              </h4>
              <p>Why not buy a new awesome theme?</p>
            </a>
          </li>
          <li>
            <a href="#">
              <div class="pull-left">
                <img src="static/adminlte/img/user3-128x128.jpg" class="img-circle" alt="User Image">
              </div>
              <h4>
                Sales Department
                <small><i class="fa fa-clock-o"></i> Yesterday</small>
              </h4>
              <p>Why not buy a new awesome theme?</p>
            </a>
          </li>
          <li>
            <a href="#">
              <div class="pull-left">
                <img src="static/adminlte/img/user4-128x128.jpg" class="img-circle" alt="User Image">
              </div>
              <h4>
                Reviewers
                <small><i class="fa fa-clock-o"></i> 2 days</small>
              </h4>
              <p>Why not buy a new awesome theme?</p>
            </a>
          </li>
        </ul>
      </li>
      <li class="footer"><a href="#">See All Messages</a></li>
    </ul>
  </li>
</template>

<script>
  export default{
    data: () => {
      return {
        isOpen: false
      }
    },
    methods: {
      toggole: function () {
        this.isOpen = !this.isOpen
      }
    }
  }
</script>
