<template>
  <div class="tsf-nav-step">
    <ul class="gsi-step-indicator triangle ">
      <slot>
      </slot>
    </ul>
  </div>
</template>

<script>
  export default {}
</script>

<style>
  .gsi-step-indicator {
    width: 100%;
    padding: 0 1.2em;
    margin: 1em auto;
    background-color: #edeff0;
    border-radius: .25em
  }

  .gsi-step-indicator li {
    display: inline-block;
    float: left;
    margin: 1.2em 0;
    width: 49%;
    /*display: table-cell;
    width: 1%;
    float: none;*/
  }

  .gsi-step-indicator li .number {
    font-size: 2em;
    padding-top: 2px;
  }

  .gsi-step-indicator li .desc {
    padding-top: 14px;
    padding-left: 10px;
    vertical-align: super
  }

  .gsi-step-indicator li .desc label {
    font-size: 1.1em;
    font-weight: normal;
  }

  .gsi-step-indicator li .desc span {
    font-size: 14px;
    display: block
  }

  .gsi-step-indicator li > * {
    display: flex;
    font-size: 1.4rem;
    color: #2c3f4c
  }

  .gsi-step-indicator.triangle {
    background-color: transparent;
    padding: 0
  }

  .gsi-step-indicator.triangle li {
    position: relative;
    padding: 0;
    margin: 4px 4px 4px 0
  }

  .gsi-step-indicator.triangle li:last-of-type {
    margin-right: 0
  }

  .gsi-step-indicator.triangle li > * {
    position: relative;
    padding: 0 .8em .8em 2.5em;
    color: #2c3f4c;
    background-color: #edeff0;
    border-color: #edeff0
  }

  .gsi-step-indicator.triangle li.active > * {
    color: #fff;
    background-color: #3c8dbc;
    border-color: #3c8dbc
  }

  .gsi-step-indicator.triangle li:first-of-type > * {
    border-radius: .25em 0 0 .25em
  }

  .gsi-step-indicator.triangle li:last-of-type > * {
    padding-right: 1.6em;
    border-radius: 0 .25em .25em 0
  }

  .gsi-step-indicator.triangle li::after, .gsi-step-indicator.triangle li > *::after {
    content: '';
    position: absolute;
    top: 2px;
    left: 100%;
    height: 0;
    width: 0;
    border: 25px solid transparent;
    border-right-width: 0;
    border-left-width: 20px
  }

  .gsi-step-indicator.triangle li::after {
    z-index: 1;
    -webkit-transform: translateX(4px);
    -moz-transform: translateX(4px);
    -ms-transform: translateX(4px);
    -o-transform: translateX(4px);
    transform: translateX(4px);
    border-left-color: #fff;
    margin: 0
  }

  .gsi-step-indicator.triangle li > *::after {
    z-index: 2;
    border-left-color: inherit
  }

  .gsi-step-indicator.triangle .triangle li:last-of-type::after, .gsi-step-indicator.triangle li:last-of-type > *::after {
    display: none
  }

  .gsi-step-indicator.triangle.gsi-arrow-left li::after, .gsi-step-indicator.triangle.gsi-arrow-left li > *::after {
    content: '';
    position: absolute;
    top: 2px;
    left: -20px;
    height: 0;
    width: 0;
    border: 21px solid transparent;
    border-right-width: 20px;
    border-left-width: 0
  }

  .gsi-step-indicator.triangle.gsi-arrow-left li > *::after {
    z-index: 2;
    border-right-color: inherit
  }

  .gsi-step-indicator.triangle.gsi-arrow-left li > a {
    padding-left: 40px;
  }
</style>
