<template>
  <div class="wrapper">
    <MainHead></MainHead>
    <MainSlidebar></MainSlidebar>
    <MainContent></MainContent>
    <MainFooter></MainFooter>
    <!--<ControlSidebar></ControlSidebar>-->
    <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
    <!--<div class="control-sidebar-bg"></div>-->
  </div>
</template>

<script>
import MainHead from './MainHead'
import MainSlidebar from './MainSlidebar'
import MainFooter from './MainFooter'
import MainContent from './MainContent'
import ControlSidebar from './ControlSidebar'

export default {
  components: {
    MainHead,
    MainSlidebar,
    MainFooter,
    MainContent,
    ControlSidebar
  }
}
</script>
