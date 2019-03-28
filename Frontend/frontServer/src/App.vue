<template>
  <div id="app">  
    <FormTemplate/>
  </div>
</template>

<script>
import FormTemplate from './FormTemplate'
export default {
  name: 'App',
  components:{
    FormTemplate:FormTemplate
  }
}
</script>

<style>
#app {
  width: 300px;
  margin-left: 40%;
  margin-right: 30%;
  
  color: #2c3e50;
  margin-top: 60px;
}
</style>
